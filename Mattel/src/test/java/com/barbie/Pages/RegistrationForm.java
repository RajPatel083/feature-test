package com.barbie.Pages;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class RegistrationForm {

	WebDriver driver;
	public PageHelper helper;
	
	public RegistrationForm(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id ="WC_UserRegistrationAddForm_links_1")
	@CacheLookup()
	WebElement CreateNewAccount;
	
	@FindBy(id ="WC_SignInRegistrationFormTop_NameEntryForm_FormInput_firstName_1")
	@CacheLookup()
	WebElement FirstName;
	
	@FindBy(id ="WC_SignInRegistrationFormTop_NameEntryForm_FormInput_lastName_1")
	@CacheLookup()
	WebElement LastName;
	
	@FindBy(id ="WC_UserRegistrationAddForm_FormInput_logonId_In_Register_1_1")
	@CacheLookup()
	WebElement EmailAddress;
	
	@FindBy(id ="WC_UserRegistrationAddForm_FormInput_logonId_In_Register_1_1_1")
	@CacheLookup()
	WebElement VerifyEmailAddress;
	
	@FindBy(id ="WC_UserRegistrationAddForm_FormInput_logonPassword_In_Register_1")
	@CacheLookup()
	WebElement Password;
	
	@FindBy(id ="WC_UserRegistrationAddForm_FormInput_logonPasswordVerify_In_Register_1")
	@CacheLookup()
	WebElement VerifyPassword;
	
	@FindBy(css ="label#WC_UserRegistrationAddForm_BlogOption_Checkbox_1_Label > span")
	@CacheLookup()
	WebElement Blog;

	@FindBy(css ="label#WC_UserRegistrationAddForm_EmailOption_Checkbox_1_Label > span")
	@CacheLookup()
	WebElement Promotions;
	
	/////////////////////////////////
	
			
	public String verifyCreateanAccoutText() {
		
		WebElement createAnAccount = helper.getElement(By.cssSelector("div#WC_AccountDisplay_div_19 > h2"));
		helper.waitForClickable(createAnAccount);
		String createAnAccountText = createAnAccount.getText();
		return 	createAnAccountText;	
		}
		
	public void createAccountButton() {
		(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(CreateNewAccount));
		CreateNewAccount.click();
		
			}
	
	//Enter registration details like user name , email id
	
		public void enterAccountDetail() throws Exception {
			
			WebElement Fname = helper.getElement(By.id("WC_SignInRegistrationFormTop_NameEntryForm_FormInput_firstName_1"));
			
			Fname.clear();
			Fname.sendKeys(TestBase.CONFIG.getProperty("FirstName"));
		
			LastName.clear();
			LastName.sendKeys(TestBase.CONFIG.getProperty("LastName"));
			Thread.sleep(500);
		
			EmailAddress.clear();
			String email = new Date().getTime()+TestBase.CONFIG.getProperty("Email"); 
			EmailAddress.sendKeys(email);
			Thread.sleep(500);
		
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)", "");
		
			VerifyEmailAddress.clear();
			VerifyEmailAddress.sendKeys(email);
			Thread.sleep(500);
		
			Password.clear();
			Password.sendKeys(TestBase.CONFIG.getProperty("Password"));
			Thread.sleep(500);
		
			VerifyPassword.clear();
			VerifyPassword.sendKeys(TestBase.CONFIG.getProperty("Password"));
			
			
		}
	
		/*//for  BA Signature check box
		public void baSignatureCheckBox() {
			
			(new WebDriverWait(driver,2)).until(ExpectedConditions.visibilityOf(Blog));
			Blog.click();
		
		}*/
		
		// for BA promotion check box	
		/*public void promotionCheckBox() {
			(new WebDriverWait(driver,2)).until(ExpectedConditions.visibilityOf(Promotions));
			Promotions.click();
		}	*/
		
		public void createNewAccountSubmitButton() throws Exception {
			
			helper.waitForPageLoaded();
			(new WebDriverWait(driver,3)).until(ExpectedConditions.visibilityOf(CreateNewAccount));
			CreateNewAccount.click();
			Thread.sleep(5000);
		}
		

}
	
	
