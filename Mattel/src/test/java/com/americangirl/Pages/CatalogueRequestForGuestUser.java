package com.americangirl.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class CatalogueRequestForGuestUser {

	WebDriver driver;
	private PageHelper helper;
	
	public CatalogueRequestForGuestUser(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		PageFactory.initElements(driver, this);
		
}
	
	@FindBy(css = "div#AG_accordion > div:nth-of-type(2) > div > a > h4 > i")
	@CacheLookup
	WebElement ExpandButton;
	
	@FindBy(css = "div#collapseTwo > div > ul > li:nth-of-type(5) > a")
	@CacheLookup
	WebElement CatalogueRequest;
	
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
	
	
	@FindBy(id = "mattel-gift-line4")
	@CacheLookup
	WebElement NameofRecipient;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_catalogReguestForm_firstName_1")
	@CacheLookup
	WebElement FirstName;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_catalogReguestForm_lastName_1")
	@CacheLookup
	WebElement LastName;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_catalogReguestForm_address1_1")
	@CacheLookup
	WebElement Address;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_catalogReguestForm_city_1")
	@CacheLookup
	WebElement City;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_catalogReguestForm_state_1")
	@CacheLookup
	WebElement State;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_catalogReguestForm_zipCode_1")
	@CacheLookup
	WebElement ZipCode;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_catalogReguestForm_email1_1")
	@CacheLookup
	WebElement EmailId;
	
	
	
	public void Clickexpand() throws Exception {
		
		helper.scrollBy(0, 800);
		helper.waitForClickable(ExpandButton);
		ExpandButton.click();
		
	}
	
public void Clickgiftcard() throws Exception {
		
		helper.waitForClickable(GiftCard);
		GiftCard.click();
		
	}

public void Clickphysicalgiftcard() throws Exception {
	
	helper.waitForClickable(PhysicalGiftCard);
	PhysicalGiftCard.click();
	
}

public void Selectgiftcard() throws Exception {
	
	helper.waitForClickable(SelectGiftCard);
	SelectGiftCard.click();
	
}

public void EnterRecipient() throws Exception {
	
	helper.waitForClickable(NameofRecipient);
	NameofRecipient.sendKeys(TestBase.CONFIG.getProperty("Recipeint Name"));
	
}

public void EnterSender() throws Exception {
	
	helper.waitForClickable(NameofSender);
	NameofSender.sendKeys(TestBase.CONFIG.getProperty("Sender Name"));
	
}

public void Selectmessage() throws Exception {
	
	helper.waitForClickable(SelectMessage);
	Select Message = new Select(SelectMessage);
	Message.selectByIndex(10);
	
}

public void EnterMessageLine1() throws Exception {
	
	helper.waitForClickable(MessageLine1);
	MessageLine1.sendKeys(TestBase.CONFIG.getProperty("Message Line1"));
	
}

public void EnterMessageLine2() throws Exception {
	
	helper.waitForClickable(MessageLine2);
	MessageLine2.sendKeys(TestBase.CONFIG.getProperty("Message Line2"));
	
}

public void EnterMessageLine3() throws Exception {
	
	helper.waitForClickable(MessageLine3);
	MessageLine3.sendKeys(TestBase.CONFIG.getProperty("Message Line3"));
	
}

public void EnterAmount() throws Exception {
	
	helper.waitForClickable(Amount);
	Amount.sendKeys(TestBase.CONFIG.getProperty("Amount"));
	
}

public void Addtobag() throws Exception {
	
	helper.waitForClickable(AddToBag);
	AddToBag.click();
	
}

}
