package com.americangirl.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mattel.Utility.PageHelper;

public class SearchProduct {

	WebDriver driver;
	private PageHelper helper;
	
	public SearchProduct(WebDriver ldriver, String url) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		driver.get(url);
		helper.waitForPageLoaded();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="SimpleSearchForm_SearchTerm")
	@CacheLookup()
	WebElement searchbox;
	
	@FindBy(xpath ="//img[@alt='Truly Me™ Doll #16 + Truly Me Accessories']")
	@CacheLookup()
	WebElement selectdoll;
	
	
	public void searchProduct(String searchTerm) throws Exception {		
		searchbox.clear();
		searchbox.sendKeys(searchTerm);
		searchbox.submit();
	}
	
}
