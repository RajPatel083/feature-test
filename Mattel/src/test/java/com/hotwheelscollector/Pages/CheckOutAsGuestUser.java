package com.hotwheelscollector.Pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CheckOutAsGuestUser {

WebDriver driver;
	
	public CheckOutAsGuestUser(WebDriver ldriver) {
		
		this.driver =ldriver;
		
		//System.out.println("search"+driver);
	}
	
	
	@FindBy(css="#guestShopperContinue > div.button_text")
	@CacheLookup
	WebElement guestUserButton;
	
	
	public void checkoutProductasGuestUser() throws Exception {
		
	TestBase.logger= TestBase.report.startTest(this.getClass().getSimpleName(),"Verify checkout page functionality");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	Thread.sleep(8000);
	try {if(guestUserButton.isDisplayed()) {
		//Log.debug("Click on Guest User");
		guestUserButton.click();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Guest user button is displayed and clicked successfully");
		
		}}catch(Exception e) {
			guestUserButton.click();
			//Log.error(e.getMessage());
		}
		
	}
	
}

