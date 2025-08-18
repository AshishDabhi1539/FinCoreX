package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tss.db.DBConnection;
import com.tss.model.Employee;

public class EmployeeDao {

    public Employee findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Employee findById(int empId) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return null;
    }

    public boolean updateLeavesTaken(int empId, int newLeavesTaken) {
        String sql = "UPDATE employees SET leaves_taken = ? WHERE emp_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newLeavesTaken);
            ps.setInt(2, empId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setEmpId(rs.getInt("emp_id"));
        e.setName(rs.getString("name"));
        e.setUsername(rs.getString("username"));
        e.setPassword(rs.getString("password"));
        e.setRole(rs.getString("role"));
        e.setTotalLeaves(rs.getInt("total_leaves"));
        e.setLeavesTaken(rs.getInt("leaves_taken"));
        return e;
    }
    
    public int create(Employee e) {
        String sql = "INSERT INTO employees (name, username, password, role, total_leaves, leaves_taken) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getUsername());
            ps.setString(3, e.getPassword()); // plain text for demo
            ps.setString(4, e.getRole());
            ps.setInt(5, e.getTotalLeaves());
            ps.setInt(6, e.getLeavesTaken());
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}