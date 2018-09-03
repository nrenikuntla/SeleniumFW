package com.placepass.marriottMoments.tests;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.AddGuestsPage;
import com.placepass.marriottMoments.pages.BookPage;
import com.placepass.marriottMoments.pages.ConfirmationPage;
import com.placepass.marriottMoments.pages.DestinationPage;
import com.placepass.marriottMoments.pages.HomePage;
import com.placepass.marriottMoments.pages.ReviewAndBookPage;
import com.placepass.marriottMoments.pages.SelectOptionPage;
import com.placepass.marriottMoments.utils.DateUtils;

public class ActivityBookingFlowCases extends BaseTest {

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

	@Test(description = "TC_TA_25")
	public void tc6_BookCalenderShouldApper() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct();
		BookPage bp = new BookPage(driver);
		bp.verifyBookingCalander();
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

	@Test
	public void tc_ta_032_ApplyValidDiscountCodeInMyOrders() {
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
		rb.enterReferralCode("");
		rb.enterDiscountCode("");
		rb.clickApplyCodeBtn();
	}

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

	@Test(description = "TC_TA_34")
	public void TC_TA_34_BookWithOutPayentInfo() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		// bp.verifyAvailability(dt1.split("-")[1] + "," + dt2.split("-")[1]);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectAddGuests("Single");
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("", "", "", "");
		rbp.book();
		rbp.verifyErrorMessage();
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
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("4000000000000069", "1219", "123", "12345");
		rbp.book();
		rbp.verifyCardExpiredErrorMessage();
	}

	@Test(description = "TC_TA_36")
	public void TC_TA_36_BookWith1Guest() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectAddGuests("Single");
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("5555555555554444", "1219", "123", "12345");
		rbp.book();
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.verifyConfirmationMessageHeadder();
	}

	@Test(description = "TC_TA_37")
	public void TC_TA_37_BookWithMultipleGuest() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectAddGuests("Multiple");
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("5555555555554444", "1219", "123", "12345");
		rbp.book();
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.verifyConfirmationMessageHeadder();
	}

	@Test(description = "TC_TA_38")
	public void TC_TA_38_BookWithMultipleGuest() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectAddGuests("Single");
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("5555555555554444", "1219", "123", "12345");
		rbp.book();
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.verifyConfirmationMessageHeadder();
		cp.verifyAllWidgets();
	}

	@Test(description = "TC_TA_39")
	public void TC_TA_39_verifyVourchersPostBooking() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectAddGuests("Single");
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("5555555555554444", "1219", "123", "12345");
		rbp.book();
		// verify Vouchers after booking
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.verifyConfirmationMessageHeadder();
		cp.verifyAllWidgets();
		cp.verifyMyVouchersPopUp();
		// 41 is covered here ***************
	}

	@Test(description = "TC_TA_40")
	public void TC_TA_40_verify() {
		// email content verification
	}

	@Test(description = "TC_TA_41")
	public void TC_TA_41_verify() {
		// 39 covered
	}

	@Test(description = "TC_TA_42")
	public void TC_TA_42_verifyBookingCancellation() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectAddGuests("Single");
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("5555555555554444", "1219", "123", "12345");
		rbp.book();
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.verifyCancelReservation();
	}

	@Test(description = "TC_TA_43")
	public void TC_TA_43_verify() {
		// email verification for cancellation
	}

	@Test(description = "TC_TA_44")
	public void TC_TA_44_verifyCancelWidgetContent() {
		String dt1 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		String dt2 = DateUtils.getFormatedDate("mm-dd-yyyy", DateUtils.getDate(0));
		HomePage hp = new HomePage(driver);
		hp.search("musement");
		DestinationPage dp = new DestinationPage(driver);
		dp.chooseProduct(2, dt1, dt2, 10);
		BookPage bp = new BookPage(driver);
		bp.book();
		SelectOptionPage sop = new SelectOptionPage(driver);
		sop.selectAddGuests("Single");
		ReviewAndBookPage rbp = new ReviewAndBookPage(driver);
		rbp.fillCustDetails("testFirst", "testLast", "test@placepass.com", "+91", "9949360460", "India");
		rbp.fillCCDetails("5555555555554444", "1219", "123", "12345");
		rbp.book();
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.verifyCancelReservation();
		cp.verifyAllWidgets();
	}

}
