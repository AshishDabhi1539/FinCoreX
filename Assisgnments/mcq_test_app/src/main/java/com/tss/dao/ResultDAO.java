package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tss.db.DBConnection;
import com.tss.model.Result;

public class ResultDAO {
	public void saveResult(Result result) throws SQLException {
		String sql = "INSERT INTO results (user_id, score, quiz_date) VALUES (?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, result.getUserId());
			stmt.setInt(2, result.getScore());
			stmt.setTimestamp(3, result.getQuizDate());

			stmt.executeUpdate();
		}
	}
}
