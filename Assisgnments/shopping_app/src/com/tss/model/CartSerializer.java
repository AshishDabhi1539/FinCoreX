package com.tss.model;
import java.io.*;
import com.tss.model.Customer;
public class CartSerializer {
    private static final String FILE = "customer_cart.ser";

    public static void saveCart(Customer customer) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Customer loadCart() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (Customer) in.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
