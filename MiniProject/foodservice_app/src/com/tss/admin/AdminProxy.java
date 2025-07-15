package com.tss.admin;

public class AdminProxy {
    private final Admin realAdmin;

    public AdminProxy(Admin realAdmin) {
        this.realAdmin = realAdmin;
    }

    // Authenticates against hardcoded admin credentials
    public boolean login(String username, String password) {
        if (realAdmin.getUsername().equals(username) &&
            realAdmin.getPassword().equals(password)) {
            return true;
        }
        System.out.println("Unauthorized Admin access.");
        return false;
    }
}
