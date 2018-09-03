package com.placepass.marriottMoments.tests;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.DestinationPage;
import com.placepass.marriottMoments.pages.HomePage;

public class HomePageCases extends BaseTest {
	@Test(description = "TC_TA_Nav")
	public void tc_verifyAllLinks() {
		HomePage hp = new HomePage(driver);
		hp.verifyHomePageLinks();
	}

	@Test(description = "TC_TA_001 - Verify Destinatin city Navigation")
	public void tc_ta_001_verifyNavigateToDestinationPage() {
		HomePage hp = new HomePage(driver);
		hp.clickDestinationWeLove("New York City");
		DestinationPage dp = new DestinationPage(driver);
		dp.verifyCity("New York");
	}

	@Test(description = "TC_TA_002- Choose a Car under - Earn Marriott Rewards Points on Hertz Rentals")
	public void tc_ta_002_VerifyNaviagateToRentalCarsPage() {
		HomePage hp = new HomePage(driver);
		hp.clickHertzRentals(1);
		hp.verifyNavigateToRentalCarsPage();
	}

	@Test(description = "TC_TA_003 SPG Member Exclusive - Exclusive SPG and Marriott Rewards Member Experiences")
	public void tc_ta_003_verifyNavigateToMemberExlusivePage() {
		HomePage hp = new HomePage(driver);
		hp.clickExclusiveSPG(1);
		hp.verifyMemberExclusiveSPGNavigation();
	}

	@Test(description = "TC_TA_004  Marriott Rewards Exclusive - Exclusive SPG and Marriott Rewards Member Experiences")
	public void tc_ta_004_exclusiveMarriatrewards() {
		HomePage hp = new HomePage(driver);
		hp.clickMarriatrewards(1);
		hp.verifyMarriotrewardsNavigation();
	}

	@Test(description = "TC_TA_005")
	public void tc_ta_005_regularShelfThumbnail() {
		String handle = driver.getWindowHandle();
		HomePage hp = new HomePage(driver);
		hp.clickMarriatrewards(1);
		hp.verifyMarriotrewardsNavigation();
		driver.close();
		driver.switchTo().window(handle);
		hp.clickHertzRentals(1);
		hp.verifyNavigateToRentalCarsPage();
		driver.close();
		driver.switchTo().window(handle);
		String input = "New York, NY";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.verifyLocation(input);
	}

	@Test(description = "TC_TA_006 - See All : link button click")
	public void tc_ta_006_seeAllLink() {
		HomePage hp = new HomePage(driver);
		hp.clickOnSeeAllForEveryTypeofTraveler();
		hp.verifySeeAllForEveryTypeofTraveler();
	}

	@Test(description = "TC_TA_007 -click on any item in - New York Events NOT ELIGIBLE FOR POINTS")
	public void tc2_verifyDestinationSearch() {
		HomePage hp = new HomePage(driver);
		hp.verifyNyNotElegibleForPointsPageRedirection();
	}

	@Test(description = "TC_TA_008 click on see all button of  New York Events NOT ELIGIBLE FOR POINTS")
	public void tc_ta_008_seeAllLinkOnStubHub() {
		HomePage hp = new HomePage(driver);
		hp.clickAndVerifySeeAllForNewYorkEvents();
	}

	@Test(description = "TC_TA_009  'DESTINATION' filed suggetion navigation")
	public void tc3_verifyDestinationSearch() {
		HomePage hp = new HomePage(driver);
		String input = "New York, NY";
		hp.searchCity(input);
		DestinationPage dp = new DestinationPage(driver);
		dp.verifyLocation(input);
	}

	@Test(description = "TC_TA_010 - invalid destinatin in Destination field")
	public void tc_ta_010_doesNotMatchAnyDestination() {
		HomePage hp = new HomePage(driver);
		hp.search("abcdefghijklmn");
		hp.verifyResultsNotFound();
	}

}
