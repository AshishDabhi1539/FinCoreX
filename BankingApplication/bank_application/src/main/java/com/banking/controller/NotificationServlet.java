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
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        boolean emailNotifications = request.getParameter("email_notifications") != null;
        boolean smsNotifications = request.getParameter("sms_notifications") != null;
        boolean transactionAlerts = request.getParameter("transaction_alerts") != null;
        boolean balanceAlerts = request.getParameter("balance_alerts") != null;
        boolean promotionalEmails = request.getParameter("promotional_emails") != null;

        boolean success = customerService.updateNotificationPreferences(
                user.getUserId(),
                emailNotifications,
                smsNotifications,
                transactionAlerts,
                balanceAlerts,
                promotionalEmails);

        request.getSession().setAttribute("flash", 
                success ? "Preferences updated!" : "Update failed.");
        response.sendRedirect(request.getContextPath() + "/customerdashboard.jsp");
    }
}
