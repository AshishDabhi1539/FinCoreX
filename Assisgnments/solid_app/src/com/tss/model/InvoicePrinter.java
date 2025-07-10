package com.tss.model;

public class InvoicePrinter {

    public void printToConsole(InvoiceCalculator invoice) {
        
        System.out.println("Invoice");
        System.out.println("ID: " + invoice.getId());
        System.out.println("Description: " + invoice.getDescription());
        System.out.println("Cost: " + invoice.getCost());
        System.out.println("Discount: " + invoice.calculateDiscount());
        System.out.println("Tax: " + invoice.calculateTax());
        System.out.println("Final Cost: " + invoice.calculateFinalCost());
        

     
        System.out.println("\nTable Format");
        System.out.printf("%-5s %-20s %-10s %-10s %-20s %-15s%n",
                "ID", "Description", "Amount", "Tax(%)", "Discount (%)", "Final Amount");

        System.out.printf("%-5d %-20s %-10.2f %-10.2f %-20.2f %-15.2f%n",
                invoice.getId(),
                invoice.getDescription(),
                invoice.getCost(),
                InvoiceCalculator.TAX,
                invoice.getDiscountPercent(),
                invoice.calculateFinalCost());
    }
}
