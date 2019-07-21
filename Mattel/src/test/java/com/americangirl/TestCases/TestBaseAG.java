package com.americangirl.TestCases;

import java.net.MalformedURLException;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBaseAG extends TestBase {
	
	private WebDriver driver;

	public void suppressSplash() {
		// Go to a fast loading page on the site so we can set a cookie
		driver.get(configStringBuilder("AGURL","WcsFastFragment"));
	    // Set cookie to suppress splash dialog
	    driver.manage().addCookie(new Cookie("emailCookie1","true","www.americangirl.com","/",null));
	}
	
	@Override
	public WebDriver getDriver() throws MalformedURLException {
		if (driver == null) {
			driver = super.getDriver();
			suppressSplash();
		}
		return driver;
	}
	
	@Override
	public ExtentTest startTest(String testName) {
		return super.startTest(testName).assignCategory("ag");
	}
}
