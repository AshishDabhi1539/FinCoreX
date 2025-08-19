package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.User;
import com.banking.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {
            String status = user.getStatus();

            switch (status) {
                case "PENDING":
                    request.setAttribute("error", "Your account is pending admin approval.");
                    request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
                    return;
                case "REJECTED":
                    request.setAttribute("error", "Your registration was rejected by admin.");
                    request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
                    return;
                case "ACTIVE":
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    // Route based on role
                    if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
                    }
                    return;
                default:
                    request.setAttribute("error", "Invalid account status.");
                    request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            }
        } else {
            // Invalid credentials
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }
}
