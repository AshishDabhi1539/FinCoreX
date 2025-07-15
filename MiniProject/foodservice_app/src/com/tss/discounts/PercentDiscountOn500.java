package com.tss.discounts;

public class PercentDiscountOn500 implements IDiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        if (amount >= 500) {
            System.out.println("💰 10% off for orders ≥ ₹500.");
            return amount * 0.90;
        }
        return amount;
    }
}
