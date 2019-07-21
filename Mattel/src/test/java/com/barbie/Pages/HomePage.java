package com.barbie.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mattel.Utility.PageHelper;

public class HomePage {
	
	WebDriver driver;
	public PageHelper helper;
	
	public HomePage(WebDriver ldriver, String url) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="BumperContinue")
	@CacheLookup()
	WebElement popupwindow;
	
	String expected ="Become a Very Important";

	public void dismissPopup() throws Exception {

		Thread.sleep(7000);

		try {

			if(popupwindow.isDisplayed()){	
				popupwindow.click();

			}}catch(Exception e) {
				Assert.assertTrue(false);	
			}	
	}
	
	public void verifyHomePage() throws Exception {
		
		WebElement homepagetext = helper.getElement(By.className("line"));
		String text = homepagetext.getText();
		Assert.assertEquals(text, expected);
	}

}
