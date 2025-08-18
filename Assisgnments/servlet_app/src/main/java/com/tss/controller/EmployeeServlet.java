package com.tss.controller;

import com.tss.model.Employee;
import com.tss.service.EmployeeService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Employee> employees = employeeService.fetchAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("allEmployee.jsp").forward(request, response);
    }
}
