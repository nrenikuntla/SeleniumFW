package com.placepass.marriottMoments.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ConfirmationPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="checkout-header")
	WebElement checkoutHeader;
	
	public void verifyConfirmationNumber(String number){
		Assert.assertTrue(checkoutHeader.getText().contains(number));
	}

}


