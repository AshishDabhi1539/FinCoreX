package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.db.DBConnection;

public class LeaveBalanceDao {

    /**
     * Insert initial snapshot for new employee.
     */
    public boolean insertInitialBalance(int employeeId, int totalLeaves, int leavesTaken, int remainingLeaves) {
        String sql = "INSERT INTO leave_balance (employee_id, total_leaves, leaves_taken, remaining_leaves) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ps.setInt(2, totalLeaves);
            ps.setInt(3, leavesTaken);
            ps.setInt(4, remainingLeaves);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    /**
     * Update existing snapshot row for employee.
     */
    public boolean updateBalanceSnapshot(int employeeId, int totalLeaves, int leavesTaken, int remainingLeaves) {
        String sql = "UPDATE leave_balance SET total_leaves=?, leaves_taken=?, remaining_leaves=? WHERE employee_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, totalLeaves);
            ps.setInt(2, leavesTaken);
            ps.setInt(3, remainingLeaves);
            ps.setInt(4, employeeId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    /**
     * If exists update otherwise insert (simple upsert).
     */
    public void updatebalance(int employeeId, int totalLeaves, int leavesTaken, int remainingLeaves) {
        String select = "SELECT balance_id FROM leave_balance WHERE employee_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    updateBalanceSnapshot(employeeId, totalLeaves, leavesTaken, remainingLeaves);
                } else {
                    insertInitialBalance(employeeId, totalLeaves, leavesTaken, remainingLeaves);
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}