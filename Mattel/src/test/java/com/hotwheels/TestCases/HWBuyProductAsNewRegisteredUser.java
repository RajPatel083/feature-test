package com.hotwheels.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.hotwheels.Pages.AddBagToCheckOut;
import com.hotwheels.Pages.EnterShippingAddress;
import com.hotwheels.Pages.PaymentToCompleteOrderForRegisterUser;
import com.hotwheels.Pages.RegistrationForm;
import com.mattel.Base.TestBase;

public class HWBuyProductAsNewRegisteredUser extends TestBase {

	
	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(@Optional("firefox") String browser) throws Exception {	
		setBrowser(browser);
	}
		
	@Test(priority=1)	
	// Test registration page
	public void registerUser() throws Exception {
		startTest("HWRegisterUser")
		.assignCategory("smoke")
		.setDescription("Register new account");
		
		String url = configStringBuilder("HWURL","HWLogonPath");
		log.info("Navigate to login page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);

		// Test create new account page 

		RegistrationForm createRegisterUser = new RegistrationForm(getDriver());		
		String actualText = createRegisterUser.verifyCreateanAccoutText();
		Assert.assertEquals(actualText, "NEW CUSTOMERS - CREATE AN ACCOUNT");
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
		startTest("HWPlaceGuestOrder")
		.assignCategory("smoke")
		.setDescription("Order after registering a new account");
		
		String url = configStringBuilder("HWURL","HWProductCarPath");
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
