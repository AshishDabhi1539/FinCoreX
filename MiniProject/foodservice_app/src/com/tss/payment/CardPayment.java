package com.tss.payment;

import java.util.Scanner;

public class CardPayment extends PaymentDecorator {

    public CardPayment(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        super.pay(amount);
        try (Scanner sc = new Scanner(System.in)) {
            String cardNumber;
            while (true) {
                System.out.print("Enter 16-digit Card Number: ");
                cardNumber = sc.nextLine().replaceAll("\\s+", ""); 
                if (!cardNumber.matches("\\d{16}")) {
                    System.out.println("❌ Invalid input. Card number must be 16 digits.");
                    continue;
                }

                
                break;
            }

            
            String cvv;
            while (true) {
                System.out.print("Enter 3-digit CVV: ");
                cvv = sc.nextLine();

                if (!cvv.matches("\\d{3}")) {
                    System.out.println("❌ Invalid CVV. Must be 3 digits.");
                } else {
                    break;
                }
            }

            
            String expiry;
            while (true) {
                System.out.print("Enter Expiry Date (MM/YY): ");
                expiry = sc.nextLine();

                if (!expiry.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                    System.out.println("Invalid expiry format. Use MM/YY.");
                } else {
                    break;
                }
            }

            System.out.println("✅ Card payment successful with card: **** **** **** " + cardNumber.substring(12));
        }
    }

   
    private boolean isValidCardNumber(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = cardNumber.charAt(i) - '0';
            if (alternate) {
                n *= 2;
                if (n > 9)
                    n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }
}
