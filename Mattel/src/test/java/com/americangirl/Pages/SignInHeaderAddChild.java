package com.americangirl.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mattel.Base.TestBase;
import com.mattel.Utility.PageHelper;

public class SignInHeaderAddChild {

	WebDriver driver;
	private PageHelper helper;
	
	public SignInHeaderAddChild(WebDriver ldriver) {
		this.driver = ldriver;
		this.helper = new PageHelper(ldriver);
		PageFactory.initElements(driver, this);
		
}
	

	@FindBy(css = "div#EmailSignupEspot > a > span:nth-of-type(2)")
	@CacheLookup
	WebElement SignUpNow;
	
	@FindBy(id = "birth_month")
	@CacheLookup
	WebElement selectmonth;
	
	@FindBy(id = "birth_date")
	@CacheLookup
	WebElement selectdate;
	
	@FindBy(id = "birth_year")
	@CacheLookup
	WebElement selectyear;
	
	@FindBy(css = "a.button_primary.tlignore > div.button_text")
	@CacheLookup
	WebElement continueButton;
	
	@FindBy(id = "WC_EmailSignupForm_div_21")
	@CacheLookup
	WebElement Email;
	
	@FindBy(css = "div#addChilBirthInfoButtonEmailSignUp > a > span:nth-of-type(2)")
	@CacheLookup
	WebElement AddChild;
	
	@FindBy(id = "childName_1")
	@CacheLookup
	WebElement ChildName;
	
	@FindBy(id = "birth_month_1")
	@CacheLookup
	WebElement selectmonth1;
	
	@FindBy(id = "birth_date_1")
	@CacheLookup
	WebElement selectdate1;
	
	@FindBy(id = "birth_year_1")
	@CacheLookup
	WebElement selectyear1;
	
	@FindBy(css = "form#childForm_1 > div:nth-of-type(3) > div:nth-of-type(2) > label > span")
	@CacheLookup
	WebElement SelectBoy;
	
	@FindBy(css = "form#childForm_1 > div:nth-of-type(4) > div:nth-of-type(2) > label > span")
	@CacheLookup
	WebElement SelectMother;
	
	@FindBy(id = "WC_EmailSignup_links_4a")
	@CacheLookup
	WebElement SignUpButton;
	
		
		public void SignUpClick() throws Exception {
			
			helper.waitForClickable(SignUpNow);
			SignUpNow.click();
			
		}
		
	public void Agemodal() {
		
		Select Month = new Select(selectmonth);
		Month.selectByIndex(2);

		Select SelectDate = new Select(selectdate);
		SelectDate.selectByIndex(2);

		Select SelectYear = new Select(selectyear);
		SelectYear.selectByVisibleText("1990");
		
		helper.waitForClickable(continueButton);
		continueButton.click();
		
	}
	
	public void AddChilddetails() {
		
		helper.scrollBy(0, 100);
		
		helper.waitForClickable(SignUpButton);
		
		Email.sendKeys(TestBase.CONFIG.getProperty("FirstName"));
		
		helper.waitForClickable(AddChild);
		AddChild.click();
		
		ChildName.sendKeys(TestBase.CONFIG.getProperty("FirstName"));
		
		Select Month = new Select(selectmonth);
		Month.selectByIndex(2);

		Select SelectDate = new Select(selectdate);
		SelectDate.selectByIndex(2);

		Select SelectYear = new Select(selectyear);
		SelectYear.selectByVisibleText("2017");
		
		helper.waitForClickable(SelectBoy);
		SelectBoy.click();
		
		helper.waitForClickable(SelectMother);
		SelectMother.click();
		
		helper.waitForClickable(SignUpButton);
		SignUpButton.click();
	}
	
	}

