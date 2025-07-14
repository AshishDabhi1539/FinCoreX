package com.tss.model.admin;

import java.util.List;

public interface IAdminService {

	 boolean login(String username, String password);
	    void addAdmin(Admin admin);
	    List<Admin> getAllAdmins();
}
