package com.banking.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction {
	private long txnId;
	private long userId;
	private String type; // DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT
	private double amount;
	private String description;
	private LocalDateTime txnDate;
	private Timestamp timestamp;

	// Getters & Setters
	public long getTxnId() {
		return txnId;
	}

	public void setTxnId(long txnId) {
		this.txnId = txnId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		// TODO Auto-generated method stub
		this.timestamp = timestamp;
	}
}
