package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.exception.ApplicationException;
import com.tss.model.Loan;

public class LoanDAO {

	public List<Loan> getAllLoans() {
		String sql = "SELECT * FROM loans ORDER BY application_date DESC";
		List<Loan> loans = new ArrayList<>();

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Loan loan = new Loan();
				loan.setLoanId(rs.getInt("loan_id"));
				loan.setUserId(rs.getInt("user_id"));
				loan.setAccountId(rs.getObject("account_id", Integer.class));
				loan.setLoanType(rs.getString("loan_type"));
				loan.setAmountRequested(rs.getDouble("amount_requested"));
				loan.setAmountApproved(rs.getDouble("amount_approved"));
				loan.setInterestRate(rs.getDouble("interest_rate"));
				loan.setTermMonths(rs.getInt("term_months"));
				loan.setApplicationDate(rs.getTimestamp("application_date").toLocalDateTime());
				loan.setApprovalStatus(rs.getString("approval_status"));
				loan.setApprovedBy(rs.getObject("approved_by", Integer.class));
				loan.setDisbursementDate(rs.getObject("disbursement_date", LocalDateTime.class));
				loan.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				loans.add(loan);
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error loading loans", e);
		}
		return loans;
	}

	public Loan getLoanById(int loanId) {
		String sql = "SELECT * FROM loans WHERE loan_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, loanId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Loan loan = new Loan();
					loan.setLoanId(rs.getInt("loan_id"));
					loan.setUserId(rs.getInt("user_id"));
					loan.setAccountId(rs.getObject("account_id", Integer.class));
					loan.setLoanType(rs.getString("loan_type"));
					loan.setAmountRequested(rs.getDouble("amount_requested"));
					loan.setAmountApproved(rs.getDouble("amount_approved"));
					loan.setInterestRate(rs.getDouble("interest_rate"));
					loan.setTermMonths(rs.getInt("term_months"));
					loan.setApplicationDate(rs.getTimestamp("application_date").toLocalDateTime());
					loan.setApprovalStatus(rs.getString("approval_status"));
					loan.setApprovedBy(rs.getObject("approved_by", Integer.class));
					loan.setDisbursementDate(rs.getObject("disbursement_date", LocalDateTime.class));
					loan.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					return loan;
				}
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error loading loan: " + loanId, e);
		}
		return null;
	}

	public void updateLoanStatus(int loanId, String status, Integer approvedBy) {
		String sql = "UPDATE loans SET approval_status = ?, approved_by = ?, "
				+ "disbursement_date = CASE WHEN ? = 'Disbursed' THEN NOW() ELSE disbursement_date END "
				+ "WHERE loan_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setObject(2, approvedBy);
			ps.setString(3, status);
			ps.setInt(4, loanId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ApplicationException("Error updating loan status", e);
		}
	}

	public void insertLoanApplication(Loan loan) {
		String sql = "INSERT INTO loans (user_id, account_id, loan_type, amount_requested, amount_approved, interest_rate, term_months, application_date, approval_status, approved_by, disbursement_date, created_at) "
				+ "VALUES (?, ?, ?, ?, 0.00, ?, ?, NOW(), 'Pending', NULL, NULL, NOW())";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, loan.getUserId());
			if (loan.getAccountId() == null) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, loan.getAccountId());
			}
			ps.setString(3, loan.getLoanType());
			ps.setDouble(4, loan.getAmountRequested());
			ps.setDouble(5, loan.getInterestRate());
			ps.setInt(6, loan.getTermMonths());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ApplicationException("Error submitting loan application", e);
		}
	}
}