package com.tss.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.dao.LoanDAO;
import com.tss.dao.UserDAO;

@WebServlet("/reports")
public class ViewReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO = new UserDAO();
	private final LoanDAO loanDAO = new LoanDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> report = new HashMap<>();
		report.put("totalUsers", userDAO.getTotalUsers());
		report.put("totalCustomers", userDAO.getTotalCustomers());
		report.put("totalLoans", loanDAO.getAllLoans().size());
		report.put("pendingLoans",
				loanDAO.getAllLoans().stream().filter(l -> "Pending".equals(l.getApprovalStatus())).count());

		request.setAttribute("report", report);
		request.getRequestDispatcher("/admin/reports.jsp").forward(request, response);
	}
}