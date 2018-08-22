package com.placepass.marriottMoments.tests;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.HomePage;
import com.placepass.marriottMoments.pages.MyAccountPage;
import com.placepass.marriottMoments.pages.SignInPage;
import com.placepass.marriottMoments.utils.DriverUtils;

public class SignInCases extends BaseTest {

	@Test
	public void tc_ta_045_signInWithValidEmail() {
		HomePage hp = new HomePage(driver);
		hp.clickOnSignIn();
		SignInPage sp = new SignInPage(driver);
		sp.clickContinueBtn();
		DriverUtils.switchToLatestWindow(driver);
		sp.login("venu.arram@zenq.com", "Second@123");
		sp.verifySignIn();
	}

	@Test
	public void tc_ta_046_updateFirstName() {
		HomePage hp = new HomePage(driver);
		hp.clickOnSignIn();
		SignInPage sp = new SignInPage(driver);
		sp.clickContinueBtn();
		DriverUtils.switchToLatestWindow(driver);
		sp.login("venu.arram@zenq.com", "Second@123");
		sp.verifySignIn();
		hp.clickOnMyAccount();
		MyAccountPage map = new MyAccountPage(driver);
		map.updateFirstName("testFirstName");

	}
}
