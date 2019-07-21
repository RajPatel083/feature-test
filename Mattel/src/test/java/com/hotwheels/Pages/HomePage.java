package com.hotwheels.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	

	String expected ="BECOME A VERY IMPORTANT";
	
	
	public void verifyHomePage() throws Exception {
		
		WebElement homepagetext = helper.getElement(By.className("line"));
		String text = homepagetext.getText();
		Assert.assertEquals(text, expected);
	}
	

}
