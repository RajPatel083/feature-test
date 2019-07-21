package com.mattel.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static Properties CONFIG;
	public static Properties propertyfile;
	public static ParallelExtentTestManager log;
	public boolean leaveBrowserOpen = false;
	
	private static FileInputStream fis;

	private WebDriver driver;
	private String browser;

	public TestBase() {
		
		if (CONFIG == null) loadConfig("config.properties");
		
		if (propertyfile == null) {
			propertyfile = new Properties();
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config/SaveData.properties");
				propertyfile.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (log == null) log = new ParallelExtentTestManager();
		log.mirrorToSystemOut(true);
		
		if (logger == null) logger = this;
		if (report == null) report = this;
		
	}
	
	public void loadConfig (String configFileName) {
		CONFIG = new Properties(CONFIG);
		try {
			InputStream ip = ClassLoader.getSystemResourceAsStream("config/" + configFileName);
			CONFIG.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public WebDriver getDriver() throws MalformedURLException {
		if (driver == null) launchBrowser(browser);
		return driver;
	}
	
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser.replaceAll("^\"|\"$", "").toLowerCase();
	}

	///////// Manage Extent Reports /////////

	public ExtentTest startTest(String testName) {
		return ParallelExtentTestManager.startTest(testName).assignCategory(browser);
	}
	public void endTest() {
		ParallelExtentTestManager.endTest();
	}
	public void flushReport() {
		ParallelExtentManager.getReporter().flush();
	}
	
	///////// TestNG /////////

	@AfterMethod public void takeScreenShotOnFailure(ITestResult testResult) throws Exception {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			if (driver != null) {
				if (CONFIG.getProperty("screenshots", "files").equals("inline")) {
					log.screenshotInline(driver).failure();
				} else {
					log.screenshot(driver).failure();
				}
			}
			log.failure(testResult.getThrowable().getMessage());
			endTest();
		}
	}

	@AfterClass(alwaysRun=true) public void endExecution() throws Exception {
		flushReport();
		if (!leaveBrowserOpen && driver != null) driver.close();
	}
	
	@BeforeSuite
	public void reportName(ITestContext context) {

		Map<String, String> params = context.getSuite().getXmlSuite().getAllParameters();
		CONFIG.putAll(params);
		
		if (params.containsKey("config")) loadConfig(params.get("config"));
		
		if (params.containsKey("reportFileBaseName"))	{
			ParallelExtentManager.setFileBaseName(params.get("reportFileBaseName"));
		} else {
			String testNgFileName = FilenameUtils.getBaseName(context.getCurrentXmlTest().getSuite().getFileName());
			if (! "testng-customsuite".equals(testNgFileName)) 
				ParallelExtentManager.setFileBaseName(testNgFileName);
		}
	}

	///////// Utility ///////// 
	
	public String configStringBuilder(String... args) {
		StringBuilder sb = new StringBuilder();
		for (String arg : args) {
			sb.append(TestBase.CONFIG.getProperty(arg, arg));
		}
		return sb.toString();
	}

	public static void addData(String key, String val) {
		try {
			File file = new File(System.getProperty("user.dir")+"//src//com//niit//config/SaveData.properties");                                               
			propertyfile.load(fis);
			FileOutputStream obj = new FileOutputStream(file);
			propertyfile.setProperty(key, val);
			propertyfile.store(obj, "Update data into file ");
			propertyfile.load(fis);             
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
	}
	
	//////// Generic Launch Browser //////////
	
	public void launchBrowser(String BrowserType) throws MalformedURLException {
		String hubURL = CONFIG.getProperty("HubURL");
		if (hubURL.equalsIgnoreCase("NOGRID")) launchLocalBrowser(BrowserType);
		else launchBrowserOnGrid(hubURL, BrowserType);
	}
	
	/////// Launch browser using Grid//////////

	private WebDriver launchBrowserOnGrid(String hubURL, String BrowserType) throws MalformedURLException {	
		
		if(driver==null){
			
			if(BrowserType.equalsIgnoreCase("firefox")){
				
				DesiredCapabilities Dcp = new DesiredCapabilities(); 
				Dcp = DesiredCapabilities.firefox();
				Dcp.setBrowserName("firefox");
				Dcp.setPlatform(Platform.ANY);
				System.out.println("URL :"+hubURL);
				driver = new RemoteWebDriver(new URL(hubURL), Dcp);
								
			} else if (BrowserType.equalsIgnoreCase("IE")){
				
				DesiredCapabilities Dcp = new DesiredCapabilities(); 
				Dcp = DesiredCapabilities.internetExplorer();
				Dcp.setBrowserName("internet explorer");
				Dcp.setPlatform(Platform.ANY);
				System.out.println("URL :"+hubURL);	
				driver = new RemoteWebDriver(new URL(hubURL), Dcp);
				
			} else if (BrowserType.equalsIgnoreCase("Chrome")){
				
				DesiredCapabilities Dcp = new DesiredCapabilities(); 
				Dcp = DesiredCapabilities.chrome();
				Dcp.setBrowserName("chrome");
				Dcp.setPlatform(Platform.ANY);
				System.out.println("URL :"+hubURL);
				driver = new RemoteWebDriver(new URL(hubURL), Dcp);
				
			} else if (BrowserType.equalsIgnoreCase("Edge")){
					
				DesiredCapabilities Dcp = new DesiredCapabilities(); 
				Dcp = DesiredCapabilities.edge();
				Dcp.setBrowserName("MicrosoftEdge");
				Dcp.setPlatform(Platform.ANY);
				System.out.println("URL :"+hubURL);
				driver = new RemoteWebDriver(new URL(hubURL), Dcp);
					
			}
					 
		}
		System.out.println("Webdriver value is :"+ driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
			
		return driver;
	} 

	///////Launch browser on local
	
	private WebDriver launchLocalBrowser(String BrowserType) {	
		if(driver == null){
			if(BrowserType.equalsIgnoreCase("FIREFOX")){
				if (SystemUtils.IS_OS_WINDOWS) {
					File file = new File("src/main/resources/Driver/windows/geckodriver.exe");
					System.setProperty("webdriver.gecko.driver",file.getAbsolutePath());
				} else if (SystemUtils.IS_OS_MAC_OSX) {
					File file = new File("src/main/resources/Driver/mac/geckodriver");
					System.setProperty("webdriver.gecko.driver",file.getAbsolutePath());
				}
				driver = new FirefoxDriver();
			} else if (BrowserType.equalsIgnoreCase("IE")){
				File file = new File("src/main/resources/Driver/windows/32bit/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
			} else if (BrowserType.equalsIgnoreCase("Edge")) {
				File file = new File("src/main/resources/Driver/windows/MicrosoftWebDriver.exe");
				System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
				driver = new EdgeDriver();
			} else if (BrowserType.equalsIgnoreCase("CHROME")){
				if (SystemUtils.IS_OS_WINDOWS) {
					System.out.println("Operating System is Windows");
					System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/windows/chromedriver.exe");
				} else if (SystemUtils.IS_OS_MAC_OSX) {
					System.out.println("Operating System is MacOS X");
					System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/mac/chromedriver");
				} else if (SystemUtils.IS_OS_LINUX) {
					System.out.println("Operating System is Linux");
					System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/linux/chromedriver");	
				}
				driver = new ChromeDriver();
				System.out.println("Webdriver value is :"+ driver);
			} else if (BrowserType.equalsIgnoreCase("SAFARI")) {
				driver = new SafariDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		return driver;
	} 

	/**
	 * @deprecated Use log.info(), log.pass(), log.failure() instead
	 * Logging should be done from the test class, not the page object.
	 */
	@Deprecated public void log(LogStatus status, String details) {
		ParallelExtentTestManager.getTest().log(status, details);
	}
	/**
	 * @deprecated Use log.info(), log.pass(), log.failure() instead
	 * Logging should be done from the test class, not the page object.
	 */
	@Deprecated public static TestBase logger;
	/**
	 * @deprecated Use startTest, endTest instead
	 * Reporting should be done from the test class, not the page object.
	 */
	@Deprecated public static TestBase report;

	/**
	 * @deprecated refactor
	 * TODO: Finish Refactor by removing this
	 * The AfterMethod in this class handles screen shots
	 */
	@Deprecated public String addScreenCapture(String screenshotPath) { return ParallelExtentTestManager.getTest().addScreenCapture(screenshotPath); }

	/**
	 * @deprecated refactor
	 * TODO: Finish Refactor by removing this
	 * Should be done in test class, not page object
	 */
	@Deprecated public void endTest(TestBase logger2) {	endTest(); }
	
	/**
	 * @deprecated refactor
	 * TODO: Finish Refactor by removing this
	 */
	@Deprecated public void initialize() { /*do nothing*/ }

	/**
	 * @deprecated refactor
	 * TODO: Finish Refactor by removing this
	 * Should be done in test class, not page object
	 */
	@Deprecated public WebDriver launchBrowserOnGrid(String browser2) throws MalformedURLException { 
		return launchBrowserOnGrid(CONFIG.getProperty("HubURL"), browser2); 
	}

	/**
	 * @deprecated refactor
	 * TODO: Finish Refactor by removing this
	 * Should be done in test class, not page object
	 */
	@Deprecated public TestBase startTest(String simpleName, String description) {
		startTest(simpleName).setDescription(description);
		return this;
	}

}