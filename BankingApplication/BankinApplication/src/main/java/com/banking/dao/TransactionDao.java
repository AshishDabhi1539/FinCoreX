package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banking.db.DBConnection;
import com.banking.exception.BankingException;
import com.banking.model.Transaction;

public class TransactionDao {
	public boolean deposit(long accountId, double amount) throws BankingException {
		String insertTx = "INSERT INTO transactions (account_id, type, amount, description, txn_date) VALUES (?, 'DEPOSIT', ?, 'Deposit successful', NOW())";
		String updateBalance = "UPDATE accounts SET balance = balance + ?, updated_at = NOW(), last_transaction_date = NOW(), transaction_count = transaction_count + 1 WHERE id = ? AND status = 'ACTIVE'";
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement ps1 = conn.prepareStatement(insertTx);
					PreparedStatement ps2 = conn.prepareStatement(updateBalance)) {
				ps1.setLong(1, accountId);
				ps1.setDouble(2, amount);
				ps1.executeUpdate();
				ps2.setDouble(1, amount);
				ps2.setLong(2, accountId);
				int updated = ps2.executeUpdate();
				if (updated == 0) {
					conn.rollback();
					throw new BankingException("Account not found or not active", "ACC_NOT_ACTIVE");
				}
				conn.commit();
				return true;
			} catch (SQLException e) {
				conn.rollback();
				throw new BankingException("Error during deposit: " + e.getMessage(), "TXN_DEPOSIT_001", e);
			}
		} catch (SQLException e) {
			throw new BankingException("Database connection error: " + e.getMessage(), "DB_CONN_001", e);
		}
	}

	public boolean withdraw(long accountId, double amount) throws BankingException {
		String insertTx = "INSERT INTO transactions (account_id, type, amount, description, txn_date) VALUES (?, 'WITHDRAWAL', ?, 'Withdrawal successful', NOW())";
		String updateBalance = "UPDATE accounts SET balance = balance - ?, updated_at = NOW(), last_transaction_date = NOW(), transaction_count = transaction_count + 1 WHERE id = ? AND status = 'ACTIVE' AND balance >= ?";
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement ps1 = conn.prepareStatement(insertTx);
					PreparedStatement ps2 = conn.prepareStatement(updateBalance)) {
				ps1.setLong(1, accountId);
				ps1.setDouble(2, amount);
				ps1.executeUpdate();
				ps2.setDouble(1, amount);
				ps2.setLong(2, accountId);
				ps2.setDouble(3, amount);
				int updated = ps2.executeUpdate();
				if (updated == 0) {
					conn.rollback();
					throw new BankingException("Insufficient balance or account not active", "ACC_INSUFFICIENT");
				}
				conn.commit();
				return true;
			} catch (SQLException e) {
				conn.rollback();
				throw new BankingException("Error during withdrawal: " + e.getMessage(), "TXN_WITHDRAW_001", e);
			}
		} catch (SQLException e) {
			throw new BankingException("Database connection error: " + e.getMessage(), "DB_CONN_001", e);
		}
	}

	public boolean transfer(long fromAccountId, long toAccountId, double amount) throws BankingException {
        String debitTx = "INSERT INTO transactions (account_id, type, amount, description, txn_date) VALUES (?, 'TRANSFER_OUT', ?, 'Transfer to account ID: " + toAccountId + "', NOW())";
        String creditTx = "INSERT INTO transactions (account_id, type, amount, description, txn_date) VALUES (?, 'TRANSFER_IN', ?, 'Transfer from account ID: " + fromAccountId + "', NOW())";
        String debitBalance = "UPDATE accounts SET balance = balance - ?, updated_at = NOW(), last_transaction_date = NOW(), transaction_count = transaction_count + 1 WHERE id = ? AND status = 'ACTIVE' AND balance >= ?";
        String creditBalance = "UPDATE accounts SET balance = balance + ?, updated_at = NOW(), last_transaction_date = NOW(), transaction_count = transaction_count + 1 WHERE id = ? AND status = 'ACTIVE'";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(debitTx);
                 PreparedStatement ps2 = conn.prepareStatement(creditTx);
                 PreparedStatement ps3 = conn.prepareStatement(debitBalance);
                 PreparedStatement ps4 = conn.prepareStatement(creditBalance)) {
                ps1.setLong(1, fromAccountId);
                ps1.setDouble(2, amount);
                ps1.executeUpdate();
                ps2.setLong(1, toAccountId);
                ps2.setDouble(2, amount);
                ps2.executeUpdate();
                ps3.setDouble(1, amount);
                ps3.setLong(2, fromAccountId);
                ps3.setDouble(3, amount);
                int debitUpdated = ps3.executeUpdate();
                if (debitUpdated == 0) {
                    conn.rollback();
                    throw new BankingException("Insufficient balance or sender account not active", "ACC_INSUFFICIENT");
                }
                ps4.setDouble(1, amount);
                ps4.setLong(2, toAccountId);
                int creditUpdated = ps4.executeUpdate();
                if (creditUpdated == 0) {
                    conn.rollback();
                    throw new BankingException("Recipient account not active", "ACC_NOT_ACTIVE");
                }
                conn.commit();
                return true;
            } .catch (SQLException e) {
                conn.rollback();
                throw new BankingException("Error during transfer: " + e.getMessage(), "TXN_TRANSFER_001", e);
            }
        } catch (SQLException e) {
            throw new BankingException("Database connection error: " + e.getMessage(), "DB_CONN_001", e);
        }
    }

	public List<Transaction> getTransactionHistory(long userId) throws BankingException {
		String sql = "SELECT * FROM transactions WHERE account_id IN (SELECT id FROM accounts WHERE user_id = ?) ORDER BY txn_date DESC";
		List<Transaction> transactions = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					transactions.add(mapResultSetToTransaction(rs));
				}
			}
			return transactions;
		} catch (SQLException e) {
			throw new BankingException("Error retrieving transaction history: " + e.getMessage(), "TXN_GET_001", e);
		}
	}

	public List<Transaction> getMiniStatement(long userId) throws BankingException {
		String sql = "SELECT * FROM transactions WHERE account_id IN (SELECT id FROM accounts WHERE user_id = ?) ORDER BY txn_date DESC LIMIT 5";
		List<Transaction> transactions = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					transactions.add(mapResultSetToTransaction(rs));
				}
			}
			return transactions;
		} catch (SQLException e) {
			throw new BankingException("Error retrieving mini statement: " + e.getMessage(), "TXN_GET_002", e);
		}
	}

	private Transaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(rs.getLong("id"));
		transaction.setAccountId(rs.getLong("account_id"));
		transaction.setType(rs.getString("type"));
		transaction.setAmount(rs.getDouble("amount"));
		transaction.setDescription(rs.getString("description"));
		transaction.setTxnDate(rs.getTimestamp("txn_date").toLocalDateTime());
		return transaction;
	}
}