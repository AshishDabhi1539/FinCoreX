package com.tss.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.tss.dao.UserDao;
import com.tss.db.DBConnection;
import com.tss.model.DBModel;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public void registerNewUser(DBModel dbmodel) {
        Connection connection = null;

        try {
            // Get database connection (assuming DBConnection is your utility class)
            connection = DBConnection.connect();

            // Call DAO method
            userDao.addNewUser(connection, dbmodel);

        } catch (Exception e) {
            System.err.println("Exception in UserService while registering: " + e.getMessage());
            e.printStackTrace();
        } 
    }
    
    public List<DBModel> fetchAllUsers() {
        List<DBModel> users = new ArrayList<>();

        try {
            Connection connection = DBConnection.connect();
            users = userDao.getAllUsers(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
