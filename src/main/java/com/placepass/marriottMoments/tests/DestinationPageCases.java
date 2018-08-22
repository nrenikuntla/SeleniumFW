package com.placepass.marriottMoments.tests;

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
		// This case is same as case 12 might fail some times
		PageLevelUtils pUtils = new PageLevelUtils();
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		String oldWindow = dp.chooseProduct(dt1, dt2);
		BookPage bp = new BookPage(driver);
		bp.verifyAvailability(dt1.split("-")[1] + "," + dt2.split("-")[1]);
		pUtils.closeAllBrowserExcept(driver, oldWindow);
	}

	/*
	 * @Test(description = "TC_TA_014") public void tc_ta_014_selectSingleActivity()
	 * { HomePage hp = new HomePage(driver);
	 * hp.clickDestinationWeLove("New York City"); DestinationPage dp = new
	 * DestinationPage(driver); dp.selectActivityDropdown(new String[] {
	 * "Museum Tickets", "Shows & Concerts" }); // TODO need to add verification
	 * steps }
	 * 
	 * @Test(description = "TC_TA_015") public void
	 * tc_ta_015_selectMultipleActivities() { HomePage hp = new HomePage(driver);
	 * hp.clickDestinationWeLove("New York City"); DestinationPage dp = new
	 * DestinationPage(driver); dp.selectActivityDropdown(new String[] {
	 * "Museum Tickets", "Shows & Concerts" }); // TODO need to add verification
	 * steps }
	 */

	@Test(description = "TC_TA_016")
	public void tc_ta_016_ratingFilters() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.selectRating("4");
		dp.verifyRatingFilter("4");
	}

	// This case might fail sometimes as keyword is not there in product name and
	// product intro
	@Test(description = "TC_TA_017")
	public void tc_ta_017_keywordFilters() {
		HomePage hp = new HomePage(driver);
		hp.searchByClickingDropdown("London, United Kingdom");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterKeywords("London");
		dp.verifyKeywordSearch("London");
	}

	// This case might fail sometimes as keyword is not there in product name and
	// product intro
	@Test(description = "TC_TA_018")
	public void tc_ta_018_keywordDescriptionFilters() {
		HomePage hp = new HomePage(driver);
		hp.searchByClickingDropdown("London, United Kingdom");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterKeywords("queen mary");
		dp.verifyKeywordSearch("queen mary");
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
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));

		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterDate(dt1, dt2);
		dp.enterPriceFilter(15);
		dp.selectRating("4");
		dp.clickClearBtn();
		dp.verifyClear(DateUtils.getFormatedDate("MM/dd/yy", DateUtils.getDate(0)),
				DateUtils.getFormatedDate("MM/dd/yy", DateUtils.getDate(0)));
	}

	@Test(description = "TC_TA_021")
	public void tc_ta_021_clearAllFilters() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));

		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.enterDate(dt1, dt2);
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
