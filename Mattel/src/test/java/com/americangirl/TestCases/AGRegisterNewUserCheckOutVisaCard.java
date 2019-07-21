	package com.americangirl.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.americangirl.Pages.AddBagToCheckOutForRegisterUser;
import com.americangirl.Pages.CreateNewRegisterUser;
import com.americangirl.Pages.EnterShippingAddress;
import com.americangirl.Pages.PaymentToCompleteOrderForRegisterUser;

public class AGRegisterNewUserCheckOutVisaCard extends TestBaseAG {

	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(@Optional("chrome") String browser) throws Exception {
		setBrowser(browser);
	}

	@Test(priority=1)	
	// Test registration page
	public void registerUser() throws Exception {
		startTest("AGRegisterUser")
		.assignCategory("smoke")
		.setDescription("Register new account");
		
		String url = configStringBuilder("AGURL","AGLogonPath");
		log.info("Navigate to login page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);

		// Test Create new account page

		CreateNewRegisterUser createRegisterUser = new CreateNewRegisterUser(getDriver());		
		String actualText = createRegisterUser.verifyCreateanAccoutText();
		Assert.assertEquals(actualText, "Create an account");
		log.info("Create an account page is displayed.");
		log.pass(actualText+ " text is verified.");
		
		//  Test Create account button
		
		createRegisterUser.createAccountButton();
		log.info("Create account button is displayed and clicked successfully.");
		
		// Enter registration detail
		
		createRegisterUser.enterAccountDetail();
		log.info("Registration detail entered successfully.");
			
		// Test promotion check box
		
		createRegisterUser.promotionCheckBox();
		log.info("Privacy statement check box checked successfully.");
		
		// Test promotion check box
		
		createRegisterUser.createNewAccountSubmitButton();
		log.info("Create account button is displayed and clicked successfully.");
		
		log.pass("Registration for new user done  successfully.");
		
		endTest();
	}

	@Test(priority=2)
	// Place order after registration user
	public void placeOrderAfterRegistration() throws Exception {
		startTest("AGOrderAsRegisteredUser")
		.assignCategory("smoke")
		.setDescription("Order after registering a new account");
		
		String url = configStringBuilder("AGURL","AGProductDollPath");
		log.info("Navigate to product detail page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);

		//Test Add product to bag and checkout product as Register user
		
		log.info("Verify add to bag functionality");
		AddBagToCheckOutForRegisterUser addProductToBag = new AddBagToCheckOutForRegisterUser(getDriver());
		addProductToBag.addToBag();
		log.info("Add to bag button is displayed and clicked successfully");
		addProductToBag.noThanks();
		log.info("No thanks link is displayed and clicked successfully");
		addProductToBag.shoppingBag();
		log.info("Shopping bag button is displayed and clicked successfully");
		addProductToBag.checkoutNow();
		log.info("Checkout button is displayed and clicked successfully");

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
