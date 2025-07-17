package com.tss.model.cuisine;

import com.tss.model.menu.BaseMenuItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuisine implements Serializable {
    protected String name;
    protected List<BaseMenuItem> menuItems;

    public Cuisine(String name) {
        this.name = name;
        this.menuItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<BaseMenuItem> getMenuItems() {
        return menuItems;
    }

    public void addItem(BaseMenuItem item) {
        this.menuItems.add(item);
    }

    public void removeItemById(String id) {
        this.menuItems.removeIf(item -> item.getId().equalsIgnoreCase(id));
    }

    public void printMenu() {
        System.out.println("\n--- " + name + " Cuisine Menu ---");
        if (menuItems.isEmpty()) {
            System.out.println("No items available.");
        } else {
            for (BaseMenuItem item : menuItems) {
                System.out.println(item);
            }
        }
    }
}
