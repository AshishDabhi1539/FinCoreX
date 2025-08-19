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
		loanDAO.updateLoanStatus(loanId, "Approved", adminId);
	}

	public void rejectLoan(int loanId, int adminId) {
		loanDAO.updateLoanStatus(loanId, "Rejected", adminId);
	}

	public void disburseLoan(int loanId, int adminId) {
		loanDAO.updateLoanStatus(loanId, "Disbursed", adminId);
	}
}