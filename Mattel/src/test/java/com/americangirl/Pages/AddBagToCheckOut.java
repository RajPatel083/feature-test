package com.americangirl.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.mattel.Utility.PageHelper;

public class AddBagToCheckOut {

	private WebDriver driver;
	private PageHelper helper;
	
	public AddBagToCheckOut(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
	}
	
	private String noThanksSelector = "a.no-thanks-button";
	private By noThanksButton = By.cssSelector(noThanksSelector);
	private By shoppingBagContinueButton = By.id("myOrderSummaryContinueButtonText2");
	private By addToCartButton = By.cssSelector("#productPageAdd2Cart");
	private By orderSummaryContinueButton = By.id("myOrderSummaryContinueButtonText");
	
	private String jQueryClickNoThanks = "$('"+noThanksSelector+"').click()";
	private String javaScriptScrollTop = "window.scrollTo(0,0)";
	
	///////////////////////
	
	public void addToBag() {
		helper.getClickable(addToCartButton).click();
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
		helper.getClickable(shoppingBagContinueButton).click();
		//TODO(hooverki): Should return a new page object for the shopping bag page.
	}
	
	public void checkoutNow() {
		WebElement checkout = helper.getElement(orderSummaryContinueButton);
		helper.doJavascript(javaScriptScrollTop);
		helper.waitForClickable(checkout);
		checkout.click();
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
