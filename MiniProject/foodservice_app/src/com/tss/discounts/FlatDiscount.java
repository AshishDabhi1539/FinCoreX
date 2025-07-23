package com.tss.discounts;

public class FlatDiscount implements IDiscountStrategy {
    private double flatAmount;

    public FlatDiscount(double flatAmount) {
        this.flatAmount = flatAmount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return Math.max(totalAmount - flatAmount, 0);
    }

    @Override
    public String getDescription() {
        return "Flat ₹" + flatAmount + " off";
    }
}
