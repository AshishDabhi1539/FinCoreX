package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.banking.db.DBConnection;
import com.banking.exception.BankingException;

public class TransferDao {
	public boolean save(long fromUserId, long toUserId, long fromAccountId, long toAccountId, double amount,
			String description, String status) throws BankingException {
		String sql = "INSERT INTO transfers (from_user_id, to_user_id, from_account_id, to_account_id, amount, description, status, transfer_date) VALUES (?,?,?,?,?,?,?, NOW())";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, fromUserId);
			ps.setLong(2, toUserId);
			ps.setLong(3, fromAccountId);
			ps.setLong(4, toAccountId);
			ps.setDouble(5, amount);
			ps.setString(6, description);
			ps.setString(7, status);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new BankingException("Error saving transfer: " + e.getMessage(), "TRANSFER_SAVE_001", e);
		}
	}
}