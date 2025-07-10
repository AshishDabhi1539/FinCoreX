package com.tss.model;

public class UpiPayment implements IPayment {
    @Override
    public void pay(int amount) {
        System.out.println("Pay using UPI: " + amount);
    }
}

