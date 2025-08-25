package com.banking.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.banking.db.DBConnection;
import com.banking.model.Transaction;

public class TransactionDao {

    // Record a new transaction
    public boolean recordTransaction(Transaction txn) {
        String sql = "INSERT INTO transactions(user_id, type, amount, description, txn_date) VALUES(?,?,?,?,NOW())";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, txn.getUserId());
            ps.setString(2, txn.getType());
            ps.setDouble(3, txn.getAmount());
            ps.setString(4, txn.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Save transaction (for loan processing)
    public boolean saveTransaction(Transaction txn) {
        String sql = "INSERT INTO transactions(user_id, type, amount, description, txn_date) VALUES(?,?,?,?,NOW())";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, txn.getUserId());
            ps.setString(2, txn.getType());
            ps.setDouble(3, txn.getAmount());
            ps.setString(4, txn.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mini Statement - last 5
    public List<Transaction> getMiniStatement(long userId) {
        return getTransactionHistory(userId, 5);
    }

    // Full history with limit
    public List<Transaction> getTransactionHistory(long userId, int limit) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id=? ORDER BY txn_date DESC LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction txn = new Transaction();
                txn.setTxnId(rs.getLong("txn_id"));
                txn.setUserId(rs.getLong("user_id"));
                txn.setType(rs.getString("type"));
                txn.setAmount(rs.getDouble("amount"));
                txn.setDescription(rs.getString("description"));
                txn.setTxnDate(rs.getTimestamp("txn_date").toLocalDateTime());
                list.add(txn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Transaction> getAllTransactions(long userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY txn_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction tx = new Transaction();
                tx.setTxnId(rs.getLong("txn_id"));
                tx.setUserId(rs.getLong("user_id"));
                tx.setType(rs.getString("type"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setDescription(rs.getString("description"));
                tx.setTxnDate(rs.getTimestamp("txn_date").toLocalDateTime());
                transactions.add(tx);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // ✅ Fetch recent N transactions (mini statement)
    public List<Transaction> getRecentTransactions(long userId, int limit) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY timestamp DESC LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction tx = new Transaction();
                tx.setTxnId(rs.getLong("transaction_id"));
                tx.setUserId(rs.getLong("user_id"));
                tx.setType(rs.getString("type"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setDescription(rs.getString("description"));
                tx.setTimestamp(rs.getTimestamp("timestamp"));
                transactions.add(tx);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // ✅ Deposit
    public boolean deposit(long userId, double amount) {
        String sql = "INSERT INTO transactions (user_id, type, amount, description) VALUES (?, 'DEPOSIT', ?, 'Deposit successful')";
        String updateBalance = "UPDATE users SET deposit = deposit + ? WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(sql);
                 PreparedStatement ps2 = conn.prepareStatement(updateBalance)) {

                ps1.setLong(1, userId);
                ps1.setDouble(2, amount);
                ps1.executeUpdate();

                ps2.setDouble(1, amount);
                ps2.setLong(2, userId);
                ps2.executeUpdate();

                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Withdraw
    public boolean withdraw(long userId, double amount) {
        String sql = "INSERT INTO transactions (user_id, type, amount, description) VALUES (?, 'WITHDRAW', ?, 'Withdraw successful')";
        String updateBalance = "UPDATE users SET deposit = deposit - ? WHERE user_id = ? AND deposit >= ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(sql);
                 PreparedStatement ps2 = conn.prepareStatement(updateBalance)) {

                ps2.setDouble(1, amount);
                ps2.setLong(2, userId);
                ps2.setDouble(3, amount);
                int updated = ps2.executeUpdate();

                if (updated == 0) { // insufficient funds
                    conn.rollback();
                    return false;
                }

                ps1.setLong(1, userId);
                ps1.setDouble(2, amount);
                ps1.executeUpdate();

                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Transfer
    public boolean transfer(long fromUserId, long toUserId, double amount) {
        String debitTx = "INSERT INTO transactions (user_id, type, amount, description) VALUES (?, 'TRANSFER_DEBIT', ?, ?)";
        String creditTx = "INSERT INTO transactions (user_id, type, amount, description) VALUES (?, 'TRANSFER_CREDIT', ?, ?)";
        String updateBalance = "UPDATE users SET deposit = deposit + ? WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement debitPS = conn.prepareStatement(debitTx);
                 PreparedStatement creditPS = conn.prepareStatement(creditTx);
                 PreparedStatement updatePS = conn.prepareStatement(updateBalance)) {

                // Deduct from sender
                updatePS.setDouble(1, -amount);
                updatePS.setLong(2, fromUserId);
                int updated = updatePS.executeUpdate();
                if (updated == 0) {
                    conn.rollback(); // insufficient funds
                    return false;
                }

                // Add to receiver
                updatePS.setDouble(1, amount);
                updatePS.setLong(2, toUserId);
                updatePS.executeUpdate();

                // Insert debit transaction
                debitPS.setLong(1, fromUserId);
                debitPS.setDouble(2, amount);
                debitPS.setString(3, "Transferred to user " + toUserId);
                debitPS.executeUpdate();

                // Insert credit transaction
                creditPS.setLong(1, toUserId);
                creditPS.setDouble(2, amount);
                creditPS.setString(3, "Received from user " + fromUserId);
                creditPS.executeUpdate();

                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Admin methods for reporting
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY txn_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transaction> getRecentTransactions(int limit) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY txn_date DESC LIMIT ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public double getMonthlyDeposits() {
        String sql = "SELECT SUM(amount) FROM transactions WHERE type = 'DEPOSIT' AND MONTH(txn_date) = MONTH(CURRENT_DATE()) AND YEAR(txn_date) = YEAR(CURRENT_DATE())";
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

    public double getMonthlyWithdrawals() {
        String sql = "SELECT SUM(amount) FROM transactions WHERE type = 'WITHDRAW' AND MONTH(txn_date) = MONTH(CURRENT_DATE()) AND YEAR(txn_date) = YEAR(CURRENT_DATE())";
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

    public double getTotalDeposits() {
        String sql = "SELECT SUM(amount) FROM transactions WHERE type = 'DEPOSIT'";
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

    public double getTotalWithdrawals() {
        String sql = "SELECT SUM(amount) FROM transactions WHERE type = 'WITHDRAW'";
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

    public double getTotalTransfers() {
        String sql = "SELECT SUM(amount) FROM transactions WHERE type IN ('TRANSFER_DEBIT', 'TRANSFER_CREDIT')";
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

    public List<Transaction> getTransactionsByDateRange(String startDate, String endDate) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE DATE(txn_date) BETWEEN ? AND ? ORDER BY txn_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Advanced reporting methods
    public List<Transaction> getTransactionsByType(String type) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE type = ? ORDER BY txn_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public double getAverageTransactionAmount() {
        String sql = "SELECT AVG(amount) FROM transactions";
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

    public List<Transaction> getHighValueTransactions(double minAmount) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE amount >= ? ORDER BY amount DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDouble(1, minAmount);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public double getDailyTransactionVolume() {
        String sql = "SELECT SUM(amount) FROM transactions WHERE DATE(txn_date) = CURRENT_DATE()";
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

    public List<Transaction> getSuspiciousTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE amount > 100000 OR (type = 'WITHDRAW' AND amount > 50000) ORDER BY txn_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transaction> getUnusualActivity() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE amount > (SELECT AVG(amount) * 3 FROM transactions) ORDER BY txn_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transaction> getAuditTrail(String userId, String startDate, String endDate) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? AND DATE(txn_date) BETWEEN ? AND ? ORDER BY txn_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, userId);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Helper method to map ResultSet to Transaction object
    private Transaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTxnId(rs.getLong("txn_id"));
        transaction.setUserId(rs.getLong("user_id"));
        transaction.setType(rs.getString("type"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setDescription(rs.getString("description"));
        transaction.setTxnDate(rs.getTimestamp("txn_date").toLocalDateTime());
        transaction.setTimestamp(rs.getTimestamp("txn_date"));
        return transaction;
    }

}
