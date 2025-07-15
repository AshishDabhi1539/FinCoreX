package com.tss.discounts;

public class DiscountContext {
    private IDiscountStrategy discountStrategy;

    public void setStrategy(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double applyDiscount(double amount) {
        return (discountStrategy != null) ? discountStrategy.applyDiscount(amount) : amount;
    }
}
