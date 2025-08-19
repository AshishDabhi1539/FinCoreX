package com.tss.model;

import java.time.LocalDateTime;

public class Transaction {
	private int transactionId;
	private Integer fromAccountId;
	private Integer toAccountId;
	private String transactionType;
	private double amount;
	private String description;
	private LocalDateTime transactionDate;
	private Integer referenceId;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(Integer fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public Integer getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Integer toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
}

