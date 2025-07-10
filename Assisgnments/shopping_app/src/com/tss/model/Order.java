package com.tss.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private Date date;
    private List<LineItem> items;

    public Order(int id) {
        this.id = id;
        this.date = new Date();
        this.items = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        items.add(item);
    }

    public double calculateOrderPrice() {
        return items.stream().mapToDouble(LineItem::calculateLineItemCost).sum();
    }

    // Getters
    public int getId() { return id; }
    public Date getDate() { return date; }
    public List<LineItem> getItems() { return items; }
}
