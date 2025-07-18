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

    public static String processPayment(Scanner scanner, double amount) {
        System.out.println("Total amount to pay: ₹" + amount);
        System.out.println("Select payment mode:\n1. Cash\n2. Card\n3. UPI");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to Cash.");
            choice = 1;
        }

        Payment payment = new BasicPayment();
        String paymentMode;

        switch (choice) {
            case 1:
                payment = new CODPayment(payment);
                paymentMode = "Cash";
                break;
            case 2:
                payment = new CardPayment(payment);
                paymentMode = "Card";
                break;
            case 3:
                payment = new UPIPayment(payment);
                paymentMode = "UPI";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Cash.");
                payment = new CODPayment(payment);
                paymentMode = "Cash";
                break;
        }

        payment.pay(amount);
        return paymentMode;
    }

}
