package com.tss.service;

import java.util.List;

import com.tss.dao.LoanDAO;
import com.tss.exception.ApplicationException;
import com.tss.model.Loan;

public class LoanService {
	private final LoanDAO loanDAO = new LoanDAO();

	public List<Loan> getAllLoans() {
		return loanDAO.getAllLoans();
	}

	public Loan getLoanById(int loanId) {
		Loan loan = loanDAO.getLoanById(loanId);
		if (loan == null) {
			throw new ApplicationException("Loan not found: " + loanId);
		}
		return loan;
	}

	public void approveLoan(int loanId, int adminId) {
		Loan loan = getLoanById(loanId);
		if (!"Pending".equals(loan.getApprovalStatus())) {
			throw new ApplicationException("Only pending loans can be approved");
		}
		loanDAO.updateLoanStatus(loanId, "Approved", adminId);
	}

	public void rejectLoan(int loanId, int adminId) {
		Loan loan = getLoanById(loanId);
		if (!"Pending".equals(loan.getApprovalStatus())) {
			throw new ApplicationException("Only pending loans can be rejected");
		}
		loanDAO.updateLoanStatus(loanId, "Rejected", adminId);
	}

	public void disburseLoan(int loanId, int adminId) {
		Loan loan = getLoanById(loanId);
		if (!"Approved".equals(loan.getApprovalStatus())) {
			throw new ApplicationException("Only approved loans can be disbursed");
		}
		loanDAO.updateLoanStatus(loanId, "Disbursed", adminId);
	}

	public void submitLoanApplication(Loan loan) {
		if (loan.getUserId() <= 0) {
			throw new ApplicationException("Invalid user for loan application");
		}
		if (loan.getAmountRequested() <= 0) {
			throw new ApplicationException("Requested amount must be greater than zero");
		}
		if (loan.getTermMonths() <= 0) {
			throw new ApplicationException("Term months must be provided");
		}
		if (loan.getLoanType() == null || loan.getLoanType().trim().isEmpty()) {
			throw new ApplicationException("Loan type is required");
		}
		if (loan.getInterestRate() <= 0) {
			loan.setInterestRate(8.5);
		}
		loanDAO.insertLoanApplication(loan);
	}
}