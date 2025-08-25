package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/withdraw")
public class WithdrawServlet extends HttpServlet {
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
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Check if user account is active
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            request.setAttribute("error", "❌ Your account is not active. Current status: " + user.getStatus() + ". Please contact customer support.");
            response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
            return;
        }

        try {
            String amountStr = request.getParameter("amount");
            
            // Validate amount parameter
            if (amountStr == null || amountStr.trim().isEmpty()) {
                request.getSession().setAttribute("error", "❌ Withdrawal amount is required. Please enter a valid amount.");
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }

            double amount = Double.parseDouble(amountStr);
            
            // Validate amount value
            if (amount <= 0) {
                request.getSession().setAttribute("error", "❌ Withdrawal amount must be greater than 0. Please enter a valid amount.");
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }

            if (amount > 50000) {
                request.getSession().setAttribute("error", "❌ Withdrawal amount exceeds the daily limit of ₹50,000. Please contact customer support for large withdrawals.");
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }

            String message = customerService.withdraw(user.getUserId(), amount);
            request.getSession().setAttribute("message", message);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid withdrawal amount. Please enter a valid number.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An unexpected error occurred during withdrawal. Please try again later or contact customer support.");
        }

        response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
    }
}
