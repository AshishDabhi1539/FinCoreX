package com.tss.behavioral.strategy.model;

public class Employee {
    private int idEmployee;
    private String nameEmployee;
    private IRole roleEmployee;

    public Employee(int idEmployee, String nameEmployee, IRole roleEmployee) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.roleEmployee = roleEmployee;
    }

    public void promote(IRole newRole) {
        this.roleEmployee = newRole;
    }

    public String getDescription() {
        return roleEmployee.description();
    }

    public String getResponsibility() {
        return roleEmployee.responsibility();
    }
}

