package com.tss.model.delivery;

import java.util.Random;

public class DeliveryProxy {
    private DeliveryPartner realPartner;

    public DeliveryProxy(DeliveryPartner partner) {
        this.realPartner = partner;
    }

    public void assign(String orderDetails) {
        
        System.out.println("Assigning delivery via proxy...");
        realPartner.deliver(orderDetails);
    }

    public static DeliveryPartner getRandomPartner() {
        String[] names = {"Swiggy", "Zomato"};
        String name = names[new Random().nextInt(names.length)];
        return new DeliveryPartner(name, "Default Region");
    }
}
