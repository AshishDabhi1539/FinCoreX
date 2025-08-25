package com.banking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.banking.dao.LoanDao;
import com.banking.dao.TransactionDao;
import com.banking.dao.UserDao;
import com.banking.db.DBConnection;
import com.banking.model.Loan;
import com.banking.model.Transaction;

public class LoanService {

	private final LoanDao loanDao = new LoanDao();
	private final UserDao userDao = new UserDao();
	private final TransactionDao txnDao = new TransactionDao();

	public long applyLoan(long userId, String loanType, double amount, double interestRate, int tenureMonths) throws SQLException {
		Loan loan = new Loan();
		loan.setUserId(userId);
		loan.setLoanType(loanType);
		loan.setAmount(amount);
		loan.setInterestRate(interestRate);
		loan.setTenureMonths(tenureMonths);
		// simple EMI calc: (P*r*(1+r)^n)/((1+r)^n-1) with monthly rate
		double r = interestRate / 12.0 / 100.0;
		double emi = r == 0 ? amount / tenureMonths : (amount * r * Math.pow(1 + r, tenureMonths)) / (Math.pow(1 + r, tenureMonths) - 1);
		loan.setMonthlyEMI(Math.round(emi * 100.0) / 100.0);
		return loanDao.applyLoan(loan);
	}

	public boolean approveLoan(long adminUserId, long loanId) throws SQLException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			// mark approved
			loanDao.updateStatus(loanId, "APPROVED", adminUserId);

			// fetch loan
			Loan loan = loanDao.getById(loanId);
			if (loan == null) throw new SQLException("Loan not found");

			// credit to user deposit and record transaction
			double current = userDao.getBalance(loan.getUserId());
			userDao.updateBalance(loan.getUserId(), current + loan.getAmount());

			Transaction t = new Transaction();
			t.setUserId(loan.getUserId());
			t.setType("LOAN_CREDIT");
			t.setAmount(loan.getAmount());
			t.setDescription("Loan disbursed for loan #" + loanId);
			txnDao.saveTransaction(t);

			conn.commit();
			return true;
		} catch (SQLException ex) {
			if (conn != null) conn.rollback();
			throw ex;
		} finally {
			if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
		}
	}

	public boolean rejectLoan(long adminUserId, long loanId) throws SQLException {
		return loanDao.updateStatus(loanId, "REJECTED", adminUserId);
	}

	public boolean repay(long userId, long loanId, double amount) throws SQLException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			// debit from user deposit
			double bal = userDao.getBalance(userId);
			if (bal < amount) throw new SQLException("Insufficient funds");
			userDao.updateBalance(userId, bal - amount);

			Transaction t = new Transaction();
			t.setUserId(userId);
			t.setType("LOAN_REPAYMENT");
			t.setAmount(amount);
			t.setDescription("Loan repayment for loan #" + loanId);
			txnDao.saveTransaction(t);

			conn.commit();
			return true;
		} catch (SQLException ex) {
			if (conn != null) conn.rollback();
			throw ex;
		} finally {
			if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
		}
	}
}
