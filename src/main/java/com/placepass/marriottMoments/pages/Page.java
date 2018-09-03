package com.placepass.marriottMoments.pages;

public class Page {
	public static void sleep(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) { 
		}
	}
}
