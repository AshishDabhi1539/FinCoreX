package com.tss.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.Account;
import com.tss.model.User;
import com.tss.service.AccountService;
import com.tss.service.TransactionService;

@WebServlet("/transfer_money")
public class TransferMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountService accountService = new AccountService();
	private final TransactionService transactionService = new TransactionService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		List<Account> accounts = accountService.getUserAccounts(user.getUserId());
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/customer/transfer_money.jsp").forward(request, response);
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
			int sourceAccountId = Integer.parseInt(request.getParameter("sourceAccountId"));
			String recipient = request.getParameter("recipientId");
			double amount = Double.parseDouble(request.getParameter("amount"));
			String description = request.getParameter("description");

			Integer toAccountId = null;
			if (recipient != null && recipient.matches("\\d+")) {
				toAccountId = Integer.parseInt(recipient);
			}
			if (toAccountId == null) {
				throw new IllegalArgumentException("Recipient account not specified");
			}

			transactionService.transfer(sourceAccountId, toAccountId, amount, description);
			response.sendRedirect("transfer_money?success=true");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
		}
	}
}

