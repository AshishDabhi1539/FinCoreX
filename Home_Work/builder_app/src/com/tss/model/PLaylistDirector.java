package com.tss.model;

public class PLaylistDirector {

	private PlaylistMusicConcreteBuilder playlistMusicConcreteBuilder;

	public PLaylistDirector(PlaylistMusicConcreteBuilder playlistMusicConcreteBuilder) {
		super();
		this.playlistMusicConcreteBuilder = playlistMusicConcreteBuilder;
	}
	
	public MusicApp constructPLaylist() {
		return 	playlistMusicConcreteBuilder
				.buildPlaylistName("PLaylist1")
				.buildGenre("Party")
				.buildDescriptionOfPlaylist("Dancing songs")
				.buildDownloaded("Yes")
				.build();
		
	}
}
