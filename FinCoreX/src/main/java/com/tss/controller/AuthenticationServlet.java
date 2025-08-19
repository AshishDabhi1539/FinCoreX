package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.exception.ApplicationException;
import com.tss.model.User;
import com.tss.service.UserService;

@WebServlet("/login")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null || password == null || username.trim().isEmpty()) {
			request.setAttribute("error", "All fields are required.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		try {
			User user = userService.login(username.trim(), password);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(1800);

			if ("Admin".equals(user.getRole())) {
				response.sendRedirect("admin/dashboard.jsp");
			} else {
				response.sendRedirect("customer/dashboard.jsp");
			}

		} catch (ApplicationException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}