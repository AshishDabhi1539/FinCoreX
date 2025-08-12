package com.tss.model;

public class Feedback {

	private String name;
	private String sessionDate;
	private int sessionContent;
	private int queryResolution;
	private int interactivity;
	private int impactfulLearning;
	private int deliverySkills;
	public String getName() {
		return name;
	}
	public String getSessionDate() {
		return sessionDate;
	}
	public int getSessionContent() {
		return sessionContent;
	}
	public int getQueryResolution() {
		return queryResolution;
	}
	public int getInteractivity() {
		return interactivity;
	}
	public int getImpactfulLearning() {
		return impactfulLearning;
	}
	public int getDeliverySkills() {
		return deliverySkills;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}
	public void setSessionContent(int sessionContent) {
		this.sessionContent = sessionContent;
	}
	public void setQueryResolution(int queryResolution) {
		this.queryResolution = queryResolution;
	}
	public void setInteractivity(int interactivity) {
		this.interactivity = interactivity;
	}
	public void setImpactfulLearning(int impactfulLearning) {
		this.impactfulLearning = impactfulLearning;
	}
	public void setDeliverySkills(int deliverySkills) {
		this.deliverySkills = deliverySkills;
	}
}
