package com.tss.discounts;

import java.util.ArrayList;
import java.util.List;

public class DiscountContext {
    private static DiscountContext instance;
    private List<IDiscountStrategy> activeDiscounts = new ArrayList<>();

    private DiscountContext() {}

    public static DiscountContext getInstance() {
        if (instance == null) {
            instance = new DiscountContext();
        }
        return instance;
    }

    public void addDiscount(IDiscountStrategy discount) {
        activeDiscounts.add(discount);
    }

    public void removeDiscount(int index) {
        if (index >= 0 && index < activeDiscounts.size()) {
            activeDiscounts.remove(index);
        }
    }

    public List<IDiscountStrategy> getActiveDiscounts() {
        return activeDiscounts;
    }

    public double applyAllDiscounts(double totalAmount) {
        double discounted = totalAmount;
        for (IDiscountStrategy strategy : activeDiscounts) {
            discounted = strategy.applyDiscount(discounted);
        }
        return discounted;
    }

    public void clearDiscounts() {
        activeDiscounts.clear();
    }
}
