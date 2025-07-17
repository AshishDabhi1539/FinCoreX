package com.tss.util;

import java.util.UUID;

public class IDGenerator {
    public static String generateShortId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
