package com.tss.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tss.db.DBConnection;
import com.tss.model.LeaveRequest;

public class LeaveDAO {

	// ✅ Check if leave already exists
	public boolean existsPendingOrApprovedOnDate(int userId, LocalDate date) throws SQLException {
		String sql = "SELECT COUNT(*) FROM leave_requests "
				+ "WHERE user_id=? AND leave_date=? AND status IN ('PENDING','APPROVED')";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setDate(2, Date.valueOf(date));
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				return rs.getInt(1) > 0;
			}
		}
	}

	// ✅ Count monthly leaves
	public int countMonthLeavesPendingOrApproved(int userId, int year, int month) throws SQLException {
		String sql = "SELECT COUNT(*) FROM leave_requests "
				+ "WHERE user_id=? AND YEAR(leave_date)=? AND MONTH(leave_date)=? "
				+ "AND status IN ('PENDING','APPROVED')";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, year);
			ps.setInt(3, month);
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	// ✅ Insert new leave
	public void insert(LeaveRequest lr) throws SQLException {
		String sql = "INSERT INTO leave_requests(user_id, leave_date, status, reason) VALUES(?,?, 'PENDING', ?)";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, lr.getUserId());
			ps.setDate(2, Date.valueOf(lr.getLeaveDate()));
			ps.setString(3, lr.getReason());
			ps.executeUpdate();
		}
	}

	// ✅ Find by ID
	public LeaveRequest findById(int id) throws SQLException {
		String sql = "SELECT * FROM leave_requests WHERE id=?";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return map(rs);
				return null;
			}
		}
	}

	// ✅ Update leave (only if pending)
	public void updatePendingLeave(int id, LocalDate newDate, String newReason) throws SQLException {
		String sql = "UPDATE leave_requests SET leave_date=?, reason=? " + "WHERE id=? AND status='PENDING'";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setDate(1, Date.valueOf(newDate));
			ps.setString(2, newReason);
			ps.setInt(3, id);
			ps.executeUpdate();
		}
	}

	// ✅ Approve with admin_reason
	public void approve(int id, String adminReason) throws SQLException {
		String sql = "UPDATE leave_requests SET status='APPROVED', rejection_reason=NULL, admin_reason=? WHERE id=?";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, adminReason);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
	}

	// ✅ Reject with rejection_reason + admin_reason
	public void reject(int id, String rejectionReason, String adminReason) throws SQLException {
		String sql = "UPDATE leave_requests SET status='REJECTED', rejection_reason=?, admin_reason=? WHERE id=?";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, rejectionReason);
			ps.setString(2, adminReason);
			ps.setInt(3, id);
			ps.executeUpdate();
		}
	}

	// ✅ Fetch by user
	public List<LeaveRequest> findByUser(int userId) throws SQLException {
		String sql = "SELECT * FROM leave_requests WHERE user_id=? ORDER BY created_at DESC";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				List<LeaveRequest> list = new ArrayList<>();
				while (rs.next())
					list.add(map(rs));
				return list;
			}
		}
	}

	// ✅ Admin search with filters
	public List<LeaveRequest> adminSearch(String status, LocalDate from, LocalDate to) throws SQLException {
		StringBuilder sql = new StringBuilder(
				"SELECT lr.*, u.username FROM leave_requests lr JOIN users u ON u.id=lr.user_id WHERE 1=1 ");
		List<Object> params = new ArrayList<>();

		if (status != null && !status.isEmpty()) {
			sql.append(" AND lr.status=?");
			params.add(status);
		}
		if (from != null) {
			sql.append(" AND lr.leave_date>=?");
			params.add(Date.valueOf(from));
		}
		if (to != null) {
			sql.append(" AND lr.leave_date<=?");
			params.add(Date.valueOf(to));
		}
		sql.append(" ORDER BY lr.created_at DESC");

		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			try (ResultSet rs = ps.executeQuery()) {
				List<LeaveRequest> list = new ArrayList<>();
				while (rs.next()) {
					LeaveRequest lr = map(rs);
					list.add(lr);
				}
				return list;
			}
		}
	}

	// ✅ Map resultset to LeaveRequest
	private LeaveRequest map(ResultSet rs) throws SQLException {
		LeaveRequest l = new LeaveRequest();
		l.setId(rs.getInt("id"));
		l.setUserId(rs.getInt("user_id"));
		l.setLeaveDate(rs.getDate("leave_date").toLocalDate());
		l.setStatus(rs.getString("status"));
		l.setReason(rs.getString("reason"));
		l.setRejectionReason(rs.getString("rejection_reason"));
		l.setAdminReason(rs.getString("admin_reason")); // ✅ new field
		Timestamp c = rs.getTimestamp("created_at");
		if (c != null)
			l.setCreatedAt(c.toLocalDateTime());
		Timestamp u = rs.getTimestamp("updated_at");
		if (u != null)
			l.setUpdatedAt(u.toLocalDateTime());
		return l;
	}

	public List<LeaveRequest> findByDateRange(String from, String to, String status) {
		List<LeaveRequest> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"SELECT id, user_id, leave_date, status, reason, admin_reason, created_at "
						+ "FROM leave_requests WHERE leave_date BETWEEN ? AND ?");
		if (status != null && !status.equalsIgnoreCase("ALL")) {
			sql.append(" AND status=?");
		}

		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql.toString())) {
			ps.setDate(1, Date.valueOf(from));
			ps.setDate(2, Date.valueOf(to));
			if (status != null && !status.equalsIgnoreCase("ALL")) {
				ps.setString(3, status.toUpperCase());
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapRow(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Fetch leaves by status
	public List<LeaveRequest> findByStatus(String status) {
		List<LeaveRequest> list = new ArrayList<>();
		String sql = "SELECT id, user_id, leave_date, status, reason, admin_reason, created_at "
				+ "FROM leave_requests WHERE status=?";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, status.toUpperCase());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapRow(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Fetch all leaves
	public List<LeaveRequest> findAll() {
		List<LeaveRequest> list = new ArrayList<>();
		String sql = "SELECT id, user_id, leave_date, status, reason, admin_reason, created_at FROM leave_requests";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(mapRow(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	private LeaveRequest mapRow(ResultSet rs) throws SQLException {
		LeaveRequest lr = new LeaveRequest();
		lr.setId(rs.getInt("id"));
		lr.setUserId(rs.getInt("user_id"));
		lr.setLeaveDate(rs.getDate("leave_date").toLocalDate());
		lr.setStatus(rs.getString("status"));
		lr.setReason(rs.getString("reason"));
		lr.setAdminReason(rs.getString("admin_reason"));
		lr.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		return lr;
	}

	public boolean approve(int id) {
		String sql = "UPDATE leave_requests SET status = 'APPROVED', admin_reason = NULL WHERE id = ?";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean reject(int id, String reason) {
		String sql = "UPDATE leave_requests SET status = 'REJECTED', admin_reason = ? WHERE id = ?";
		try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, reason);
			ps.setInt(2, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
