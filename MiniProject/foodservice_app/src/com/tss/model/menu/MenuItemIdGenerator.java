package com.tss.model.menu;

import java.util.UUID;

public class MenuItemIdGenerator {
    public static String generateId(String cuisinePrefix) {
        return cuisinePrefix.toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
