package com.americangirl.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mattel.Utility.PageHelper;

public class CheckOutAsGuestUser {

	WebDriver driver;
	private PageHelper helper;
	
	public CheckOutAsGuestUser(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "birth_month")
	@CacheLookup
	WebElement selectmonth;
	
	@FindBy(id = "birth_date")
	@CacheLookup
	WebElement selectdate;
	
	@FindBy(id = "birth_year")
	@CacheLookup
	WebElement selectyear;
	
	@FindBy(css = "a.button_primary.tlignore > div.button_text")
	@CacheLookup
	WebElement continueButton;
	
	public void checkoutProductasGuestUser() throws Exception {
		helper.getClickable(By.id("guestShopperContinue")).click();
	}
		
	public void selectBirthdate() {
		(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.age-gate-wrapper")));
		Select Selectmonth = new Select(selectmonth);
		Selectmonth.selectByIndex(2);
		Select SelectDate = new Select(selectdate);
		SelectDate.selectByIndex(2);
		Select SelectYear = new Select(selectyear);
		SelectYear.selectByVisibleText("1990");
		continueButton.click();
	}	

}

