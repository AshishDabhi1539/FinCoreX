package com.banking.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.banking.db.DBConnection;
import com.banking.model.Account;
import com.banking.exception.BankingException;

public class AccountDao {
    
    public boolean createAccount(Account account) throws BankingException {
        String sql = "INSERT INTO accounts (user_id, account_number, account_type, balance, status, created_at, updated_at, " +
                    "branch_code, ifsc_code, interest_rate, daily_limit, monthly_limit, currency) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setLong(1, account.getUserId());
            pstmt.setString(2, account.getAccountNumber());
            pstmt.setString(3, account.getAccountType());
            pstmt.setDouble(4, account.getBalance());
            pstmt.setString(5, account.getStatus());
            pstmt.setTimestamp(6, Timestamp.valueOf(account.getCreatedAt()));
            pstmt.setTimestamp(7, Timestamp.valueOf(account.getUpdatedAt()));
            pstmt.setString(8, account.getBranchCode());
            pstmt.setString(9, account.getIfscCode());
            pstmt.setDouble(10, account.getInterestRate());
            pstmt.setDouble(11, account.getDailyLimit());
            pstmt.setDouble(12, account.getMonthlyLimit());
            pstmt.setString(13, account.getCurrency());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        account.setAccountId(rs.getLong(1));
                        return true;
                    }
                }
            }
            return false;
            
        } catch (SQLException e) {
            throw new BankingException("Error creating account: " + e.getMessage(), "ACC_CREATE_001", e);
        }
    }
    
    public Optional<Account> getAccountById(Long accountId) throws BankingException {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, accountId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToAccount(rs));
                }
            }
            return Optional.empty();
            
        } catch (SQLException e) {
            throw new BankingException("Error retrieving account: " + e.getMessage(), "ACC_GET_001", e);
        }
    }
    
    public Optional<Account> getAccountByNumber(String accountNumber) throws BankingException {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, accountNumber);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToAccount(rs));
                }
            }
            return Optional.empty();
            
        } catch (SQLException e) {
            throw new BankingException("Error retrieving account by number: " + e.getMessage(), "ACC_GET_002", e);
        }
    }
    
    public List<Account> getAccountsByUserId(Long userId) throws BankingException {
        String sql = "SELECT * FROM accounts WHERE user_id = ? ORDER BY created_at DESC";
        List<Account> accounts = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    accounts.add(mapResultSetToAccount(rs));
                }
            }
            return accounts;
            
        } catch (SQLException e) {
            throw new BankingException("Error retrieving user accounts: " + e.getMessage(), "ACC_GET_003", e);
        }
    }
    
    public List<Account> getAllAccounts() throws BankingException {
        String sql = "SELECT * FROM accounts ORDER BY created_at DESC";
        List<Account> accounts = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                accounts.add(mapResultSetToAccount(rs));
            }
            return accounts;
            
        } catch (SQLException e) {
            throw new BankingException("Error retrieving all accounts: " + e.getMessage(), "ACC_GET_004", e);
        }
    }
    
    public List<Account> getAccountsByStatus(String status) throws BankingException {
        String sql = "SELECT * FROM accounts WHERE status = ? ORDER BY created_at DESC";
        List<Account> accounts = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    accounts.add(mapResultSetToAccount(rs));
                }
            }
            return accounts;
            
        } catch (SQLException e) {
            throw new BankingException("Error retrieving accounts by status: " + e.getMessage(), "ACC_GET_005", e);
        }
    }
    
    public boolean updateAccount(Account account) throws BankingException {
        String sql = "UPDATE accounts SET balance = ?, status = ?, updated_at = ?, last_transaction_date = ?, " +
                    "transaction_count = ?, daily_limit = ?, monthly_limit = ? WHERE account_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, account.getBalance());
            pstmt.setString(2, account.getStatus());
            pstmt.setTimestamp(3, Timestamp.valueOf(account.getUpdatedAt()));
            pstmt.setTimestamp(4, account.getLastTransactionDate() != null ? 
                              Timestamp.valueOf(account.getLastTransactionDate()) : null);
            pstmt.setInt(5, account.getTransactionCount());
            pstmt.setDouble(6, account.getDailyLimit());
            pstmt.setDouble(7, account.getMonthlyLimit());
            pstmt.setLong(8, account.getAccountId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new BankingException("Error updating account: " + e.getMessage(), "ACC_UPDATE_001", e);
        }
    }
    
    public boolean updateAccountStatus(Long accountId, String status) throws BankingException {
        String sql = "UPDATE accounts SET status = ?, updated_at = ? WHERE account_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setTimestamp(2, Timestamp.valueOf(java.time.LocalDateTime.now()));
            pstmt.setLong(3, accountId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new BankingException("Error updating account status: " + e.getMessage(), "ACC_UPDATE_002", e);
        }
    }
    
    public boolean updateAccountBalance(Long accountId, double newBalance) throws BankingException {
        String sql = "UPDATE accounts SET balance = ?, updated_at = ?, last_transaction_date = ? WHERE account_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, newBalance);
            pstmt.setTimestamp(2, Timestamp.valueOf(java.time.LocalDateTime.now()));
            pstmt.setTimestamp(3, Timestamp.valueOf(java.time.LocalDateTime.now()));
            pstmt.setLong(4, accountId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new BankingException("Error updating account balance: " + e.getMessage(), "ACC_UPDATE_003", e);
        }
    }
    
    public boolean deleteAccount(Long accountId) throws BankingException {
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, accountId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new BankingException("Error deleting account: " + e.getMessage(), "ACC_DELETE_001", e);
        }
    }
    
    public double getTotalBalance() throws BankingException {
        String sql = "SELECT SUM(balance) as total_balance FROM accounts WHERE status = 'ACTIVE'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getDouble("total_balance");
            }
            return 0.0;
            
        } catch (SQLException e) {
            throw new BankingException("Error calculating total balance: " + e.getMessage(), "ACC_CALC_001", e);
        }
    }
    
    public long getTotalAccounts() throws BankingException {
        String sql = "SELECT COUNT(*) as total_accounts FROM accounts";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong("total_accounts");
            }
            return 0;
            
        } catch (SQLException e) {
            throw new BankingException("Error counting accounts: " + e.getMessage(), "ACC_COUNT_001", e);
        }
    }
    
    public boolean isAccountNumberExists(String accountNumber) throws BankingException {
        String sql = "SELECT COUNT(*) as count FROM accounts WHERE account_number = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, accountNumber);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
            return false;
            
        } catch (SQLException e) {
            throw new BankingException("Error checking account number existence: " + e.getMessage(), "ACC_CHECK_001", e);
        }
    }
    
    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getLong("account_id"));
        account.setUserId(rs.getLong("user_id"));
        account.setAccountNumber(rs.getString("account_number"));
        account.setAccountType(rs.getString("account_type"));
        account.setBalance(rs.getDouble("balance"));
        account.setStatus(rs.getString("status"));
        account.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        account.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        account.setBranchCode(rs.getString("branch_code"));
        account.setIfscCode(rs.getString("ifsc_code"));
        account.setInterestRate(rs.getDouble("interest_rate"));
        account.setDailyLimit(rs.getDouble("daily_limit"));
        account.setMonthlyLimit(rs.getDouble("monthly_limit"));
        account.setCurrency(rs.getString("currency"));
        
        Timestamp lastTransactionDate = rs.getTimestamp("last_transaction_date");
        if (lastTransactionDate != null) {
            account.setLastTransactionDate(lastTransactionDate.toLocalDateTime());
        }
        
        account.setTransactionCount(rs.getInt("transaction_count"));
        account.setJointAccount(rs.getBoolean("is_joint_account"));
        account.setJointAccountHolder(rs.getString("joint_account_holder"));
        
        return account;
    }
}

