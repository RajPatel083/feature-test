package com.fisherPrice.TestCases;

import java.net.MalformedURLException;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import com.mattel.Base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBaseFP extends TestBase {
	
	private WebDriver fpDriver;
	
	@Override
	public WebDriver getDriver() throws MalformedURLException {
		if (fpDriver == null) {
			fpDriver = super.getDriver();
			suppressSplash();
		}
		return fpDriver;
	}
	
	@Override
	public ExtentTest startTest(String testName) {
		return super.startTest(testName).assignCategory("fisherprice");
	}

	public void suppressSplash() {
		// Go to a fast loading page on the site so we can set a cookie
		fpDriver.get(configStringBuilder("FPURL","WcsFastFragment"));
		// Set cookie to suppress splash dialog
		fpDriver.manage().addCookie(new Cookie("EmailCampVisited","done","fisher-price.mattel.com","/",null));
	}

}

