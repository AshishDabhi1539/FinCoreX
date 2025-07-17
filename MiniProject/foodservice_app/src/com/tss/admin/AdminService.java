package com.tss.admin;

import com.tss.admin.Admin;
import com.tss.util.DataStore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AdminService {

    private static final String ADMIN_FILE = "data/admins.dat";
    private Map<String, Admin> admins;

    public AdminService() {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdir();

        admins = DataStore.readFromFile(ADMIN_FILE);
        if (admins == null) {
            admins = new HashMap<>();
            admins.put("admin", new Admin("admin", "admin123")); // default admin
            saveAdmins();
        }
    }

    public boolean authenticate(String username, String password) {
        Admin admin = admins.get(username);
        return (admin != null && admin.getPassword().equals(password));
    }

    private void saveAdmins() {
        DataStore.saveToFile(admins, ADMIN_FILE);
    }
}
