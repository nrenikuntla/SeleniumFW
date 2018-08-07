package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.placepass.marriottMoments.utils.DriverUtils;
import com.placepass.marriottMoments.utils.PageLevelUtils;
import com.placepass.marriottMoments.utils.WaitUtil;


public class ReviewAndBookPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public ReviewAndBookPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#first-name")
	WebElement fName;

	@FindBy(css = "#last-name")
	WebElement lName;

	@FindBy(css = "#activities_checkout_form_travelers_attributes_0_email")
	WebElement email;

	@FindBy(css = "#activities_checkout_form_travelers_attributes_0_phone_number")
	WebElement phone;

	@FindBy(css = "#activities_checkout_form_travelers_attributes_0_country_code")
	WebElement country;

	@FindBy(name = "cardnumber")
	WebElement cardnumber;

	@FindBy(name = "exp-date")
	WebElement expdate;

	@FindBy(name = "cvc")
	WebElement cvc;

	@FindBy(name = "postal")
	WebElement postal;

	@FindBy(xpath = "//button[contains(text(),'Review & Book')]")
	List<WebElement> reviewAndBook;

	@FindBy(xpath = "//button[contains(text(),'Place Order')]")
	List<WebElement> placeOrder;

	public void fillCustDetails(String fn, String ln, String email_, String ph, String c) {
		fName.sendKeys(fn);
		lName.sendKeys(ln);
		email.sendKeys(email_);
		phone.sendKeys(ph);
		new Select(country).selectByVisibleText(c);
	}

	public void fillCCDetails(String cc, String exp, String cvc_, String zip) {
		// new PageLevelUtils().bringElementInView(driver, cardnumber);
		String handle = driver.getWindowHandle();
		driver.switchTo().frame("__privateStripeFrame3");
		cardnumber.sendKeys(cc);
		driver.switchTo().window(handle);
		driver.switchTo().frame("__privateStripeFrame4");
		expdate.sendKeys(exp);
		driver.switchTo().window(handle);
		driver.switchTo().frame("__privateStripeFrame5");
		cvc.sendKeys(cvc_);
		driver.switchTo().window(handle);
		driver.switchTo().frame("__privateStripeFrame6");
		postal.sendKeys(zip);
		driver.switchTo().window(handle);
	}

	public void book() {
		if (reviewAndBook.size() > 0)
			reviewAndBook.get(0).click();
		if (placeOrder.size() > 0)
			placeOrder.get(0).click();
		sleep(10);
		System.out.println("msg>>" + error.getText());
	}

	@FindBy(css = "#StripeCardErrors")
	WebElement error;

	public void verifyErrorMessage() {
		System.out.println(error.getText());
		Assert.assertTrue(error.getText().contains("Your card number is incomplete."), "invalid err");
	}

}
