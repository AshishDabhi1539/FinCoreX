package com.tss.model;

public class Customer {
    private String customerName;
    private int customerAge;
    private double customerIncome;
    private int customerCreditScore;

    public Customer(String customerName, int customerAge, double customerIncome, int customerCreditScore) {
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerIncome = customerIncome;
        this.customerCreditScore = customerCreditScore;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public double getCustomerIncome() {
        return customerIncome;
    }

    public int getCustomerCreditScore() {
        return customerCreditScore;
    }
}

