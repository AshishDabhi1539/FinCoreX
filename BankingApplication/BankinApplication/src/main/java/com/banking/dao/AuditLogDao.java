package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.banking.db.DBConnection;

public class AuditLogDao {

	public void log(Long userId, String action, String tableName, Long recordId, String oldValuesJson, String newValuesJson, String ip, String userAgent) {
		String sql = "INSERT INTO audit_logs (user_id, action, table_name, record_id, old_values, new_values, ip_address, user_agent, created_at) VALUES (?,?,?,?,?,?,?, ?, NOW())";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			if (userId == null) ps.setNull(1, java.sql.Types.BIGINT); else ps.setLong(1, userId);
			ps.setString(2, action);
			ps.setString(3, tableName);
			if (recordId == null) ps.setNull(4, java.sql.Types.BIGINT); else ps.setLong(4, recordId);
			ps.setString(5, oldValuesJson);
			ps.setString(6, newValuesJson);
			ps.setString(7, ip);
			ps.setString(8, userAgent);
			ps.executeUpdate();
		} catch (SQLException e) {
			// swallow to not block primary flows
		}
	}
}
