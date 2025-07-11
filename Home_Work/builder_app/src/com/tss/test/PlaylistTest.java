package com.tss.test;

import com.tss.model.Playlist;

public class PlaylistTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Playlist partyPlaylist = new Playlist.Builder("Party").genre("EDM")
				.description("Dancing songs").numberOfSongs(25).isDownloaded(true).build();

		
		Playlist chillPlaylist = new Playlist.Builder("Chill songs").genre("Lo-fi").build();

		
		Playlist offlinePlaylist = new Playlist.Builder("Downloaded Tracks").numberOfSongs(60).isDownloaded(true)
				.build();

		
		System.out.println("Playlist 1:");
		partyPlaylist.showDetails();

		System.out.println(" Playlist 2:");
		chillPlaylist.showDetails();

		System.out.println(" Playlist 3:");
		offlinePlaylist.showDetails();

	}

}
