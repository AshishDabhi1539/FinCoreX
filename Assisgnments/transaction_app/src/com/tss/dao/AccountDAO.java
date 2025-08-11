package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.model.Account;

public class AccountDAO {
	
	
    public Account getAccountById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Account(rs.getInt("id"), rs.getString("name"), rs.getDouble("balance"));
        }
        return null;
    }

    public boolean debitAmount(int id, double amount, Connection conn) throws SQLException {
        String query = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDouble(1, amount);
        ps.setInt(2, id);
        return ps.executeUpdate() > 0;
    }

    public boolean creditAmount(int id, double amount, Connection conn) throws SQLException {
        String query = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDouble(1, amount);
        ps.setInt(2, id);
        return ps.executeUpdate() > 0;
    }
}
