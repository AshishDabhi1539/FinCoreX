package com.tss.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.tss.model.menu.BaseMenuItem;
import com.tss.model.order.Order;

public class ReceiptService {

    private static final String RECEIPT_FOLDER = "receipts";

    public static void generateReceipt(Order order) {
        try {
            
            File folder = new File(RECEIPT_FOLDER);
            if (!folder.exists()) folder.mkdirs();

           
            String filename = RECEIPT_FOLDER + "/receipt_" + order.getOrderId() + ".txt";
            FileWriter writer = new FileWriter(filename);

            writer.write("RECEIPT - Order ID: " + order.getOrderId() + "\n");
            writer.write("Date: " + order.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n");
            writer.write("Customer: " + order.getCustomer().getName() + "\n");
            writer.write("Delivery Partner: " + order.getAssignedDeliveryPartner() + "\n");
            writer.write("--------------------------------------------------\n");

            double total = 0.0;
            for (BaseMenuItem item : order.getItems()) {
                writer.write(item.getName() + " - ₹" + item.getPrice() + "\n");
                total += item.getPrice();
            }

            writer.write("--------------------------------------------------\n");
            writer.write("Total Amount: ₹" + total + "\n");
            writer.write("Status: " + order.getStatus() + "\n");
            writer.write("Thank you for ordering with us! 😊\n");

            writer.close();
            System.out.println("Receipt saved: " + filename);
        } catch (IOException e) {
            System.out.println("Failed to write receipt: " + e.getMessage());
        }
    }
}
