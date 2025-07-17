package com.tss.payment;

public class CODPayment extends PaymentDecorator {
    public CODPayment(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        super.pay(amount);
        System.out.println("Cash on Delivery selected. Pay at doorstep.");
    }
}
