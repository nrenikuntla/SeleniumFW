package com.placepass.marriottMoments.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;

public class MemberExclusivePage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public MemberExclusivePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "mr-moments")
	WebElement marriottRewards;

	public void clickOnMarriottRewards() {
		marriottRewards.click();
	}

	public void verifyMarriottRewardsPage() {
		DriverUtils.switchToLatestWindow(driver);
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.moments.marriottrewards.com/");
	}

	@FindBy(className = "spg-moments")
	WebElement spgMoments;

	public void clickOnSPGMoments() {
		spgMoments.click();
	}

	public void verifySPGMomentsPage() {
		DriverUtils.switchToLatestWindow(driver);
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), "https://auction.starwoodhotels.com/");
	}

}
