package com.hotwheelscollector.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;


public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver ldriver) {
		
		this.driver =ldriver;
		
	}
	
	@FindBy(id ="contentRecommendationWidget_HWCHeaderEspot2")
	@CacheLookup()
	WebElement hotwheelscollectortext;

	
	
	
	public void verifyHomePage() throws Exception {
		
		
		
		TestBase.logger= TestBase.report.startTest(this.getClass().getSimpleName(),"Verify home page functionality");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
		
		String expected = "Free Ship over $49 for RLC Members. Join Now! ";
		String text = hotwheelscollectortext.getText();
		Assert.assertEquals(text, expected);
		System.out.println("Home page open succesfully.");
		TestBase.logger.log(LogStatus.PASS, "Home page open succesfully.");
		TestBase.report.endTest(TestBase.logger);
			
	}
	
	
	
	

}
