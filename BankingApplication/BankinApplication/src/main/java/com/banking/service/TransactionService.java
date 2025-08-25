package com.banking.service;

import java.util.List;

import com.banking.dao.TransactionDao;
import com.banking.dao.UserDao;
import com.banking.dao.AuditLogDao;
import com.banking.model.Transaction;

public class TransactionService {
    private TransactionDao txnDao = new TransactionDao();
    private UserDao userDao = new UserDao();
    private AuditLogDao auditLogDao = new AuditLogDao();

    // Deposit
    public boolean deposit(long userId, double amount) {
        if (amount <= 0) return false;
        double before = userDao.getBalance(userId);
        boolean success = userDao.deposit(userId, amount);
        if (success) {
            txnDao.recordTransaction(new Transaction() {{
                setUserId(userId);
                setType("DEPOSIT");
                setAmount(amount);
                setDescription("Deposit successful");
            }});
            double after = userDao.getBalance(userId);
            auditLogDao.log(userId, "DEPOSIT", "users", userId, "{\"deposit\":"+before+"}", "{\"deposit\":"+after+"}", null, null);
        }
        return success;
    }

    // Withdraw
    public boolean withdraw(long userId, double amount) {
        if (amount <= 0) return false;
        double before = userDao.getBalance(userId);
        boolean success = userDao.withdraw(userId, amount);
        if (success) {
            txnDao.recordTransaction(new Transaction() {{
                setUserId(userId);
                setType("WITHDRAW");
                setAmount(amount);
                setDescription("Withdrawal successful");
            }});
            double after = userDao.getBalance(userId);
            auditLogDao.log(userId, "WITHDRAW", "users", userId, "{\"deposit\":"+before+"}", "{\"deposit\":"+after+"}", null, null);
        }
        return success;
    }

    // Transfer
    public boolean transfer(long fromUserId, long toUserId, double amount) {
        if (amount <= 0) return false;
        double beforeFrom = userDao.getBalance(fromUserId);
        double beforeTo = userDao.getBalance(toUserId);
        boolean success = userDao.transfer(fromUserId, toUserId, amount);
        if (success) {
            txnDao.recordTransaction(new Transaction() {{
                setUserId(fromUserId);
                setType("TRANSFER_DEBIT");
                setAmount(amount);
                setDescription("Transferred to user "+toUserId);
            }});
            txnDao.recordTransaction(new Transaction() {{
                setUserId(toUserId);
                setType("TRANSFER_CREDIT");
                setAmount(amount);
                setDescription("Received from user "+fromUserId);
            }});
            double afterFrom = userDao.getBalance(fromUserId);
            double afterTo = userDao.getBalance(toUserId);
            auditLogDao.log(fromUserId, "TRANSFER_DEBIT", "users", fromUserId, "{\"deposit\":"+beforeFrom+"}", "{\"deposit\":"+afterFrom+"}", null, null);
            auditLogDao.log(toUserId, "TRANSFER_CREDIT", "users", toUserId, "{\"deposit\":"+beforeTo+"}", "{\"deposit\":"+afterTo+"}", null, null);
        }
        return success;
    }

    // Mini statement
    public List<Transaction> getMiniStatement(long userId) {
        return txnDao.getMiniStatement(userId);
    }

    // Full history
    public List<Transaction> getTransactionHistory(long userId, int limit) {
        return txnDao.getTransactionHistory(userId, limit);
    }

    // Reporting helpers
    public List<Transaction> getTransactionsByType(String type) {
        return txnDao.getTransactionsByType(type);
    }

    public double getAverageTransactionAmount() {
        return txnDao.getAverageTransactionAmount();
    }
}
