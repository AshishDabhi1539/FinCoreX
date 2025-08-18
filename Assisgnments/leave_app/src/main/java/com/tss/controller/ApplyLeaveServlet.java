package com.tss.controller;

import com.tss.model.Employee;
import com.tss.service.LeaveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/employee/apply")
public class ApplyLeaveServlet extends javax.servlet.http.HttpServlet {
    private LeaveService leaveService = new LeaveService();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Employee user = (session == null) ? null : (Employee) session.getAttribute("user");
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        req.getRequestDispatcher("/apply_leave.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Employee user = (session == null) ? null : (Employee) session.getAttribute("user");
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }

        try {
            Date start = Date.valueOf(req.getParameter("startDate"));
            Date end = Date.valueOf(req.getParameter("endDate"));
            String reason = req.getParameter("reason");

            String result = leaveService.applyLeave(user.getEmpId(), start, end, reason);
            if ("SUCCESS".equals(result)) {
                resp.sendRedirect(req.getContextPath() + "/employee/dashboard");
            } else {
                req.setAttribute("error", result);
                req.getRequestDispatcher("/employee_dashboard.jsp").forward(req, resp);
            }
        } catch (IllegalArgumentException ex) {
            req.setAttribute("error", "Invalid date format");
            req.getRequestDispatcher("/employee_dashboard.jsp").forward(req, resp);
        }
    }
}