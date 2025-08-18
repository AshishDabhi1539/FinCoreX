package com.tss.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tss.dao.LeaveDAO;
import com.tss.model.LeaveRequest;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LeaveDAO leaveDAO = new LeaveDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");
        String status = req.getParameter("status");

        List<LeaveRequest> leaves;

        try {
            if (fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()) {
                // Filter by date + status
                leaves = leaveDAO.findByDateRange(fromDate, toDate, status);
            } else if (status != null && !status.isEmpty()) {
                // Filter by status only
                leaves = leaveDAO.findByStatus(status);
            } else {
                // Default: show all
                leaves = leaveDAO.findAll();
            }

            req.setAttribute("leaves", leaves);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error fetching leaves");
        }

        req.getRequestDispatcher("/admin_dashboard.jsp").forward(req, resp);
    }
}
