package com.banking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/dashboard")
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !"CUSTOMER".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }
        
        Long userId = user.getUserId();

        // Forward to customer dashboard JSP
        request.getRequestDispatcher("/customer/dashboard.jsp").forward(request, response);
    }
}
