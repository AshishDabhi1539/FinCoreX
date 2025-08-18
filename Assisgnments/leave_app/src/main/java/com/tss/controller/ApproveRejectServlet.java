package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import com.tss.dao.LeaveRequestDao;
import com.tss.model.Employee;
import com.tss.service.LeaveService;

@WebServlet("/admin/action")
public class ApproveRejectServlet extends javax.servlet.http.HttpServlet {
    private LeaveService leaveService = new LeaveService();
    private LeaveRequestDao lrDao = new LeaveRequestDao();

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Employee admin = (session == null) ? null : (Employee) session.getAttribute("user");
        if (admin == null || !"ADMIN".equalsIgnoreCase(admin.getRole()) && !"Admin".equalsIgnoreCase(admin.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int leaveId = Integer.parseInt(req.getParameter("leaveId"));
        String action = req.getParameter("action"); // APPROVE or REJECT

        if ("APPROVE".equalsIgnoreCase(action)) {
            boolean ok = leaveService.approveLeave(leaveId);
            // optionally set message
        } else if ("REJECT".equalsIgnoreCase(action)) {
            leaveService.rejectLeave(leaveId);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }
}