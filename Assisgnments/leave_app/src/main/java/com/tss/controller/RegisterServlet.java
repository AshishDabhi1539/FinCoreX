package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.dao.EmployeeDao;
import com.tss.dao.LeaveBalanceDao;
import com.tss.model.Employee;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private EmployeeDao empDao = new EmployeeDao();
    private LeaveBalanceDao lbDao = new LeaveBalanceDao();
    private static final int DEFAULT_LEAVES = 20;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // role default EMPLOYEE
        String role = "EMPLOYEE";

        // TODO: Validate inputs, check username uniqueness (simple attempt here)
        Employee existing = empDao.findByUsernameAndPassword(username, password); // not ideal for check but quick
        // Better: add a findByUsername() method. For demo, we check username uniqueness simply:
        // We'll try to create and catch unique constraint exception instead.

        Employee e = new Employee();
        e.setName(name);
        e.setUsername(username);
        e.setPassword(password); // << plain text here. Hash in production.
        e.setRole(role);
        e.setTotalLeaves(DEFAULT_LEAVES);
        e.setLeavesTaken(0);

        int newId = empDao.create(e);
        if (newId > 0) {
            // insert initial snapshot in leave_balance
            lbDao.insertInitialBalance(newId, DEFAULT_LEAVES, 0, DEFAULT_LEAVES);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("error", "Could not register user (username may already exist).");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}