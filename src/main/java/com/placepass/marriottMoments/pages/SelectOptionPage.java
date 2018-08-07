package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.placepass.marriottMoments.utils.DriverUtils;
import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.WaitUtil;


public class SelectOptionPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public SelectOptionPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#pick-an-option .booking-option-radio")
	List<WebElement> options;

	@FindBy(css = ".icon-plus")
	WebElement plus;

	@FindBy(xpath = "//button[contains(text(),'Review & Book')]")
	List<WebElement> reviewAndBook;

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	List<WebElement> checkOut;

	public void selectOption() {
		sleep(5);
		options.get(0).click();
		sleep(5);
		plus.click();
		if (reviewAndBook.size() > 0)
			reviewAndBook.get(0).click();
		else if (checkOut.size() > 0)
			checkOut.get(0).click();
	}

}
