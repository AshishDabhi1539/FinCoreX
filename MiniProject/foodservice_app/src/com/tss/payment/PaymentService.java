package com.tss.payment;

import java.util.Scanner;

public class PaymentService {

    // This method asks user for payment mode and returns it
    public static String simulatePayment(double amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Total amount to pay: ₹" + amount);
        System.out.println("Select payment mode:\n1. Cash\n2. Card\n3. UPI");
        
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to Cash.");
            return "Cash";
        }

        switch (choice) {
            case 1:
                return "Cash";
            case 2:
                return "Card";
            case 3:
                return "UPI";
            default:
                System.out.println("Invalid choice. Defaulting to Cash.");
                return "Cash";
        }
    }

    // This method invokes simulatePayment and logs the choice
    public static void processPayment(Scanner scanner, double discountedTotal) {
        String paymentMode = simulatePayment(discountedTotal);
        System.out.println("Payment received via " + paymentMode + ". Thank you!");
    }
}
