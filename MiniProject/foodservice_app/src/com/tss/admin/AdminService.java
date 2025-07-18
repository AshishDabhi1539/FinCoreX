package com.tss.admin;

import com.tss.util.DataStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminService {
    private static final String ADMIN_FILE = "data/admins.ser";
    private static AdminService instance;
    private Map<String, Admin> admins;

    private AdminService() {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdir();

        admins = DataStore.readFromFile(ADMIN_FILE);
        if (admins == null) admins = new HashMap<>();
    }

    public static AdminService getInstance() {
        if (instance == null) {
            synchronized (AdminService.class) {
                if (instance == null) {
                    instance = new AdminService();
                }
            }
        }
        return instance;
    }

    public boolean registerAdmin(Admin admin) {
        String key = admin.getUsername();
        if (admins.containsKey(key)) return false;

        admins.put(key, admin);
        saveAdmins();
        return true;
    }

    public Admin authenticate(String username, String password) {
        Admin admin = admins.get(username.toLowerCase());
        return (admin != null && admin.getPassword().equals(password)) ? admin : null;
    }

    private void saveAdmins() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ADMIN_FILE))) {
            oos.writeObject(admins);
        } catch (Exception e) {
            System.err.println("Saving admins failed: " + e.getMessage());
        }
    }
}
