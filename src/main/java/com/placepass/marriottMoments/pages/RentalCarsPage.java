package com.placepass.marriottMoments.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentalCarsPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public RentalCarsPage(WebDriver driver) {
		System.out.println("Rental Cars Page Object created");
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "single_location_id")
	WebElement singleLocation;

	@FindBy(id = "human-pickup-date")
	WebElement pickUpDate;

	@FindBy(id = "human-return-date")
	WebElement returnDate;

	@FindBy(id = "pickup_location_id")
	WebElement pickupLocation;

	@FindBy(id = "dropoff_location_id")
	WebElement dropOffLocation;

	public void enterLocation(String location) {
		singleLocation.sendKeys(location);
	}
}
