package com.tss.discounts;

public class PercentageDiscount implements IDiscountStrategy {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * percent / 100.0);
    }

    @Override
    public String getDescription() {
        return percent + "% Off";
    }
}
