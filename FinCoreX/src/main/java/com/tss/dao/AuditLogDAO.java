package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.tss.database.DBConnection;
import com.tss.exception.ApplicationException;

public class AuditLogDAO {
	public void logAction(Integer userId, String action, String entityType, Integer entityId, String details,
			String ipAddress) {
		String sql = "INSERT INTO audit_logs (user_id, action, entity_type, entity_id, details, ip_address) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setObject(1, userId);
			ps.setString(2, action);
			ps.setString(3, entityType);
			ps.setObject(4, entityId);
			ps.setString(5, details);
			ps.setString(6, ipAddress);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException("Failed to log audit", e);
		}
	}
}