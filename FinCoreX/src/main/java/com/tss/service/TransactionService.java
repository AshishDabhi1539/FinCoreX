package com.tss.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.tss.dao.AccountDAO;
import com.tss.dao.TransactionDAO;
import com.tss.database.DBConnection;
import com.tss.exception.ApplicationException;
import com.tss.model.Account;
import com.tss.model.Transaction;

public class TransactionService {

	private final AccountDAO accountDAO = new AccountDAO();
	private final TransactionDAO transactionDAO = new TransactionDAO();

	public void deposit(int accountId, double amount, String description) {
		if (amount <= 0) {
			throw new ApplicationException("Amount must be greater than zero");
		}
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false);
			Account to = accountDAO.getAccountByIdForUpdate(conn, accountId);
			if (to == null) {
				throw new ApplicationException("Account not found");
			}
			double newBalance = to.getBalance() + amount;
			accountDAO.updateAccountBalance(conn, accountId, newBalance);
			Transaction t = new Transaction();
			t.setFromAccountId(null);
			t.setToAccountId(accountId);
			t.setTransactionType("Deposit");
			t.setAmount(amount);
			t.setDescription(description);
			transactionDAO.insertTransaction(conn, t);
			conn.commit();
		} catch (SQLException e) {
			throw new ApplicationException("Deposit failed", e);
		}
	}

	public void withdraw(int accountId, double amount, String description) {
		if (amount <= 0) {
			throw new ApplicationException("Amount must be greater than zero");
		}
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false);
			Account from = accountDAO.getAccountByIdForUpdate(conn, accountId);
			if (from == null) {
				throw new ApplicationException("Account not found");
			}
			if (from.getBalance() < amount) {
				throw new ApplicationException("Insufficient funds");
			}
			double newBalance = from.getBalance() - amount;
			accountDAO.updateAccountBalance(conn, accountId, newBalance);
			Transaction t = new Transaction();
			t.setFromAccountId(accountId);
			t.setToAccountId(null);
			t.setTransactionType("Withdrawal");
			t.setAmount(amount);
			t.setDescription(description);
			transactionDAO.insertTransaction(conn, t);
			conn.commit();
		} catch (SQLException e) {
			throw new ApplicationException("Withdrawal failed", e);
		}
	}

	public void transfer(int fromAccountId, int toAccountId, double amount, String description) {
		if (amount <= 0) {
			throw new ApplicationException("Amount must be greater than zero");
		}
		if (fromAccountId == toAccountId) {
			throw new ApplicationException("Cannot transfer to the same account");
		}
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false);
			Account from = accountDAO.getAccountByIdForUpdate(conn, fromAccountId);
			Account to = accountDAO.getAccountByIdForUpdate(conn, toAccountId);
			if (from == null || to == null) {
				throw new ApplicationException("Account not found");
			}
			if (from.getBalance() < amount) {
				throw new ApplicationException("Insufficient funds");
			}
			accountDAO.updateAccountBalance(conn, fromAccountId, from.getBalance() - amount);
			accountDAO.updateAccountBalance(conn, toAccountId, to.getBalance() + amount);
			Transaction t = new Transaction();
			t.setFromAccountId(fromAccountId);
			t.setToAccountId(toAccountId);
			t.setTransactionType("Transfer");
			t.setAmount(amount);
			t.setDescription(description);
			transactionDAO.insertTransaction(conn, t);
			conn.commit();
		} catch (SQLException e) {
			throw new ApplicationException("Transfer failed", e);
		}
	}

	public List<Transaction> getRecentTransactions(int accountId, int limit) {
		try (Connection conn = DBConnection.getConnection()) {
			return new TransactionDAO().getTransactionsByAccountId(conn, accountId, limit);
		} catch (SQLException e) {
			throw new ApplicationException("Failed to load transactions", e);
		}
	}
}

