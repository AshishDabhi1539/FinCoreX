package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.exception.ApplicationException;
import com.tss.model.Account;

public class AccountDAO {

	public List<Account> getAccountsByUserId(int userId) {
		String sql = "SELECT account_id, user_id, account_number, account_type, balance, status, created_at "
				+ "FROM accounts WHERE user_id = ? AND status <> 'Closed' ORDER BY created_at DESC";
		List<Account> accounts = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Account a = new Account();
					a.setAccountId(rs.getInt("account_id"));
					a.setUserId(rs.getInt("user_id"));
					a.setAccountNumber(rs.getString("account_number"));
					a.setAccountType(rs.getString("account_type"));
					a.setBalance(rs.getDouble("balance"));
					a.setStatus(rs.getString("status"));
					a.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					accounts.add(a);
				}
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error loading accounts", e);
		}
		return accounts;
	}

	public Account getAccountById(Connection conn, int accountId) throws SQLException {
		String sql = "SELECT account_id, user_id, account_number, account_type, balance, status, created_at FROM accounts WHERE account_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, accountId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Account a = new Account();
					a.setAccountId(rs.getInt("account_id"));
					a.setUserId(rs.getInt("user_id"));
					a.setAccountNumber(rs.getString("account_number"));
					a.setAccountType(rs.getString("account_type"));
					a.setBalance(rs.getDouble("balance"));
					a.setStatus(rs.getString("status"));
					a.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					return a;
				}
			}
		}
		return null;
	}

	public Account getAccountByIdForUpdate(Connection conn, int accountId) throws SQLException {
		String sql = "SELECT account_id, user_id, account_number, account_type, balance, status, created_at FROM accounts WHERE account_id = ? FOR UPDATE";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, accountId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Account a = new Account();
					a.setAccountId(rs.getInt("account_id"));
					a.setUserId(rs.getInt("user_id"));
					a.setAccountNumber(rs.getString("account_number"));
					a.setAccountType(rs.getString("account_type"));
					a.setBalance(rs.getDouble("balance"));
					a.setStatus(rs.getString("status"));
					a.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					return a;
				}
			}
		}
		return null;
	}

	public void updateAccountBalance(Connection conn, int accountId, double newBalance) throws SQLException {
		String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDouble(1, newBalance);
			ps.setInt(2, accountId);
			ps.executeUpdate();
		}
	}

	public Account findByAccountNumber(Connection conn, String accountNumber) throws SQLException {
		String sql = "SELECT account_id, user_id, account_number, account_type, balance, status, created_at FROM accounts WHERE account_number = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, accountNumber);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Account a = new Account();
					a.setAccountId(rs.getInt("account_id"));
					a.setUserId(rs.getInt("user_id"));
					a.setAccountNumber(rs.getString("account_number"));
					a.setAccountType(rs.getString("account_type"));
					a.setBalance(rs.getDouble("balance"));
					a.setStatus(rs.getString("status"));
					a.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					return a;
				}
			}
		}
		return null;
	}
}

