package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.User;
import com.tss.service.AuditLogService;
import com.tss.service.LoanService;

@WebServlet("/loan_approval")
public class LoanApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoanService loanService = new LoanService();
	private final AuditLogService auditLogService = new AuditLogService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("loans", loanService.getAllLoans());
		request.getRequestDispatcher("/admin/loan_approval.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int loanId = Integer.parseInt(request.getParameter("loanId"));
		String action = request.getParameter("action");
		User admin = (User) request.getSession().getAttribute("user");

		try {
			switch (action) {
			case "approve":
				loanService.approveLoan(loanId, admin.getUserId());
				auditLogService.log(request, "APPROVE_LOAN", "Loan", loanId, "Approved loan");
				break;
			case "reject":
				loanService.rejectLoan(loanId, admin.getUserId());
				auditLogService.log(request, "REJECT_LOAN", "Loan", loanId, "Rejected loan");
				break;
			case "disburse":
				loanService.disburseLoan(loanId, admin.getUserId());
				auditLogService.log(request, "DISBURSE_LOAN", "Loan", loanId, "Disbursed loan");
				break;
			}
			response.sendRedirect("loan_approval?success=true");
		} catch (Exception e) {
			request.setAttribute("error", "Action failed: " + e.getMessage());
			doGet(request, response);
		}
	}
}