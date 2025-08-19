package com.tss.service;

import javax.servlet.http.HttpServletRequest;

import com.tss.dao.AuditLogDAO;

public class AuditLogService {
	private final AuditLogDAO auditLogDAO = new AuditLogDAO();

	public void log(HttpServletRequest request, String action, String entityType, Integer entityId, String details) {
		Object userObj = request.getSession().getAttribute("user");
		Integer userId = null;
		if (userObj != null) {
			userId = ((com.tss.model.User) userObj).getUserId();
		}
		String ip = request.getRemoteAddr();
		auditLogDAO.logAction(userId, action, entityType, entityId, details, ip);
	}
}