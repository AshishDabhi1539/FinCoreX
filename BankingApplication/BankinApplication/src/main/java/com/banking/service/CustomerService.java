package com.banking.service;

import java.util.List;

import com.banking.dao.AccountDao;
import com.banking.dao.TransactionDao;
import com.banking.dao.TransferDao;
import com.banking.dao.UserDao;
import com.banking.exception.BankingException;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.util.ValidationUtil;

public class CustomerService {
	private AccountDao accountDao = new AccountDao();
	private TransactionDao transactionDao = new TransactionDao();
	private TransferDao transferDao = new TransferDao();
	private UserDao userDao = new UserDao();

	public String deposit(long accountId, double amount) {
		try {
			Account account = accountDao.getAccountById(accountId)
					.orElseThrow(() -> new BankingException("Account not found", "ACC_NOT_FOUND"));
			if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {
				return "❌ Your account is not active. Current status: " + account.getStatus();
			}
			if (!ValidationUtil.isValidAmount(amount)) {
				return "❌ Invalid deposit amount. Amount must be greater than 0.";
			}
			if (amount > 100000) {
				return "❌ Deposit amount exceeds the maximum limit of ₹100,000.";
			}
			boolean success = transactionDao.deposit(accountId, amount);
			if (success) {
				Account updatedAccount = accountDao.getAccountById(accountId).orElseThrow();
				return "✅ Deposit successful! Amount: ₹" + String.format("%.2f", amount)
						+ " has been credited to your account. New balance: ₹"
						+ String.format("%.2f", updatedAccount.getBalance());
			}
			return "❌ Deposit failed. Please try again later.";
		} catch (BankingException e) {
			return "❌ " + e.getMessage();
		}
	}

	public String withdraw(long accountId, double amount) {
		try {
			Account account = accountDao.getAccountById(accountId)
					.orElseThrow(() -> new BankingException("Account not found", "ACC_NOT_FOUND"));
			if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {
				return "❌ Your account is not active. Current status: " + account.getStatus();
			}
			if (!ValidationUtil.isValidAmount(amount)) {
				return "❌ Invalid withdrawal amount. Amount must be greater than 0.";
			}
			if (amount > 50000) {
				return "❌ Withdrawal amount exceeds the daily limit of ₹50,000.";
			}
			if (!account.hasSufficientBalance(amount)) {
				return "❌ Insufficient balance. Current balance: ₹" + String.format("%.2f", account.getBalance());
			}
			boolean success = transactionDao.withdraw(accountId, amount);
			if (success) {
				Account updatedAccount = accountDao.getAccountById(accountId).orElseThrow();
				return "✅ Withdrawal successful! Amount: ₹" + String.format("%.2f", amount)
						+ " has been debited from your account. New balance: ₹"
						+ String.format("%.2f", updatedAccount.getBalance());
			}
			return "❌ Withdrawal failed. Please try again later.";
		} catch (BankingException e) {
			return "❌ " + e.getMessage();
		}
	}

	public String transfer(long fromAccountId, long toAccountId, long fromUserId, long toUserId, double amount) {
		try {
			Account fromAccount = accountDao.getAccountById(fromAccountId)
					.orElseThrow(() -> new BankingException("Sender account not found", "ACC_NOT_FOUND"));
			Account toAccount = accountDao.getAccountById(toAccountId)
					.orElseThrow(() -> new BankingException("Recipient account not found", "ACC_NOT_FOUND"));
			if (!"ACTIVE".equalsIgnoreCase(fromAccount.getStatus())) {
				return "❌ Your account is not active. Current status: " + fromAccount.getStatus();
			}
			if (!"ACTIVE".equalsIgnoreCase(toAccount.getStatus())) {
				return "❌ Recipient account is not active. Current status: " + toAccount.getStatus();
			}
			if (!ValidationUtil.isValidAmount(amount)) {
				return "❌ Invalid transfer amount. Amount must be greater than 0.";
			}
			if (amount > 100000) {
				return "❌ Transfer amount exceeds the maximum limit of ₹100,000.";
			}
			if (!fromAccount.hasSufficientBalance(amount)) {
				return "❌ Insufficient balance. Current balance: ₹" + String.format("%.2f", fromAccount.getBalance());
			}
			boolean success = transactionDao.transfer(fromAccountId, toAccountId, amount);
			if (success) {
				transferDao.save(fromUserId, toUserId, fromAccountId, toAccountId, amount, "Transfer successful",
						"COMPLETED");
				Account updatedAccount = accountDao.getAccountById(fromAccountId).orElseThrow();
				return "✅ Transfer successful! Amount: ₹" + String.format("%.2f", amount)
						+ " has been transferred. New balance: ₹" + String.format("%.2f", updatedAccount.getBalance());
			}
			transferDao.save(fromUserId, toUserId, fromAccountId, toAccountId, amount, "Transfer failed", "FAILED");
			return "❌ Transfer failed. Please try again later.";
		} catch (BankingException e) {
			try {
				transferDao.save(fromUserId, toUserId, fromAccountId, toAccountId, amount,
						"Transfer failed: " + e.getMessage(), "FAILED");
			} catch (BankingException ignored) {
			}
			return "❌ " + e.getMessage();
		}
	}

	public List<Transaction> getTransactionHistory(long userId) throws BankingException {
		return transactionDao.getTransactionHistory(userId);
	}

	public List<Transaction> getMiniStatement(long userId) throws BankingException {
		return transactionDao.getMiniStatement(userId);
	}

	public String updateNotificationPreferences(long userId, boolean email, boolean sms, boolean whatsapp) {
		try {
			boolean success = userDao.updateNotificationPreferences(userId, email, sms, whatsapp);
			return success ? "✅ Notification preferences updated successfully!"
					: "❌ Failed to update notification preferences.";
		} catch (BankingException e) {
			return "❌ " + e.getMessage();
		}
	}

	public String updateProfile(User user) {
		try {
			if (!ValidationUtil.isValidEmail(user.getEmail()) || !ValidationUtil.isValidPhone(user.getPhone())
					|| !ValidationUtil.isValidAddress(user.getAddress())) {
				return "❌ Invalid profile details. Please check email, phone, and address.";
			}
			boolean success = userDao.updateUserProfile(user);
			return success ? "✅ Profile updated successfully!" : "❌ Failed to update profile.";
		} catch (BankingException e) {
			return "❌ " + e.getMessage();
		}
	}
}