package com.banking.service;

import java.util.List;

import com.banking.dao.TransactionDao;
import com.banking.dao.UserDao;
import com.banking.model.Transaction;

public class TransactionService {
    private TransactionDao txnDao = new TransactionDao();
    private UserDao userDao = new UserDao();

    // Deposit
    public boolean deposit(long userId, double amount) {
        if (amount <= 0) return false;
        boolean success = userDao.deposit(userId, amount);
        if (success) {
            txnDao.recordTransaction(new Transaction() {{
                setUserId(userId);
                setType("DEPOSIT");
                setAmount(amount);
                setDescription("Deposit successful");
            }});
        }
        return success;
    }

    // Withdraw
    public boolean withdraw(long userId, double amount) {
        if (amount <= 0) return false;
        boolean success = userDao.withdraw(userId, amount);
        if (success) {
            txnDao.recordTransaction(new Transaction() {{
                setUserId(userId);
                setType("WITHDRAW");
                setAmount(amount);
                setDescription("Withdrawal successful");
            }});
        }
        return success;
    }

    // Transfer
    public boolean transfer(long fromUserId, long toUserId, double amount) {
        if (amount <= 0 || fromUserId == toUserId) return false;
        boolean success = userDao.transfer(fromUserId, toUserId, amount);
        if (success) {
            txnDao.recordTransaction(new Transaction() {{
                setUserId(fromUserId);
                setType("TRANSFER_OUT");
                setAmount(amount);
                setDescription("Transfer to User " + toUserId);
            }});
            txnDao.recordTransaction(new Transaction() {{
                setUserId(toUserId);
                setType("TRANSFER_IN");
                setAmount(amount);
                setDescription("Transfer from User " + fromUserId);
            }});
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
}
