package com.placepass.marriottMoments.tests;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.HomePage;

public class HomePageCases extends BaseTest {

	@Test(description = "TC_TA_002")
	public void tc_ta_002_rentalCars() {
		HomePage hp = new HomePage(driver);
		hp.clickHertzRentals("1");
		hp.verifyNavigateToRentalCarsPage();
	}

	@Test(description = "TC_TA_003")
	public void tc_ta_003_exclusiveSPG() {
		HomePage hp = new HomePage(driver);
		hp.clickExclusiveSPG("1");
		hp.verifyExclusiveSPGNavigation();
	}

	@Test(description = "TC_TA_004")
	public void tc_ta_004_exclusiveMarriatrewards() {
		HomePage hp = new HomePage(driver);
		hp.clickMarriatrewards("1");
		hp.verifyMarriotrewardsNavigation();
	}

	@Test(description = "TC_TA_005")
	public void tc_ta_005_regularShelfThumbnail() {
		HomePage hp = new HomePage(driver);
		hp.clickMarriatrewards("1");
		hp.verifyMarriotrewardsNavigation();
	}

	@Test(description = "TC_TA_006")
	public void tc_ta_006_seeAllLink() {
		HomePage hp = new HomePage(driver);
		hp.clickOnSeeAllForEveryTypeofTraveler();
		hp.verifySeeAllForEveryTypeofTraveler();
	}

	@Test(description = "TC_TA_008")
	public void tc_ta_008_seeAllLinkOnStubHub() {
		HomePage hp = new HomePage(driver);
		hp.clickAndVerifySeeAllForNewYorkEvents();
	}

	@Test(description = "TC_TA_010")
	public void tc_ta_010_doesNotMatchAnyDestination() {
		HomePage hp = new HomePage(driver);
		hp.search("abcdefghijklmn");
		hp.verifyResultsNotFound();
	}

}
