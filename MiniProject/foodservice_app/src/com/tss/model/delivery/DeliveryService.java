package com.tss.model.delivery;

import java.util.Scanner;

public class DeliveryService {

    public void assignPartner(Scanner sc) {
        System.out.println("\nAssign Delivery Partner to a dummy order");
        System.out.print("Enter dummy order details: ");
        String orderDetails = sc.nextLine();

        DeliveryPartner randomPartner = DeliveryProxy.getRandomPartner();
        DeliveryProxy proxy = new DeliveryProxy(randomPartner);
        proxy.assign(orderDetails);

        System.out.println("Partner Assigned: " + randomPartner.getName());
    }
}
