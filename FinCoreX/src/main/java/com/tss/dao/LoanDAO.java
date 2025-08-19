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
		String sql = "UPDATE loans SET approval_status = ?, approved_by = ?, disbursement_date = CASE WHEN ? = 'Disbursed' THEN NOW() ELSE NULL END WHERE loan_id = ?";
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
}