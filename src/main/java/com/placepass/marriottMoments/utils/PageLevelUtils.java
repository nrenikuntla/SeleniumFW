package com.placepass.marriottMoments.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageLevelUtils {
	public void getBackToDefaultWindow(WebDriver driver) {
		String handle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.close();
		driver.switchTo().window(handle);
	}

	public void closeAllBrowserExcept(WebDriver driver, String handle) {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			if (!winHandle.contentEquals(handle))
				driver.close();
		}
	}

	public void bringElementInView(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void scrollBy(WebDriver driver, String verticalPx) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + verticalPx + ")", "");
	}
}
