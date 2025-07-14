package com.tss.model.admin.discounts;

public interface IDiscountStrategy {
    double applyDiscount(double totalAmount);
    String getDiscountName();
}
