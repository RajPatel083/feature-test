package com.fisherPrice.TestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fisherPrice.Pages.AddBagToCheckOut;
import com.fisherPrice.Pages.CheckOutAsGuestUser;
import com.fisherPrice.Pages.EnterShippingAddress;
import com.fisherPrice.Pages.HomePage;
import com.fisherPrice.Pages.PaymentToCompleteOrder;
import com.fisherPrice.Pages.SearchProduct;
import com.mattel.Base.TestBase;

public class FPBuyProductAsGuestUser extends TestBaseFP {

	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(@Optional("firefox") String browser) throws Exception {	
		setBrowser(browser);
	}
	
	@Test(priority=0)	
	//Test Home Page
	public void verifyHome() throws Exception {
		startTest("FPHomePage")
		.assignCategory("smoke")
		.setDescription("Verify home page functionality");
				
		String url = TestBase.CONFIG.getProperty("FPURL");
		log.info("Navigate to home page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		HomePage homepage = new HomePage(getDriver(), url);
		log.info("Check for text");
		homepage.verifyHomePage();
		log.pass("Home page open succesfully.");
		endTest();
	}
		
	@Test(priority=2)	
	//Test Search Product 
	public void verifySearch() throws Exception {
		startTest("FPSearch")
		.assignCategory("smoke")
		.setDescription("Verify search functionality");

		String url = TestBase.CONFIG.getProperty("FPURL");
		log.info("Navigate to home page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		SearchProduct searchpage = new SearchProduct(getDriver(), url);

		log.info("Search for 'car'");
		searchpage.searchProduct("car");

		log.info("Check results");
		String results = getDriver().findElement(By.cssSelector("div.searchSummaryWidget")).getText();
		assertTrue(results.toLowerCase().contains("search results"),"Looking for 'search results' text, ");
		assertTrue(results.toLowerCase().contains("results for \"car\""),"Looking for 'results for \"car\" text, ");
		log.pass("Search product successfully");
		endTest();
	}
	
	@Test(priority=1)
	// Place order as guest user
		public void placeGuestOrder() throws Exception {
			startTest("FPPlaceGuestOrder")
			.assignCategory("smoke")
			.setDescription("Place order as guest user");
			
			String url = configStringBuilder("FPURL","FPProductCarPath");
			log.info("Navigate to product detail page:");
			log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
			getDriver().get(url);

			//Test Add product to bag and check product 
			log.info("Verify add to bag functionality");
			AddBagToCheckOut addProductToBag = new AddBagToCheckOut(getDriver());
			addProductToBag.addToBag();
			
			log.info("Checkout button is displayed and clicked successfully");
			addProductToBag.checkoutNow();

			//Test check out product as guest user

			log.info("Verify checkout page functionality");
			CheckOutAsGuestUser checkoutasGuestUser = new CheckOutAsGuestUser(getDriver());
			checkoutasGuestUser.checkoutProductasGuestUser();
			log.info("Checkout as guest");
			
			//Test shipping address functionality

			log.info("Verify shipping page functionality");
			EnterShippingAddress entershippingaddress = new EnterShippingAddress(getDriver());
			entershippingaddress.enterShippingDetail();
			log.info("Shipping details are entered successfully");
			entershippingaddress.AddressValidationDialog();
			log.info("Dismissed address validation dialog");

			//Test payment functionality

			log.info("Verify payment page functionality");	
			PaymentToCompleteOrder payment = new PaymentToCompleteOrder(getDriver());
			payment.enterCreditCardPayment();
			payment.completeOrder();
			log.info("Wait for order confirmation page");	
			payment.orderConfirmationPage();

			String msg = payment.getOrderNumberMessage();
			log.pass(msg);
			System.out.println(msg);

			endTest();
		}
		
		
}
