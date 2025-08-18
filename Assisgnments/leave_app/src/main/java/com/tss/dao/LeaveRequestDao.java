package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tss.db.DBConnection;
import com.tss.model.LeaveRequest;

public class LeaveRequestDao {

    public boolean create(LeaveRequest lr) {
        String sql = "INSERT INTO leave_requests (emp_id, start_date, end_date, reason, status) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, lr.getEmpId());
            ps.setDate(2, new java.sql.Date(lr.getStartDate().getTime()));
            ps.setDate(3, new java.sql.Date(lr.getEndDate().getTime()));
            ps.setString(4, lr.getReason());
            ps.setString(5, lr.getStatus() == null ? "PENDING" : lr.getStatus());
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) lr.setLeaveId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    public LeaveRequest findById(int leaveId) {
        String sql = "SELECT * FROM leave_requests WHERE leave_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, leaveId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return null;
    }

    public List<LeaveRequest> findByEmpId(int empId) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM leave_requests WHERE emp_id = ? ORDER BY applied_on DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public List<LeaveRequest> findAllPending() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM leave_requests WHERE status = 'PENDING' ORDER BY applied_on DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public boolean updateStatus(int leaveId, String status) {
        String sql = "UPDATE leave_requests SET status = ? WHERE leave_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, leaveId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    private LeaveRequest mapRow(ResultSet rs) throws SQLException {
        LeaveRequest lr = new LeaveRequest();
        lr.setLeaveId(rs.getInt("leave_id"));
        lr.setEmpId(rs.getInt("emp_id"));
        lr.setStartDate(rs.getDate("start_date"));
        lr.setEndDate(rs.getDate("end_date"));
        lr.setReason(rs.getString("reason"));
        lr.setStatus(rs.getString("status"));
        lr.setAppliedOn(rs.getTimestamp("applied_on"));
        return lr;
    }
    
    public List<LeaveRequest> findByEmpIdWithFilters(int empId, String month, String status) {
        List<LeaveRequest> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM leave_requests WHERE emp_id = ?");
        
        if (month != null && !month.isEmpty()) {
            sql.append(" AND MONTH(start_date) = ?");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = ?");
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int idx = 1;
            ps.setInt(idx++, empId);
            if (month != null && !month.isEmpty()) {
                ps.setInt(idx++, Integer.parseInt(month));
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(idx++, status);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest();
                lr.setLeaveId(rs.getInt("leave_id"));
                lr.setStartDate(rs.getDate("start_date"));
                lr.setEndDate(rs.getDate("end_date"));
                lr.setStatus(rs.getString("status"));
                list.add(lr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}