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

import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.WaitUtil;

public class BookPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public BookPage(WebDriver driver) {
		System.out.println("Book Page Object created");
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".widget-launcher")
	WebElement calander;

	@FindBy(css = "button.product-booking-confirm-date")
	WebElement bookButton;

	@FindBy(linkText = "Select a Date")
	List<WebElement> selectDateButton;

	@FindBy(css = "div.elm-datepicker--picker")
	WebElement datePickerPopUp;

	public void verifyBookingCalander() {
		if (selectDateButton.size() > 0) {
			selectDateButton.get(0).click();
			WaitUtil.waitForElementToBeDisplayed(driver, datePickerPopUp, 10);
		} else {
			WaitUtil.waitForElementToBeDisplayed(driver, calander, 10);
			WaitUtil.waitForElementToBeDisplayed(driver, bookButton, 10);
		}
		Assert.assertTrue(true);
	}

	public void verifyAvailability(String dates) {
		if (selectDateButton.size() > 0) {
			selectDateButton.get(0).click();
			WaitUtil.waitForElementToBeDisplayed(driver, datePickerPopUp, 10);
			for (String s : dates.split(",")) {
				String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
				Assert.assertTrue(isDateFoundInPopUp(txt));
			}
		} else {
			WaitUtil.waitForElementToBeDisplayed(driver, calander, 10);
			WaitUtil.waitForElementToBeDisplayed(driver, bookButton, 10);

			for (String s : dates.split(",")) {
				String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
				Assert.assertTrue(isDateFoundInline(txt));
			}
		}
	}

	@FindBy(css = "button i.icon-chevron-left")
	WebElement prevMonth;

	@FindBy(css = "button i.icon-chevron-right")
	WebElement nextMonthInline;

	@FindBy(css = "div.elm-datepicker--prev-container a.elm-datepicker--prev")
	WebElement prevMonth1;

	@FindBy(css = "div.elm-datepicker--next-container a.elm-datepicker--next")
	WebElement nextMonthPopUp;

	public void book() {
		if (selectDateButton.size() > 0) {
			selectDateButton.get(0).click();
			WaitUtil.waitForElementToBeDisplayed(driver, datePickerPopUp, 20);
			while (enabledDatesPopUp.size() <= 0) {
				sleep(2);
				nextMonthPopUp.click();
				sleep(2);
			}
			enabledDatesPopUp.get(0).click();
		} else {
			WaitUtil.waitForElementToBeDisplayed(driver, calander, 20);
			while (enabledDatesInline.size() <= 0) {
				sleep(2);
				nextMonthInline.click();
				sleep(2);
			}
			enabledDatesInline.get(0).click();
			new PageLevelUtils().bringElementInView(driver, bookButton);
			bookButton.click();
		}
		sleep(2);
	}

	public boolean isDateFoundInPopUp(String s) {
		boolean result = false;
		for (WebElement ele : enabledDatesPopUp) {
			String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
			if (ele.getText().contentEquals(txt)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean isDateFoundInline(String s) {
		boolean result = false;
		for (WebElement ele : enabledDatesInline) {
			String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
			if (ele.getText().contentEquals(txt)) {
				result = true;
				break;
			}
		}
		return result;
	}

	// $x("//td[not(contains(@class,'elm-datepicker--disabled'))][contains(@class,'elm-datepicker--day')][not(contains(@class,'elm-datepicker--other-month'))]")
	@FindBy(xpath = "//td[not(contains(@class,'elm-datepicker--disabled'))][contains(@class,'elm-datepicker--day')][not(contains(@class,'elm-datepicker--other-month'))]")
	List<WebElement> enabledDates;

	// $x("//td[not(contains(@class,'elm-datepicker--disabled'))][contains(@class,'elm-datepicker--day')][not(contains(@class,'elm-datepicker--other-month'))]")
	@FindBy(xpath = "//td[not(contains(@class,'elm-datepicker--disabled'))][contains(@class,'elm-datepicker--day')][not(contains(@class,'elm-datepicker--other-month'))]")
	List<WebElement> enabledDatesPopUp;

	@FindBy(xpath = "//td[not(contains(@class,'CalendarDay__blocked_calendar'))][contains(@class,'CalendarDay__default_2')]")
	List<WebElement> enabledDatesNew__l;

	@FindBy(xpath = "//td[not(contains(@class,'CalendarDay__blocked_calendar'))][contains(@class,'CalendarDay__default_2')]")
	List<WebElement> enabledDatesInline;

	@FindBy(xpath = "//label[@for='MAR']")
	public WebElement marriottRewardsPointsRadioBtn;

	@FindBy(xpath = "//label[@for='SPG']")
	public WebElement starPointsRadioBtn;

	public void clickOnStarPointsRadioBtn() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("rewards-points-toggle-option")));
		starPointsRadioBtn.click();
	}

	@FindBy(className = "experiences-order-summary-points")
	WebElement pointsText;

	public void verifyStarPointsRadioBtn() {
		Assert.assertTrue(pointsText.getText().contains("SPG Points"));
	}

	@FindBy(className = "product-name")
	WebElement productName;

	public void verifyProductName(String productNameStr) {
		wait.until(ExpectedConditions.visibilityOf(productName));
		System.out.println("Product Name is:" + productName);
		Assert.assertEquals(productNameStr, productName.getText());
	}
}
