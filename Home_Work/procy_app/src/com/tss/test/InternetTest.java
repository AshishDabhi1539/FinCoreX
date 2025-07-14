package com.tss.test;

import com.tss.model.IInternet;
import com.tss.model.ProxyInternet;

public class InternetTest {

	public static void main(String[] args) {
		IInternet internet = new ProxyInternet();
		try {
			internet.connectTo("example.com");
			internet.connectTo("youtube.com"); // This site is banned
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	}
}
