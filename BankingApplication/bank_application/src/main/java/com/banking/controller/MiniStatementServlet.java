package com.banking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/miniStatement")
public class MiniStatementServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        List<Transaction> transactions = customerService.getMiniStatement(user.getUserId());
        request.setAttribute("transactions", transactions);

        request.getRequestDispatcher("/customer/statement.jsp").forward(request, response);
    }
}
