package com.placepass.marriottMoments.tests;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.AddGuestsPage;
import com.placepass.marriottMoments.pages.BookPage;
import com.placepass.marriottMoments.pages.DestinationPage;
import com.placepass.marriottMoments.pages.HomePage;
import com.placepass.marriottMoments.pages.ReviewAndBookPage;
import com.placepass.marriottMoments.pages.SelectOptionPage;

public class ActivityBookingFlow extends BaseTest {

	@Test
	public void tc_ta_024_selectStarPoints() {
		HomePage hp = new HomePage(driver);
		String input = "New York, NY";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.clickOnStarPointsRadioBtn();
		bp.verifyStarPointsRadioBtn();
	}

	@Test
	public void tc_ta_026_MostPopular() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		String productTitle = dp.clickOnFirstMostPopularProduct();
		BookPage bp = new BookPage(driver);
		bp.verifyProductName(productTitle);
	}

	@Test
	public void tc_ta_027_VerifyTotalAmountDisplayed() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage options = new SelectOptionPage(driver);
		options.selectFirstOption();
		AddGuestsPage ag = new AddGuestsPage(driver);
		ag.increment();
	}

	@Test
	public void tc_ta_028_ModifyExperienceDateInMyOrders() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage options = new SelectOptionPage(driver);
		options.selectFirstOption();
		AddGuestsPage ag = new AddGuestsPage(driver);
		ag.increment();
		ag.clickOnDateEdit();
		String selectedDate = ag.selectDate();
		options.verifySelectOptionsPage();
		options.verifyDate(selectedDate);
	}

	@Test
	public void tc_ta_029_ModifyOptionsInMyOrders() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage options = new SelectOptionPage(driver);
		options.selectFirstOption();
		AddGuestsPage ag = new AddGuestsPage(driver);
		ag.increment();
		ag.clickOnOptionsEdit();
		options.verifySelectOptionsPage();
		options.selectFirstOption();
	}

	@Test
	public void tc_ta_030_VerifyTotalAmountInMyOrders() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage options = new SelectOptionPage(driver);
		options.selectFirstOption();
		AddGuestsPage ag = new AddGuestsPage(driver);
		Double totalAmount = ag.increment();
		ag.clickBookOrCheckOut();
		ReviewAndBookPage rb = new ReviewAndBookPage(driver);
		rb.verifyTotalAmount(totalAmount);
	}

	@Test
	public void tc_ta_031_VerifyStarPointsToggleInMyOrders() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage options = new SelectOptionPage(driver);
		options.selectFirstOption();
		AddGuestsPage ag = new AddGuestsPage(driver);
		ag.increment();
		ag.clickBookOrCheckOut();
		ReviewAndBookPage rb = new ReviewAndBookPage(driver);
		rb.clickOnSPGRadioButton();
		rb.verifySPGPointsToggle();
	}

	/*
	 * TODO Need valid discount code and Referral code
	 * 
	 * @Test public void tc_ta_032_ApplyValidDiscountCodeInMyOrders() { HomePage hp
	 * = new HomePage(driver); String input = "San Francisco, CA";
	 * hp.searchCity(input); DestinationPage dp = new DestinationPage(driver);
	 * dp.chooseProduct(); BookPage bp = new BookPage(driver); bp.book();
	 * SelectOptionPage options = new SelectOptionPage(driver);
	 * options.selectFirstOption(); AddGuestsPage ag = new AddGuestsPage(driver);
	 * ag.increment(); ag.clickBookOrCheckOut(); ReviewAndBookPage rb = new
	 * ReviewAndBookPage(driver); rb.clickOnRedeemCodeLink();
	 * rb.enterReferralCode(""); rb.enterDiscountCode(""); rb.clickApplyCodeBtn(); }
	 */

	@Test
	public void tc_ta_033_ApplyInValidDiscountCodeInMyOrders() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage options = new SelectOptionPage(driver);
		options.selectFirstOption();
		AddGuestsPage ag = new AddGuestsPage(driver);
		ag.increment();
		ag.clickBookOrCheckOut();
		ReviewAndBookPage rb = new ReviewAndBookPage(driver);
		rb.clickOnRedeemCodeLink();
		rb.enterReferralCode("test");
		rb.enterDiscountCode("test");
		rb.clickApplyCodeBtn();
		rb.verifyInvalidDiscountErrorMessage();
	}

	@Test
	public void tc_ta_035_invalidCardDetailsInMyOrders() {
		HomePage hp = new HomePage(driver);
		String input = "San Francisco, CA";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage options = new SelectOptionPage(driver);
		options.selectFirstOption();
		AddGuestsPage ag = new AddGuestsPage(driver);
		ag.increment();
		ag.clickBookOrCheckOut();
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "9949360460", "India");
		rbp.fillCCDetails("4000000000000069", "1219", "123", "12345");
		rbp.book();
		rbp.verifyCardExpiredErrorMessage();
	}

}
