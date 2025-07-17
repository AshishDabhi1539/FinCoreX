package com.tss.model.menu;

public class BaseMenuItem implements IMenuItem {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private double price;

    public BaseMenuItem(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " - ₹" + price + " | " + description;
    }
}
