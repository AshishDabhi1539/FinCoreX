package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.model.User;
import com.banking.service.CustomerService;

@WebServlet("/customer/notifications")
public class NotificationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        boolean email = request.getParameter("email") != null;
        boolean sms = request.getParameter("sms") != null;
        boolean whatsapp = request.getParameter("whatsapp") != null;

        boolean success = customerService.updateNotificationPreferences(
                user.getUserId(), email, sms, whatsapp);

        request.getSession().setAttribute("flash", 
                success ? "Preferences updated!" : "Update failed.");
        response.sendRedirect(request.getContextPath() + "/customer/dashboard.jsp");
    }
}
