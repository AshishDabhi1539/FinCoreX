package com.banking.model;

import java.sql.Date;

public class AuditLog {
    private int logId;
    private User user;
    private String action; // e.g., LOGIN, TRANSFER, LOAN_APPLY
    private String ipAddress;
    private Date createdAt;

    public AuditLog(int logId, User user, String action, String ipAddress, Date createdAt) {
        this.logId = logId;
        this.user = user;
        this.action = action;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "AuditLog [id=" + logId + ", user=" + user.getUsername() + ", action=" + action + ", ip=" + ipAddress + "]";
    }
}
