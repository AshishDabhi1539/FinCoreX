package com.tss.service;

import java.time.LocalDate;
import java.util.List;

import com.tss.dao.LeaveDAO;
import com.tss.model.LeaveRequest;

public class LeaveService {
    private final LeaveDAO leaveDAO = new LeaveDAO();
    private static final int MAX_LEAVES_PER_MONTH = 3;

    public String applyLeave(int userId, LocalDate date, String reason) throws Exception {
        if (date == null || reason == null || reason.isBlank())
            return "Please provide a valid date and reason.";

        if (date.isBefore(LocalDate.now()))
            return "Past dates are not allowed.";

        if (leaveDAO.existsPendingOrApprovedOnDate(userId, date))
            return "You already have a leave for this date.";

        int count = leaveDAO.countMonthLeavesPendingOrApproved(userId, date.getYear(), date.getMonthValue());
        if (count >= MAX_LEAVES_PER_MONTH)
            return "Monthly limit reached (max 3).";

        LeaveRequest lr = new LeaveRequest();
        lr.setUserId(userId);
        lr.setLeaveDate(date);
        lr.setReason(reason);
        leaveDAO.insert(lr);
        return "OK";
    }

    public String updatePendingLeave(int leaveId, int userId, LocalDate newDate, String newReason) throws Exception {
        if (newDate.isBefore(LocalDate.now()))
            return "Past dates are not allowed.";

        if (leaveDAO.existsPendingOrApprovedOnDate(userId, newDate))
            return "You already have a leave for this date.";

        int count = leaveDAO.countMonthLeavesPendingOrApproved(userId, newDate.getYear(), newDate.getMonthValue());
        if (count >= MAX_LEAVES_PER_MONTH)
            return "Monthly limit reached (max 3).";

        leaveDAO.updatePendingLeave(leaveId, newDate, newReason);
        return "OK";
    }
    
    public List<LeaveRequest> getLeavesByDateRange(String from, String to, String status) {
        return leaveDAO.findByDateRange(from, to, status);
    }

    public List<LeaveRequest> getLeavesByStatus(String status) {
        return leaveDAO.findByStatus(status);
    }

    public List<LeaveRequest> getAllLeaves() {
        return leaveDAO.findAll();
    }

    


}
