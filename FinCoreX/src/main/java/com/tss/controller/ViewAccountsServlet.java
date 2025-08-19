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

@WebServlet("/view_accounts")
public class ViewAccountsServlet extends HttpServlet {
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
		request.getRequestDispatcher("/customer/view_accounts.jsp").forward(request, response);
	}
}

