package com.tss.model;

public class ShoppingCart {
    private CreditCard payment; 

    public ShoppingCart() {
        this.payment = new CreditCard(); 
    }

    public void checkout(int amount) {
        payment.pay(amount); 
    }
}
