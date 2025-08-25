package com.banking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/fullHistory")
public class FullHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		if (user == null) {
			session.setAttribute("error", "❌ You must be logged in to view transaction history.");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		try {
			List<Transaction> transactions = customerService.getTransactionHistory(user.getUserId());
			request.setAttribute("transactions", transactions);
			request.getRequestDispatcher("/statement.jsp").forward(request, response);
		} catch (Exception e) {
			session.setAttribute("error", "❌ Error retrieving transaction history: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
		}
	}
}