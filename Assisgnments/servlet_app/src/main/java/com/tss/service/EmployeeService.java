package com.tss.service;

import com.tss.dao.EmployeeDao;
import com.tss.model.Employee;
import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDao();
    }

    public List<Employee> fetchAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}
