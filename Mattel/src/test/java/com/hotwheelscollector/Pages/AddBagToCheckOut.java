package com.hotwheelscollector.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mattel.Base.TakeScreenshot;
import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class AddBagToCheckOut {

WebDriver driver;
	
	public AddBagToCheckOut(WebDriver ldriver) {
		
		this.driver =ldriver;
		
		//System.out.println("search"+driver);
	}
	
		
	@FindBy(id ="productPageAdd2Cart")
	@CacheLookup()
	WebElement addtocart;
	
	@FindBy(id ="myOrderSummaryContinueButtonText")
	@CacheLookup
	WebElement checkout;
	
	
	public void addBagtocheckoutProduct() throws Exception {
		
		TestBase.logger= TestBase.report.startTest(this.getClass().getSimpleName(),"Verify add to bag functionality");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
	try{if(addtocart.isDisplayed()) {
		//Log.debug("Click on Add To Bag");
		addtocart.click();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Add to cart button is displayed and clicked successfully");
	}}catch(Exception e) {
		//addtocart.click();
		TestBase.logger.log(LogStatus.FAIL, "Add to bag button is not displayed");
		String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
		Assert.assertTrue(false);
		TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
	
	}
	

	Thread.sleep(14000);

	//JavascriptExecutor jse = (JavascriptExecutor)driver;
	//jse.executeScript("window.scrollBy(0,400)", "");
	try {if(checkout.isDisplayed()) {
		//Log.debug("Click on Checkout");
		checkout.click();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Checkout button is displayed and clicked successfully");
		}}catch(Exception e) {
			
			TestBase.logger.log(LogStatus.FAIL, "Checkout button is not displayed");
			String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
			Assert.assertTrue(false);
			TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
			//checkout.click();
			//Log.error(e.getMessage());
		}
	TestBase.report.endTest(TestBase.logger);
	
	}
	
}
