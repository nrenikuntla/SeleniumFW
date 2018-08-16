package com.placepass.marriottMoments.tests;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.*;
import com.placepass.marriottMoments.utils.DateUtils;
import com.placepass.marriottMoments.utils.DriverUtils;
import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.WaitUtil;

 

public class TestClass1 extends BaseTest {

	@Test(description = "TC_TA_Nav")
	public void tc0_verifyAllLinks() {
		HomePage hp = new HomePage(driver);
		hp.verifyHomePageLinks();
	}

	@Test(description = "TC_TA_001")
	public void tc1_verifyDestinationPageNavigation() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.verifyCity("New York");
	}

	@Test(description = "TC_TA_007")
	public void tc2_verifyDestinationSearch() {
		HomePage hp = new HomePage(driver);
		hp.verifyNyNotElegibleForPointsPageRedirection();
	}

	@Test(description = "TC_TA_009")
	public void tc3_verifyDestinationSearch() {
		HomePage hp = new HomePage(driver);
		String input = "New York, NY";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.verifyLocation(input);
	}

	//@Test(description = "TC_TA_12")
	public void tc4_verifyAavilabityDates() {
		// fails - issue found
		PageLevelUtils pUtils = new PageLevelUtils();
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(1));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		String oldWindow = dp.chooseProduct(dt1, dt2);
		BookPage bp = new BookPage(driver);
		bp.verifyAvailability(dt1.split("-")[1] + "," + dt2.split("-")[1]);
		pUtils.closeAllBrowserExcept(driver, oldWindow);
	}

	@Test(description = "TC_TA_13")
	public void tc5_maxPriceValidation() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.verifyPriceFilter(10);
	}

	@Test(description = "TC_TA_25")
	public void tc6_BookCalenderShouldApper() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.verifyBookingCalander();
	}

	@Test(description = "TC_TA_34")
	public void tc7_BookWithOutPayentInfo() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(dt1, dt2);
		BookPage bp = new BookPage(driver);
		//bp.verifyAvailability(dt1.split("-")[1] + "," + dt2.split("-")[1]);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectOption();
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "9949360460", "India");
		rbp.fillCCDetails("", "", "", "");
		rbp.book();
		rbp.verifyErrorMessage();
	}

	@Test(description = "TC_TA_36")
	public void tc7_BookWith1Guest() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(dt1, dt2);
		BookPage bp = new BookPage(driver);
		//bp.verifyAvailability(dt1.split("-")[1] + "," + dt2.split("-")[1]);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectOption();
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "9949360460", "India");
		rbp.fillCCDetails("5555555555554444", "1219", "123", "12345");
		rbp.book();

	}

}
