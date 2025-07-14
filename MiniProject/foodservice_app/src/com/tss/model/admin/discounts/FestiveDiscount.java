package com.tss.model.admin.discounts;

public class FestiveDiscount implements IDiscountStrategy {

    private final double percentage;

    public FestiveDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * (percentage / 100));
    }

    @Override
    public String getDiscountName() {
        return percentage + "% Festive Discount";
    }
}
