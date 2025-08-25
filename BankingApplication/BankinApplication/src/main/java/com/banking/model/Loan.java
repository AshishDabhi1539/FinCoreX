package com.banking.model;

import java.time.LocalDateTime;

public class Loan {
    private Long loanId;
    private Long userId;
    private String loanType;
    private double amount;
    private double interestRate;
    private int tenureMonths;
    private String status;
    private LocalDateTime applicationDate;
    private LocalDateTime approvalDate;
    private LocalDateTime disbursementDate;
    private String approvedBy;
    private String rejectionReason;
    private double monthlyEMI;
    private double totalAmount;
    private int emiPaid;
    private int emiRemaining;
    private double outstandingAmount;
    private String purpose;
    private String collateralDetails;
    private String guarantorName;
    private String guarantorPhone;
    private String guarantorAddress;
    private String documentsSubmitted;
    private LocalDateTime lastPaymentDate;
    private String paymentStatus;
    
    // Constructors
    public Loan() {}
    
    public Loan(Long userId, String loanType, double amount, int tenureMonths, String purpose) {
        this.userId = userId;
        this.loanType = loanType;
        this.amount = amount;
        this.tenureMonths = tenureMonths;
        this.purpose = purpose;
        this.status = "PENDING";
        this.applicationDate = LocalDateTime.now();
        this.emiRemaining = tenureMonths;
        this.outstandingAmount = amount;
    }
    
    // Getters and Setters
    public Long getLoanId() {
        return loanId;
    }
    
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getLoanType() {
        return loanType;
    }
    
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
    public int getTenureMonths() {
        return tenureMonths;
    }
    
    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }
    
    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }
    
    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }
    
    public LocalDateTime getDisbursementDate() {
        return disbursementDate;
    }
    
    public void setDisbursementDate(LocalDateTime disbursementDate) {
        this.disbursementDate = disbursementDate;
    }
    
    public String getApprovedBy() {
        return approvedBy;
    }
    
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
    
    public String getRejectionReason() {
        return rejectionReason;
    }
    
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
    
    public double getMonthlyEMI() {
        return monthlyEMI;
    }
    
    public void setMonthlyEMI(double monthlyEMI) {
        this.monthlyEMI = monthlyEMI;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public int getEmiPaid() {
        return emiPaid;
    }
    
    public void setEmiPaid(int emiPaid) {
        this.emiPaid = emiPaid;
    }
    
    public int getEmiRemaining() {
        return emiRemaining;
    }
    
    public void setEmiRemaining(int emiRemaining) {
        this.emiRemaining = emiRemaining;
    }
    
    public double getOutstandingAmount() {
        return outstandingAmount;
    }
    
    public void setOutstandingAmount(double outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public String getCollateralDetails() {
        return collateralDetails;
    }
    
    public void setCollateralDetails(String collateralDetails) {
        this.collateralDetails = collateralDetails;
    }
    
    public String getGuarantorName() {
        return guarantorName;
    }
    
    public void setGuarantorName(String guarantorName) {
        this.guarantorName = guarantorName;
    }
    
    public String getGuarantorPhone() {
        return guarantorPhone;
    }
    
    public void setGuarantorPhone(String guarantorPhone) {
        this.guarantorPhone = guarantorPhone;
    }
    
    public String getGuarantorAddress() {
        return guarantorAddress;
    }
    
    public void setGuarantorAddress(String guarantorAddress) {
        this.guarantorAddress = guarantorAddress;
    }
    
    public String getDocumentsSubmitted() {
        return documentsSubmitted;
    }
    
    public void setDocumentsSubmitted(String documentsSubmitted) {
        this.documentsSubmitted = documentsSubmitted;
    }
    
    public LocalDateTime getLastPaymentDate() {
        return lastPaymentDate;
    }
    
    public void setLastPaymentDate(LocalDateTime lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    // Business methods
    public boolean isPending() {
        return "PENDING".equalsIgnoreCase(status);
    }
    
    public boolean isApproved() {
        return "APPROVED".equalsIgnoreCase(status);
    }
    
    public boolean isRejected() {
        return "REJECTED".equalsIgnoreCase(status);
    }
    
    public boolean isDisbursed() {
        return "DISBURSED".equalsIgnoreCase(status);
    }
    
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }
    
    public boolean isClosed() {
        return "CLOSED".equalsIgnoreCase(status);
    }
    
    public void approve(String approvedBy) {
        this.status = "APPROVED";
        this.approvedBy = approvedBy;
        this.approvalDate = LocalDateTime.now();
        calculateEMI();
    }
    
    public void reject(String reason) {
        this.status = "REJECTED";
        this.rejectionReason = reason;
    }
    
    public void disburse() {
        this.status = "DISBURSED";
        this.disbursementDate = LocalDateTime.now();
    }
    
    public void activate() {
        this.status = "ACTIVE";
    }
    
    public void close() {
        this.status = "CLOSED";
    }
    
    private void calculateEMI() {
        if (interestRate > 0 && tenureMonths > 0) {
            double monthlyRate = interestRate / (12 * 100);
            this.monthlyEMI = amount * (monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) / 
                             (Math.pow(1 + monthlyRate, tenureMonths) - 1);
            this.totalAmount = monthlyEMI * tenureMonths;
        }
    }
    
    public void makePayment(double amount) {
        if (outstandingAmount >= amount) {
            outstandingAmount -= amount;
            emiPaid++;
            emiRemaining--;
            lastPaymentDate = LocalDateTime.now();
            
            if (outstandingAmount <= 0) {
                close();
            }
        }
    }
    
    public double getNextEMIDue() {
        return Math.min(monthlyEMI, outstandingAmount);
    }
    
    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", userId=" + userId +
                ", loanType='" + loanType + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", applicationDate=" + applicationDate +
                ", outstandingAmount=" + outstandingAmount +
                '}';
    }
}