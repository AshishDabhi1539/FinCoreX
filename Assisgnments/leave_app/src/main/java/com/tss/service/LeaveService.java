package com.tss.service;

import com.tss.dao.EmployeeDao;
import com.tss.dao.LeaveRequestDao;
import com.tss.dao.LeaveBalanceDao;
import com.tss.model.Employee;
import com.tss.model.LeaveRequest;

import java.sql.Date;
import java.time.temporal.ChronoUnit;

public class LeaveService {
    private LeaveRequestDao lrDao = new LeaveRequestDao();
    private EmployeeDao empDao = new EmployeeDao();
    private LeaveBalanceDao lbDao = new LeaveBalanceDao();

    /**
     * Apply leave: validate balance and create request (status = PENDING).
     */
    public String applyLeave(int empId, Date start, Date end, String reason) {
        Employee emp = empDao.findById(empId);
        if (emp == null) return "Employee not found";

        long days = ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate()) + 1;
        if (days <= 0) return "Invalid date range";

        int available = emp.getTotalLeaves() - emp.getLeavesTaken();
        if (days > available) {
            return "Insufficient leave balance. Available: " + available;
        }

        LeaveRequest lr = new LeaveRequest();
        lr.setEmpId(empId);
        lr.setStartDate(start);
        lr.setEndDate(end);
        lr.setReason(reason);
        lr.setStatus("PENDING");

        boolean ok = lrDao.create(lr);
        return ok ? "SUCCESS" : "Error creating leave request";
    }

    /**
     * Approve request: mark request APPROVED, update employees.leaves_taken and update leave_balance snapshot.
     */
    public boolean approveLeave(int leaveId) {
        LeaveRequest lr = lrDao.findById(leaveId);
        if (lr == null) return false;

        // compute days
        long days = ChronoUnit.DAYS.between(lr.getStartDate().toLocalDate(), lr.getEndDate().toLocalDate()) + 1;
        if (days <= 0) return false;

        Employee emp = empDao.findById(lr.getEmpId());
        if (emp == null) return false;

        int newLeavesTaken = emp.getLeavesTaken() + (int) days;
        boolean updatedEmp = empDao.updateLeavesTaken(emp.getEmpId(), newLeavesTaken);
        boolean updatedReq = lrDao.updateStatus(leaveId, "APPROVED");

        if (updatedEmp && updatedReq) {
            int remaining = emp.getTotalLeaves() - newLeavesTaken;
            lbDao.updatebalance(emp.getEmpId(), emp.getTotalLeaves(), newLeavesTaken, remaining);
            return true;
        } else {
            // If partially updated, you may want to roll back in production. Here we return false.
            return false;
        }
    }

    /**
     * Reject request: mark request REJECTED and update balance snapshot (no leaves changed)
     */
    public boolean rejectLeave(int leaveId) {
        LeaveRequest lr = lrDao.findById(leaveId);
        if (lr == null) return false;
        Employee emp = empDao.findById(lr.getEmpId());
        if (emp == null) return false;

        boolean updatedReq = lrDao.updateStatus(leaveId, "REJECTED");
        if (updatedReq) {
            // snapshot (no changes in leaves_taken)
            int remaining = emp.getTotalLeaves() - emp.getLeavesTaken();
            lbDao.updatebalance(emp.getEmpId(), emp.getTotalLeaves(), emp.getLeavesTaken(), remaining);
            return true;
        }
        return false;
    }
}