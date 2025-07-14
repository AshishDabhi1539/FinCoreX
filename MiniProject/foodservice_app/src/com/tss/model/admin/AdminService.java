package com.tss.model.admin;

import java.util.List;

import com.tss.model.admin.cuisine.DataStore;

public class AdminService implements IAdminService {
	private static AdminService instance;
	private final String FILE = "admins.ser";
	private List<Admin> admins;

	private AdminService() {
		admins = DataStore.loadFromFile(FILE);
		if (admins.isEmpty()) {
			Admin defaultAdmin = new Admin("admin", "admin123");
			admins.add(defaultAdmin);
			DataStore.saveToFile(admins, FILE);
		}
	}

	public static AdminService getInstance() {
		if (instance == null)
			instance = new AdminService();
		return instance;
	}

	@Override
	public boolean login(String username, String password) {
		return admins.stream().anyMatch(a -> a.getUsername().equals(username) && a.getPassword().equals(password));
	}

	@Override
	public void addAdmin(Admin admin) {
		admins.add(admin);
		DataStore.saveToFile(admins, FILE);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return admins;
	}
}
