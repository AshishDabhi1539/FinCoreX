package com.tss.model;

public class Employee {
    private String empName;
    private Department department;

    public Employee() {
        super();
    }

    public Employee(String empName, Department department) {
        this.empName = empName;
        this.department = department;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [empName=" + empName + ", department=" + department + "]";
    }
}
