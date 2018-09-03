package com.placepass.marriottMoments.tests;

import org.testng.annotations.Test;

import com.placepass.marriottMoments.driver.BaseTest;
import com.placepass.marriottMoments.pages.ConfirmationPage;
import com.placepass.marriottMoments.pages.HelpPage;
import com.placepass.marriottMoments.pages.HomePage;
import com.placepass.marriottMoments.pages.MemberExclusivePage;

public class OthersCases extends BaseTest {

	@Test
	public void tc_ta_048_MarriottRewardsMoments() {
		HomePage hp = new HomePage(driver);
		hp.clickOnMemberExclusive();
		MemberExclusivePage mp = new MemberExclusivePage(driver);
		mp.clickOnMarriottRewards();
		mp.verifyMarriottRewardsPage();
	}

	@Test
	public void tc_ta_049_SPGMoments() {
		HomePage hp = new HomePage(driver);
		hp.clickOnMemberExclusive();
		MemberExclusivePage mp = new MemberExclusivePage(driver);
		mp.clickOnMarriottRewards();
		mp.verifyMarriottRewardsPage();
	}

	@Test
	public void tc_ta_050_LocateConfirmedExperience() {
		HomePage hp = new HomePage(driver);
		hp.clickOnHelpMenu();
		HelpPage help = new HelpPage(driver);
		help.enterBookingCode("MM3B2A21", "test@placepass.com");
		ConfirmationPage confirm = new ConfirmationPage(driver);
		confirm.verifyConfirmationNumber("MM3B2A21");
	}
}
