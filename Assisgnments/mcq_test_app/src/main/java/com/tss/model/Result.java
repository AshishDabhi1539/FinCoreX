package com.tss.model;

import java.sql.Timestamp;

public class Result {
	private int id;
	private int userId;
	private int score;
	private Timestamp quizDate;

	public Result() {
	}

	public Result(int id, int userId, int score, Timestamp quizDate) {
		this.id = id;
		this.userId = userId;
		this.score = score;
		this.quizDate = quizDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Timestamp getQuizDate() {
		return quizDate;
	}

	public void setQuizDate(Timestamp quizDate) {
		this.quizDate = quizDate;
	}
}
