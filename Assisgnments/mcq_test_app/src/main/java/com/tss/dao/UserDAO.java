package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.db.DBConnection;
import com.tss.model.User;

public class UserDAO {
	public void registerUser(User user) throws SQLException {
		String sqlQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.executeUpdate();
		}
	}

	public User loginUser(String username, String password) throws SQLException {
		String sqlQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("email"));
			}
			return null;
		}
	}
}
