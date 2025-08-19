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
}

