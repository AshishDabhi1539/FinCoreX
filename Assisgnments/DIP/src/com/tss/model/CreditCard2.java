package com.tss.model;

public class CreditCard2 implements IPayment {
    @Override
    public void pay(int amount) {
        System.out.println("Pay using Credit Card: " + amount);
    }
}
