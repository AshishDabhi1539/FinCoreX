package com.banking.model;

import java.sql.Date;

public class Transfer {
    private int transferId;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String transferType; // NEFT, RTGS, IMPS
    private Date createdAt;

    public Transfer(int transferId, Account fromAccount, Account toAccount, double amount, String transferType, Date createdAt) {
        this.transferId = transferId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transferType = transferType;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getTransferId() { return transferId; }
    public void setTransferId(int transferId) { this.transferId = transferId; }

    public Account getFromAccount() { return fromAccount; }
    public void setFromAccount(Account fromAccount) { this.fromAccount = fromAccount; }

    public Account getToAccount() { return toAccount; }
    public void setToAccount(Account toAccount) { this.toAccount = toAccount; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getTransferType() { return transferType; }
    public void setTransferType(String transferType) { this.transferType = transferType; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Transfer [id=" + transferId + ", from=" + fromAccount.getAccountNumber() + ", to=" + toAccount.getAccountNumber() + ", amount=" + amount + ", type=" + transferType + "]";
    }
}
