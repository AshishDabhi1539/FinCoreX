package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.Loan;
import com.tss.model.User;
import com.tss.service.LoanService;

@WebServlet("/apply_loan")
public class ApplyLoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoanService loanService = new LoanService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/customer/apply_loan.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		try {
			String loanType = request.getParameter("loanType");
			double amountRequested = Double.parseDouble(request.getParameter("amountRequested"));
			int termMonths = Integer.parseInt(request.getParameter("termMonths"));
			String accountIdParam = request.getParameter("accountId");
			Integer accountId = (accountIdParam == null || accountIdParam.isEmpty()) ? null
					: Integer.parseInt(accountIdParam);

			Loan loan = new Loan();
			loan.setUserId(user.getUserId());
			loan.setLoanType(loanType);
			loan.setAmountRequested(amountRequested);
			loan.setTermMonths(termMonths);
			loan.setAccountId(accountId);
			loan.setInterestRate(8.5);

			loanService.submitLoanApplication(loan);
			response.sendRedirect("apply_loan?success=true");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
		}
	}
}

