package com.banking.service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import com.banking.dao.AdminDao;
import com.banking.dao.UserDao;
import com.banking.dao.TransactionDao;
import com.banking.model.User;
import com.banking.model.Transaction;

public class AdminService {
    private AdminDao adminDao = new AdminDao();
    private UserDao userDao = new UserDao();
    private TransactionDao transactionDao = new TransactionDao();

    // Dashboard Statistics
    public long getTotalCustomers() {
        return adminDao.getTotalCustomers();
    }

    public long getPendingApprovals() {
        return adminDao.getPendingApprovals();
    }

    public long getActiveAccounts() {
        return adminDao.getActiveAccounts();
    }

    public double getTotalBalance() {
        return adminDao.getTotalBalance();
    }

    // User Management
    public List<User> getAllCustomers() {
        return adminDao.getAllCustomers();
    }

    public List<User> getPendingUsers() {
        return adminDao.getPendingUsers();
    }

    public List<User> getRecentUsers(int limit) {
        return adminDao.getRecentUsers(limit);
    }

    public boolean approveUser(long userId) {
        return adminDao.updateUserStatus(userId, "ACTIVE");
    }

    public boolean rejectUser(long userId, String reason) {
        return adminDao.updateUserStatus(userId, "REJECTED");
    }

    public boolean deleteUser(long userId) {
        return adminDao.deleteUser(userId);
    }

    public boolean updateUser(long userId, String status, String role) {
        return adminDao.updateUser(userId, status, role);
    }

    public boolean createUserDirectly(String fullName, String username, String email, 
                                    String phone, String accountType, double initialDeposit) {
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAccountType(accountType);
        user.setDeposit(initialDeposit);
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");
        user.setPasswordHash("default123"); // Admin will set password later
        user.setDob(LocalDateTime.now().toLocalDate().minusYears(25)); // Default DOB
        user.setGender("Other");
        user.setAddress("Address to be updated");
        user.setAadhaar("000000000000");
        user.setPan("ABCDE1234F");
        
        return userDao.saveUser(user);
    }

    // Transaction Management
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    public List<Transaction> getRecentTransactions(int limit) {
        return transactionDao.getRecentTransactions(limit);
    }

    public double getMonthlyDeposits() {
        return transactionDao.getMonthlyDeposits();
    }

    public double getMonthlyWithdrawals() {
        return transactionDao.getMonthlyWithdrawals();
    }

    public double getTotalDeposits() {
        return transactionDao.getTotalDeposits();
    }

    public double getTotalWithdrawals() {
        return transactionDao.getTotalWithdrawals();
    }

    public double getTotalTransfers() {
        return transactionDao.getTotalTransfers();
    }

    public List<Transaction> getTransactionsByDateRange(String startDate, String endDate) {
        return transactionDao.getTransactionsByDateRange(startDate, endDate);
    }

    // User Details
    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    public List<Transaction> getUserTransactions(long userId) {
        return transactionDao.getTransactionHistory(userId, 50);
    }

    public List<User> getUsersByStatus(String status) {
        return adminDao.getUsersByStatus(status);
    }

    public List<User> searchUsers(String searchTerm) {
        return adminDao.searchUsers(searchTerm);
    }

    // Account Management
    public boolean createAccount(long userId, String accountType, double initialDeposit) {
        return adminDao.createAccount(userId, accountType, initialDeposit);
    }

    public boolean closeAccount(long userId) {
        return adminDao.closeAccount(userId);
    }

    public boolean freezeAccount(long userId) {
        return adminDao.updateUserStatus(userId, "FROZEN");
    }

    public boolean unfreezeAccount(long userId) {
        return adminDao.updateUserStatus(userId, "ACTIVE");
    }

    // Loan Management
    public List<User> getLoanApplicants() {
        return adminDao.getLoanApplicants();
    }

    public boolean processLoan(long userId, String action, double amount, String reason) {
        if ("approve".equalsIgnoreCase(action)) {
            // Create loan transaction
            Transaction loanTransaction = new Transaction();
            loanTransaction.setUserId(userId);
            loanTransaction.setType("LOAN_CREDIT");
            loanTransaction.setAmount(amount);
            loanTransaction.setDescription("Loan approved: " + reason);
            loanTransaction.setTxnDate(LocalDateTime.now());
            
            // Add to user's balance
            double currentBalance = userDao.getBalance(userId);
            userDao.updateBalance(userId, currentBalance + amount);
            
            // Save transaction
            return transactionDao.saveTransaction(loanTransaction);
        } else if ("reject".equalsIgnoreCase(action)) {
            // Just update user status or add rejection note
            return adminDao.updateUserStatus(userId, "LOAN_REJECTED");
        }
        return false;
    }

