package com.americangirl.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class EnterShippingAddress {

	WebDriver driver;
	private PageHelper helper;
	
	public EnterShippingAddress(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_firstName_1")
	@CacheLookup
	WebElement firstName;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_lastName_1")
	@CacheLookup
	WebElement lastName;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_country_1")
	@CacheLookup
	WebElement country;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_address1_1")
	@CacheLookup
	WebElement streetAddress;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_city_1")
	@CacheLookup
	WebElement city;

	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_state_1")
	@CacheLookup
	WebElement state;
	
	@FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddress_zipCode_1")
	@CacheLookup
	WebElement zipcode;

	@FindBy(id = "myOrderSummaryContinueButtonText")
	@CacheLookup
	WebElement continuetoPayment;
	
	@FindBy(id = "useSugAddress")
	@CacheLookup
	WebElement UseUSPSaddress;
	

		
	public void enterShippingDetail() throws Exception {

		helper.waitForClickable(continuetoPayment);

		firstName.sendKeys(TestBase.CONFIG.getProperty("FirstName"));
		lastName.sendKeys(TestBase.CONFIG.getProperty("LastName"));
		Select selectCountry = new Select(country);
		selectCountry.selectByVisibleText(TestBase.CONFIG.getProperty("Country"));
		streetAddress.sendKeys(TestBase.CONFIG.getProperty("StreetAdd"));
		city.clear();
		city.sendKeys(TestBase.CONFIG.getProperty("City"));

		helper.scrollBy(0, 100);
	
		Select selectState = new Select(state);
		selectState.selectByVisibleText(TestBase.CONFIG.getProperty("State"));
		zipcode.sendKeys(TestBase.CONFIG.getProperty("Zipcode"));
	
		continuetoPayment.click();

	}
	
	public void AddressValidationDialog() {
		helper.waitForClickable(UseUSPSaddress);
		UseUSPSaddress.click();
	}
	
}

