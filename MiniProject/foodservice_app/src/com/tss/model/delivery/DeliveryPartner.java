package com.tss.model.delivery;

public class DeliveryPartner {
    private String name;
    private String region;

    public DeliveryPartner(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public void deliver(String orderDetails) {
        System.out.println("Delivery assigned to " + name + " for order: " + orderDetails);
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }
}
