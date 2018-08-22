package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.WaitUtil;
 

public class AddGuestsPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public AddGuestsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".icon-plus")
	WebElement plus;

	@FindBy(css = ".experiences-order-summary-total-price")
	WebElement totalAmt;

	@FindBy(className = "experiences-order-summary-points")
	WebElement rewardsText;

	public Double increment() {
		Double totalAmount = getTotalAmount();
		plus.click();
		sleep(5);
		Assert.assertEquals(getTotalAmount(), totalAmount * 2);
		Assert.assertTrue(rewardsText.getText().contains("Points"));
		return totalAmount * 2;
	}

	private Double getTotalAmount() {
		String totalAmtValue = totalAmt.getText();
		String[] split = totalAmtValue.split(" ");
		String substring = split[0].substring(1, split[0].indexOf("\n"));
		System.out.println("SubString::" + substring);
		Double actualAmount = Double.valueOf(substring);
		return actualAmount;
	}

	@FindBy(className = "product-booking-option-edit")
	List<WebElement> editLinks;

	@FindBy(className = "product-booking-edit-date")
	WebElement calender;

	public void clickOnDateEdit() {
		sleep(5);
		editLinks.get(0).click();
	}
	
	public void clickOnOptionsEdit() {
		sleep(5);
		editLinks.get(1).click();
	}

	@FindBy(linkText = "Select a Date")
	List<WebElement> selectDateButton;

	@FindBy(xpath = "//div[not(contains(@class,'CalendarMonthGrid_month__hidden'))][contains(@class,'CalendarMonthGrid_month__horizontal')]//td[contains(@class,'CalendarDay__default_2')]")
	List<WebElement> enabledDates;

	@FindBy(xpath = "//td[not(contains(@class,'elm-datepicker--disabled'))][contains(@class,'elm-datepicker--day')]")
	List<WebElement> enabledDatesNew;

	@FindBy(css = "div.elm-datepicker--picker")
	WebElement datePickerPopUp;

	@FindBy(css = ".widget-launcher")
	WebElement calander;

	public String selectDate() {
		String date = null;
		if (selectDateButton.size() > 0) {
			WaitUtil.waitForElementToBeDisplayed(driver, datePickerPopUp, 60);
			date = enabledDatesNew.get(0).getText();
			enabledDatesNew.get(0).click();
		} else {
			date = enabledDates.get(0).getText();;
			enabledDates.get(0).click();
		}
		sleep(2);
		return date;
	}
	

	@FindBy(xpath = "//button[contains(text(),'Review & Book')]")
	List<WebElement> reviewAndBook;

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	List<WebElement> checkOut;
	
	public void clickBookOrCheckOut(){
		if (reviewAndBook.size() > 0)
			reviewAndBook.get(0).click();
		else if (checkOut.size() > 0)
			checkOut.get(0).click();
	}

}
