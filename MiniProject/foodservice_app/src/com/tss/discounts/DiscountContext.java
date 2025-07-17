package com.tss.discounts;

public class DiscountContext {
    private IDiscountStrategy strategy;

    public void setStrategy(IDiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public void autoSetStrategy(double total) {
        if (total >= 500) {
            strategy = new PercentageDiscount(10);  // 10% off
        } else if (total >= 200) {
            strategy = new FlatDiscount(50);       // ₹50 off
        } else {
            strategy = new NoDiscount();           // No discount
        }
    }

    public double applyDiscount(double total) {
        if (strategy == null) return total;
        return strategy.applyDiscount(total);
    }
}
