package com.tss.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.dao.UserDAO;
import com.tss.model.User;

@WebServlet("/manage_customers")
public class ManageCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> customers = userDAO.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/admin/manage_customers.jsp").forward(request, response);
    }
}