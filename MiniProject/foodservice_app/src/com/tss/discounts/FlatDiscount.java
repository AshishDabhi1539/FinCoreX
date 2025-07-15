package com.tss.discounts;

public class FlatDiscount implements IDiscountStrategy {
    private double flatAmount;

    public FlatDiscount(double flatAmount) {
        this.flatAmount = flatAmount;
    }

    @Override
    public double applyDiscount(double amount) {
        return Math.max(amount - flatAmount, 0);
    }
}