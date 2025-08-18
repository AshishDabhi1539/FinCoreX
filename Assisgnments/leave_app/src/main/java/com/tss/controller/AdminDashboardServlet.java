package com.tss.controller;

import com.tss.dao.LeaveRequestDao;
import com.tss.model.Employee;
import com.tss.model.LeaveRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private LeaveRequestDao lrDao = new LeaveRequestDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Employee user = (session == null) ? null : (Employee) session.getAttribute("user");

        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<LeaveRequest> pending = lrDao.findAllPending();
        req.setAttribute("leaveRequests", pending);
        req.getRequestDispatcher("/admin_dashboard.jsp").forward(req, resp);
    }
}
