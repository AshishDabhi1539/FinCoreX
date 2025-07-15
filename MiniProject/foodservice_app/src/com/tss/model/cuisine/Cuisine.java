package com.tss.model.cuisine;

import com.tss.model.menu.BaseMenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cuisine implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<BaseMenuItem> items;

    public Cuisine(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<BaseMenuItem> getItems() {
        return items;
    }

    public void addItem(BaseMenuItem item) {
        items.add(item);
    }

    public void removeItemById(String itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
    }

    public BaseMenuItem getItemById(String itemId) {
        for (BaseMenuItem item : items) {
            if (item.getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    public void printMenu() {
        System.out.println("\n🍽️ " + name + " Cuisine Menu:");
        for (BaseMenuItem item : items) {
            System.out.println(item);
        }
    }
}
