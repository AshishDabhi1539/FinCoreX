package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.db.DBConnection;
import com.tss.model.DBModel;
import com.tss.service.UserService;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection;
	private UserService userService;

	public UserController() {
		super();
		connection = DBConnection.connect();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String username = request.getParameter("usernameTxt");
		String password = request.getParameter("passwordTxt");

		// Register new user only if username and password are provided
		if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
			DBModel dbmodel = new DBModel(username, password);
			userService.registerNewUser(dbmodel);
			writer.println("<h3>User registered successfully!</h3>");
		}

		// Fetch and display all users in a table
		List<DBModel> userList = userService.fetchAllUsers();

		writer.println("<h2>Registered Users:</h2>");
		writer.println("<table border='1' cellpadding='10'>");
		writer.println("<tr><th>Username</th><th>Password</th>");

		for (DBModel user : userList) {
			writer.println("<tr>");
			writer.println("<td>" + user.getUsername() + "</td>");
			writer.println("<td>" + user.getPassword() + "</td>");
			
			writer.println("</tr>");
		}

		writer.println("</table>");
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
