package com.tss.controller;

import com.tss.dao.UserDAO;
import com.tss.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    // Handle GET requests → show login page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // If there’s a message (e.g., registration success), it will be shown via request param "m"
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    // Handle POST requests → process login
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userDAO.findByUsernameAndPassword(username, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user);

                // Redirect based on role
                if ("admin".equalsIgnoreCase(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/employee/dashboard");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login?e=Invalid+credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/error.jsp")
                   .forward(req, resp);
        }
    }
}
