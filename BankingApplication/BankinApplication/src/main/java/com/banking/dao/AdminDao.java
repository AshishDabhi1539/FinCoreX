package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

import com.banking.db.DBConnection;
import com.banking.model.User;

public class AdminDao {

    // Dashboard Statistics
    public long getTotalCustomers() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'CUSTOMER'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getPendingApprovals() {
        String sql = "SELECT COUNT(*) FROM users WHERE status = 'PENDING'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getActiveAccounts() {
        String sql = "SELECT COUNT(*) FROM users WHERE status = 'ACTIVE'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalBalance() {
        String sql = "SELECT SUM(deposit) FROM users WHERE status = 'ACTIVE'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // User Management
    public List<User> getAllCustomers() {
        List<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'CUSTOMER' ORDER BY user_id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                customers.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<User> getPendingUsers() {
        List<User> pendingUsers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE status = 'PENDING' ORDER BY user_id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                pendingUsers.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingUsers;
    }

    public List<User> getRecentUsers(int limit) {
        List<User> recentUsers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'CUSTOMER' ORDER BY created_at DESC LIMIT ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                recentUsers.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recentUsers;
    }

    public List<User> getUsersByStatus(String status) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE status = ? ORDER BY user_id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUserStatus(long userId, String status) {
        String sql = "UPDATE users SET status = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setLong(2, userId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(long userId, String status, String role) {
        String sql = "UPDATE users SET status = ?, role = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setString(2, role);
            ps.setLong(3, userId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(long userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> searchUsers(String searchTerm) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE full_name LIKE ? OR username LIKE ? OR email LIKE ? ORDER BY user_id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + searchTerm + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Account Management
    public boolean createAccount(long userId, String accountType, double initialDeposit) {
        String sql = "UPDATE users SET account_type = ?, deposit = ?, status = 'ACTIVE' WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, accountType);
            ps.setDouble(2, initialDeposit);
            ps.setLong(3, userId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeAccount(long userId) {
        String sql = "UPDATE users SET status = 'CLOSED' WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Loan Management
    public List<User> getLoanApplicants() {
        List<User> loanApplicants = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE status = 'LOAN_PENDING' ORDER BY user_id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                loanApplicants.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loanApplicants;
    }

    // Notification System
    public boolean saveNotification(long userId, String message, String type) {
        String sql = "INSERT INTO notifications (user_id, message, type, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, userId);
            ps.setString(2, message);
            ps.setString(3, type);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Advanced Analytics
    public List<User> getTopCustomers(int limit) {
        List<User> topCustomers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'CUSTOMER' AND status = 'ACTIVE' ORDER BY deposit DESC LIMIT ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                topCustomers.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCustomers;
    }

    public long getNewUsersThisMonth() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'CUSTOMER' AND MONTH(created_at) = MONTH(CURRENT_DATE()) AND YEAR(created_at) = YEAR(CURRENT_DATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getActiveUsersThisMonth() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'CUSTOMER' AND status = 'ACTIVE' AND last_login >= DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getAverageAccountBalance() {
        String sql = "SELECT AVG(deposit) FROM users WHERE role = 'CUSTOMER' AND status = 'ACTIVE'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<User> getInactiveUsers(int daysInactive) {
        List<User> inactiveUsers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'CUSTOMER' AND (last_login IS NULL OR last_login < DATE_SUB(CURRENT_DATE(), INTERVAL ? DAY))";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, daysInactive);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                inactiveUsers.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inactiveUsers;
    }

    // System Health
    public boolean isDatabaseHealthy() {
        String sql = "SELECT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getSystemUptime() {
        // This would typically come from system monitoring
        // For now, return a placeholder value
        return System.currentTimeMillis();
    }

    public List<String> getSystemAlerts() {
        List<String> alerts = new ArrayList<>();
        // Check for various system issues
        if (!isDatabaseHealthy()) {
            alerts.add("Database connection issues detected");
        }
        // Add more system health checks as needed
        return alerts;
    }

    // Audit and Compliance
    public boolean logAdminAction(String adminId, String action, String details) {
        String sql = "INSERT INTO admin_audit_log (admin_id, action, details, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, adminId);
            ps.setString(2, action);
            ps.setString(3, details);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getComplianceReport() {
        List<String> complianceIssues = new ArrayList<>();
        
        // Check for compliance issues
        try (Connection conn = DBConnection.getConnection()) {
            // Check for users without KYC
            String kycSql = "SELECT COUNT(*) FROM users WHERE aadhaar = '000000000000' OR pan = 'ABCDE1234F'";
            try (PreparedStatement ps = conn.prepareStatement(kycSql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getLong(1) > 0) {
                    complianceIssues.add("Users found without proper KYC documentation");
                }
            }
            
            // Check for suspicious transactions
            String suspiciousSql = "SELECT COUNT(*) FROM transactions WHERE amount > 100000";
            try (PreparedStatement ps = conn.prepareStatement(suspiciousSql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getLong(1) > 0) {
                    complianceIssues.add("High-value transactions detected requiring review");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return complianceIssues;
    }

    // Data Export
    public String exportUserData(String format) {
        // This would generate CSV/Excel file
        // For now, return a placeholder
        return "user_export_" + LocalDate.now() + "." + format;
    }

    public String exportTransactionData(String format) {
        return "transaction_export_" + LocalDate.now() + "." + format;
    }

    public String exportFinancialReport(String format) {
        return "financial_report_" + LocalDate.now() + "." + format;
    }

    // Security and Monitoring
    public List<User> getFailedLoginAttempts() {
        List<User> failedAttempts = new ArrayList<>();
        // This would query a login_attempts table
        // For now, return empty list
        return failedAttempts;
    }

    // Customer Support
    public List<User> getCustomersNeedingSupport() {
        List<User> supportNeeded = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'CUSTOMER' AND status IN ('FROZEN', 'BLOCKED') ORDER BY user_id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                supportNeeded.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supportNeeded;
    }

    public boolean updateCustomerNotes(long userId, String notes) {
        String sql = "UPDATE users SET notes = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, notes);
            ps.setLong(2, userId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getCustomerFeedback() {
        List<String> feedback = new ArrayList<>();
        // This would query a feedback table
        // For now, return placeholder feedback
        feedback.add("Customer feedback system not yet implemented");
        return feedback;
    }

    // Helper method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
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
        if (rs.getTimestamp("last_login") != null) {
            user.setLastLogin(rs.getTimestamp("last_login").toLocalDateTime());
        }
        return user;
    }
}
