package com.fisherPrice.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mattel.Utility.PageHelper;

public class SearchProduct {

	WebDriver driver;
	public PageHelper helper;
	
	public SearchProduct(WebDriver ldriver, String url) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="SimpleSearchForm_SearchTerm")
	@CacheLookup()
	WebElement searchbox;
	
	@FindBy(css ="#MiniListQuickViewLink_18917 > div > span")
	@CacheLookup() 
	WebElement selectdoll;
	
	
	public void searchProduct(String searchTerm) throws Exception {	
		WebElement searchbox = helper.getElement(By.id("SimpleSearchForm_SearchTerm"));
		helper.waitForClickable(searchbox);
		searchbox.clear();
		searchbox.sendKeys(searchTerm);
		searchbox.submit();
		WebElement searchResultText = helper.getElement(By.cssSelector("div.searchSummaryWidget"));
		helper.waitForClickable(searchResultText);
	}
}
