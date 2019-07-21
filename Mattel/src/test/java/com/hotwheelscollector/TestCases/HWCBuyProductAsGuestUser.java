package com.hotwheelscollector.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hotwheelscollector.Pages.AddBagToCheckOut;
import com.hotwheelscollector.Pages.CheckOutAsGuestUser;
import com.hotwheelscollector.Pages.EnterShippingAddress;
import com.hotwheelscollector.Pages.HomePage;
import com.hotwheelscollector.Pages.PaymentToCompleteOrder;
import com.hotwheelscollector.Pages.SearchProduct;
import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class HWCBuyProductAsGuestUser extends TestBaseHWC {

	public WebDriver driver;
	
	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(String browser) throws Exception {
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
	//Test Search Product 
	public void verifySearch() throws Exception {
		driver.get(TestBase.CONFIG.getProperty("HWCURL"));
		SearchProduct searchpage = PageFactory.initElements(driver, SearchProduct.class);
		searchpage.searchProduct();
	}

	@Test(priority=2)
	// Place order as guest user
	public void placeGuestOrder() throws Exception {
		//driver.get("https://hotwheelscollectors.com/shop/en-us/hwc/shop-accessories/hwc-mug2-dtw36");

		//Test Add product to bag and check product 

		AddBagToCheckOut addProducttoBag = PageFactory.initElements(driver, AddBagToCheckOut.class);
		addProducttoBag.addBagtocheckoutProduct();

		//Test check out product as guest user

		//System.out.println("Webdriver value"+driver);
		CheckOutAsGuestUser checkoutasGuestUser = PageFactory.initElements(driver, CheckOutAsGuestUser.class);
		checkoutasGuestUser.checkoutProductasGuestUser();

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
