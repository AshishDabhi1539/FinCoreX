package com.tss.model;

import java.io.Serializable;

public class LineItem implements Serializable {
    private int id;
    private int quantity;
    private Product product;

    public LineItem(int id, int quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public double calculateLineItemCost() {
        return quantity * product.calculateDiscountedPrice();
    }

    // Getters & Setters
    public int getId() { return id; }
    public int getQuantity() { return quantity; }
    public Product getProduct() { return product; }

    public void setId(int id) { this.id = id; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setProduct(Product product) { this.product = product; }
}

