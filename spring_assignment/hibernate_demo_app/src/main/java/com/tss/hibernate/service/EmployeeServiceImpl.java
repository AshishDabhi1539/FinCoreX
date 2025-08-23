package com.tss.hibernate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tss.hibernate.dao.EmployeeDao;
import com.tss.hibernate.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @Override
    public Employee addNewEmployee(Employee employee) {
        return dao.addNewEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    @Override
    public Employee readEmployeeById(int id) {
        return dao.readEmployeeById(id);
    }

    @Override
    public List<Employee> readEmployeeByName(String name) {
        return dao.readEmployeeByName(name);
    }

    @Override
    public List<Employee> readEmployeeByDept(String deptName) {
        return dao.readEmployeeByDept(deptName);
    }
}
