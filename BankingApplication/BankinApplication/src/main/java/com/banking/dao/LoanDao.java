package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banking.db.DBConnection;
import com.banking.model.Loan;

public class LoanDao {

	public long applyLoan(Loan loan) throws SQLException {
		String sql = "INSERT INTO loans (user_id, loan_type, amount, interest_rate, tenure_months, emi_amount, status, created_at) VALUES (?,?,?,?,?,?, 'PENDING', NOW())";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setLong(1, loan.getUserId());
			ps.setString(2, loan.getLoanType());
			ps.setDouble(3, loan.getAmount());
			ps.setDouble(4, loan.getInterestRate());
			ps.setInt(5, loan.getTenureMonths());
			ps.setDouble(6, loan.getMonthlyEMI());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) return rs.getLong(1);
			}
		}
		return -1;
	}

	public boolean updateStatus(long loanId, String status, Long approvedBy) throws SQLException {
		String sql = approvedBy != null
				? "UPDATE loans SET status=?, approved_by=?, approved_at=NOW() WHERE loan_id=?"
				: "UPDATE loans SET status=? WHERE loan_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			if (approvedBy != null) {
				ps.setLong(2, approvedBy);
				ps.setLong(3, loanId);
			} else {
				ps.setLong(2, loanId);
			}
			return ps.executeUpdate() > 0;
		}
	}

	public Loan getById(long loanId) throws SQLException {
		String sql = "SELECT * FROM loans WHERE loan_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, loanId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Loan l = new Loan();
					l.setLoanId(rs.getLong("loan_id"));
					l.setUserId(rs.getLong("user_id"));
					l.setLoanType(rs.getString("loan_type"));
					l.setAmount(rs.getDouble("amount"));
					l.setInterestRate(rs.getDouble("interest_rate"));
					l.setTenureMonths(rs.getInt("tenure_months"));
					l.setMonthlyEMI(rs.getDouble("emi_amount"));
					l.setStatus(rs.getString("status"));
					return l;
				}
			}
		}
		return null;
	}

	public List<Loan> getByUser(long userId) throws SQLException {
		String sql = "SELECT * FROM loans WHERE user_id=? ORDER BY created_at DESC";
		List<Loan> list = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Loan l = new Loan();
					l.setLoanId(rs.getLong("loan_id"));
					l.setUserId(rs.getLong("user_id"));
					l.setLoanType(rs.getString("loan_type"));
					l.setAmount(rs.getDouble("amount"));
					l.setInterestRate(rs.getDouble("interest_rate"));
					l.setTenureMonths(rs.getInt("tenure_months"));
					l.setMonthlyEMI(rs.getDouble("emi_amount"));
					l.setStatus(rs.getString("status"));
					list.add(l);
				}
			}
		}
		return list;
	}
}
