package com.tss.model;

public class LoanType {
	private int loanTypeId;
	private String loanTypeName;
	private Double defaultInterestRate;
	private Boolean isActive;

	public int getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(int loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public Double getDefaultInterestRate() {
		return defaultInterestRate;
	}

	public void setDefaultInterestRate(Double defaultInterestRate) {
		this.defaultInterestRate = defaultInterestRate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}

