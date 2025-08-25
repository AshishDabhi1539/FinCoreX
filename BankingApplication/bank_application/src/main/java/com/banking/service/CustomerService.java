package com.banking.service;

import java.util.List;

import com.banking.dao.TransactionDao;
import com.banking.dao.UserDao;
import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.util.ValidationUtil;

public class CustomerService {
    private UserDao userDao = new UserDao();
    private TransactionDao transactionDao = new TransactionDao();

    // 1. Account Details
    public User getAccountDetails(long userId) {
        return userDao.getUserById(userId);
    }

    // 2. Deposit
    public String deposit(long userId, double amount) {
        // Check if user exists and is active
        User user = userDao.getUserById(userId);
        if (user == null) {
            return "❌ Account not found. Please contact customer support.";
        }
        
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            return "❌ Your account is not active. Current status: " + user.getStatus() + ". Please contact customer support.";
        }

        if (!ValidationUtil.isValidAmount(amount)) {
            return "❌ Invalid deposit amount. Amount must be greater than 0.";
        }

        if (amount > 100000) {
            return "❌ Deposit amount exceeds the maximum limit of ₹100,000. Please contact customer support for large deposits.";
        }

        boolean success = transactionDao.deposit(userId, amount);
        if (success) {
            double newBalance = userDao.getBalance(userId);
            return "✅ Deposit successful! Amount: ₹" + String.format("%.2f", amount) + " has been credited to your account. New balance: ₹" + String.format("%.2f", newBalance);
        } else {
            return "❌ Deposit failed. Please try again later or contact customer support if the problem persists.";
        }
    }

    // 3. Withdraw
    public String withdraw(long userId, double amount) {
        // Check if user exists and is active
        User user = userDao.getUserById(userId);
        if (user == null) {
            return "❌ Account not found. Please contact customer support.";
        }
        
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            return "❌ Your account is not active. Current status: " + user.getStatus() + ". Please contact customer support.";
        }

        if (!ValidationUtil.isValidAmount(amount)) {
            return "❌ Invalid withdrawal amount. Amount must be greater than 0.";
        }

        if (amount > 50000) {
            return "❌ Withdrawal amount exceeds the daily limit of ₹50,000. Please contact customer support for large withdrawals.";
        }

        double balance = userDao.getBalance(userId);
        if (balance < amount) {
            return "❌ Insufficient balance. Available balance: ₹" + String.format("%.2f", balance) + ", Requested amount: ₹" + String.format("%.2f", amount);
        }

        boolean success = transactionDao.withdraw(userId, amount);
        if (success) {
            double newBalance = userDao.getBalance(userId);
            return "✅ Withdrawal successful! Amount: ₹" + String.format("%.2f", amount) + " has been debited from your account. New balance: ₹" + String.format("%.2f", newBalance);
        } else {
            return "❌ Withdrawal failed. Please try again later or contact customer support if the problem persists.";
        }
    }

    // 4. Transfer
    public String transfer(long fromUserId, long toUserId, double amount) {
        // Check if sender exists and is active
        User sender = userDao.getUserById(fromUserId);
        if (sender == null) {
            return "❌ Your account not found. Please contact customer support.";
        }
        
        if (!"ACTIVE".equalsIgnoreCase(sender.getStatus())) {
            return "❌ Your account is not active. Current status: " + sender.getStatus() + ". Please contact customer support.";
        }

        // Check if recipient exists and is active
        User recipient = userDao.getUserById(toUserId);
        if (recipient == null) {
            return "❌ Recipient account not found. Please verify the account number and try again.";
        }
        
        if (!"ACTIVE".equalsIgnoreCase(recipient.getStatus())) {
            return "❌ Recipient account is not active. Current status: " + recipient.getStatus() + ". Please contact the recipient.";
        }

        if (!ValidationUtil.isValidAmount(amount)) {
            return "❌ Invalid transfer amount. Amount must be greater than 0.";
        }

        if (amount > 100000) {
            return "❌ Transfer amount exceeds the maximum limit of ₹100,000. Please contact customer support for large transfers.";
        }

        if (fromUserId == toUserId) {
            return "❌ Cannot transfer to the same account. Please enter a different recipient account number.";
        }

        double balance = userDao.getBalance(fromUserId);
        if (balance < amount) {
            return "❌ Insufficient balance. Available balance: ₹" + String.format("%.2f", balance) + ", Transfer amount: ₹" + String.format("%.2f", amount);
        }

        boolean success = transactionDao.transfer(fromUserId, toUserId, amount);
        if (success) {
            double newBalance = userDao.getBalance(fromUserId);
            return "✅ Transfer successful! Amount: ₹" + String.format("%.2f", amount) + " has been transferred to account " + toUserId + ". Your new balance: ₹" + String.format("%.2f", newBalance);
        } else {
            return "❌ Transfer failed. Please try again later or contact customer support if the problem persists.";
        }
    }

    // 5. Mini Statement (last 5 transactions)
    public List<Transaction> getMiniStatement(long userId) {
        return transactionDao.getMiniStatement(userId);
    }

    // 6. Full Transaction History
    public List<Transaction> getTransactionHistory(long userId) {
        return transactionDao.getTransactionHistory(userId, 50);
    }

    // 7. Update Profile
    public String updateProfile(User user) {
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            return "❌ Full name is required.";
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "❌ Email is required.";
        }
        
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            return "❌ Phone number is required.";
        }
        
        if (user.getAddress() == null || user.getAddress().trim().isEmpty()) {
            return "❌ Address is required.";
        }

        boolean success = userDao.updateUserProfile(user);
        if (success) {
            return "✅ Profile updated successfully! Your changes have been saved.";
        } else {
            return "❌ Profile update failed. Please try again later or contact customer support.";
        }
    }

    // 8. Get Balance
    public String getBalance(long userId) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            return "❌ Account not found.";
        }
        
        double balance = userDao.getBalance(userId);
        return "💰 Current Balance: ₹" + String.format("%.2f", balance);
    }

    // 9. Check Account Status
    public String getAccountStatus(long userId) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            return "❌ Account not found.";
        }
        
        String status = user.getStatus();
        switch (status) {
            case "ACTIVE":
                return "✅ Your account is active and ready for transactions.";
            case "PENDING":
                return "⏳ Your account is pending admin approval.";
            case "REJECTED":
                return "❌ Your account registration was rejected.";
            case "SUSPENDED":
                return "🚫 Your account has been suspended.";
            case "BLOCKED":
                return "🚫 Your account has been blocked.";
            case "INACTIVE":
                return "⏸️ Your account is inactive.";
            default:
                return "⚠️ Unknown account status: " + status;
        }
    }

    // 10. Update Notification Preferences
    public boolean updateNotificationPreferences(long userId,
                                                 boolean emailNotifications,
                                                 boolean smsNotifications,
                                                 boolean transactionAlerts,
                                                 boolean balanceAlerts,
                                                 boolean promotionalEmails) {
        return userDao.updateNotificationPreferences(userId,
                emailNotifications,
                smsNotifications,
                transactionAlerts,
                balanceAlerts,
                promotionalEmails);
    }
}
