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
import com.banking.util.ValidationUtil;

@WebServlet("/customer/updateProfile")
public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		if (user == null) {
			session.setAttribute("error", "❌ You must be logged in to update your profile.");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		try {
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			if (email == null || email.trim().isEmpty() || phone == null || phone.trim().isEmpty() || address == null
					|| address.trim().isEmpty()) {
				session.setAttribute("error", "❌ All fields (email, phone, address) are required.");
				response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
				return;
			}
			if (!ValidationUtil.isValidEmail(email) || !ValidationUtil.isValidPhone(phone)
					|| !ValidationUtil.isValidAddress(address)) {
				session.setAttribute("error", "❌ Invalid email, phone, or address format.");
				response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
				return;
			}
			user.setEmail(email);
			user.setPhone(phone);
			user.setAddress(address);
			String message = customerService.updateProfile(user);
			session.setAttribute("message", message);
			session.setAttribute("user", user);
		} catch (Exception e) {
			session.setAttribute("error", "❌ Error updating profile: " + e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
	}
}