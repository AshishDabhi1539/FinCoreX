package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.database.DBConnection;
import com.tss.model.Account;

@WebServlet("/manage_accounts")
public class ManageAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (java.sql.Connection conn = DBConnection.getConnection()) {
			java.sql.PreparedStatement ps = conn.prepareStatement("SELECT account_id, user_id, account_number, account_type, balance, status, created_at FROM accounts ORDER BY created_at DESC LIMIT 200");
			java.sql.ResultSet rs = ps.executeQuery();
			java.util.ArrayList<Account> accounts = new java.util.ArrayList<>();
			while (rs.next()) {
				Account a = new Account();
				a.setAccountId(rs.getInt("account_id"));
				a.setUserId(rs.getInt("user_id"));
				a.setAccountNumber(rs.getString("account_number"));
				a.setAccountType(rs.getString("account_type"));
				a.setBalance(rs.getDouble("balance"));
				a.setStatus(rs.getString("status"));
				a.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				accounts.add(a);
			}
			rs.close();
			ps.close();
			request.setAttribute("accounts", accounts);
		}
		catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/admin/manage_accounts.jsp").forward(request, response);
	}
}

