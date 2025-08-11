package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Registration_Controller")
public class Registration_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Registration_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		String languages = request.getParameter("languages");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		
		writer.println("<h1>Registration Form</h1>");
		writer.println("Name: "+ name + "<br>");
		writer.println("Address: " + address + "<br>");
		writer.println("Gender: " + gender + "<br>");
		writer.println("City: " + city + "<br>");
		
		writer.println("languages: "+languages+"<br>");
		
		writer.println("<br>");

		writer.println("Username: " + username + "<br>");

		if (password.equals(confirmPassword)) {
			writer.println("Registration Successful");
		} else {
			writer.println("Password and Confirm Password do not match");
		}

		writer.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
