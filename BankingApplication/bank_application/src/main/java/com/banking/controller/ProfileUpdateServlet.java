package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/updateProfile")
public class ProfileUpdateServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (sessionUser == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        // update from form
        sessionUser.setEmail(request.getParameter("email"));
        sessionUser.setPhone(request.getParameter("phone"));
        sessionUser.setAddress(request.getParameter("address"));

        String message = customerService.updateProfile(sessionUser);

        request.getSession().setAttribute("flash", message);
        response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
    }
}
