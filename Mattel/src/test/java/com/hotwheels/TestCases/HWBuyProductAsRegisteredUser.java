package com.hotwheels.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fisherPrice.Pages.RegistrationForm;
import com.hotwheels.Pages.AddBagToCheckOut;
import com.hotwheels.Pages.EnterShippingAddress;
import com.hotwheels.Pages.HomePage;
import com.hotwheels.Pages.PaymentToCompleteOrderReg;
import com.hotwheels.Pages.SearchProduct;
import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class HWBuyProductAsRegisteredUser extends TestBase {

	
public WebDriver driver;
	
	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(String browser) throws Exception {
		initialize();
		driver = new TestBase().launchBrowserOnGrid(browser);
		Thread.sleep(10000);
	}
	
	
	@Test(priority=0)	
	//Test Home Page
	public void verifyHome() throws Exception {
		driver.get(TestBase.CONFIG.getProperty("HWURL"));
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Thread.sleep(5000);
		homepage.verifyHomePage();
	}
	
	@Test(priority=1)	
	//Test Registration
	public void verifyRegistrationForm() throws Exception {
		driver.get(TestBase.CONFIG.getProperty("HWURL"));
		RegistrationForm RegistrationFormClass = PageFactory.initElements(driver, RegistrationForm.class);
		//RegistrationFormClass.RegistrationFormClass();
	}
	
	@Test(priority=2)	
	//Test Search Product 
	public void verifySearch() throws Exception {
		driver.get(TestBase.CONFIG.getProperty("HWURL"));
		SearchProduct searchpage = PageFactory.initElements(driver, SearchProduct.class);
		searchpage.searchProduct();
	}

	@Test(priority=3)
	// Place order as registered user
	public void placeRegisteredOrder() throws Exception {
		driver.get("https://hotwheels.mattel.com/shop/en-us/hw/hot-wheels-star-wars-bb-8-character-car-fdj75");

		//Test Add product to bag and check product 

		AddBagToCheckOut addProducttoBag = PageFactory.initElements(driver, AddBagToCheckOut.class);
		addProducttoBag.addBagtocheckoutProduct();

		//Test check out product as guest user

		//System.out.println("Webdriver value"+driver);
		//CheckOutAsGuestUser checkoutasGuestUser = PageFactory.initElements(driver, CheckOutAsGuestUser.class);
		//checkoutasGuestUser.checkoutProductasGuestUser();

		//Test shipping address functionality

		//System.out.println("Webdriver value"+driver);
		EnterShippingAddress entershippingaddress = PageFactory.initElements(driver, EnterShippingAddress.class);
		entershippingaddress.enterShippingDeatil();

		//Test payment functionality

		//System.out.println("Webdriver value"+driver);
		PaymentToCompleteOrderReg payment = PageFactory.initElements(driver, PaymentToCompleteOrderReg.class);
		///////// call the method ////////////
		payment.paymentToCompleteOrder();

	}
	
}
