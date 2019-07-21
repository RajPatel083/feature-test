package com.hotwheelscollector.Pages;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mattel.Base.TakeScreenshot;
import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SearchProduct {

WebDriver driver;
	
	public SearchProduct(WebDriver ldriver) {
		
		this.driver =ldriver;
		
		//System.out.println("search"+driver);
	}
	
	@FindBy(id ="SimpleSearchForm_SearchTerm")
	@CacheLookup()
	WebElement searchbox;
	
	@FindBy(xpath =".//*[@id='WC_CatalogEntryDBThumbnailDisplayJSPF_55815_link_9b']")
	@CacheLookup()
	WebElement selectproduct;
	
	
	public void searchProduct() throws Exception {
		
	TestBase.logger= TestBase.report.startTest(this.getClass().getSimpleName(),"Verify search page functionality");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		try {
		searchbox.clear();
		searchbox.sendKeys("mug");
		searchbox.submit();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Search product successfully");
		
		}catch(Exception e) {
			
			TestBase.logger.log(LogStatus.FAIL, "Search box is not found.");
			String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
			Assert.assertTrue(false);
			TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
			
		}
		
		Thread.sleep(5000);
		
		
		
		System.out.println("Search product successfully");
		//Log.info("Search product successfully");
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,400)", "");
		Thread.sleep(3000);	
		selectproduct.click();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "product selected successfully");
		System.out.println("Product selected successfully");
		//Log.info("Product selected successfully");
		//jse.executeScript("window.scrollBy(0,400)", "");*/

		TestBase.report.endTest(TestBase.logger);
		
	}
	
}
