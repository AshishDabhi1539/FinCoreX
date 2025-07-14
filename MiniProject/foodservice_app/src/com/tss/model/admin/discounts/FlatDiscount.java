package com.tss.model.admin.discounts;

public class FlatDiscount implements IDiscountStrategy {

    private final double discountAmount;

    public FlatDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        double finalAmount = totalAmount - discountAmount;
        return finalAmount > 0 ? finalAmount : 0;
    }

    @Override
    public String getDiscountName() {
        return "Flat ₹" + discountAmount + " Off";
    }
}
