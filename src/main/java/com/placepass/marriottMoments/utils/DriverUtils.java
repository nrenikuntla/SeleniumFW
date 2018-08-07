package com.placepass.marriottMoments.utils;

import org.openqa.selenium.WebDriver;

public class DriverUtils {
	public static void switchToLatestWindow(WebDriver driver) {
		for (String handle : driver.getWindowHandles())
			driver.switchTo().window(handle);
	}
}
