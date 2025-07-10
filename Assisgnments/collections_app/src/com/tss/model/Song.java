package com.tss.model;

public class Song {
	private String title;
	private String artist;
	private int duration;

	public Song() {

	}

	public Song(String title, String artist, int duration) {
		this.title = title;
		this.artist = artist;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		int min = duration / 60;
		int sec = duration % 60;
		return "\"" + title + "\" by " + artist + " (" + min + "m " + sec + "s)";
	}
}
