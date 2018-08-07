package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;
import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.RestUtil;
import com.placepass.marriottMoments.utils.WaitUtil;


public class HomePage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(@class,'experience-card-destination')]")
	List<WebElement> destinationsWeLoveList;

	private WebElement getDestinationWeLove(String city) {
		return driver.findElement(
				By.xpath("//a[contains(@class,'experience-card-destination')]//div[text()='" + city + "']"));
	}

	public void clickDestinationWeLove(String city) {
		getDestinationWeLove(city).click();
	}

	@FindBy(xpath = "//*[@href]")
	List<WebElement> allLinks;

	public void verifyHomePageLinks() {
		RestUtil ru = new RestUtil();
		for (WebElement ele : allLinks) {
			String href = ele.getAttribute("href");
			System.out.println("verifying for : " + ele.getText() + "  link");
			System.out.println(href);
			// if (href != "" && !href.contains("/N/A") && !href.contains("javascript")) {
			if (href.contains("http")) {
				int respCode = ru.get(href);
				Assert.assertTrue(respCode == 200);
				System.out.println("------------------------");
			}
		}
	}

	@FindBy(id = "search-location")
	WebElement searchBox;

	public void searchCity(String city) {
		searchBox.clear();
		searchBox.sendKeys(city);
		sleep(10);
		searchBox.sendKeys(Keys.DOWN);
		searchBox.sendKeys(Keys.ENTER);
	}

	@FindBy(css = "button.global-search-submit")
	WebElement searchButton;

	public void search(String text) {
		searchBox.clear();
		searchBox.sendKeys(text);
		searchButton.click();
	}

	@FindBy(xpath = "//div[@class='shelf-title-bar']//h2[contains(text(),'New York Events')]")
	WebElement ny_notElegibleForPoints;

	@FindBy(xpath = "//div[@class='shelf-title-bar']//h2[contains(text(),'New York Events')]//ancestor::div[@class='container-for-grid shelf stubhub-shelf']//div[@class='shelf-products']")
	WebElement ny_notElegibleForPointsContainer;

	private List<WebElement> getAllNyNotElegibleForPointsLinks() {
		return ny_notElegibleForPointsContainer.findElements(By.tagName("a"));
	}

	public void verifyNyNotElegibleForPointsPageRedirection() {
		getAllNyNotElegibleForPointsLinks().get(0).click();
		DriverUtils.switchToLatestWindow(driver);
		sleep(15);
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("stubhub"), "not redirected to correct url");
	}
}
