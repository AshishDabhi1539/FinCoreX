package com.tss.model.menu;

public class DiscountedItemDecorator extends BaseMenuItem {
    private final BaseMenuItem item;
    private final double discountRate; // 0.1 for 10%, 0.2 for 20%, etc.

    public DiscountedItemDecorator(BaseMenuItem item, double discountRate) {
        super(item.getId(), item.getName(), item.getDescription(), item.getPrice());
        this.item = item;
        this.discountRate = discountRate;
    }

    @Override
    public double getPrice() {
        return Math.round((item.getPrice() * (1 - discountRate)) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return item.getName() + " [Discounted " + (int)(discountRate * 100) + "%] => " + getPrice();
    }
}
