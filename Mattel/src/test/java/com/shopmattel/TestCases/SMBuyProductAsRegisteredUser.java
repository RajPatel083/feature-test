package com.shopmattel.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mattel.Base.TestBase;
import com.shopmattel.Pages.AddBagToCheckOut;
import com.shopmattel.Pages.EnterShippingAddress;
import com.shopmattel.Pages.PaymentToCompleteOrderForRegisterUser;
import com.shopmattel.Pages.RegistrationForm;

public class SMBuyProductAsRegisteredUser extends TestBase {

	
	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(@Optional("firefox") String browser) throws Exception {	
		setBrowser(browser);
	}
		
	@Test(priority=1)	
	// Test registration page
	public void registerUser() throws Exception {
		startTest("SMRegisterUser")
		.assignCategory("smoke")
		.setDescription("Register new account");
		
		String url = configStringBuilder("SMURL","SMLogonPath");
		log.info("Navigate to login page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);

		// Test create new account page 

		RegistrationForm createRegisterUser = new RegistrationForm(getDriver());		
		String actualText = createRegisterUser.verifyCreateanAccoutText();
		Assert.assertEquals(actualText, "New Customers - Create an Account");
		log.info("Create an account page is displayed.");
		log.pass(actualText+ " text is verified.");
		
		//  Test Create account button
		
		createRegisterUser.createAccountButton();
		log.info("Create account button is displayed and clicked successfully.");
		
		// Enter registration detail
		
		createRegisterUser.enterAccountDetail();
		log.info("Registration detail entered successfully.");
			
		// Test promotion check box
		
		/*createRegisterUser.promotionCheckBox();
		logInfo("BA promotion check box checked successfully.");
		*/
		// Test promotion check box
		
		createRegisterUser.createNewAccountSubmitButton();
		log.info("Create account button is displayed and clicked successfully.");
		
		log.pass("Registration for new user done  successfully.");
		
		endTest();
	}
	
	@Test(priority=2)
	// Place order as registered user
	public void placeRegisteredOrder() throws Exception {
		startTest("SMPlaceGuestOrder")
		.assignCategory("smoke")
		.setDescription("Order after registering a new account");
		
		String url = configStringBuilder("SMURL","SMProductDollPath");
		log.info("Navigate to product detail page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);
		
		//Test Add product to bag and check product 

		log.info("Verify add to bag functionality");
		AddBagToCheckOut addProductToBag = new AddBagToCheckOut(getDriver());
		addProductToBag.addToBag();
		
		log.info("Checkout button is displayed and clicked successfully");
		addProductToBag.checkoutNow();

		//Test shipping address functionality

		log.info("Verify shipping page functionality");
		EnterShippingAddress entershippingaddress = new EnterShippingAddress(getDriver());
		entershippingaddress.enterShippingDetail();
		log.info("Shipping details are entered successfully");
		entershippingaddress.AddressValidationDialog();
		log.info("Dismissed address validation dialog");

		//Test payment functionality

		log.info("Verify payment page functionality");	
		PaymentToCompleteOrderForRegisterUser payment = new PaymentToCompleteOrderForRegisterUser(getDriver());
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
