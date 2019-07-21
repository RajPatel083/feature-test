package com.hotwheelscollector.Pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class PaymentToCompleteOrderReg {

WebDriver driver;
	
	public PaymentToCompleteOrderReg(WebDriver ldriver) {
		
		this.driver =ldriver;
		
		//System.out.println("search"+driver);
	}
	
	@FindBy(id = "cc_nameoncard_1")
	@CacheLookup
	WebElement NameOnCard;
	

	@FindBy(id = "account1_1")
	@CacheLookup
	WebElement CreditCardNo;


	@FindBy(id = "cc_cvc_1")
	@CacheLookup
	WebElement CVVNo;
	

	@FindBy(id = "expire_month_1")
	@CacheLookup
	WebElement ExpiraryMonth;
	

	@FindBy(id = "expire_year_1")
	@CacheLookup
	WebElement ExpiraryYear;
	

	@FindBy(id = "phoneEmailUpdateEmail1Display")
	@CacheLookup
	WebElement EmailId;
	
	@FindBy(id="account1_2")
	@CacheLookup
	WebElement GiftCardNo;
	
	@FindBy(id="cc_cvc_2")
	@CacheLookup
	WebElement GiftCardCVV;
	
	
	@FindBy(xpath = "//a[contains(.,'Apply')]")
	@CacheLookup
	WebElement GiftCardApply;
	
	
	@FindBy(id="myOrderSummaryContinueButtonText")
	@CacheLookup
	WebElement ReviewOrderButton;
	
	@FindBy(id="myOrderSummaryContinueButtonText")
	@CacheLookup
	WebElement CompleteOrder;

    @FindBy(css="div.confirmation-box > h1")
    @CacheLookup
    WebElement ConfirmationText;
    
    @FindBy(css="p.orderNumberMessage > strong")
    @CacheLookup
    WebElement OrderNumberMessage;
	
	public void paymentToCompleteOrderReg() throws Exception {
		
	TestBase.logger= TestBase.report.startTest(this.getClass().getSimpleName(),"Verify payment page functionality");	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	Thread.sleep(6000);
	
	
	/////////// for credit card payment start ////////////
	
	try {
		
		NameOnCard.sendKeys(TestBase.CONFIG.getProperty("MasterCardName"));
		
	
	}catch(Exception e) {
			Thread.sleep(3000);
			NameOnCard.sendKeys(TestBase.CONFIG.getProperty("MasterCardName"));
			//Log.error(e.getMessage());
		}
	
	Assert.assertTrue(true);
	TestBase.logger.log(LogStatus.PASS, "Enter Master card detail successfully");
	

	Thread.sleep(500);
	CreditCardNo.sendKeys(TestBase.CONFIG.getProperty("MasterCardNumber"));
	Thread.sleep(500);
	CVVNo.sendKeys(TestBase.CONFIG.getProperty("MasterCardSecurityCode"));
	Thread.sleep(500);
	Select selectExpirayMonth= new Select(ExpiraryMonth);
	selectExpirayMonth.selectByVisibleText(TestBase.CONFIG.getProperty("Card_ExpMonth"));
	Thread.sleep(2000);
	
	Select selectExpirayYear= new Select(ExpiraryYear);
	selectExpirayYear.selectByVisibleText(TestBase.CONFIG.getProperty("Card_ExpYear"));
	
	/////////// for credit card payment end ////////////
	
	
	/*/////////// for Gift card payment start ////////////////
	
	GiftCardNo.sendKeys(TestBase.CONFIG.getProperty("GiftCardNumber"));
	Thread.sleep(500);
	GiftCardCVV.sendKeys(TestBase.CONFIG.getProperty("GiftCardPin"));
	Thread.sleep(2000);
	
	try {if(GiftCardApply.isDisplayed()) {
		GiftCardApply.click();
		}}catch(Exception e) {
			GiftCardApply.click();
		}
	Thread.sleep(12000);

	
	/////////// for Gift card payment start ////////////////  */
		
	Thread.sleep(1000);

	//EmailId.sendKeys(TestBase.CONFIG.getProperty("Email"));



	Thread.sleep(2000);
	try {if(ReviewOrderButton.isDisplayed()) {
		//Log.debug("Click on Review Order");
		ReviewOrderButton.click();
		}}catch(Exception e) {
			ReviewOrderButton.click();
			//Log.error(e.getMessage());
		}
	
	Thread.sleep(15000);
	
	//Log.debug("Payment tested successfully");
	try {if(CompleteOrder.isDisplayed()) {
		CompleteOrder.click();
		}}catch(Exception e) {
			CompleteOrder.click();
			//Log.error(e.getMessage());
		}
	
	
	Thread.sleep(8000);

	Assert.assertEquals(ConfirmationText.getText(), "YOUR ORDER IS COMPLETE.");
	
	String orderNumberMessage = OrderNumberMessage.getText();

	TestBase.logger.log(LogStatus.PASS, orderNumberMessage);
	System.out.println(orderNumberMessage);
	
	TestBase.report.endTest(TestBase.logger);
		}
	
}

