package com.americangirl.Pages;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class PaymentToCompleteOrder {

	WebDriver driver;
	private PageHelper helper;
	
	public PaymentToCompleteOrder(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		PageFactory.initElements(driver, this);
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
	
	@FindBy(id="shippingBillingPageNext")
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
	
	public void enterCreditCardPayment() throws Exception {
		
		helper.waitForClickable(ReviewOrderButton);

		NameOnCard.sendKeys(TestBase.CONFIG.getProperty("MasterCardName"));
		CreditCardNo.sendKeys(TestBase.CONFIG.getProperty("MasterCardNumber"));
		// Firefox seems to lose the CCV for some reason, so enter it after the expire date
		// //CVVNo.sendKeys(TestBase.CONFIG.getProperty("MasterCardSecurityCode"));
		Select selectExpiryMonth= new Select(ExpiraryMonth);
		selectExpiryMonth.selectByVisibleText(TestBase.CONFIG.getProperty("Card_ExpMonth"));
		Select selectExpiryYear= new Select(ExpiraryYear);
		selectExpiryYear.selectByVisibleText(TestBase.CONFIG.getProperty("Card_ExpYear"));
		CVVNo.sendKeys(TestBase.CONFIG.getProperty("MasterCardSecurityCode")); // Help firefox out
		assertEquals(CVVNo.getAttribute("value"),TestBase.CONFIG.getProperty("MasterCardSecurityCode"),"Incorrect CVV");
	}
	
	public void enterGiftCardPayment() {
		/*/////////// for Gift card payment start ////////////////
		
		GiftCardNo.sendKeys(TestBase.CONFIG.getProperty("GiftCardNumber"));
		GiftCardCVV.sendKeys(TestBase.CONFIG.getProperty("GiftCardPin"));
		
		try {if(GiftCardApply.isDisplayed()) {
			GiftCardApply.click();
			}}catch(Exception e) {
				GiftCardApply.click();
			}
		Thread.sleep(12000);

		
		/////////// for Gift card payment end ////////////////  */
	}
	
	public void completeOrder() {
		
		EmailId.click(); // Help firefox out
		EmailId.sendKeys(TestBase.CONFIG.getProperty("Email"));
		assertEquals(EmailId.getAttribute("value"),TestBase.CONFIG.getProperty("Email"),"Incorrect email");
		ReviewOrderButton.click();

		helper.getElement(By.id("payment"));
		helper.waitForClickable(CompleteOrder);
		CompleteOrder.click();
	}
	
	public void orderConfirmationPage() {
		(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(ConfirmationText));
		Assert.assertEquals(ConfirmationText.getText(), "Your order is complete.");
	}
	
	public String getOrderNumberMessage() {
		return OrderNumberMessage.getText();
	}
	
}
