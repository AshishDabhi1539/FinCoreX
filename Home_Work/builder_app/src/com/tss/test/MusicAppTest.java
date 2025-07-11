package com.tss.test;

import com.tss.model.MusicApp;
import com.tss.model.PLaylistDirector;
import com.tss.model.PlaylistMusicConcreteBuilder;

public class MusicAppTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PlaylistMusicConcreteBuilder playlistBuilder = new PlaylistMusicConcreteBuilder();
		PLaylistDirector director = new PLaylistDirector(playlistBuilder);
		MusicApp app = director.constructPLaylist();
		
		System.out.println(app);
	}

}
