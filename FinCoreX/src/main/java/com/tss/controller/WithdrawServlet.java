package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.User;
import com.tss.service.TransactionService;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final TransactionService transactionService = new TransactionService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		try {
			int accountId = Integer.parseInt(request.getParameter("accountId"));
			double amount = Double.parseDouble(request.getParameter("amount"));
			String description = request.getParameter("description");
			transactionService.withdraw(accountId, amount, description);
			response.sendRedirect("view_accounts?success=withdraw");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/customer/view_accounts.jsp").forward(request, response);
		}
	}
}

