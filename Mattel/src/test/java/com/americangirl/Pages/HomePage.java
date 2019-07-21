package com.americangirl.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mattel.Utility.PageHelper;


public class HomePage {
	
	WebDriver driver;
	PageHelper helper;
	
	public HomePage(WebDriver ldriver, String url) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		driver.get(url);
		helper.waitForPageLoaded();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css ="button.close")
	@CacheLookup()
	WebElement popupwindow;
	
	public void dismissPopup() {
		popupwindow.isDisplayed();	
		popupwindow.click();
	}
	
	@FindBy(className ="see-details")
	@CacheLookup()
	WebElement americangirltext;

	public String expected = "Be in the know! Get email offers, news, and more. Sign up now";

	public void verifyHomePage() throws Exception {
		Assert.assertEquals(americangirltext.getText().replaceAll("\\s+", " ").trim(), expected);
			// Safari returns additional whitespace that Chrome and Firefox do not, thus the replace and trim.
	}
	
}
