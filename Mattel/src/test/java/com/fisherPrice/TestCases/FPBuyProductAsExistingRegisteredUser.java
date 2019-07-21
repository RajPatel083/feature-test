package com.fisherPrice.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fisherPrice.Pages.AddBagToCheckOut;
import com.fisherPrice.Pages.EnterShippingAddress;
import com.fisherPrice.Pages.HomePage;
import com.fisherPrice.Pages.PaymentToCompleteOrder;
import com.fisherPrice.Pages.SignIn;
import com.fisherPrice.Pages.SearchProduct;
import com.mattel.Base.TestBase;

public class FPBuyProductAsExistingRegisteredUser extends TestBaseFP {

public WebDriver driver;
	
	@SuppressWarnings("deprecation")
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
		driver.get(TestBase.CONFIG.getProperty("FPURL"));
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Thread.sleep(5000);
		homepage.verifyHomePage();
	}
	
	@Test(priority=1)	
	//Test Sign In 
	public void Signin() throws Exception {
		startTest("FPSignIn")
		.assignCategory("ba",browser,"smoke")
		.setDescription("Verify Sign In functionality");
		
		logInfo("Verify Sign In link availaibility");
			driver.get(TestBase.CONFIG.getProperty("FPURL"));
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
		driver.get(TestBase.CONFIG.getProperty("FPURL"));
		SearchProduct searchpage = PageFactory.initElements(driver, SearchProduct.class);
		searchpage.searchProduct();
	}

	@Test(priority=3)
	// Place order as registered user
	public void placeRegisteredOrder() throws Exception {
		driver.get("https://fisher-price.mattel.com/shop/en-us/fp/whats-new/think-learn-smart-cycle-drp30");

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
		PaymentToCompleteOrder payment = PageFactory.initElements(driver, PaymentToCompleteOrder.class);
		///////// call the method ////////////
		payment.paymentToCompleteOrder();

	}
		
}
