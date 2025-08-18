package com.tss.controller;

import com.tss.dao.LeaveDAO;
import com.tss.model.LeaveRequest;
import com.tss.model.User;
import com.tss.service.LeaveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/employee/updateLeave")
public class UpdateLeaveServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LeaveService leaveService = new LeaveService();
    private final LeaveDAO leaveDAO = new LeaveDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("leaveId"));
        String dateStr = req.getParameter("leaveDate");
        String reason = req.getParameter("reason");
        try {
            LeaveRequest lr = leaveDAO.findById(id);
            if (lr == null || !"PENDING".equals(lr.getStatus()) || lr.getUserId()!=u.getId()) {
                resp.sendRedirect(req.getContextPath()+"/employee/dashboard?e=Update+not+allowed");
                return;
            }
            LocalDate d = LocalDate.parse(dateStr);
            String res = leaveService.updatePendingLeave(id, u.getId(), d, reason);
            if (!"OK".equals(res)) {
                resp.sendRedirect(req.getContextPath()+"/employee/dashboard?e="+java.net.URLEncoder.encode(res,"UTF-8"));
                return;
            }
            resp.sendRedirect(req.getContextPath()+"/employee/dashboard?m=Leave+updated");
        } catch (Exception ex) {
            resp.sendRedirect(req.getContextPath()+"/employee/dashboard?e=Error+updating");
        }
    }
}
