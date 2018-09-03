package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;

public class ConfirmationPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public ConfirmationPage(WebDriver driver) {
		System.out.println("Confirmation Page Object created");
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "checkout-header")
	WebElement checkoutHeader;

	public void verifyConfirmationNumber(String number) {
		Assert.assertTrue(checkoutHeader.getText().contains(number));
	}

	@FindBy(css = "h1")
	WebElement confirmationHeadder;

	public void verifyConfirmationMessageHeadder() {
		Assert.assertTrue(confirmationHeadder.getText().contains("Your reservation is confirmed!"));
	}

	@FindBy(css = ".checkout-header h4 strong")
	WebElement confirmationNumber;

	public String getConfimationId() {
		return confirmationNumber.getAttribute("data-activity-confirmation-id");
	}

	@FindBy(css = ".checkout-header h4")
	WebElement confirmationMessage;

	@FindBy(linkText = "View & Print Vouchers")
	WebElement printVouchersButton;

	@FindBy(linkText = "Cancel Reservation")
	WebElement cancelReservationButton;

	@FindBy(linkText = "Cancel Reservation")
	List<WebElement> cancelReservationButtonList;

	@FindBy(xpath = "//div[@class='side-cards']//h3[contains(text(),'Guest Details')]/..")
	WebElement guestcard;

	@FindBy(xpath = "//div[@class='side-cards']//h3[contains(text(),'Summary of Charges')]/..")
	WebElement summaryOfChargesCard;

	@FindBy(xpath = "//div[@class='main-cards']//h2[contains(text(),'Key')]/../..")
	WebElement keyDetailsCard;

	@FindBy(xpath = "//div[@class='main-cards']//h2[contains(text(),'Additional')]/../..")
	WebElement additionalDetailsCard;

	@FindBy(xpath = "//div[@class='main-cards']//h2[contains(text(),'Cancellation')]/../..")
	WebElement cancellationPolicyCard;

	public void verifyAllWidgets() {
		Assert.assertTrue(confirmationMessage.isDisplayed());
		Assert.assertTrue(printVouchersButton.isDisplayed());
		if (cancelReservationButtonList.size() > 0)
			Assert.assertTrue(cancelReservationButton.isDisplayed());
		Assert.assertTrue(guestcard.isDisplayed());
		Assert.assertTrue(summaryOfChargesCard.isDisplayed());
		Assert.assertTrue(keyDetailsCard.isDisplayed());
		Assert.assertTrue(additionalDetailsCard.isDisplayed());
		Assert.assertTrue(cancellationPolicyCard.isDisplayed());
	}

	@FindBy(id = "experience-vouchers")
	WebElement myVouchersPopUp;

	@FindBy(css = "#experience-vouchers a.button")
	WebElement myVouchersPopUpViewAndPrintButton;

	public void verifyMyVouchersPopUp() {
		printVouchersButton.click();
		sleep(2);
		Assert.assertTrue(myVouchersPopUp.isDisplayed());
		DriverUtils.jsClick(driver, myVouchersPopUpViewAndPrintButton);
		sleep(2);
		verifyVoucherUrl();
	}

	public void verifyVoucherUrl() {
		DriverUtils.switchToLatestWindow(driver);
		sleep(5);
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("voucher"));
	}

	@FindBy(id = "cancellation")
	WebElement cancelReservationPopUp;

	public void verifyCancelReservation() {
		if (cancelReservationButtonList.size() > 0) {
			Assert.assertTrue(cancelReservationButton.isDisplayed());
			cancelReservationButton.click();

			Assert.assertTrue(cancelReservationPopUp.isDisplayed());
			Assert.assertTrue(cancelReservationPopUpCancelButton.isDisplayed());
			cancelReservationPopUpCancelButton.click();
			Assert.assertTrue(confirmationHeadder.getText().contains("This booking has been cancelled"));
		}
	}

	@FindBy(css = "div.cancel-button input")
	WebElement cancelReservationPopUpCancelButton;
}
