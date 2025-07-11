package com.tss.structural.adaptor.model;

public class Hat {
    private String shortName;
    private String longName;
    private double basePrice;
    private double discount;

    public Hat(String shortName, String longName, double basePrice, double discount) {
        this.shortName = shortName;
        this.longName = longName;
        this.basePrice = basePrice;
        this.discount = discount;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getDiscount() {
        return discount;
    }
}
