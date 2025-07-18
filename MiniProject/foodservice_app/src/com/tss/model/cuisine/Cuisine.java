package com.tss.model.cuisine;

import com.tss.model.menu.BaseMenuItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuisine implements Serializable {
    
	private static final long serialVersionUID = 1L;
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
        if (menuItems.isEmpty()) {
            System.out.println(" No menu items available.");
            return;
        }

        System.out.println("\n--- " + name + " Cuisine Menu ---");
        System.out.printf("+--------+----------------------+--------+------------------------------+%n");
        System.out.printf("| ID     | Name                 | Price  | Description                  |%n");
        System.out.printf("+--------+----------------------+--------+------------------------------+%n");

        for (BaseMenuItem item : menuItems) {
            System.out.printf("| %-6s | %-20s | ₹%-5.1f | %-28s |%n",
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getDescription());
        }

        System.out.printf("+--------+----------------------+--------+------------------------------+%n");
    }

}
