package com.placepass.marriottMoments.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;
import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.RestUtil;

public class HomePage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		System.out.println("Home Page Object created");
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	/*
	 * This will return one product shelf based on the title
	 */
	private WebElement getContainer(String title) {
		WebElement titleElement = driver
				.findElement(By.xpath("//div[contains(@class,'container-for-grid')]//h2[text()='" + title
						+ "']//ancestor::div[contains(@class,'shelf-title-bar')]/.."));
		String str = titleElement.getText();
		return titleElement;
	}

	/*
	 * returns the shelf Description
	 */
	private String getShelfDescription(String title) {
		WebElement container = getContainer(title);
		return container.findElement(By.xpath(".//div[@class='shelf-description']")).getText();
	}

	/*
	 * returns the shelf sub title
	 */
	private String getShelfSubTitle(String title) {
		WebElement container = getContainer(title);
		return container.findElement(By.xpath(".//h4[@class='shelf-subtitle']")).getText();
	}

	/*
	 * returns the see All link of the product shelf
	 */
	private WebElement getSeeAllLink(String title) {
		return getContainer(title).findElement(By.xpath(".//a[@class='see-all-link']"));
	}

	/*
	 * returns all the cards of a product shelf
	 */
	private List<WebElement> getAllShelfProducts(String title) {
		List<WebElement> list = getContainer(title).findElements(By.cssSelector("a span"));
		return getContainer(title).findElements(By.cssSelector("a span"));
	}

	/*
	 * returns a card based on the card Title
	 */
	private WebElement cardElement(String shelfTitle, String cardTitle) {
		WebElement cardElement = getContainer(shelfTitle).findElement(
				By.xpath(".//div[@class='shelf-products']/a//div[@class='experience-card-info']/h2[text()='" + cardTitle
						+ "']/parent::div"));
		return cardElement;
	}

	/*
	 * returns the Destination card based on the card title
	 */
	private WebElement destinationCardElement(String shelfTitle, String cardTitle) {
		WebElement cardElement = getContainer(shelfTitle).findElement(
				By.xpath(".//div[@class='shelf-products']/a//div[@class='experience-card-destination-name' and text()='"
						+ cardTitle + "']"));
		return cardElement;
	}

	@FindBy(xpath = "//a[contains(@class,'experience-card-destination')]")
	List<WebElement> destinationsWeLoveList;

	private WebElement getDestinationWeLove(String city) {
		return driver.findElement(
				By.xpath("//a[contains(@class,'experience-card-destination')]//div[text()='" + city + "']"));
	}

	public void clickDestinationWeLove(String city) {
		System.out.println("Cick on Destinatin card:" + city + ", under Destination We love panel");
		getDestinationWeLove(city).click();
	}

	@FindBy(xpath = "//*[@href]")
	List<WebElement> allLinks;

	public void verifyHomePageLinks() {
		RestUtil ru = new RestUtil();
		for (WebElement ele : allLinks) {
			String href = ele.getAttribute("href");

			// if (href != "" && !href.contains("/N/A") && !href.contains("javascript")) {
			if (href.contains("http")) {
				if (!href.contains("/N/A") && !href.contains("javascript") && !href.contains("googleapis")) {
					System.out.println("verifying for : " + ele.getText() + "  link");
					System.out.println(href);
					int respCode = ru.get(href);
					Assert.assertTrue(respCode == 200);
					System.out.println("------------------------");
				}
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

	public void searchByClickingDropdown(String location) {
		searchBox.clear();
		searchBox.sendKeys(location);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("global-search-bar-dropdown")));
		driver.findElement(By.xpath(".//ul/li[text()='" + location + "']")).click();
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

	@FindBy(className = "member-exclusives-nav-link")
	WebElement memberExclusiveLink;

	public void clickOnMemberExclusive() {
		memberExclusiveLink.click();
	}

	public void clickHertzRentals(int itemIndex) {
		List<WebElement> hertzRentals = getAllShelfProducts("Earn Marriott Rewards Points on Hertz Rentals"); 
		DriverUtils.jsClick(driver, hertzRentals.get(itemIndex-1));
	}

	public void verifyNavigateToRentalCarsPage() {
		verifyUrl("cars");
	}

	private void verifyUrl(String subStringURL) {
		System.out.println("Verifying url:" + subStringURL);
		DriverUtils.switchToLatestWindow(driver);
		sleep(2);
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains(subStringURL), "not redirected to correct url");
	}

	public void clickExclusiveSPG(int index) {
		List<WebElement> list = new ArrayList<WebElement>();
		List<WebElement> exclusiveMRAndSPG = getAllShelfProducts(
				"Exclusive SPG and Marriott Rewards Member Experiences");
		for (WebElement ele : exclusiveMRAndSPG) {
			if (ele.getText().contains("SPG Member Exclusive")) {
				list.add(ele);
			}
		}
		DriverUtils.jsClick(driver, list.get(index - 1));
		System.out.println("clicked on :" + index + ", Car.");
	}

	public void verifyMemberExclusiveSPGNavigation() {
		verifyUrl("item");
	}

	public void clickMarriatrewards(int index) {
		List<WebElement> list = new ArrayList<WebElement>();
		List<WebElement> exclusiveMRAndSPG = getAllShelfProducts(
				"Exclusive SPG and Marriott Rewards Member Experiences");
		for (WebElement ele : exclusiveMRAndSPG) {
			String str = ele.getText();
			if (ele.getText().equalsIgnoreCase("Marriott Rewards Exclusive")) {
				list.add(ele);
			}
		}
		DriverUtils.jsClick(driver, list.get(index - 1));
		System.out.println("clicked on :" + index + ", Car.");
	}

	public void verifyMarriotrewardsNavigation() {
		verifyUrl("item");
	}

	@FindBy(xpath = "//*[@id='6a1369c7-8567-4fad-b5e9-dd8a6b669f85']//a[@class = 'see-all-link']")
	WebElement favoriteExperiences;

	public void clickOnSeeAll(WebElement element) {
		element.click();
	}

	public void verifySeeAll(String cityName) {
		clickOnSeeAll(favoriteExperiences);
		DriverUtils.switchToLatestWindow(driver);
		sleep(5);
		DestinationPage dp = new DestinationPage(driver);
		dp.verifyCity(cityName);
	}

	@FindBy(linkText = "Sign In")
	WebElement signInLink;

	public void clickOnSignIn() {
		signInLink.click();
	}

	@FindBy(linkText = "My Account")
	WebElement myAccountLink;

	public void clickOnMyAccount() {
		myAccountLink.click();
	}

	private void clickOnSeeAllLink(WebElement seeAllLink) {
		PageLevelUtils util = new PageLevelUtils();
		util.bringElementInView(driver, seeAllLink);
		sleep(5);
		seeAllLink.click();
		DriverUtils.switchToLatestWindow(driver);
	}

	public void clickOnSeeAllForEveryTypeofTraveler() {
		WebElement seeAllLink = getSeeAllLink("For Every Type of Traveler");
		clickOnSeeAllLink(seeAllLink);
	}

	@FindBy(className = "playlist-hero-headline")
	WebElement headlineText;

	public void verifySeeAllForEveryTypeofTraveler() {
		Assert.assertEquals(headlineText.getText(), "For Every Type of Traveler");
	}

	public void clickAndVerifySeeAllForNewYorkEvents() {
		WebElement seeAllLink = getSeeAllLink("New York Events");
		clickOnSeeAllLink(seeAllLink);
		verifyUrl("stubhub.com");
	}

	@FindBy(className = "search-results-not-found")
	WebElement searchResultsNotFound;

	public void verifyResultsNotFound() {
		System.out.println("Searh results Not found Text::" + searchResultsNotFound.getText());
		Assert.assertTrue(searchResultsNotFound.getText().contains("Sorry, no results found"));
	}

	@FindBy(linkText = "Help")
	WebElement helpLink;

	public void clickOnHelpMenu() {
		helpLink.click();
	}

	@FindBy(linkText = "Rental Cars")
	WebElement rentalCarsLink;

	public void clickOnRentalCars() {
		rentalCarsLink.click();
	}
}
