package com.tss.model;

public interface PlaylistBuilder {

	PlaylistBuilder buildPlaylistName(String playlistName);
	PlaylistBuilder buildGenre(String genre);
	PlaylistBuilder buildDescriptionOfPlaylist(String descriptionOfPlaylist);
	PlaylistBuilder buildDownloaded(String downloaded);
	MusicApp build();
}
