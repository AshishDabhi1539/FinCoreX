package com.banking.model;

import java.time.LocalDateTime;

public class Transaction {
	private Long transactionId;
	private Long accountId;
	private String type;
	private double amount;
	private String description;
	private LocalDateTime txnDate;

	public Transaction() {
	}

	public Transaction(Long accountId, String type, double amount, String description, LocalDateTime txnDate) {
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.txnDate = txnDate;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public LocalDateTime getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(LocalDateTime txnDate) {
		this.txnDate = txnDate;
	}
}