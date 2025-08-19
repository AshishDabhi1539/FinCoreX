package com.banking.model;

import java.sql.Date;
import java.util.List;

public class Customer {
    private int customerId;
    private User user;  // Linked User (login credentials)
    private String name;
    private String email;
    private String phone;
    private String address;
    private String kycDocument;

    // Relations
    private List<Account> accounts;
    private List<Loan> loans;

    public Customer(int customerId, User user, String name, String email, String phone, String address, String kycDocument) {
        this.customerId = customerId;
        this.user = user;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.kycDocument = kycDocument;
    }

    // Getters & Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getKycDocument() { return kycDocument; }
    public void setKycDocument(String kycDocument) { this.kycDocument = kycDocument; }

    public List<Account> getAccounts() { return accounts; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }

    public List<Loan> getLoans() { return loans; }
    public void setLoans(List<Loan> loans) { this.loans = loans; }

    @Override
    public String toString() {
        return "Customer [id=" + customerId + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }
}


