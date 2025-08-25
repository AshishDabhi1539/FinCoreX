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

        // Validate input
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "⚠️ Username is required. Please enter your username.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "⚠️ Password is required. Please enter your password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        User user = userService.login(username, password);

        if (user != null) {
            String status = user.getStatus();

            switch (status) {
                case "PENDING":
                    request.setAttribute("error", "⏳ Your account is pending admin approval. Please wait for approval before logging in. You will receive an email notification once your account is approved.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                case "REJECTED":
                    request.setAttribute("error", "❌ Your registration was rejected by admin. Please contact customer support for more information or re-register with corrected information.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                case "SUSPENDED":
                    request.setAttribute("error", "🚫 Your account has been suspended due to suspicious activity. Please contact customer support immediately.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                case "BLOCKED":
                    request.setAttribute("error", "🚫 Your account has been blocked. Please contact customer support to resolve this issue.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                case "INACTIVE":
                    request.setAttribute("error", "⏸️ Your account is inactive due to prolonged inactivity. Please contact customer support to reactivate your account.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                case "ACTIVE":
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("userId", user.getUserId());

                    // Route based on role
                    if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                        session.setAttribute("success", "🎉 Welcome back, " + user.getFullName() + "! You have successfully logged in as Administrator.");
                        response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                    } else {
                        session.setAttribute("success", "🎉 Welcome back, " + user.getFullName() + "! You have successfully logged in to your account.");
                        response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                    }
                    return;
                default:
                    request.setAttribute("error", "⚠️ Invalid account status: " + status + ". Please contact customer support.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            // Invalid credentials
            request.setAttribute("error", "❌ Invalid username or password. Please check your credentials and try again. If you've forgotten your password, please use the 'Forgot Password' option.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests (show login form)
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
