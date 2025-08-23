package com.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.User;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String logoutMessage = "👋 You have been successfully logged out. Thank you for using our banking services!";
        
        if (session != null) {
            try {
                // Get user info before invalidating session
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    logoutMessage = "👋 Goodbye, " + user.getFullName() + "! You have been successfully logged out. Thank you for using our banking services!";
                }
                
                // Update last login time before logout (if needed)
                // You can add logout logging here if needed
                
                session.invalidate();
            } catch (Exception e) {
                logoutMessage = "⚠️ An error occurred during logout, but you have been logged out. Please log in again if needed.";
                e.printStackTrace();
            }
        } else {
            logoutMessage = "ℹ️ You were not logged in. Please log in to access your account.";
        }
        
        // Set logout message in session and redirect to login page
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("logoutMessage", logoutMessage);
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
