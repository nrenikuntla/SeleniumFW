package com.placepass.marriottMoments.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.placepass.marriottMoments.utils.DriverUtils;

public class ReviewAndBookPage extends Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public ReviewAndBookPage(WebDriver driver) {
		System.out.println("Review And Book Page Object created");
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#first-name")
	List<WebElement> fName;

	@FindBy(css = "#last-name")
	List<WebElement> lName;

	@FindBy(css = "#activities_checkout_form_travelers_attributes_0_email")
	WebElement email;

	@FindBy(css = "#activities_checkout_form_travelers_attributes_0_phone_country_code")
	WebElement countryCode;

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

	public void fillCustDetails(String fn, String ln, String email_, String cc, String ph, String c) {
		for (WebElement ele : fName)
			ele.sendKeys(fn);
		for (WebElement ele : lName)
			ele.sendKeys(ln);
		email.sendKeys(email_);
		new Select(countryCode).selectByValue(cc);
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
		// System.out.println("msg>>" + error.getText());
	}

	@FindBy(css = "#StripeCardErrors")
	WebElement error;

	public void verifyErrorMessage() {
		System.out.println(error.getText());
		Assert.assertTrue(error.getText().contains("Your card number is incomplete."), "invalid err");
	}

	@FindBy(className = "label-error")
	WebElement cardExpiredErrorMessage;

	public void verifyCardExpiredErrorMessage() {
		System.out.println("Card Expired error message is::" + cardExpiredErrorMessage.getText());
		Assert.assertTrue(
				cardExpiredErrorMessage.getText().contains("Please ensure that all fields are complete and correct."));
	}

	@FindBy(xpath = "//div[@id='sticky-order-summary']//div[contains(@class,'experiences-order-summary-total-price')]")
	WebElement totalAmt;

	public void verifyTotalAmount(Double amount) {
		Double totalAmount = getTotalAmount();
		System.out.println("Total Amount in Review and Book page::" + totalAmount);
		Assert.assertEquals(amount, totalAmount);
	}

	private Double getTotalAmount() {
		String totalAmtValue = totalAmt.getText();
		String[] split = totalAmtValue.split(" ");
		String substring = split[0].substring(1, split[0].indexOf("\n"));
		System.out.println("SubString::" + substring);
		Double actualAmount = Double.valueOf(substring);
		return actualAmount;
	}

	@FindBy(xpath = "//label[@for='activities_checkout_form_rewards_scheme_mar']")
	public WebElement marriottRewardsPointsRadioBtn;

	// @FindBy(xpath =
	// "//label[@for='activities_checkout_form_rewards_scheme_spg']")
	@FindBy(id = "activities_checkout_form_rewards_scheme_spg")
	public WebElement starPointsRadioBtn;

	@FindBy(xpath = "//div[@id='sticky-order-summary']//span[@data-rewards-alternative='SPG']")
	public WebElement totalEarnPointsText;

	public void clickOnSPGRadioButton() {
		DriverUtils.jsClick(driver, starPointsRadioBtn);
		// starPointsRadioBtn.click();
	}

	public void verifySPGPointsToggle() {
		Assert.assertTrue(totalEarnPointsText.isDisplayed());
		Assert.assertTrue(totalEarnPointsText.getText().contains("SPG Points"));
	}

	// @FindBy(className = "additional-information-toggle")
	@FindBy(css = "#discount-code a")
	WebElement redeemCodeLink;

	@FindBy(id = "activities_checkout_form_referral_code")
	WebElement referralCode;

	@FindBy(id = "activities_checkout_form_discount_code")
	WebElement discountCode;

	@FindBy(id = "apply-discount")
	WebElement applyCodeBtn;

	public void clickOnRedeemCodeLink() {
		// redeemCodeLink.click();
		DriverUtils.jsClick(driver, redeemCodeLink);
		wait.until(ExpectedConditions.visibilityOf(referralCode));
		Assert.assertTrue(referralCode.isDisplayed());
		Assert.assertTrue(discountCode.isDisplayed());
	}

	public void enterReferralCode(String referralCodeStr) {
		referralCode.sendKeys(referralCodeStr);
	}

	public void enterDiscountCode(String discountCodeStr) {
		discountCode.sendKeys(discountCodeStr);
	}

	public void clickApplyCodeBtn() {
		Assert.assertTrue(applyCodeBtn.isDisplayed());
		applyCodeBtn.click();
	}

	@FindBy(id = "display-discount-message")
	WebElement discountErrorMessage;

	public void verifyInvalidDiscountErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(discountErrorMessage));
		Assert.assertEquals(discountErrorMessage.getText(), "This code was not found");
	}
}
