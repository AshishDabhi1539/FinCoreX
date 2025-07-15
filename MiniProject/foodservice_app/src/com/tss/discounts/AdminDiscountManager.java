package com.tss.discounts;

import java.util.Scanner;

public class AdminDiscountManager {
    public void openDiscountPanel(Scanner sc) {
        System.out.println("\nDiscount Manager:");
        System.out.println("1. Flat rupees 50 Discount");
        System.out.println("2. Festive 15% Discount");
        System.out.println("3. Free Delivery on rupees 200+");
        System.out.println("4. 10% Off on rupees 500+");
        System.out.println("5. No Discount");
        System.out.print("Choose Discount: ");
        String opt = sc.nextLine();

        IDiscountStrategy strategy;
        switch (opt) {
            case "1" -> strategy = new FlatDiscount(50);
            case "2" -> strategy = new FestiveDiscount();
            case "3" -> strategy = new FreeDeliveryDiscount();
            case "4" -> strategy = new PercentDiscountOn500();
            default -> strategy = amount -> amount;
        }

        System.out.println("Discount strategy set. (To use, inject via OrderService)");
        
    }
}
