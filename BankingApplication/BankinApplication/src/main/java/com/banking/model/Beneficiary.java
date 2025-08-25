package com.banking.model;

public class Beneficiary {
    private long beneficiaryId;    // matches beneficiary_id in DB
    private long userId;            // matches user_id
    private String beneficiaryName; // matches beneficiary_name
    private String accountNumber;   // matches account_number
    private String ifsc;            // matches ifsc
    private String bankName;        // matches bank_name
    private String nickname;        // matches nickname

    public long getBeneficiaryId() {
        return beneficiaryId;
    }
    public void setBeneficiaryId(long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }
    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }
    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
