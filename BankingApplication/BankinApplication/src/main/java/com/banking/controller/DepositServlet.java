package com.banking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.banking.model.User;
import com.banking.model.Account;
import com.banking.dao.AccountDao;
import com.banking.service.CustomerService;
import com.banking.exception.BankingException;

@WebServlet("/customer/deposit")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerService customerService = new CustomerService();
    private AccountDao accountDao = new AccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            session.setAttribute("error", "❌ You must be logged in to perform this action.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Account account;
        try {
            account = accountDao.getAccountsByUserId(user.getUserId()).stream().findFirst()
                    .orElseThrow(() -> new BankingException("No active account found", "ACC_NOT_FOUND"));
            if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {
                session.setAttribute("error", "❌ Your account is not active. Status: " + account.getStatus());
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }
        } catch (BankingException e) {
            session.setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
            return;
        }

        try {
            String amountStr = request.getParameter("amount");
            if (amountStr == null || amountStr.trim().isEmpty()) {
                session.setAttribute("error", "❌ Deposit amount is required.");
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                session.setAttribute("error", "❌ Invalid deposit amount. Please enter a valid number.");
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }

            if (amount <= 0) {
                session.setAttribute("error", "❌ Deposit amount must be greater than 0.");
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }

            if (amount > 100000) {
                session.setAttribute("error", "❌ Deposit amount exceeds the maximum limit of ₹100,000.");
                response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
                return;
            }

            String message = customerService.deposit(account.getAccountId(), amount);
            session.setAttribute("message", message);
        } catch (Exception e) {
            session.setAttribute("error", "❌ An unexpected error occurred during deposit: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
    }
}