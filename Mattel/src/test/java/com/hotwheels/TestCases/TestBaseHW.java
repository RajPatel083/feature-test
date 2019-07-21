package com.hotwheels.TestCases;

import java.net.MalformedURLException;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBaseHW extends TestBase {
	
private WebDriver hwDriver;
	
	@Override
	public WebDriver getDriver() throws MalformedURLException {
		if (hwDriver == null) {
			hwDriver = super.getDriver();
			suppressSplash();
		}
		return hwDriver;
	}
	
	@Override
	public ExtentTest startTest(String testName) {
		return super.startTest(testName).assignCategory("hotwheels");
	}

	public void suppressSplash() {
		// Go to a fast loading page on the site so we can set a cookie
		hwDriver.get(configStringBuilder("HWURL","WcsFastFragment"));
		// Set cookie to suppress splash dialog
		hwDriver.manage().addCookie(new Cookie("EmailCampVisited","done","hotwheels.mattel.com","/",null));
	}
}
