package com.tss.payment;

public abstract class PaymentDecorator implements Payment {
    protected Payment payment;

    public PaymentDecorator(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void pay(double amount) {
        payment.pay(amount);
    }
}
