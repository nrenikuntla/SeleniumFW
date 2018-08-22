package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
 

public class SelectOptionPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public SelectOptionPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#pick-an-option .booking-option-radio")
	List<WebElement> options;

	@FindBy(css = ".icon-plus")
	WebElement plus;

	@FindBy(xpath = "//button[contains(text(),'Review & Book')]")
	List<WebElement> reviewAndBook;

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	List<WebElement> checkOut;

	public void selectOption() {
		sleep(5);
		options.get(0).click();
		sleep(5);
		plus.click();
		if (reviewAndBook.size() > 0)
			reviewAndBook.get(0).click();
		else if (checkOut.size() > 0)
			checkOut.get(0).click();
	}
	
	public void selectFirstOption(){
		sleep(5);
		options.get(0).click();
	}
	
	@FindBy(xpath="//h1[@class='contrast-header']")
	WebElement selectOptionsText;
	
	public void verifySelectOptionsPage(){
		System.out.println("Select options Text::"+selectOptionsText.getText());
		Assert.assertTrue(selectOptionsText.getText().equals("Select an Option"));
	}
	
	@FindBy(className="experiences-order-option-headline")
	List<WebElement> orderOptions;
	
	public void verifyDate(String date){
		Assert.assertTrue(orderOptions.get(0).getText().contains(date));
	}
	
	
	
}
