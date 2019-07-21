package com.hotwheels.Pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class EnterShippingAddress {

	public WebDriver driver;
	public PageHelper helper;
	
	public EnterShippingAddress(WebDriver ldriver) {
		this.driver =ldriver;
		this.helper = new PageHelper(ldriver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

WebElement Fname = helper.getElement(By.id("WC__ShoppingCartAddressEntryForm_shippingAddress_firstName_1"));
		
		Select selectCountry = new Select(country);
		selectCountry.selectByVisibleText(TestBase.CONFIG.getProperty("BACountry"));
		streetAddress.sendKeys(TestBase.CONFIG.getProperty("StreetAdd"));
		Fname.sendKeys(TestBase.CONFIG.getProperty("FirstName"));
		lastName.sendKeys(TestBase.CONFIG.getProperty("LastName"));
		city.clear();
		city.sendKeys(TestBase.CONFIG.getProperty("City"));

		helper.scrollBy(0, 300);
	
		Select selectState = new Select(state);
		selectState.selectByVisibleText(TestBase.CONFIG.getProperty("State"));
		zipcode.sendKeys(TestBase.CONFIG.getProperty("Zipcode"));
		
		WebElement continuetoPayment = helper.getElement(By.id("WC_UnregisteredCheckout_links_4"));
		helper.waitForClickable(continuetoPayment);
		
		helper.doJavascript("document.getElementsByClassName('button_primary button_left_padding  t_order_payment ')[0].click()");
	}
	
	public void AddressValidationDialog() {
		WebElement UseUSPSaddress = helper.getElement(By.id("useSugAddress"));
		helper.waitForClickable(UseUSPSaddress);
		UseUSPSaddress.click();
	}
	
}

