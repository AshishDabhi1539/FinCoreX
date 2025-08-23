package com.tss.ioc.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Employee {

    @Value("1")
    private int id;

    @Value("Mahek Morzaria")
    private String name;

    @Autowired
    private Department department;

    public Employee() {
        super();
    }

    public Employee(int id, String name, Department department) {
        super();
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
