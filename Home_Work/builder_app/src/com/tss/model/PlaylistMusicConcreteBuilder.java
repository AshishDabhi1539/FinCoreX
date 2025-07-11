package com.tss.model;

public class PlaylistMusicConcreteBuilder implements PlaylistBuilder {

	private MusicApp musicApp;
	
	public PlaylistMusicConcreteBuilder() {
		this.musicApp = new MusicApp();
	}

	@Override
	public PlaylistBuilder buildPlaylistName(String playlistName) {
		musicApp.setPlayListName(playlistName);
		return this;
	}

	@Override
	public PlaylistBuilder buildGenre(String genre) {
		musicApp.setGenre(genre);
		return this;
	}

	@Override
	public PlaylistBuilder buildDescriptionOfPlaylist(String descriptionOfPlaylist) {
		musicApp.setDescriptionOfPlaylist(descriptionOfPlaylist);
		return this;
	}

	@Override
	public PlaylistBuilder buildDownloaded(String downloaded) {
		musicApp.setDownloaded(downloaded);
		return this;
	}

	@Override
	public MusicApp build() {
		return musicApp;
	}
}
