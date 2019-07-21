package com.americangirl.Pages;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class CreateNewRegisterUser {

	WebDriver driver;
	private PageHelper helper;
	
	public CreateNewRegisterUser(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id ="navLogin")
	@CacheLookup()
	WebElement AgrewardsSigninButton;
	
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
	
	@FindBy(id ="birth_month__AG_User_Reg")
	@CacheLookup()
	WebElement selectmonth;
	
	@FindBy(id ="birth_date__AG_User_Reg")
	@CacheLookup()
	WebElement selectdate;
	
	@FindBy(id ="birth_year_AG_User_Reg")
	@CacheLookup()
	WebElement selectyear;
	
	@FindBy(id ="demographicField5Display")
	@CacheLookup()
	WebElement displayShadow;
	
	@FindBy(css ="#WC_AGRewardsForm_div_251 > div.checkboxField > label.checkboxLabel > span")
	@CacheLookup()
	WebElement AGRewardsCheckbox;
	
	@FindBy(xpath =".//*[@id='RewardsDialog']/div[2]/div/section/div/div/a/div[2]")
	@CacheLookup()
	WebElement okpopup;
	
	@FindBy(id ="WC_UserRegistrationAddForm_EmailOption_Checkbox_1")
	@CacheLookup()
	WebElement checkboxShadow;
	
	@FindBy(xpath =".//*[@id='WC_UserRegistrationAddForm_EmailOption_Checkbox_1_Label']/span")
	@CacheLookup()
	WebElement Promotions;
	
			
	public String verifyCreateanAccoutText() {
		
		WebElement createAnAccount = helper.getElement(By.cssSelector("div#WC_AGRewardsForm_div_3 > h2"));
		String createAnAccountText = (new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOf(createAnAccount))).getText();
		
		return 	createAnAccountText;	
		}
		
	public void createAccountButton() {
		helper.waitForClickable(CreateNewAccount);
		CreateNewAccount.click();
	}
	
	//Enter registration details like user name , email id
	
	public void enterAccountDetail() throws Exception {
		
		(new WebDriverWait(driver,3)).until(ExpectedConditions.visibilityOf(FirstName));
		
		FirstName.clear();
		FirstName.sendKeys(TestBase.CONFIG.getProperty("FirstName"));
	
		LastName.clear();
		LastName.sendKeys(TestBase.CONFIG.getProperty("LastName"));
		Thread.sleep(500);
	
		EmailAddress.clear();
		String email = new Date().getTime()+TestBase.CONFIG.getProperty("Email"); 
		EmailAddress.sendKeys(email);
		Thread.sleep(500);
	
		helper.scrollBy(0, 600);
	
		VerifyEmailAddress.clear();
		VerifyEmailAddress.sendKeys(email);
		Thread.sleep(500);
	
		Password.clear();
		Password.sendKeys(TestBase.CONFIG.getProperty("Password"));
		Thread.sleep(500);
	
		VerifyPassword.clear();
		VerifyPassword.sendKeys(TestBase.CONFIG.getProperty("Password"));
		Thread.sleep(500);
	
		Select Month = new Select(selectmonth);
		Month.selectByIndex(2);

		Thread.sleep(1000);
		Select SelectDate = new Select(selectdate);
		SelectDate.selectByIndex(2);

		Thread.sleep(1000);
		Select SelectYear = new Select(selectyear);
		SelectYear.selectByVisibleText("1990");
		Thread.sleep(1000);
		
	}
	
	//for AG reward check box
		
	/*public void checkAgrewardBox() {
		
		AGRewardsCheckbox.click();
		(new WebDriverWait(driver,5)).until(ExpectedConditions.visibilityOf(okpopup));
		okpopup.click();
	
	}*/
	
	// for promotion check box	
	public void promotionCheckBox() {
		helper.waitForClickable(Promotions);
		Promotions.click();
	}	
	
	public void createNewAccountSubmitButton() throws Exception {
		helper.waitForClickable(CreateNewAccount);
		CreateNewAccount.click();
		Thread.sleep(5000);
	}
	
	
}

