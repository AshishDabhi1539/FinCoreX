package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banking.db.DBConnection;
import com.banking.model.NotificationPreference;

public class NotificationPreferenceDao {

	public boolean upsert(NotificationPreference np) throws SQLException {
		String sql = "INSERT INTO notification_preferences (user_id, email, sms, whatsapp) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE email=VALUES(email), sms=VALUES(sms), whatsapp=VALUES(whatsapp), updated_at=NOW()";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, np.getUserId());
			ps.setBoolean(2, np.isEmailEnabled());
			ps.setBoolean(3, np.isSmsEnabled());
			ps.setBoolean(4, np.isWhatsappEnabled());
			return ps.executeUpdate() > 0;
		}
	}

	public NotificationPreference get(long userId) throws SQLException {
		String sql = "SELECT * FROM notification_preferences WHERE user_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					NotificationPreference np = new NotificationPreference();
					np.setUserId(rs.getLong("id"));
					np.setUserId(rs.getLong("user_id"));
					np.setEmailEnabled(rs.getBoolean("email"));
					np.setSmsEnabled(rs.getBoolean("sms"));
					np.setWhatsappEnabled(rs.getBoolean("whatsapp"));
					return np;
				}
			}
		}
		return null;
	}
}
