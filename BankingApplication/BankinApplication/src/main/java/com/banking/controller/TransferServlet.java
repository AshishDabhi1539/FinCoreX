package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.dao.AccountDao;
import com.banking.service.CustomerService;

@WebServlet("/customer/transfer")
public class TransferServlet extends HttpServlet {
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
        try {
            Account fromAccount = accountDao.getAccountsByUserId(user.getUserId()).stream()
                    .filter(Account::isActive)
                    .findFirst()
                    .orElseThrow(() -> new BankingException("No active account found", "ACC_NOT_FOUND"));
            String toAccountNumber = request.getParameter("toAccount");
            String amountStr = request.getParameter("amount");
            if