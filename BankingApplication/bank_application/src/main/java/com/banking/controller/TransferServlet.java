package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/transfer")
public class TransferServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check if user is logged in
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "❌ You must be logged in to perform this action. Please log in and try again.");
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        // Check if user account is active
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            request.setAttribute("error", "❌ Your account is not active. Current status: " + user.getStatus() + ". Please contact customer support.");
            response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
            return;
        }

        try {
            String toAccountStr = request.getParameter("toAccount");
            String amountStr = request.getParameter("amount");
            
            // Validate recipient account parameter
            if (toAccountStr == null || toAccountStr.trim().isEmpty()) {
                request.getSession().setAttribute("error", "❌ Recipient account number is required. Please enter a valid account number.");
                response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
                return;
            }

            // Validate amount parameter
            if (amountStr == null || amountStr.trim().isEmpty()) {
                request.getSession().setAttribute("error", "❌ Transfer amount is required. Please enter a valid amount.");
                response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
                return;
            }

            long toAccount = Long.parseLong(toAccountStr);
            double amount = Double.parseDouble(amountStr);
            
            // Validate amount value
            if (amount <= 0) {
                request.getSession().setAttribute("error", "❌ Transfer amount must be greater than 0. Please enter a valid amount.");
                response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
                return;
            }

            if (amount > 100000) {
                request.getSession().setAttribute("error", "❌ Transfer amount exceeds the maximum limit of ₹100,000. Please contact customer support for large transfers.");
                response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
                return;
            }

            // Check if transferring to own account
            if (toAccount == user.getUserId()) {
                request.getSession().setAttribute("error", "❌ Cannot transfer to your own account. Please enter a different recipient account number.");
                response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
                return;
            }

            String message = customerService.transfer(user.getUserId(), toAccount, amount);
            request.getSession().setAttribute("message", message);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid account number or amount. Please enter valid numbers.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An unexpected error occurred during transfer. Please try again later or contact customer support.");
        }

        response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
    }
}