    // Notification System
    public int sendNotification(String[] userIds, String message, String type) {
        int sentCount = 0;
        if (userIds != null) {
            for (String userIdStr : userIds) {
                try {
                    long userId = Long.parseLong(userIdStr);
                    boolean sent = adminDao.saveNotification(userId, message, type);
                    if (sent) sentCount++;
                } catch (NumberFormatException e) {
                    // Skip invalid user IDs
                }
            }
        }
        return sentCount;
    }

    // Advanced Reporting
    public List<Transaction> getTransactionsByType(String type) {
        return transactionDao.getTransactionsByType(type);
    }

    public double getAverageTransactionAmount() {
        return transactionDao.getAverageTransactionAmount();
    }

    public List<User> getTopCustomers(int limit) {
        return adminDao.getTopCustomers(limit);
    }

    public List<Transaction> getHighValueTransactions(double minAmount) {
        return transactionDao.getHighValueTransactions(minAmount);
    }

    public double getDailyTransactionVolume() {
        return transactionDao.getDailyTransactionVolume();
    }

    public List<Transaction> getSuspiciousTransactions() {
        return transactionDao.getSuspiciousTransactions();
    }

    // User Analytics
    public long getNewUsersThisMonth() {
        return adminDao.getNewUsersThisMonth();
    }

    public long getActiveUsersThisMonth() {
        return adminDao.getActiveUsersThisMonth();
    }

    public double getAverageAccountBalance() {
        return adminDao.getAverageAccountBalance();
    }

    public List<User> getInactiveUsers(int daysInactive) {
        return adminDao.getInactiveUsers(daysInactive);
    }

    // System Health
    public boolean isDatabaseHealthy() {
        return adminDao.isDatabaseHealthy();
    }

    public long getSystemUptime() {
        return adminDao.getSystemUptime();
    }

    public List<String> getSystemAlerts() {
        return adminDao.getSystemAlerts();
    }

    // Audit and Compliance
    public List<Transaction> getAuditTrail(String userId, String startDate, String endDate) {
        return transactionDao.getAuditTrail(userId, startDate, endDate);
    }

    public boolean logAdminAction(String adminId, String action, String details) {
        return adminDao.logAdminAction(adminId, action, details);
    }

    public List<String> getComplianceReport() {
        return adminDao.getComplianceReport();
    }

    // Data Export
    public String exportUserData(String format) {
        return adminDao.exportUserData(format);
    }

    public String exportTransactionData(String format) {
        return adminDao.exportTransactionData(format);
    }

    public String exportFinancialReport(String format) {
        return adminDao.exportFinancialReport(format);
    }

    // Bulk Operations
    public int bulkUpdateUserStatus(List<Long> userIds, String status) {
        int updatedCount = 0;
        for (Long userId : userIds) {
            if (adminDao.updateUserStatus(userId, status)) {
                updatedCount++;
            }
        }
        return updatedCount;
    }

    public int bulkSendNotifications(List<Long> userIds, String message) {
        int sentCount = 0;
        for (Long userId : userIds) {
            if (adminDao.saveNotification(userId, message, "EMAIL")) {
                sentCount++;
            }
        }
        return sentCount;
    }

    // Security and Monitoring
    public List<User> getFailedLoginAttempts() {
        return adminDao.getFailedLoginAttempts();
    }

    public boolean blockUser(long userId, String reason) {
        return adminDao.updateUserStatus(userId, "BLOCKED");
    }

    public boolean unblockUser(long userId) {
        return adminDao.updateUserStatus(userId, "ACTIVE");
    }

    public List<Transaction> getUnusualActivity() {
        return transactionDao.getUnusualActivity();
    }

    // Customer Support
    public List<User> getCustomersNeedingSupport() {
        return adminDao.getCustomersNeedingSupport();
    }

    public boolean updateCustomerNotes(long userId, String notes) {
        return adminDao.updateCustomerNotes(userId, notes);
    }

    public List<String> getCustomerFeedback() {
        return adminDao.getCustomerFeedback();
    }
}
