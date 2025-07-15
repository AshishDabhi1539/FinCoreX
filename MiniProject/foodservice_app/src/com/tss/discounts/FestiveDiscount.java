package com.tss.discounts;

public class FestiveDiscount implements IDiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        System.out.println("Festive 15% discount applied!");
        return amount * 0.85;
    }
}
