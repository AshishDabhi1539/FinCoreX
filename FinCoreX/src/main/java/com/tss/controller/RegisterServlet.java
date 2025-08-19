package com.tss.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.dao.UserDAO;
import com.tss.exception.ApplicationException;
import com.tss.model.User;
import com.tss.service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserService();
	private final UserDAO userDAO = new UserDAO();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Validate inputs
		if (anyNull(fullName, email, phone, username, password)) {
			request.setAttribute("error", "All fields are required.");
			forwardToForm(request, response);
			return;
		}

		if (username.length() < 4) {
			request.setAttribute("error", "Username must be at least 4 characters.");
			forwardToForm(request, response);
			return;
		}

		if (password.length() < 6) {
			request.setAttribute("error", "Password must be at least 6 characters.");
			forwardToForm(request, response);
			return;
		}

		// Check if username already exists
		if (userDAO.findByUsername(username) != null) {
			request.setAttribute("error", "Username already taken. Please choose another.");
			forwardToForm(request, response);
			return;
		}

		try {
			// Create new user - DON'T hash password here, let service handle it
			User user = new User();
			user.setUsername(username);
			user.setFullName(fullName);
			user.setEmail(email);
			user.setPhone(phone);
			user.setRole("Customer"); // Only Customer can register
			user.setCreatedAt(LocalDateTime.now());
			user.setPasswordHash(password); // Pass raw password to service

			// Save to database
			userService.registerCustomer(user);

			// Redirect to login on success
			response.sendRedirect("login.jsp?registered=true");

		} catch (ApplicationException e) {
			request.setAttribute("error", e.getMessage());
			forwardToForm(request, response);
		}
	}

	private boolean anyNull(String... fields) {
		for (String field : fields) {
			if (field == null || field.trim().isEmpty())
				return true;
		}
		return false;
	}

	private void forwardToForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
}