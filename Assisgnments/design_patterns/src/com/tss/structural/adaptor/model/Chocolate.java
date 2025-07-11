package com.tss.structural.adaptor.model;

public class Chocolate implements IItem {
    private String name;
    private double price;

    public Chocolate(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getItemName() {
        return name;
    }

    public double getItemPrice() {
        return price;
    }
}

