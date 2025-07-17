package com.tss.model.delivery;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DeliveryContext {
    private static final List<String> PARTNERS = Arrays.asList("Zomato", "Swiggy");

    public String assignRandomPartner() {
        Random rand = new Random();
        return PARTNERS.get(rand.nextInt(PARTNERS.size()));
    }
}
