package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/notifications")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		if (user == null) {
			session.setAttribute("error", "❌ You must be logged in to update notification preferences.");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		try {
			boolean email = "on".equals(request.getParameter("email"));
			boolean sms = "on".equals(request.getParameter("sms"));
			boolean whatsapp = "on".equals(request.getParameter("whatsapp"));
			String message = customerService.updateNotificationPreferences(user.getUserId(), email, sms, whatsapp);
			session.setAttribute("message", message);
		} catch (Exception e) {
			session.setAttribute("error", "❌ Error updating notification preferences: " + e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
	}
}