package com.mattel.Base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ParallelExtentTestManager {
	private static Map<Long,ExtentTest> extentTestMap = new HashMap<Long,ExtentTest>();
	private static Boolean mirrorOut = false;
	
	private static ExtentReports getReporter() {
		return ParallelExtentManager.getReporter();
	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(Thread.currentThread().getId());
	}
	
	public static synchronized void endTest() {
		getReporter().endTest(extentTestMap.get(Thread.currentThread().getId()));
	}
	
	public static synchronized ExtentTest startTest(String testName) {
		return startTest(testName,"");
	}
	
	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = getReporter().startTest(testName, desc);
		extentTestMap.put(Thread.currentThread().getId(), test);
		return test;
	}
	
	public synchronized void mirrorToSystemOut(Boolean mirror) {
		mirrorOut = mirror;
	}
	
	private void log (LogStatus status, String details) {
		getTest().log(status, details);
		if (mirrorOut) System.out.println(
			status.toString() 
			+ " (" + Thread.currentThread().getId() + "): " 
			+ StringUtils.substring(details, 0, 140)
		);
	}
	
	public void pass(String details) { log(LogStatus.PASS, details); }
	public void info(String details) { log(LogStatus.INFO, details); }
	public void failure(String details) { log(LogStatus.FAIL, details); }
	
	public class ScreenShot {
		protected String screenCapture;
		public ScreenShot(WebDriver driver) throws Exception {
			ExtentTest test = getTest();
			String testName = test.getTest().getName();
			String screenshotPath = TakeScreenshot.getScreenshot(driver, testName);
			this.screenCapture = test.addScreenCapture(screenshotPath);
		}
		protected ScreenShot() {
			this.screenCapture = null;
		}
		public void pass() throws Exception { ParallelExtentTestManager.this.pass(screenCapture); }
		public void info() throws Exception { ParallelExtentTestManager.this.info(screenCapture); }
		public void failure() throws Exception { ParallelExtentTestManager.this.failure(screenCapture); }
	}
	
	public class ScreenShotInline extends ScreenShot {
		public ScreenShotInline(WebDriver driver) {
			super();
			String screenData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			this.screenCapture = 
					"<img class='report-img' data-featherlight='image' src='data:image/png;base64,"
					+ screenData
					+ "' data-featherlight-after-content='this.$content.attr(\"src\",this.$source.attr(\"src\"))'/>";
		}
	}
	
	public synchronized ScreenShot screenshot(WebDriver driver) throws Exception {
		return new ScreenShot(driver);
	}
	public synchronized ScreenShotInline screenshotInline(WebDriver driver) {
		return new ScreenShotInline(driver);
	}
}
