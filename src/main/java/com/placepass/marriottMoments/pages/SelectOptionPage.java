package com.placepass.marriottMoments.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;

public class SelectOptionPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public SelectOptionPage(WebDriver driver) {
		System.out.println("Select Option Page Object created");
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#pick-an-option .booking-option-radio")
	List<WebElement> options;

	@FindBy(css = ".icon-plus")
	List<WebElement> plusList;

	@FindBy(xpath = "//button[contains(text(),'Review & Book')]")
	List<WebElement> reviewAndBook;

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	List<WebElement> checkOut;

	@FindBy(css = "div.product-booking-ticket-row")
	List<WebElement> addGuestOptionsList;

	public void selectAddGuests(String type) {
		sleep(5);
		options.get(0).click();
		sleep(5);

		// Choose Add Guests
		// Map<String, String> uiMap = readAddGuestUI();
		cleanAddGuests();
		if (type.equalsIgnoreCase("Single"))
			plusList.get(0).click();
		else {
			for (WebElement ele : plusList)
				ele.click();
		}
		// click on Review And Book
		if (reviewAndBook.size() > 0)
			DriverUtils.jsClick(driver, reviewAndBook.get(0));
		else if (checkOut.size() > 0)
			DriverUtils.jsClick(driver, checkOut.get(0));
	}

	public Map<String, String> readAddGuestUI() {
		Map<String, String> result = new HashMap<>();
		for (WebElement ele : addGuestOptionsList) {
			WebElement e = ele.findElement(By.cssSelector("div.product-booking-control-ticket-selection"));
			if (ele.getText().contains("Adult")) {
				result.put("Adult", e.getText().replace("Adult", "").replace("(13+)", "").trim());
			} else if (ele.getText().contains("Child")) {
				result.put("Child",
						e.getText().replace("Child", "").replace("(5-15)", "").replace("(4-12)", "").trim());
			} else if (ele.getText().contains("Infant")) {
				result.put("Infant", e.getText().replace("Infant", "").replace("(3-4)", "").trim());
			} else if (ele.getText().contains("Standard")) {
				result.put("Standard", e.getText().replace("Standard", "").trim());
			} else if (ele.getText().contains("Senior")) {
				result.put("Senior", e.getText().replace("Senior", "").replace("(66+)", "").trim());
			}
		}
		return result;
	}

	@FindBy(css = "i.icon-minus")
	List<WebElement> allMinusButtons;

	public void cleanAddGuests() {
		Map<String, String> uiMap = readAddGuestUI();
		for (String key : uiMap.keySet()) {
			System.out.println(">>>>>>." + uiMap.get(key).toString());
			int count = Integer.parseInt(uiMap.get(key).toString());
			if (count > 0) {
				for (int i = 0; i < count; i++)
					DriverUtils.jsClick(driver, getMinus(key).findElement(By.cssSelector(".icon-minus")));
			}
		}

	}

	public WebElement getMinus(String guest) {
		WebElement result = null;
		for (WebElement ele : addGuestOptionsList) {
			if (ele.getText().contains(guest)) {
				result = ele;
				break;
			}
		}
		return result;
	}

	public void selectFirstOption() {
		sleep(5);
		options.get(0).click();
	}

	@FindBy(xpath = "//h1[@class='contrast-header']")
	WebElement selectOptionsText;

	public void verifySelectOptionsPage() {
		System.out.println("Select options Text::" + selectOptionsText.getText());
		Assert.assertTrue(selectOptionsText.getText().equals("Select an Option"));
	}

	@FindBy(className = "experiences-order-option-headline")
	List<WebElement> orderOptions;

	public void verifyDate(String date) {
		Assert.assertTrue(orderOptions.get(0).getText().contains(date));
	}
}
