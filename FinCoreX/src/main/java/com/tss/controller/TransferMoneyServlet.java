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

@WebServlet("/transfer_money")
public class TransferMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountService accountService = new AccountService();

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
		// Placeholder for future implementation of actual transfer logic
		response.sendRedirect("transfer_money?success=true");
	}
}

