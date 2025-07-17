package com.tss.test;

import java.util.Arrays;
import java.util.List;
import com.tss.model.Product;

public class Problem6 {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.0, 2),
            new Product("Phone", 600.0, 3),
            new Product("Tablet", 400.0, 1)
        );

        double total = products.stream()
            .mapToDouble(product -> product.getPrice() * product.getQuantity())
            .sum();

        System.out.println("Total Bill: " + total);
    }
}
