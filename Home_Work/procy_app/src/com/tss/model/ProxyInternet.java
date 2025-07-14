package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class ProxyInternet implements IInternet{

	private RealInternet realInternet = new RealInternet();
    private static List<String> bannedSites;

    static {
        bannedSites = new ArrayList<>();
        bannedSites.add("youtube.com");
        bannedSites.add("open.spotify.com");
        bannedSites.add("hotstar.com");
    }
	@Override
	public void connectTo(String serverHost) throws Exception {
		// TODO Auto-generated method stub
		if (bannedSites.contains(serverHost.toLowerCase())) {
            throw new Exception("Access Denied to " + serverHost);
        }
        realInternet.connectTo(serverHost);
	}

}
