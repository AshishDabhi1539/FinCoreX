package com.banking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.banking.db.DBConnection;
import com.banking.exception.BankingException;
import com.banking.model.User;

public class UserDao {
	public boolean saveUser(User user) throws BankingException {
		String sql = "INSERT INTO users(full_name, username, password_hash, email, phone, dob, gender, address, aadhaar, pan, account_type, role, status, email_notification, sms_notification, whatsapp_notification) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPasswordHash());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setDate(6, Date.valueOf(user.getDob()));
			ps.setString(7, user.getGender());
			ps.setString(8, user.getAddress());
			ps.setString(9, user.getAadhaar());
			ps.setString(10, user.getPan());
			ps.setString(11, user.getAccountType());
			ps.setString(12, user.getRole());
			ps.setString(13, user.getStatus());
			ps.setBoolean(14, user.isEmailNotification());
			ps.setBoolean(15, user.isSmsNotification());
			ps.setBoolean(16, user.isWhatsappNotification());

			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						user.setUserId(rs.getLong(1));
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			throw new BankingException("Error saving user: " + e.getMessage(), "USER_SAVE_001", e);
		}
	}

	public boolean isUsernameExists(String username) throws BankingException {
		String sql = "SELECT user_id FROM users WHERE username=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			throw new BankingException("Error checking username: " + e.getMessage(), "USER_CHECK_001", e);
		}
	}

	public boolean isEmailExists(String email) throws BankingException {
		String sql = "SELECT user_id FROM users WHERE email=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			throw new BankingException("Error checking email: " + e.getMessage(), "USER_CHECK_002", e);
		}
	}

	public boolean isAadhaarExists(String aadhaar) throws BankingException {
		String sql = "SELECT user_id FROM users WHERE aadhaar=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, aadhaar);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			throw new BankingException("Error checking aadhaar: " + e.getMessage(), "USER_CHECK_003", e);
		}
	}

	public boolean isPanExists(String pan) throws BankingException {
		String sql = "SELECT user_id FROM users WHERE pan=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, pan);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			throw new BankingException("Error checking PAN: " + e.getMessage(), "USER_CHECK_004", e);
		}
	}

	public User login(String username, String password) throws BankingException {
		String sql = "SELECT * FROM users WHERE username=? AND password_hash=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
			return null;
		} catch (SQLException e) {
			throw new BankingException("Error during login: " + e.getMessage(), "USER_LOGIN_001", e);
		}
	}

	public List<User> getUsersByStatus(String status) throws BankingException {
		String sql = "SELECT * FROM users WHERE status=?";
		List<User> list = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapResultSetToUser(rs));
				}
			}
			return list;
		} catch (SQLException e) {
			throw new BankingException("Error retrieving users by status: " + e.getMessage(), "USER_GET_001", e);
		}
	}

	public void updateUserStatus(long userId, String status) throws BankingException {
		String sql = "UPDATE users SET status=?, updated_at=NOW() WHERE user_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setLong(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new BankingException("Error updating user status: " + e.getMessage(), "USER_UPDATE_001", e);
		}
	}

	public User getUserById(long userId) throws BankingException {
		String sql = "SELECT * FROM users WHERE user_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
			return null;
		} catch (SQLException e) {
			throw new BankingException("Error retrieving user by ID: " + e.getMessage(), "USER_GET_002", e);
		}
	}

	public boolean updateUserProfile(User user) throws BankingException {
		String sql = "UPDATE users SET email=?, phone=?, address=?, updated_at=NOW() WHERE user_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getAddress());
			ps.setLong(4, user.getUserId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new BankingException("Error updating user profile: " + e.getMessage(), "USER_UPDATE_002", e);
		}
	}

	public boolean updateNotificationPreferences(long userId, boolean email, boolean sms, boolean whatsapp)
			throws BankingException {
		String sql = "UPDATE users SET email_notification=?, sms_notification=?, whatsapp_notification=?, updated_at=NOW() WHERE user_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setBoolean(1, email);
			ps.setBoolean(2, sms);
			ps.setBoolean(3, whatsapp);
			ps.setLong(4, userId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new BankingException("Error updating notification preferences: " + e.getMessage(), "USER_UPDATE_003",
					e);
		}
	}

	private User mapResultSetToUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getLong("user_id"));
		user.setFullName(rs.getString("full_name"));
		user.setUsername(rs.getString("username"));
		user.setPasswordHash(rs.getString("password_hash"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setDob(rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null);
		user.setGender(rs.getString("gender"));
		user.setAddress(rs.getString("address"));
		user.setAadhaar(rs.getString("aadhaar"));
		user.setPan(rs.getString("pan"));
		user.setAccountType(rs.getString("account_type"));
		user.setRole(rs.getString("role"));
		user.setStatus(rs.getString("status"));
		user.setLastLogin(
				rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toLocalDateTime() : null);
		user.setEmailNotification(rs.getBoolean("email_notification"));
		user.setSmsNotification(rs.getBoolean("sms_notification"));
		user.setWhatsappNotification(rs.getBoolean("whatsapp_notification"));
		return user;
	}
}