package com.tss.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private int id;
    private String name;
    private List<Order> orders;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }
}
