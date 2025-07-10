package com.tss.model;

public class ShoppingCart2 {
    private IPayment payment;

    public ShoppingCart2(IPayment payment) {
        this.payment = payment;
    }

    public void checkout(int amount) {
        payment.pay(amount);
    }
}
