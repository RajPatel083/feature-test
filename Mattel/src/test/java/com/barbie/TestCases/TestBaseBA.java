package com.barbie.TestCases;

import java.net.MalformedURLException;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBaseBA extends TestBase {

	private WebDriver barbieDriver;
	
	@Override
	public WebDriver getDriver() throws MalformedURLException {
		if (barbieDriver == null) {
			barbieDriver = super.getDriver();
			suppressSplash();
		}
		return barbieDriver;
	}
	
	@Override
	public ExtentTest startTest(String testName) {
		return super.startTest(testName).assignCategory("barbie");
	}

	public void suppressSplash() {
		// Go to a fast loading page on the site so we can set a cookie
		barbieDriver.get(configStringBuilder("BAURL","WcsFastFragment"));
		// Set cookie to suppress splash dialog
		barbieDriver.manage().addCookie(new Cookie("bumperPopClosed","true","barbie.mattel.com","/",null));
		barbieDriver.manage().addCookie(new Cookie("EmailCampVisited","done","barbie.mattel.com","/",null));
	}
	 
}
