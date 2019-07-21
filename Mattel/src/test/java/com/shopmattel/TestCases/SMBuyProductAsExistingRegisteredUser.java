package com.shopmattel.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.shopmattel.Pages.SignIn;
import com.shopmattel.Pages.AddBagToCheckOut;
import com.shopmattel.Pages.EnterShippingAddress;
import com.shopmattel.Pages.HomePage;
import com.shopmattel.Pages.PaymentToCompleteOrderReg;
import com.shopmattel.Pages.SearchProduct;
import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SMBuyProductAsExistingRegisteredUser extends TestBase {

	
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
		driver.get(TestBase.CONFIG.getProperty("SMURL"));
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Thread.sleep(5000);
		homepage.verifyHomePage();
	}
	
	@Test(priority=1)	
	//Test Sign In 
	public void Signin() throws Exception {
		startTest("HWSignIn")
		.assignCategory("hw",browser,"smoke")
		.setDescription("Verify Sign In functionality");
		
		logInfo("Verify Sign In link availaibility");
			driver.get(TestBase.CONFIG.getProperty("HWURL"));
			SignIn SignIn1 = PageFactory.initElements(driver, SignIn.class);
			SignIn1.SignInLink();

		
		logInfo("Verify EmailId field availaibility");
		SignIn1.EmailId();
		
		logInfo("Verify Password field availaibility");
		SignIn1.Password();
		
		logInfo("Verify Sign In button is clicked");
		SignIn1.SignInOption();
	}
	
	@Test(priority=2)	
	//Test Search Product 
	public void verifySearch() throws Exception {
		driver.get(TestBase.CONFIG.getProperty("SMURL"));
		SearchProduct searchpage = PageFactory.initElements(driver, SearchProduct.class);
		searchpage.searchProduct();
	}

	@Test(priority=3)
	// Place order as registered user
	public void placeRegisteredOrder() throws Exception {
		driver.get("http://mqa2.shop.corp.mattel.com/shop/en-us/ms/birthday-wishes-2016-%28cau%29-bundle-dgw29");

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
