package com.banking.controller;

import com.banking.model.User;
import com.banking.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    // Handle GET request to show registration form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            User user = new User();
            user.setFullName(req.getParameter("fullName"));
            user.setUsername(req.getParameter("username"));
            user.setEmail(req.getParameter("email"));
            user.setPhone(req.getParameter("phone"));
            user.setDob(LocalDate.parse(req.getParameter("dob")));
            user.setGender(req.getParameter("gender"));
            user.setAddress(req.getParameter("address"));
            user.setAadhaar(req.getParameter("aadhaar"));
            user.setPan(req.getParameter("pan"));
            user.setAccountType(req.getParameter("accountType"));
            user.setDeposit(Double.parseDouble(req.getParameter("deposit")));
            user.setPasswordHash(req.getParameter("password"));
            user.setRole("CUSTOMER");   // ensure customer role
            user.setStatus("PENDING");  // pending approval

            String confirmPassword = req.getParameter("confirmPassword");

            List<String> errors = userService.validateAndRegister(user, confirmPassword);

            if (errors.isEmpty()) {
                req.setAttribute("success", "✅ Registration submitted successfully! Your account is now pending admin approval. You will receive an email notification once your account is approved. You can then log in with your username and password.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("errors", errors);
                req.setAttribute("error", "❌ Registration failed. Please correct the following errors and try again.");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "❌ Invalid deposit amount. Please enter a valid number.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "❌ An unexpected error occurred during registration. Please try again later or contact customer support if the problem persists.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
