package com.tss.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.exception.ApplicationException;
import com.tss.model.User;
import com.tss.service.UserService;

@WebServlet("/admin_add_customer")
public class AdminAddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			User user = new User();
			user.setFullName(fullName);
			user.setEmail(email);
			user.setPhone(phone);
			user.setUsername(username);
			user.setRole("Customer");
			user.setCreatedAt(LocalDateTime.now());
			user.setPasswordHash(password);

			userService.registerCustomer(user);
			response.sendRedirect("manage_customers?success=true");
		} catch (ApplicationException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/admin/manage_customers.jsp").forward(request, response);
		}
	}
}

