package com.shopmattel.TestCases;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBaseSM extends TestBase {
private WebDriver SMDriver;
	
	@Override
	public WebDriver getDriver() throws MalformedURLException {
		if (SMDriver == null) {
			SMDriver = super.getDriver();
			suppressSplash();
		}
		return SMDriver;
	}
	
	@Override
	public ExtentTest startTest(String testName) {
		return super.startTest(testName).assignCategory("shopmettal");
	}

	public void suppressSplash() {
		// Go to a fast loading page on the site so we can set a cookie
		SMDriver.get(configStringBuilder("SMURL","WcsFastFragment"));
		// Set cookie to suppress splash dialog
		//SMDriver.manage().addCookie(new Cookie("EmailCampVisited","done","fisher-price.mattel.com","/",null));
	}
	
}
