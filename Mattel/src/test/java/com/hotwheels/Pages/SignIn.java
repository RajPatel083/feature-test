package com.hotwheels.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mattel.Base.TestBase;

public class SignIn {

WebDriver driver;
	
	public SignIn(WebDriver ldriver) {
		this.driver =ldriver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
}

	///////
	
	public void SignInLink() {
		WebElement signinlink = (new WebDriverWait(driver,13))
				.until(ExpectedConditions.elementToBeClickable(By.id("navLogin")));
		
		signinlink.click();
	}
	
	public void EmailId() {
		WebElement emailid = (new WebDriverWait(driver,13))
				.until(ExpectedConditions.elementToBeClickable(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")));
		
		emailid.sendKeys(TestBase.CONFIG.getProperty("Email"));
	}
	
	public void Password() {
		WebElement password = (new WebDriverWait(driver,13))
				.until(ExpectedConditions.elementToBeClickable(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")));
		
		password.sendKeys(TestBase.CONFIG.getProperty("Password"));
	}
	
	public void SignInOption() {
		WebElement signinoption = (new WebDriverWait(driver,13))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#WC_AccountDisplay_links_2 > div.button_text")));
		
		signinoption.click();
	}

	
	}
