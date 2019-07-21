package com.hotwheelscollector.Pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.mattel.Base.TakeScreenshot;
import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class EnterShippingAddress {

WebDriver driver;
	
	public EnterShippingAddress(WebDriver ldriver) {
		
		this.driver =ldriver;
		
		//System.out.println("search"+driver);
	}
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_firstName_1")
	@CacheLookup
	WebElement firstName;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_lastName_1")
	@CacheLookup
	WebElement lastName;
	
	/* @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_country_1")
	@CacheLookup
	WebElement country;*/
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_address1_1")
	@CacheLookup
	WebElement streetAddress;
	
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_city_1")
	@CacheLookup
	WebElement city;
	

	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_state_1")
	@CacheLookup
	WebElement state;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_zipCode_1")
	@CacheLookup
	WebElement zipcode;
	

	@FindBy(id = "myOrderSummaryContinueButtonText")
	@CacheLookup
	WebElement continuetoPayment;

	
	@FindBy(id = "useSugAddress")
	@CacheLookup
	WebElement UseUSPSaddress;
	

		
	public void enterShippingDeatil() throws Exception {
		
	TestBase.logger= TestBase.report.startTest(this.getClass().getSimpleName(),"Verify shipping page functionality");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	Thread.sleep(8000);
	
	firstName.sendKeys(TestBase.CONFIG.getProperty("FirstName"));
	Thread.sleep(500);
	lastName.sendKeys(TestBase.CONFIG.getProperty("LastName"));
	Thread.sleep(500);
	/*Select selectCountry = new Select(country);
	selectCountry.selectByVisibleText(TestBase.CONFIG.getProperty("Country"));
	Thread.sleep(1000);*/
	streetAddress.sendKeys(TestBase.CONFIG.getProperty("StreetAdd"));
	Thread.sleep(500);
	city.sendKeys(TestBase.CONFIG.getProperty("City"));

	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy(0,100)", "");
	
	
	Select selectState = new Select(state);
	selectState.selectByVisibleText(TestBase.CONFIG.getProperty("State"));
	Thread.sleep(2000);
	zipcode.sendKeys(TestBase.CONFIG.getProperty("Zipcode"));
	Thread.sleep(3000);
	
	Assert.assertTrue(true);
	TestBase.logger.log(LogStatus.PASS, "Shipping details are enter successfully");
	
	try {if(continuetoPayment.isDisplayed()) {
		//Log.debug("Click on Continue to Payment");
		continuetoPayment.click();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Continue payment button clicked successfully");

		}}catch(Exception e) {
			TestBase.logger.log(LogStatus.FAIL, "Continue Payment button is not displayed");
			String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
			Assert.assertTrue(false);
			TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
		}
	
	Thread.sleep(9000);
	try {if(UseUSPSaddress.isDisplayed()) {
		UseUSPSaddress.click();
		}}catch(Exception e) {
			
			TestBase.logger.log(LogStatus.FAIL, "UseUSPSaddress button is not displayed");
			String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
			Assert.assertTrue(false);
			TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
			/*Thread.sleep(2000);
			if(UseUSPSaddress.isDisplayed()) {
				UseUSPSaddress.click();
				}*/
			//Log.error(e.getMessage());
		}
	
	
	TestBase.report.endTest(TestBase.logger);	
	
	}
	
}

