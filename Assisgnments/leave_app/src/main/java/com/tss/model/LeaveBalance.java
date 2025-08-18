package com.tss.model;

public class LeaveBalance {
    private int balanceId;
    private int employeeId;
    private int totalLeaves;
    private int leavesTaken;
    private int remainingLeaves;

    public int getBalanceId() { return balanceId; }
    public void setBalanceId(int balanceId) { this.balanceId = balanceId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public int getTotalLeaves() { return totalLeaves; }
    public void setTotalLeaves(int totalLeaves) { this.totalLeaves = totalLeaves; }

    public int getLeavesTaken() { return leavesTaken; }
    public void setLeavesTaken(int leavesTaken) { this.leavesTaken = leavesTaken; }

    public int getRemainingLeaves() { return remainingLeaves; }
    public void setRemainingLeaves(int remainingLeaves) { this.remainingLeaves = remainingLeaves; }
}