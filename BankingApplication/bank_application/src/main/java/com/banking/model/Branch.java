package com.banking.model;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

public class Branch {
    private int branchId;
    private String name;
    private String ifsc;
    private String address;

    // Relations
    private List<Account> accounts;

    public Branch(int branchId, String name, String ifsc, String address) {
        this.branchId = branchId;
        this.name = name;
        this.ifsc = ifsc;
        this.address = address;
    }

    // Getters & Setters
    public int getBranchId() { return branchId; }
    public void setBranchId(int branchId) { this.branchId = branchId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIfsc() { return ifsc; }
    public void setIfsc(String ifsc) { this.ifsc = ifsc; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<Account> getAccounts() { return accounts; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }

    @Override
    public String toString() {
        return "Branch [branchId=" + branchId + ", name=" + name + ", IFSC=" + ifsc + ", address=" + address + "]";
    }
}