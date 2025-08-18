package com.tss.controller;

import com.tss.dao.LeaveRequestDao;
import com.tss.model.Employee;
import com.tss.model.LeaveRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee/dashboard")
public class EmployeeDashboardServlet extends HttpServlet {
    private LeaveRequestDao lrDao = new LeaveRequestDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Employee user = (Employee) req.getSession(false).getAttribute("user");

        String month = req.getParameter("month");   // Example: "01" for January
        String status = req.getParameter("status"); // Example: "Approved"

        List<LeaveRequest> requests = lrDao.findByEmpIdWithFilters(user.getEmpId(), month, status);

        req.setAttribute("selectedMonth", month);
        req.setAttribute("selectedStatus", status);
        req.setAttribute("requests", requests);
        req.getRequestDispatcher("/employee_dashboard.jsp").forward(req, resp);
    }
}
