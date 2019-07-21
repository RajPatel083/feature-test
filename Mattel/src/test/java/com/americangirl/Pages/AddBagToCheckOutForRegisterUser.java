package com.americangirl.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mattel.Utility.PageHelper;

public class AddBagToCheckOutForRegisterUser {

	WebDriver driver;
	private PageHelper helper;
	
	public AddBagToCheckOutForRegisterUser(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		PageFactory.initElements(driver, this);
	}
	
	private String noThanksSelector = "a.no-thanks-button";
	private By noThanksButton = By.cssSelector(noThanksSelector);
	private By shoppingBagContinueButton = By.id("myOrderSummaryContinueButtonText2");
	private String jQueryClickNoThanks = "$('"+noThanksSelector+"').click()";

	///////////////////////
	
	public void addToBag() {
		WebElement addtobag = helper.getClickable(By.cssSelector("#productPageAdd2Cart"));
		addtobag.click();
	}
	
	public void noThanks() throws Exception {
		// Wait for and click the "no thanks" link.
		helper.getClickable(noThanksButton);
				
		// Use JQuery javascript to click  the "no thanks" link every one second until the next dialog shows up.
		(new WebDriverWait(driver, 20, 1000))
			.withMessage("Shopping bag did not appear after clicking no-thanks")
			.until(shoppingBagAppears(jQueryClickNoThanks,"*** Extra Clicking 'no thanks' button ***"));
	}
	
	public void shoppingBag() {
		WebElement bag = helper.getClickable(By.id("myOrderSummaryContinueButtonText"));
		bag.click();
		//TODO(hooverki): Should return a new page object for the shopping bag page.
	}
	
	public void checkoutNow() {
		WebElement checkout = helper.getClickable(By.id("guestShopperContinue"));
		helper.scrollBy(0, 30);
		checkout.click();
		helper.waitForPageLoaded();
		//TODO(hooverki): Should return a new page object for the checkout page.
	}
	
	///////////////////////

	public ExpectedCondition<Boolean> shoppingBagAppears(final String javascript, final String message) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(shoppingBagContinueButton);
					if (element != null && element.isEnabled() && element.isDisplayed()) return true;
				} catch (StaleElementReferenceException e) {
					/* do nothing */
				}
				// element not found or not clickable -- try clicking the no-thanks-button again
				System.out.println(message);
				((JavascriptExecutor)driver).executeScript(javascript, "");				
				return false;
			}
		};
	}

}
