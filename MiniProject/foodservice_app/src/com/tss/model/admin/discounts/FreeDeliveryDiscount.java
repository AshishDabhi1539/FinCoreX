package com.tss.model.admin.discounts;

public class FreeDeliveryDiscount implements IDiscountStrategy {

    private final double threshold;

    public FreeDeliveryDiscount(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        if (totalAmount < threshold) {
            System.out.println("✅ Free delivery applied!");
        }
        return totalAmount; // no actual change in amount here
    }

    @Override
    public String getDiscountName() {
        return "Free Delivery (if under ₹" + threshold + ")";
    }
}
