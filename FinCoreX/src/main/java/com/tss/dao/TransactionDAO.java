package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.exception.ApplicationException;
import com.tss.model.Transaction;

public class TransactionDAO {

	public void insertTransaction(Connection conn, Transaction t) throws SQLException {
		String sql = "INSERT INTO transactions (from_account_id, to_account_id, transaction_type, amount, description, transaction_date, reference_id) "
				+ "VALUES (?, ?, ?, ?, ?, NOW(), ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			if (t.getFromAccountId() == null) {
				ps.setNull(1, java.sql.Types.INTEGER);
			} else {
				ps.setInt(1, t.getFromAccountId());
			}
			if (t.getToAccountId() == null) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, t.getToAccountId());
			}
			ps.setString(3, t.getTransactionType());
			ps.setDouble(4, t.getAmount());
			ps.setString(5, t.getDescription());
			if (t.getReferenceId() == null) {
				ps.setNull(6, java.sql.Types.INTEGER);
			} else {
				ps.setInt(6, t.getReferenceId());
			}
			ps.executeUpdate();
		}
	}

	public List<Transaction> getTransactionsByAccountId(Connection conn, int accountId, int limit) {
		String sql = "SELECT transaction_id, from_account_id, to_account_id, transaction_type, amount, description, transaction_date, reference_id "
				+ "FROM transactions WHERE from_account_id = ? OR to_account_id = ? ORDER BY transaction_date DESC LIMIT ?";
		List<Transaction> result = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, accountId);
			ps.setInt(2, accountId);
			ps.setInt(3, limit);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Transaction t = new Transaction();
					t.setTransactionId(rs.getInt("transaction_id"));
					t.setFromAccountId((Integer) rs.getObject("from_account_id"));
					t.setToAccountId((Integer) rs.getObject("to_account_id"));
					t.setTransactionType(rs.getString("transaction_type"));
					t.setAmount(rs.getDouble("amount"));
					t.setDescription(rs.getString("description"));
					t.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime());
					t.setReferenceId((Integer) rs.getObject("reference_id"));
					result.add(t);
				}
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error fetching transactions", e);
		}
		return result;
	}
}

