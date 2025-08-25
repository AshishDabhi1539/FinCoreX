package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.model.User;
import com.banking.service.LoanService;

@WebServlet(urlPatterns = { "/loan/apply", "/loan/approve", "/loan/reject", "/loan/repay" })
public class LoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoanService loanService = new LoanService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		try {
			switch (path) {
			case "/loan/apply": {
				String type = req.getParameter("loanType");
				double amount = Double.parseDouble(req.getParameter("amount"));
				double rate = Double.parseDouble(req.getParameter("interestRate"));
				int tenure = Integer.parseInt(req.getParameter("tenureMonths"));
				loanService.applyLoan(user.getUserId(), type, amount, rate, tenure);
				resp.sendRedirect(req.getContextPath() + "/loan_status.jsp?applied=1");
				break;
			}
			case "/loan/approve": {
				long loanId = Long.parseLong(req.getParameter("loanId"));
				loanService.approveLoan(user.getUserId(), loanId);
				resp.sendRedirect(req.getContextPath() + "/loan_approval.jsp?approved=1");
				break;
			}
			case "/loan/reject": {
				long loanId = Long.parseLong(req.getParameter("loanId"));
				loanService.rejectLoan(user.getUserId(), loanId);
				resp.sendRedirect(req.getContextPath() + "/loan_approval.jsp?rejected=1");
				break;
			}
			case "/loan/repay": {
				long loanId = Long.parseLong(req.getParameter("loanId"));
				double pay = Double.parseDouble(req.getParameter("amount"));
				loanService.repay(user.getUserId(), loanId, pay);
				resp.sendRedirect(req.getContextPath() + "/repay_loan.jsp?success=1");
				break;
			}
			default:
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
