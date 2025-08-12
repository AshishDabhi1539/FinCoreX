package com.tss.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("usernameTxt");
		String password=request.getParameter("passwordTxt");
		
		RequestDispatcher dispatcher=null;
		if(username.equals("Mahek") && password.equals("1234"))
		{
		dispatcher=request.getRequestDispatcher("Success.jsp");
		dispatcher.forward (request, response);
//		response.sendRedirect("Success.jsp");
		}
		else
		{
		dispatcher=request.getRequestDispatcher("Error.jsp");
		dispatcher.forward (request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
