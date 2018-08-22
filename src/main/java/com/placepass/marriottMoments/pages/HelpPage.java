package com.placepass.marriottMoments.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.placepass.marriottMoments.utils.PageLevelUtils;

public class HelpPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public HelpPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "activities_confirmation_lookup_confirmation_code")
	WebElement bookingConfirmationCode;

	@FindBy(id = "activities_confirmation_lookup_email")
	WebElement bookingEMail;

	@FindBy(xpath = "//input[@value='Locate Ticket']")
	WebElement locateTicketBtn;

	public void enterBookingCode(String confirmationCodeStr, String email) {
		bookingConfirmationCode.sendKeys(confirmationCodeStr);
		bookingEMail.sendKeys(email);
		PageLevelUtils utils = new PageLevelUtils();
		utils.bringElementInView(driver, locateTicketBtn);
		utils.scrollBy(driver, "-100");
		locateTicketBtn.click();
	}

}
