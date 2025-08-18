package com.tss.model;

public class Employee {
    private int empId;
    private String name;
    private String username;
    private String password;
    private String role;
    private int totalLeaves;
    private int leavesTaken;

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getTotalLeaves() { return totalLeaves; }
    public void setTotalLeaves(int totalLeaves) { this.totalLeaves = totalLeaves; }

    public int getLeavesTaken() { return leavesTaken; }
    public void setLeavesTaken(int leavesTaken) { this.leavesTaken = leavesTaken; }
}