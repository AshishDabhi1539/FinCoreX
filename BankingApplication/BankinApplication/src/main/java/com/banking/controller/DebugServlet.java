package com.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.db.DBConnection;
import com.banking.model.User;

@WebServlet("/debug")
public class DebugServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Debug Info</title></head><body>");
        out.println("<h1>Debug Information</h1>");
        
        // Check session
        HttpSession session = request.getSession(false);
        out.println("<h2>Session Information</h2>");
        if (session != null) {
            out.println("<p>Session ID: " + session.getId() + "</p>");
            out.println("<p>Session Creation Time: " + session.getCreationTime() + "</p>");
            
            User user = (User) session.getAttribute("user");
            if (user != null) {
                out.println("<p>User in session: " + user.getUsername() + " (Role: " + user.getRole() + ", Status: " + user.getStatus() + ")</p>");
            } else {
                out.println("<p>No user in session</p>");
            }
        } else {
            out.println("<p>No session exists</p>");
        }
        
        // Check database
        out.println("<h2>Database Information</h2>");
        try (Connection conn = DBConnection.getConnection()) {
            out.println("<p>Database connection: SUCCESS</p>");
            
            // Check for admin users
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT user_id, username, role, status FROM users WHERE role='ADMIN'")) {
                
                out.println("<h3>Admin Users:</h3>");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    out.println("<p>ID: " + rs.getLong("user_id") + 
                               ", Username: " + rs.getString("username") + 
                               ", Role: " + rs.getString("role") + 
                               ", Status: " + rs.getString("status") + "</p>");
                }
                if (!found) {
                    out.println("<p>No admin users found in database</p>");
                }
            }
            
            // Check total users
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM users")) {
                if (rs.next()) {
                    out.println("<p>Total users in database: " + rs.getInt("total") + "</p>");
                }
            }
            
        } catch (Exception e) {
            out.println("<p>Database connection: FAILED - " + e.getMessage() + "</p>");
            e.printStackTrace(out);
        }
        
        out.println("<h2>Request Information</h2>");
        out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
        out.println("<p>Context Path: " + request.getContextPath() + "</p>");
        out.println("<p>Servlet Path: " + request.getServletPath() + "</p>");
        out.println("<p>Path Info: " + request.getPathInfo() + "</p>");
        
        out.println("</body></html>");
    }
}
