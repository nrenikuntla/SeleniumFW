package com.placepass.marriottMoments.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;

public class SignInPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "sign-in-button")
	WebElement continueBtn;

	public void clickContinueBtn() {
		continueBtn.click();
	}

	@FindBy(name = "userID")
	WebElement userId;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[text()='Sign In']")
	WebElement singInBtn;

	private void enterUserId(String userIdStr) {
		userId.sendKeys(userIdStr);
	}

	private void enterPassword(String passwordStr) {
		password.sendKeys(passwordStr);
	}

	private void clickSignIn() {
		singInBtn.click();
	}

	public void login(String userName, String password) {
		enterUserId(userName);
		enterPassword(password);
		clickSignIn();
		sleep(10);
	}

	@FindBy(linkText = "Sign Out")
	WebElement singOutLink;

	public void verifySignIn() {
		DriverUtils.switchToLatestWindow(driver);
		wait.until(ExpectedConditions.visibilityOf(singOutLink));
		Assert.assertTrue(singOutLink.isDisplayed());
	}
}
