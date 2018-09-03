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
import com.placepass.marriottMoments.utils.WaitUtil;

public class DestinationPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public DestinationPage(WebDriver driver) {
		System.out.println("Destination Page Object created");
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1")
	WebElement headder;

	public void verifyCity(String city) {
		System.out.println("Verify city:" + city + " @ Destination page");
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

	public void enterPriceFilter(int price) {
		maxPriceTextBox.clear();
		maxPriceTextBox.sendKeys(price + "");
		maxPriceTextBox.sendKeys(Keys.TAB);
		sleep(15);
	}

	public void verifyPriceFilter(int price) {
		for (WebElement ele : priceOfProduct) {
			String p = ele.getText().replace("$", "").replace("USD", "");
			int i = Integer.parseInt(p);
			Assert.assertTrue(i <= price);
		}
	}

	public void verifyContent(String[] text) {
		allproducts.get(0).click();
		DriverUtils.switchToLatestWindow(driver);
		for (String s : text) {
			String textToCheck = "";
			if (s.contains("Bik"))
				textToCheck = "bike";
			else if (s.contains("well"))
				textToCheck = "spa";
			Assert.assertTrue(driver.getPageSource().contains(textToCheck));
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

	@FindBy(id = "start-date")
	WebElement fromDate;

	@FindBy(id = "end-date")
	WebElement toDate;

	@FindBy(css = "button i.icon-chevron-right")
	WebElement nextMonthButton;

	public void enterDate(int nextMonth, String from, String to,int price) {
		startDateTextBox.click();
		sleep(3);
		for (int i = 1; i <= nextMonth; i++) {
			sleep(2);
			nextMonthButton.click();
			sleep(2);
		}
		selectDate(from.split("-")[1]);
		sleep(3);
		selectDate(to.split("-")[1]);
		sleep(3);
		// fromDate.sendKeys(from);
		// toDate.sendKeys(to);
		DriverUtils.jsClick(driver, calDoneButton);
		enterPriceFilter(price);
		
	}

	@FindBy(xpath = "//div[not(contains(@class,'CalendarMonthGrid_month__hidden'))][contains(@class,'CalendarMonthGrid_month__horizontal')]//td[contains(@class,'CalendarDay__default_2')]")
	List<WebElement> enabledDates;

	public String chooseProduct(int nextMonth, String fromDate, String toDate,int price) {
		String oldWindow = driver.getWindowHandle();
		enterDate(nextMonth, fromDate, toDate, price);
		allproducts.get(0).click();
		DriverUtils.switchToLatestWindow(driver);
		sleep(15);
		return oldWindow;
	}

	public void selectDate(String s) {
		String text = Integer.parseInt(s) < 10 ? s.replace("0", "") : s;
		for (WebElement ele : enabledDates) {
			if (ele.getText().contentEquals(text)) {
				DriverUtils.jsClick(driver, ele);
				break;
			}
		}
	}

	@FindBy(css = ".categories-filter")
	WebElement activityDropDown;

	public void selectActivityDropdown(String[] activities) {
		activityDropDown.click();
		for (String activity : activities) {
			WebElement ele = driver.findElement(By.xpath("//input[@value='" + activity + "']/../label"));
			ele.click();
			sleep(2);
		}
		sleep(15);
		// to collapse the dropdown
		activityDropDown.click();
	}

	@FindBy(css = ".ratings-filter")
	WebElement ratingsDropdown;

	// valid values - any,1,2,3,4,5
	public void selectRating(String rating) {
		ratingsDropdown.click();
		String temp = rating.equalsIgnoreCase("any") ? "any-rating" : "rating-" + rating;
		if (rating.equalsIgnoreCase("any")) {
			temp = "any-rating";
		}
		WebElement ele = driver.findElement(By.xpath("//label[@for='" + temp + "']"));
		ele.click();

		WebElement ele1 = ratingsDropdown.findElement(By.className("dropdown-select-trigger-label"));
		if (rating.equalsIgnoreCase("any")) {
			Assert.assertEquals(ele1.findElement(By.tagName("div")).getText(), "Any");
		} else {
			List<WebElement> stars = ele1.findElements(By.className("icon-star-filled"));
			Assert.assertEquals(stars.size(), Integer.parseInt(rating));
		}
		sleep(10);
	}

	@FindBy(css = ".star-rating")
	List<WebElement> ratingOfProduct;

	public void verifyRatingFilter(String rating) {
		for (WebElement ele : ratingOfProduct) {
			WebElement ratingEle = ele.findElement(By.className("stars"));
			String ratingValue = ratingEle.getAttribute("data-product-rating");
			Assert.assertTrue((int) Double.parseDouble(ratingValue) >= Integer.parseInt(rating));
		}
	}

	public void enterKeywords(String keyword) {
		keyWordSearchTextBox.sendKeys(keyword);
		sleep(10);
	}

	@FindBy(xpath = "//div[contains(@class, 'experience-search-clear-filters-button')]/div")
	WebElement clearBtn;

	public void clickClearBtn() {
		clearBtn.click();
	}

	public void verifyClear(String fromDate, String toDate) {
		System.out.println(startDateTextBox.getAttribute("value") + "||" + fromDate + "|| " + maxPriceTextBox.getText()
				+ " ||" + activityDropDown.findElement(By.className("dropdown-select-trigger-label")).getText() + "||"
				+ keyWordSearchTextBox.getText());
		Assert.assertTrue(startDateTextBox.getAttribute("value").equals(fromDate));
		Assert.assertTrue(endDateTextBox.getAttribute("value").equals(toDate));
		Assert.assertTrue(maxPriceTextBox.getText().equals(""));
		Assert.assertTrue(
				activityDropDown.findElement(By.className("dropdown-select-trigger-label")).getText().equals("Select"));
		Assert.assertTrue(
				ratingsDropdown.findElement(By.className("dropdown-select-trigger-label")).getText().equals("Any"));
		Assert.assertTrue(keyWordSearchTextBox.getText().equals(""));
	}

	@FindBy(className = "search-results-not-found")
	WebElement searchResultsNotFound;

	@FindBy(className = "no-results-clear-filters-link")
	WebElement clearAllFiltersLink;

	public void clinkOnClearAllFiltersLink() {
		clearAllFiltersLink.click();
	}

	public void verifySearchResultsNotFoundText() {
		String text = searchResultsNotFound.getText();
		System.out.println(text);
		Assert.assertEquals(text,
				"Sorry, no results found.\nTry changing your dates, removing some filters, or clear all filters");
	}

	@FindBy(className = "pagination-link")
	List<WebElement> paginationLinks;

	public void clickOnPagination(String page) {
		if (page.equalsIgnoreCase("previous")) {
			paginationLinks.get(0).click();
		} else if (page.equalsIgnoreCase("next")) {
			paginationLinks.get(paginationLinks.size() - 1).click();
		} else {
			for (WebElement pagination : paginationLinks) {
				if (pagination.getText().equals(page)) {
					System.out.println(pagination.getText());
					new PageLevelUtils().bringElementInView(driver, pagination);
					pagination.click();
					break;
				}
			}
		}
	}

	@FindBy(css = ".results-count")
	WebElement resultsCount;

	public void verifyPagination(String page) {
		String[] split = resultsCount.getText().split("-");
		Integer a = (Integer.parseInt(page) * 20) - 19;

		Assert.assertTrue(split[0].contains(a.toString()));
		Assert.assertTrue(driver.getCurrentUrl().contains("page=" + (Integer.parseInt(page) - 1)));
	}

	public void enterDestinationGlobalSearch(String location) {
		searchLocation.clear();
		searchLocation.sendKeys(location);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("global-search-bar-dropdown")));
		driver.findElement(By.xpath(".//ul/li[text()='" + location + "']")).click();
	}

	@FindBy(className = "search-hero-headline")
	WebElement searchHeadline;

	public void verifySearchHeadline(String headline) {
		Assert.assertTrue(searchHeadline.getText().equalsIgnoreCase(headline));
	}

	@FindBy(className = "experience-card-title")
	List<WebElement> cardTitles;

	public void verifyKeywordSearch(String keyword) {
		// list of cards not having the keyword
		List<WebElement> list = new ArrayList<WebElement>();
		int i = 0;
		for (WebElement title : cardTitles) {
			i = i + 1;
			if (title.getText().contains(keyword)) {
			} else {
				list.add(title);
			}
			if (i == 3)
				continue;

		}
		i = 0;
		if (list.size() == 0) {
			Assert.assertTrue(true);
		} else {
			i = i + 1;
			// making sure that atleast 1 card will have the keyword
			// Assert.assertTrue(titleCount > 0);
			String parentWindowHandle = driver.getWindowHandle();
			for (WebElement card : list) {
				new PageLevelUtils().bringElementInView(driver, card);
				card.click();
				DriverUtils.switchToLatestWindow(driver);
				String source = driver.getPageSource();
				Assert.assertTrue(source.contains(keyword));
				driver.close();
				driver.switchTo().window(parentWindowHandle);
				if (i == 3)
					continue;
			}
		}
	}

	/*
	 * This will return one product shelf based on the title
	 */
	private WebElement getContainer(String title) {
		WebElement titleElement = driver
				.findElement(By.xpath("//div[contains(@class,'container-for-grid')]//h2[text()='" + title
						+ "']//ancestor::div[contains(@class,'container-for-grid')]"));
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
		return getContainer(title).findElements(By.xpath(".//div[@class='shelf-products']/a"));
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

	public String clickOnFirstMostPopularProduct() {
		List<WebElement> allMostPopularShelfProducts = getAllShelfProducts("Most Popular");
		String cardTitle = null;
		if (allMostPopularShelfProducts.size() > 0) {
			cardTitle = allMostPopularShelfProducts.get(0).findElement(By.className("experience-card-title")).getText();
			allMostPopularShelfProducts.get(0).click();
		}
		DriverUtils.switchToLatestWindow(driver);
		return cardTitle;
	}

}
