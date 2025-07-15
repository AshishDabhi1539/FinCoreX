package com.tss.admin;

import java.io.Serializable;

public class Admin implements Serializable {
    private static Admin instance;

    private final String username;
    private final String password;

    // 🔒 Private constructor (Singleton)
    private Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // ✅ Singleton with arguments
    public static Admin getInstance(String username, String password) {
        if (instance == null) {
            instance = new Admin(username, password);
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
