package com.tss.payment;

public class BasicPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("💳 Total Bill: ₹" + amount);
    }
}
