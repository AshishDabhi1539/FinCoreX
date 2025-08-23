package com.tss.ioc.entity;

import org.springframework.beans.factory.annotation.Value;

public class Department {

    @Value("1")
    private int deptId;

    @Value("SDE")
    private String deptName;

    public Department() {
        super();
    }

    public Department(int deptId, String deptName) {
        super();
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
