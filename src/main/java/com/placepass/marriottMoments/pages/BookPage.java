package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.WaitUtil;

 

public class BookPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public BookPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	// div.CalendarMonth
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
			WaitUtil.waitForElementToBeDisplayed(driver, datePickerPopUp, 60);
		} else {
			WaitUtil.waitForElementToBeDisplayed(driver, calander, 60);
			WaitUtil.waitForElementToBeDisplayed(driver, bookButton, 60);
		}
		Assert.assertTrue(true);
	}

	public void verifyAvailability(String dates) {
		if (selectDateButton.size() > 0) {
			selectDateButton.get(0).click();
			WaitUtil.waitForElementToBeDisplayed(driver, datePickerPopUp, 60);
			for (String s : dates.split(",")) {
				String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
				Assert.assertTrue(isDateFoundNew(txt));
			}
		} else {
			WaitUtil.waitForElementToBeDisplayed(driver, calander, 60);
			WaitUtil.waitForElementToBeDisplayed(driver, bookButton, 60);
			for (String s : dates.split(",")) {
				String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
				Assert.assertTrue(isDateFoundNew(txt));
			}
		}
	}

	public void book() {
		if (selectDateButton.size() > 0) {
			selectDateButton.get(0).click();
			WaitUtil.waitForElementToBeDisplayed(driver, datePickerPopUp, 60);
			enabledDatesNew.get(0).click();
		} else {
			WaitUtil.waitForElementToBeDisplayed(driver, calander, 60);
			WaitUtil.waitForElementToBeDisplayed(driver, bookButton, 60);
			enabledDates.get(0).click();
			new PageLevelUtils().bringElementInView(driver, bookButton);
			bookButton.click();
		}

		sleep(2);
	}

	public boolean isDateFound(String s) {
		boolean result = false;
		for (WebElement ele : enabledDates) {
			String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
			// Assert.assertTrue(isDateFoundNew(txt));
			if (ele.getText().contentEquals(txt)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public boolean isDateFoundNew(String s) {
		boolean result = false;
		for (WebElement ele : enabledDatesNew) {
			String txt = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;

			if (ele.getText().contentEquals(txt)) {
				result = false;
				break;
			}
		}
		return result;
	}

	@FindBy(xpath = "//div[not(contains(@class,'CalendarMonthGrid_month__hidden'))][contains(@class,'CalendarMonthGrid_month__horizontal')]//td[contains(@class,'CalendarDay__default_2')]")
	List<WebElement> enabledDates;

	@FindBy(xpath = "//td[not(contains(@class,'elm-datepicker--disabled'))][contains(@class,'elm-datepicker--day')]")
	List<WebElement> enabledDatesNew;
}
