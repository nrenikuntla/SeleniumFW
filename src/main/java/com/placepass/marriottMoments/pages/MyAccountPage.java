package com.placepass.marriottMoments.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyAccountPage extends Page {
	private WebDriver driver;
	private WebDriverWait wait;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id = "accounts_edit_profile_form_first_name")
	WebElement firstName;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(className="edit-profile-saved-success")
	WebElement savedSuccessfullText;
	
	public void updateFirstName(String firstNameStr){
		//firstName.getText();
		firstName.clear();
		firstName.sendKeys(firstNameStr);
		saveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(savedSuccessfullText));
		System.out.println("Successfull message is::" + savedSuccessfullText.getText());
		Assert.assertEquals(savedSuccessfullText.getText(), " Changes saved successfully!");
		Assert.assertEquals(firstName.getText(), firstNameStr);
	}
	
	

}
