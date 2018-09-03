package com.placepass.marriottMoments.tests;

import java.util.Calendar;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.BookPage;
import com.placepass.marriottMoments.pages.DestinationPage;
import com.placepass.marriottMoments.pages.HomePage;
import com.placepass.marriottMoments.utils.DateUtils;
import com.placepass.marriottMoments.utils.PageLevelUtils;

public class DestinationPageCases extends BaseTest {

	@Test(description = "TC_TA_11")
	public void tc_ta_011_verifyAavilabityDates() {
		PageLevelUtils pUtils = new PageLevelUtils();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);

		String dt1 = DateUtils.getFormatedDate("MM-dd-yy", c);
		String dt2 = DateUtils.getFormatedDate("MM-dd-yy", c);
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		String oldWindow = dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.verifyAvailability(dt1.split("-")[1] + "," + dt2.split("-")[1]);
		pUtils.closeAllBrowserExcept(driver, oldWindow);
	}

	@Test(description = "TC_TA_12")
	public void tc4_verifyAavilabityDates() {
		Calendar c = DateUtils.getDate(0);
		c.add(Calendar.MONTH, 2);
		PageLevelUtils pUtils = new PageLevelUtils();
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", c);
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", c);
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		String oldWindow = dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.verifyAvailability(dt1.split("-")[1] + "," + dt2.split("-")[1]);
		pUtils.closeAllBrowserExcept(driver, oldWindow);
	}

	@Test(description = "TC_TA_13")
	public void TC_TA_13_maxPriceValidation() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterPriceFilter(10);
		dp.verifyPriceFilter(10);
	}

	@Test(description = "TC_TA_014")
	public void tc_ta_014_selectSingleActivity() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		String[] arr = new String[] { "Walking & Biking" };
		dp.selectActivityDropdown(arr);
		dp.verifyContent(arr);
	}

	@Test(description = "TC_TA_015")
	public void tc_ta_015_selectMultipleActivities() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);

		String[] arr = { "Walking & Biking", "Wellness" };
		dp.selectActivityDropdown(arr);
		dp.verifyContent(arr);
	}

	@Test(description = "TC_TA_016")
	public void tc_ta_016_ratingFilters() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.selectRating("4");
		dp.verifyRatingFilter("4");
	}

	@Test(description = "TC_TA_017")
	public void tc_ta_017_keywordFilters() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterKeywords("spa");
		dp.verifyKeywordSearch("spa");
	}

	@Test(description = "TC_TA_018")
	public void tc_ta_018_keywordDescriptionFilters() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterKeywords("bike");
		dp.verifyKeywordSearch("bike");
	}

	@Test(description = "TC_TA_019")
	public void tc_ta_019_multipleFilters() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterPriceFilter(15);
		dp.selectRating("4");
		dp.verifyPriceFilter(15);
		dp.verifyRatingFilter("4");
	}

	@Test(description = "TC_TA_020")
	public void tc_ta_020_clearFilters() {
		Calendar c = DateUtils.getDate(0);
		c.add(Calendar.MONTH, 2);
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", c);
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", c);

		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterDate(2, dt1, dt2, 10);
		dp.enterPriceFilter(15);
		dp.selectRating("4");
		dp.clickClearBtn();
		dp.verifyClear(DateUtils.getFormatedDate("MM/dd/yy", DateUtils.getDate(0)),
				DateUtils.getFormatedDate("MM/dd/yy", DateUtils.getDate(0)));
	}

	@Test(description = "TC_TA_021")
	public void tc_ta_021_clearAllFilters() {
		Calendar c = DateUtils.getDate(0);
		c.add(Calendar.MONTH, 2);
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", c);
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", c);

		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterDate(2, dt1, dt2, 10);
		dp.enterPriceFilter(1);
		dp.selectActivityDropdown(new String[] { "Sightseeing" });
		dp.clinkOnClearAllFiltersLink();
		dp.verifySearchResultsNotFoundText();
		dp.verifyClear(DateUtils.getFormatedDate("MM/dd/yy", DateUtils.getDate(0)),
				DateUtils.getFormatedDate("MM/dd/yy", DateUtils.getDate(0)));
	}

	@Test(description = "TC_TA_022")
	public void tc_ta_022_pagination() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.clickOnPagination("2");
		dp.verifyPagination("2");
	}

	@Test(description = "TC_TA_023")
	public void tc_ta_023_AnotherDestination() {
		HomePage hp = new HomePage(driver);
		String input = "New York, NY";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.enterDestinationGlobalSearch("San Francisco, CA");
		dp.verifySearchHeadline("San Francisco");
	}

}
