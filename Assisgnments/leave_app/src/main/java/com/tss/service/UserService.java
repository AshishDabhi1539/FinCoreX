package com.tss.service;

import com.tss.dao.UserDAO;
import com.tss.model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) throws Exception {
        return userDAO.findByUsernameAndPassword(username, password);
    }
}
