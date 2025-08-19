package com.banking.dao;

import com.banking.db.DBConnection;
import com.banking.model.User;
import java.sql.*;

public class UserDao {

	

	public boolean saveUser(User user) {
		String sql = "INSERT INTO users(full_name, username, password_hash, email, phone, dob, gender, address, aadhaar, pan, account_type, deposit, role, status) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getFullName());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPasswordHash()); // plain password for now
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setDate(6, Date.valueOf(user.getDob()));
			ps.setString(7, user.getGender());
			ps.setString(8, user.getAddress());
			ps.setString(9, user.getAadhaar());
			ps.setString(10, user.getPan());
			ps.setString(11, user.getAccountType());
			ps.setDouble(12, user.getDeposit());
			ps.setString(13, user.getRole());
			ps.setString(14, user.getStatus());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isUsernameExists(String username) {
		String sql = "SELECT user_id FROM users WHERE username=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public boolean isEmailExists(String email) {
		String sql = "SELECT user_id FROM users WHERE email=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public User login(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password_hash=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setUsername(rs.getString("username"));
				user.setPasswordHash(rs.getString("password_hash"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setDob(rs.getDate("dob").toLocalDate());
				user.setGender(rs.getString("gender"));
				user.setAddress(rs.getString("address"));
				user.setAadhaar(rs.getString("aadhaar"));
				user.setPan(rs.getString("pan"));
				user.setAccountType(rs.getString("account_type"));
				user.setDeposit(rs.getDouble("deposit"));
				user.setRole(rs.getString("role"));
				user.setStatus(rs.getString("status"));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
