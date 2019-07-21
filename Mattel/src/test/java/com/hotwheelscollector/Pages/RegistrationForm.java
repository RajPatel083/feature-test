package com.hotwheelscollector.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mattel.Base.TakeScreenshot;
import com.mattel.Base.TestBase;
//import com.mattel.Utility.Log;
import com.relevantcodes.extentreports.LogStatus;

public class RegistrationForm {

WebDriver driver;
	
	public RegistrationForm(WebDriver ldriver) {
		
		this.driver =ldriver;
		
		System.out.println("search"+driver);
	}

	
	@FindBy(id ="navLogin")
	@CacheLookup()
	WebElement Register;
	
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
	
	@FindBy(id ="WC_UserRegistrationAddForm_FormInput_userName_In_Register_1_1")
	@CacheLookup()
	WebElement Username;
	
	@FindBy(xpath =".//*[@id='WC_UserRegistrationAddForm_EmailOption_Checkbox_1_Label']/span")
	@CacheLookup()
	WebElement Promotions;

	
public void RegistrationFormClass() throws Exception {
	
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Thread.sleep(20000);
	
	try{if(Register.isDisplayed()) {
		//Log.debug("Click on Sign In/Register");
		Register.click();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Register link is clicked successfully");
	}}catch(Exception e) {
		//Register.click();
		TestBase.logger.log(LogStatus.FAIL, "Register link is not displayed");
		String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
		Assert.assertTrue(false);
		TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
	}

	Thread.sleep(14000);

	try{if(CreateNewAccount.isDisplayed()) {
		//Log.debug("Click on Create New Account");
		CreateNewAccount.click();
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Create new account button is clicked successfully");
	}}catch(Exception e) {
		//CreateNewAccount.click();
		TestBase.logger.log(LogStatus.FAIL, "Create new account button is not displayed");
		String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
		Assert.assertTrue(false);
		TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
	}

	Thread.sleep(14000);
	
	FirstName.sendKeys(TestBase.CONFIG.getProperty("FirstName1"));
	Thread.sleep(500);
	LastName.sendKeys(TestBase.CONFIG.getProperty("LastName1"));
	Thread.sleep(500);
	EmailAddress.sendKeys(TestBase.CONFIG.getProperty("EmailAddress1"));
	Thread.sleep(500);
	VerifyEmailAddress.sendKeys(TestBase.CONFIG.getProperty("EmailAddress1"));
	Password.sendKeys(TestBase.CONFIG.getProperty("Password1"));
	Thread.sleep(3000);
	VerifyPassword.sendKeys(TestBase.CONFIG.getProperty("Password1"));
	Thread.sleep(3000);
	Username.sendKeys(TestBase.CONFIG.getProperty("Username"));
	Thread.sleep(3000);
	
	try{if(Promotions.isDisplayed()) {
		//Log.debug("Click on Promotions checkbox");
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Promotions checkbox is clicked successfully");
		Promotions.click();
	}}catch(Exception e) {
		//Promotions.click();
		TestBase.logger.log(LogStatus.FAIL, "promotions checkbox is not displayed");
		String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
		Assert.assertTrue(false);
		TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
	}

	Thread.sleep(8000);
	
	try{if(CreateNewAccount.isDisplayed()) {
		//Log.debug("Click on Create New Account");
		Assert.assertTrue(true);
		TestBase.logger.log(LogStatus.PASS, "Create new account button is clicked successfully");
		CreateNewAccount.click();
	}}catch(Exception e) {
		//CreateNewAccount.click();
		TestBase.logger.log(LogStatus.FAIL, "Create new account button is not displayed");
		String screenshotPath= TakeScreenshot.getScreenshot(driver, this.getClass().getSimpleName());
		Assert.assertTrue(false);
		TestBase.logger.log(LogStatus.FAIL, TestBase.logger.addScreenCapture(screenshotPath));
	}

	Thread.sleep(14000);
	
}
}
	
	
