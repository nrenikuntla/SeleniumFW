package com.placepass.marriottMoments.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverUtils {
	public static void switchToLatestWindow(WebDriver driver) {
		for (String handle : driver.getWindowHandles())
			driver.switchTo().window(handle);
	}

	public static void jsClick(WebDriver driver, WebElement pageElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageElement);
		System.out.println("clicked");
	}
}
