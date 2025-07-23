package com.tss.discounts;

public interface IDiscountStrategy {
    double applyDiscount(double totalAmount);
    String getDescription();
}
