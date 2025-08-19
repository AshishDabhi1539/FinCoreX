package com.tss.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.Transaction;
import com.tss.model.User;
import com.tss.service.TransactionService;

@WebServlet("/account_statement")
public class AccountStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final TransactionService transactionService = new TransactionService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		try {
			int accountId = Integer.parseInt(request.getParameter("accountId"));
			List<Transaction> txns = transactionService.getRecentTransactions(accountId, 100);
			request.setAttribute("transactions", txns);
			request.getRequestDispatcher("/customer/transaction_history.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/customer/view_accounts.jsp").forward(request, response);
		}
	}
}

