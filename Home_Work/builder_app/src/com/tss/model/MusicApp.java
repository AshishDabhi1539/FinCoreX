package com.tss.model;

public class MusicApp {

	private String playListName;
	private String genre;
	private String descriptionOfPlaylist;
	private String downloaded;
	
	public MusicApp() {
	}

	public MusicApp(String playListName, String genre, String descriptionOfPlaylist, String downloaded) {
		this.playListName = playListName;
		this.genre = genre;
		this.descriptionOfPlaylist = descriptionOfPlaylist;
		this.downloaded = downloaded;
	}

	// Setters
	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDescriptionOfPlaylist(String descriptionOfPlaylist) {
		this.descriptionOfPlaylist = descriptionOfPlaylist;
	}

	public void setDownloaded(String downloaded) {
		this.downloaded = downloaded;
	}

	// Getters
	public String getPlayListName() {
		return playListName;
	}

	public String getGenre() {
		return genre;
	}

	public String getDescriptionOfPlaylist() {
		return descriptionOfPlaylist;
	}

	public String getDownloaded() {
		return downloaded;
	}

	@Override
	public String toString() {
		return "MusicApp [playListName=" + playListName + ", genre=" + genre +
			", descriptionOfPlaylist=" + descriptionOfPlaylist + ", downloaded=" + downloaded + "]";
	}
}
