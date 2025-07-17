package com.tss.discounts;

public class PercentageDiscount implements IDiscountStrategy {
    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double total) {
        return total - (total * percentage / 100.0);
    }
}
