package com.banking.model;

import java.time.LocalDateTime;

public class Account {
	private Long accountId;
	private Long userId;
	private String accountNumber;
	private String accountType;
	private double balance;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String branchCode;
	private String ifscCode;
	private double interestRate;
	private LocalDateTime lastTransactionDate;
	private int transactionCount;
	private double dailyLimit;
	private double monthlyLimit;
	private String currency;

	public Account() {
	}

	public Account(Long userId, String accountNumber, String accountType, double balance) {
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.status = "ACTIVE";
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public LocalDateTime getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(LocalDateTime lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}

	public double getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(double dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public double getMonthlyLimit() {
		return monthlyLimit;
	}

	public void setMonthlyLimit(double monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isActive() {
		return "ACTIVE".equalsIgnoreCase(status);
	}

	public boolean isFrozen() {
		return "FROZEN".equalsIgnoreCase(status);
	}

	public boolean isClosed() {
		return "CLOSED".equalsIgnoreCase(status);
	}

	public boolean hasSufficientBalance(double amount) {
		return balance >= amount;
	}

	public void credit(double amount) {
		this.balance += amount;
		this.updatedAt = LocalDateTime.now();
		this.lastTransactionDate = LocalDateTime.now();
		this.transactionCount++;
	}

	public boolean debit(double amount) {
		if (hasSufficientBalance(amount)) {
			this.balance -= amount;
			this.updatedAt = LocalDateTime.now();
			this.lastTransactionDate = LocalDateTime.now();
			this.transactionCount++;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Account{" + "accountId=" + accountId + ", userId=" + userId + ", accountNumber='" + accountNumber + '\''
				+ ", accountType='" + accountType + '\'' + ", balance=" + balance + ", status='" + status + '\''
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
	}
}