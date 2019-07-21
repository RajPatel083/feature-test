package com.americangirl.TestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.*;

import com.americangirl.Pages.*;
import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class AGBuyProductAsGuestUser extends TestBaseAG {

	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(@Optional("chrome") String browser) throws Exception {
		setBrowser(browser);
	}
	
	@Test(priority=0)	
	//Test Home Page
	public void verifyHome() throws Exception {
		startTest("AGHomePage")
		.assignCategory("smoke")
		.setDescription("Verify home page functionality");
		
		String url = TestBase.CONFIG.getProperty("AGURL");
		log.info("Navigate to home page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		HomePage homepage = new HomePage(getDriver(), url);

		log.info("Check for text '"+ homepage.expected + "'");
		homepage.verifyHomePage();
		log.pass("Home page open succesfully.");
		endTest();
	}
	
	@Test(priority=2)	
	//Test Search Product 
	public void verifySearch() throws Exception {
		startTest("AGSearch")
		.assignCategory("smoke")
		.setDescription("Verify search functionality");

		String url = TestBase.CONFIG.getProperty("AGURL");
		log.info("Navigate to home page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		SearchProduct searchpage = new SearchProduct(getDriver(), url);

		log.info("Search for 'doll'");
		searchpage.searchProduct("doll");

		log.info("Check results");
		String results = (new PageHelper(getDriver())).getElement(By.cssSelector("div.searchSummaryWidget")).getText();
		assertTrue(results.contains("Search results"));
		assertTrue(results.contains("results for \"doll\""));
		log.pass("Search product successfully");
		endTest();
	}

	@Test(priority=1)
	// Place order as guest user
	public void placeGuestOrder() throws Exception {
		startTest("AGPlaceGuestOrder")
		.assignCategory("smoke")
		.setDescription("Place order as guest user");
				
		String url = configStringBuilder("AGURL","AGProductDollPath");
		log.info("Navigate to product detail page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);

		//Test Add product to bag and check product 
		
		log.info("Verify add to bag functionality");
		AddBagToCheckOut addProductToBag = new AddBagToCheckOut(getDriver());
		addProductToBag.addToBag();
		log.info("Add to bag button is displayed and clicked successfully");
		addProductToBag.noThanks();
		log.info("No thanks link is displayed and clicked successfully");
		addProductToBag.shoppingBag();
		log.info("Shopping bag button is displayed and clicked successfully");
		addProductToBag.checkoutNow();
		log.info("Checkout button is displayed and clicked successfully");

		//Test check out product as guest user

		log.info("Verify checkout page functionality");
		CheckOutAsGuestUser checkoutasGuestUser = new CheckOutAsGuestUser(getDriver());
		checkoutasGuestUser.checkoutProductasGuestUser();
		log.info("Checkout as guest");
		checkoutasGuestUser.selectBirthdate();
		log.info("Age Gateway - Birthdate entered");

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
