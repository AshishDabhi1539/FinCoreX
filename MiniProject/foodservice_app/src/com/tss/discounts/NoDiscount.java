package com.tss.discounts;

public class NoDiscount implements IDiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total;
    }
}
