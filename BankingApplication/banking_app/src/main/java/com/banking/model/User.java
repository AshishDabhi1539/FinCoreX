package com.banking.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String passwordHash;
	private String role;
	private String email;
	private String phone;
	private boolean active;
	private LocalDateTime lastLogin;
	private Date createdAt;

	private List<Account> accounts = new ArrayList<>();
	private List<Loan> loans = new ArrayList<>();

	public User() {
	}

	public User(int id, String username, String passwordHash, String role, String email, String phone, Date createdAt) {
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.email = email;
		this.phone = phone;
		this.createdAt = createdAt;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void addLoan(Loan loan) {
		this.loans.add(loan);
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", role='" + role + '\'' + ", email='" + email
				+ '\'' + ", phone='" + phone + '\'' + ", accounts=" + accounts.size() + ", loans=" + loans.size() + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
}
