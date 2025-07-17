package com.tss.discounts;

public class FlatDiscount implements IDiscountStrategy {
    private double amount;

    public FlatDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double total) {
        return Math.max(0, total - amount);
    }
}
