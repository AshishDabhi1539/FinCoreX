package com.tss.discounts;

public class FreeDeliveryDiscount implements IDiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        if (amount >= 200) {
            System.out.println("Free delivery applied (₹30 off).");
            return amount - 30;
        }
        return amount;
    }
}
