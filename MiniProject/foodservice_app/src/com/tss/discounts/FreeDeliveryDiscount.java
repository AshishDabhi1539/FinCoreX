package com.tss.discounts;

public class FreeDeliveryDiscount implements IDiscountStrategy {
    private double threshold;
    private double deliveryFee;

    public FreeDeliveryDiscount(double threshold, double deliveryFee) {
        this.threshold = threshold;
        this.deliveryFee = deliveryFee;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        if (totalAmount >= threshold) {
            return totalAmount - deliveryFee;
        }
        return totalAmount;
    }

    @Override
    public String getDescription() {
        return "Free delivery for orders above ₹" + threshold;
    }
}
