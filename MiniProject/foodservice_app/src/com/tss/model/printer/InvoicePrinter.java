package com.tss.model.printer;

import com.tss.model.order.Order;
import com.tss.model.menu.BaseMenuItem;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class InvoicePrinter {

    public static void printInvoice(Order order, double discountedTotal, String paymentMode, String deliveryPartner) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("+------------------------------------------------+\n");
        invoice.append(String.format("| %-46s |\n", "INVOICE"));
        invoice.append("+------------------------------------------------+\n");
        invoice.append(String.format("| Order ID       : %-30s |\n", order.getOrderId()));
        invoice.append(String.format("| Customer Name  : %-30s |\n", order.getCustomer().getName()));
        invoice.append(String.format("| Date           : %-30s |\n", order.getTimestamp().toLocalDate()));
        invoice.append(String.format("| Time           : %-30s |\n", order.getTimestamp().toLocalTime().withNano(0)));
        invoice.append(String.format("| Payment Mode   : %-30s |\n", paymentMode));
        invoice.append(String.format("| Delivery Via   : %-30s |\n", deliveryPartner));
        invoice.append("+------------------------------------------------+\n");
        invoice.append(String.format("| %-20s %-6s %-8s %-9s |\n", "Item", "Qty", "Price", "Subtotal"));
        invoice.append("+------------------------------------------------+\n");

        for (BaseMenuItem item : order.getItems()) {
            invoice.append(String.format("| %-20s %-6d ₹%-7.2f ₹%-8.2f |\n",
                    item.getName(), 1, item.getPrice(), item.getPrice()));
        }

        invoice.append("+------------------------------------------------+\n");
        invoice.append(String.format("| %-36s ₹%-8.2f |\n", "Original Total", order.getTotalAmount()));
        invoice.append(String.format("| %-36s ₹%-8.2f |\n", "Discounted Total", discountedTotal));
        invoice.append("+------------------------------------------------+\n");

        // Print to console
        System.out.println(invoice);

        // Save to receipt file
        saveInvoiceToFile(order.getOrderId(), invoice.toString());
    }

    private static void saveInvoiceToFile(String orderId, String content) {
        String receiptFile = "data/receipts/receipt_" + orderId + ".txt";
        try (FileWriter writer = new FileWriter(receiptFile)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("[Error] Saving receipt failed: " + e.getMessage());
        }
    }
}
