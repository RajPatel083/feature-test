package com.hotwheelscollector.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hotwheelscollector.Pages.AddBagToCheckOut;
import com.hotwheelscollector.Pages.EnterShippingAddress;
import com.hotwheelscollector.Pages.HomePage;
import com.hotwheelscollector.Pages.PaymentToCompleteOrderReg;
import com.hotwheelscollector.Pages.SignIn;
import com.hotwheelscollector.Pages.SearchProduct;
import com.mattel.Base.TestBase;

public class HWCBuyProductAsExistingRegisteredUser extends TestBase {

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
		driver.get(TestBase.CONFIG.getProperty("HWCURL"));
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
		driver.get(TestBase.CONFIG.getProperty("HWCURL"));
		SearchProduct searchpage = PageFactory.initElements(driver, SearchProduct.class);
		searchpage.searchProduct();
	}

	@Test(priority=3)
	// Place order as guest user
	public void placeRegisteredOrder() throws Exception {
		//driver.get("https://hotwheelscollectors.com/shop/en-us/hwc/shop-accessories/hwc-mug2-dtw36");

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
		payment.paymentToCompleteOrderReg();

	}
}
