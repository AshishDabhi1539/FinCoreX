package com.tss.discounts;

import java.util.Scanner;

public class DiscountManager {
    private static double flatDiscount = 0;
    private static double festivalDiscount = 0;
    private static boolean freeDeliveryOn200 = false;
    private static boolean tenPercentOffOn500 = false;

    public static void manageDiscounts(Scanner scanner) {
        while (true) {
            System.out.println("\n========= 💸 Manage Discounts =========");
            System.out.println("1. Set Flat Discount");
            System.out.println("2. Set Festival Discount");
            System.out.println("3. Toggle Free Delivery on Orders ≥ ₹200");
            System.out.println("4. Toggle 10% Off on Orders ≥ ₹500");
            System.out.println("5. Back");
            System.out.print("👉 Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("💰 Enter flat discount amount (₹): ");
                    flatDiscount = Double.parseDouble(scanner.nextLine());
                    System.out.println("✅ Flat discount updated.");
                    break;
                case 2:
                    System.out.print("🎉 Enter festival discount amount (₹): ");
                    festivalDiscount = Double.parseDouble(scanner.nextLine());
                    System.out.println("✅ Festival discount updated.");
                    break;
                case 3:
                    freeDeliveryOn200 = !freeDeliveryOn200;
                    System.out.println(freeDeliveryOn200 ? "✅ Free delivery on ₹200+ enabled." : "❌ Free delivery disabled.");
                    break;
                case 4:
                    tenPercentOffOn500 = !tenPercentOffOn500;
                    System.out.println(tenPercentOffOn500 ? "✅ 10% off on ₹500+ enabled." : "❌ 10% discount disabled.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    public static double getFlatDiscount() {
        return flatDiscount;
    }

    public static double getFestivalDiscount() {
        return festivalDiscount;
    }

    public static boolean isFreeDeliveryOn200() {
        return freeDeliveryOn200;
    }

    public static boolean isTenPercentOffOn500() {
        return tenPercentOffOn500;
    }
}
