package com.barbie.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mattel.Utility.PageHelper;

public class AddBagToCheckOut {

	WebDriver driver;
	PageHelper helper;
	
	public AddBagToCheckOut(WebDriver ldriver) {
		this.driver =ldriver;
		this.helper = new PageHelper(ldriver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	public void addToBag() {
		WebElement addtobag = helper.getElement(By.id("productPageAdd2Cart"));
		helper.waitForClickable(addtobag);
		addtobag.click();
		//TODO(hooverki): Should return a new page object for the checkout page.
	}
	
	public void checkoutNow() {
		@SuppressWarnings("unused")
		WebElement checkout = helper.getElement(By.className("button_text"));
		helper.doJavascript("document.getElementById('myOrderSummaryContinueButtonText').click()", "");
		//TODO(hooverki): Should return a new page object for the checkout page.
	}

}
