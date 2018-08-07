package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;
import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.WaitUtil;

 

public class DestinationPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public DestinationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1")
	WebElement headder;

	public void verifyCity(String city) {
		WaitUtil.waitForElementToBeDisplayed(driver, headder, 60);
		Assert.assertEquals(headder.getText(), city);
	}

	@FindBy(css = "#search-location")
	WebElement searchLocation;

	public void verifyLocation(String city) {
		WaitUtil.waitForElementToBeDisplayed(driver, searchLocation, 60);
		Assert.assertEquals(searchLocation.getAttribute("Value"), city);
	}

	@FindBy(css = ".experience-card")
	List<WebElement> allproducts;

	public void chooseProduct() {
		wait.until(ExpectedConditions.elementToBeClickable(allproducts.get(0)));
		sleep(15);
		allproducts.get(0).click();
		DriverUtils.switchToLatestWindow(driver);
		sleep(15);
	}

	@FindBy(css = "#max-price")
	WebElement maxPriceTextBox;

	@FindBy(css = "#experience")
	WebElement keyWordSearchTextBox;

	@FindBy(css = "span.price")
	List<WebElement> priceOfProduct;

	public void verifyPriceFilter(int price) {
		maxPriceTextBox.clear();
		maxPriceTextBox.sendKeys(price + "");
		keyWordSearchTextBox.click();
		sleep(10);

		for (WebElement ele : priceOfProduct) {
			String p = ele.getText().replace("$", "").replace("USD", "");
			int i = Integer.parseInt(p);
			Assert.assertTrue(i <= price);
		}
	}

	@FindBy(name = "start-date")
	WebElement startDateTextBox;

	@FindBy(name = "end-date")
	WebElement endDateTextBox;

	@FindBy(linkText = "Done")
	WebElement calDoneButton;

	@FindBy(linkText = "Clear")
	WebElement calClearButton;

	public void enterDate1(String fromDate, String toDate) {
		startDateTextBox.clear();
		startDateTextBox.sendKeys(fromDate);

		endDateTextBox.clear();
		endDateTextBox.sendKeys(toDate);
		endDateTextBox.click();
		sleep(5);
		keyWordSearchTextBox.click();
		// calDoneButton.click();
		sleep(5);
	}

	public void enterDate(String fromDate, String toDate) {
		startDateTextBox.click();
		sleep(3);
		selectDate(fromDate.split("-")[1]);
		sleep(3);
		selectDate(toDate.split("-")[1]);
		sleep(3);
		new PageLevelUtils().bringElementInView(driver, calDoneButton);
		calDoneButton.click();
		sleep(5);
	}

	@FindBy(xpath = "//div[not(contains(@class,'CalendarMonthGrid_month__hidden'))][contains(@class,'CalendarMonthGrid_month__horizontal')]//td[contains(@class,'CalendarDay__default_2')]")
	List<WebElement> enabledDates;

	public String chooseProduct(String fromDate, String toDate) {
		String oldWindow = driver.getWindowHandle();
		enterDate(fromDate, toDate);
		allproducts.get(0).click();
		DriverUtils.switchToLatestWindow(driver);
		sleep(15);
		return oldWindow;
	}

	public void selectDate(String s) {
		String text = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
		for (WebElement ele : enabledDates) {
			if (ele.getText().contentEquals(text)) {
				ele.click();
				break;
			}
		}
	}

}
