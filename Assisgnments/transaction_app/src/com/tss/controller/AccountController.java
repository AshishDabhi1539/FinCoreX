package com.tss.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.tss.dao.AccountDAO;
import com.tss.database.DBConnection;
import com.tss.model.Account;

public class AccountController {

	 private final AccountDAO dao = new AccountDAO();

	    public String transferFunds(int senderId, int receiverId, double amount) {
	        try (Connection connection = DBConnection.connection()) {
	            connection.setAutoCommit(false);

	            Account sender = dao.getAccountById(senderId, connection);
	            if (sender == null) return "Sender account not found.";
	            if (sender.getBalance() < amount) return "Insufficient balance.";

	            dao.debitAmount(senderId, amount, connection);
	            Savepoint savepoint = connection.setSavepoint("AfterDebit");

	            boolean creditSuccess = dao.creditAmount(receiverId, amount, connection);
	            if (creditSuccess) {
	                connection.commit();
	                return "Transfer successful.";
	            } else {
	                connection.rollback(savepoint);
	                connection.commit();
	                return "Credit failed.";
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Transfer failed due to system error.");
	            
	        }
			return null;
	    }
}
