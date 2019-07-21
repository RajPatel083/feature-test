package com.fisherPrice.Pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mattel.Utility.PageHelper;

public class CheckOutAsGuestUser {

	WebDriver driver;
	public PageHelper helper;
	
	public CheckOutAsGuestUser(WebDriver ldriver) {
		this.driver =ldriver;
		this.helper = new PageHelper(ldriver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
		
	public void checkoutProductasGuestUser() throws Exception {
		WebElement checkOutguestUser = helper.getElement(By.cssSelector("#guestShopperContinue > div.button_text"));
		helper.waitForClickable(checkOutguestUser);
		checkOutguestUser.click();
	}
	
}

