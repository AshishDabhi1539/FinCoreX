package com.tss.controller;

import com.tss.dao.LeaveDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/leaveAction")
public class LeaveActionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LeaveDAO leaveDAO = new LeaveDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("leaveId"));
        String action = req.getParameter("action"); // APPROVE / REJECT

        try {
            if ("APPROVE".equals(action)) {
                leaveDAO.approve(id);
            } else {
                String chosen = req.getParameter("rejectionReason");
                String other = req.getParameter("customReason");
                String finalReason = "Other".equals(chosen) && other != null && !other.isBlank() ? other : chosen;
                if (finalReason == null || finalReason.isBlank()) {
                    resp.sendRedirect(req.getContextPath()+"/admin/dashboard?e=Rejection+reason+required");
                    return;
                }
                leaveDAO.reject(id, finalReason);
            }
            resp.sendRedirect(req.getContextPath()+"/admin/dashboard?m=Action+done");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath()+"/admin/dashboard?e=Error+performing+action");
        }
    }
}
