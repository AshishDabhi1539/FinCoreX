package com.tss.model.order;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final String ORDER_FILE = "data/orders.ser";

    public static void saveOrder(Order order) {
        List<Order> orders = loadAllOrders();
        orders.add(order);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDER_FILE))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            System.out.println(" Failed to save order: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Order> loadAllOrders() {
        File file = new File(ORDER_FILE);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDER_FILE))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static Order getOrderById(String orderId) {
        return loadAllOrders().stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }
}
