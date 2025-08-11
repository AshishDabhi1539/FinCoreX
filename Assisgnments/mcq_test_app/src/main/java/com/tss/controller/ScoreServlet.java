package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.ResultDAO;
import com.tss.model.Result;
import com.tss.model.User;
import com.tss.service.QuizService;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizService quizService;
	private ResultDAO resultDAO;

	public ScoreServlet() {
		super();
		this.quizService = new QuizService();
		this.resultDAO = new ResultDAO(); // ✅ Ensure DAO is initialized
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return;
		}

		int score = quizService.getFinalScore(session);
		User user = (User) session.getAttribute("user");

		try {

			Timestamp now = new Timestamp(System.currentTimeMillis());
			Result result = new Result(0, user.getId(), score, now);
			resultDAO.saveResult(result);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html><head><title>Quiz Result</title>");
			out.println("<style>");
			out.println("body { font-family: Arial; text-align: center; background: #f2f2f2; padding-top: 100px; }");
			out.println(
					".score-box { background: white; padding: 40px; margin: auto; width: 400px; box-shadow: 0 10px 30px rgba(0,0,0,0.2); border-radius: 10px; }");
			out.println("h2 { color: #185a9d; }");
			out.println(
					"button { padding: 10px 20px; margin-top: 20px; background-color: #185a9d; color: white; border: none; border-radius: 5px; cursor: pointer; }");
			out.println("button:hover { background-color: #14457a; }");
			out.println("form { margin-top: 15px; }");
			out.println("</style>");
			out.println("</head><body>");
			out.println("<div class='score-box'>");
			out.println("<h2>Quiz Completed</h2>");
			out.println("<p>Hello, <strong>" + user.getUsername() + "</strong>!</p>");
			out.println("<p>Your Score: <strong>" + score + "</strong></p>");
			out.println("<p>Time: " + now + "</p>");

			out.println("<form action='logout' method='post'>");
			out.println("<button type='submit'>Logout</button>");
			out.println("</form>");

			out.println("</div></body></html>");

			session.invalidate();

		} catch (SQLException e) {
			response.setContentType("text/html");
			response.getWriter().println("<h3>Error saving score: " + e.getMessage() + "</h3>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
