package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.exception.ApplicationException;
import com.tss.model.User;

public class UserDAO {

	/**
	 * Find user by username (for login)
	 */
	public User findByUsername(String username) {
		String sql = "SELECT user_id, username, full_name, email, phone, role, password_hash, created_at "
				+ "FROM users WHERE username = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					user.setUsername(rs.getString("username"));
					user.setFullName(rs.getString("full_name"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setRole(rs.getString("role"));
					user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					user.setPasswordHash(rs.getString("password_hash"));
					return user;
				}
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error loading user: " + username, e);
		}
		return null;
	}

	/**
	 * Check if username already exists
	 */
	public boolean usernameExists(String username) {
		String sql = "SELECT 1 FROM users WHERE username = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error checking username: " + username, e);
		}
	}

	/**
	 * Insert new customer into database
	 */
	public void insertUser(User user) {
		String sql = "INSERT INTO users " + "(username, password_hash, full_name, email, phone, role, created_at) "
				+ "VALUES (?, ?, ?, ?, ?, 'Customer', NOW())";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPasswordHash());
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());

			int rows = ps.executeUpdate();
			if (rows == 0) {
				throw new ApplicationException("Failed to register user: " + user.getUsername());
			}

		} catch (SQLException e) {
			throw new ApplicationException("Database error during user registration", e);
		}
	}
	/**
     * Get all customers (role = 'Customer')
     */
    public List<User> getAllCustomers() {
        String sql = "SELECT u.user_id, u.username, u.full_name, u.email, u.phone, u.role, u.created_at, "
                + "(SELECT COUNT(*) FROM accounts a WHERE a.user_id = u.user_id AND a.status <> 'Closed') AS account_count "
                + "FROM users u WHERE u.role = 'Customer' ORDER BY u.created_at DESC";
        List<User> customers = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                user.setAccountCount(rs.getInt("account_count"));
                customers.add(user);
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error loading customers", e);
        }
        return customers;
    }

    /**
     * Get total number of users
     */
    public int getTotalUsers() {
        String sql = "SELECT COUNT(*) FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error counting total users", e);
        }
        return 0;
    }

    /**
     * Get total number of customers (non-admin users)
     */
    public int getTotalCustomers() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'Customer'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error counting customers", e);
        }
        return 0;
    }
}