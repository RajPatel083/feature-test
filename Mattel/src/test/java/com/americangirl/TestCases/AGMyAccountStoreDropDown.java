package com.americangirl.TestCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mattel.Utility.PageHelper;

public class AGMyAccountStoreDropDown extends TestBaseAG {

	private Select dropdown;

	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(@Optional("chrome") String pBrowser) throws Exception {
		setBrowser(pBrowser);
	}
	
	//TODO(hooverki): Move into a page object
	@FindBy(id="WC_AccountDisplay_FormInput_logonId_In_Logon_1")
	WebElement userName;
	
	@FindBy(id="WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")
	WebElement passWord;
	
	@FindBy(id="WC_AccountDisplay_links_2")
	WebElement signInButton;
	
	@FindBy(id="selectCountry")
	WebElement storeDropDown;
	
	@Test(groups={"regression"})
	public void testAGMyAccountStoreDropDownDefault() throws Exception {
		startTest("AGMyAccountStoreDropDownDefault")
		.assignCategory("regression")
		.assignAuthor("Kit Hoover")
	    .setDescription("Verify Store Drop Down in My Account Page (<a href=https://mattel.cprime.com/jira/browse/OWL-4738 target=_blank>OWL-4738</a>)");

		String url = configStringBuilder("AGURL","AGLogonPath");
		log.info("Navigate to Logon Page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);

		PageFactory.initElements(getDriver(), this);
		PageHelper helper = new PageHelper(getDriver());
		helper.waitForPageLoaded();

		log.info("Enter username, password, and logon");
		helper.scrollHilite(userName).sendKeys("agprodtest@gmail.com");
		helper.scrollHilite(passWord).sendKeys("P@ssw0rd");
		signInButton.click();

		//TODO(hooverki): Another page object
		log.info("Check my account page is correct person");
		WebElement myAccountTitle = helper.getElement(By.cssSelector("div.myaccount_desc_title"));
		assertEquals(helper.scrollHilite(myAccountTitle).getText(), "Hello, ag");

		log.info("Store dropdown should default to 'STORE LOCATION'");
		dropdown = new Select(helper.scrollHilite(storeDropDown));
		assertEquals(dropdown.getFirstSelectedOption().getText(),"STORE LOCATION");

		log.pass("Pass");
		endTest();
	}

	@Test(dependsOnMethods = {"testAGMyAccountStoreDropDownDefault"},groups={"regression"})
	public void testAGMyAccountStoreDropDownCityList() throws Exception {
		startTest("AGMyAccountStoreDropDownCityList")
		.assignCategory("regression")
		.assignAuthor("Kit Hoover")
		.setDescription("Verify Store Drop Down City List (<a href=https://mattel.cprime.com/jira/browse/OWL-5657 target=_blank>OWL-5657</a>)");

		List<WebElement> options = dropdown.getOptions();
		testOptionCount(options,"American Girl Atlanta",1);
		testOptionCount(options,"American Girl Boston",1);
		testOptionCount(options,"American Girl Charlotte",1);
		testOptionCount(options,"American Girl Place Chicago",1);
		testOptionCount(options,"American Girl Columbus",1);
		testOptionCount(options,"American Girl Dallas",1);
		testOptionCount(options,"American Girl Denver",1);
		testOptionCount(options,"American Girl Houston",1);
		testOptionCount(options,"American Girl Place Los Angeles",1);
		testOptionCount(options,"American Girl Miami",1);
		testOptionCount(options,"American Girl Minneapolis",1);
		testOptionCount(options,"American Girl Nashville",1);
		testOptionCount(options,"American Girl Place New York",1);
		testOptionCount(options,"American Girl Orlando",1);
		testOptionCount(options,"American Girl San Fransisco",1);
		testOptionCount(options,"American Girl Seattle",1);
		testOptionCount(options,"American Girl Scottsdale",1);
		testOptionCount(options,"American Girl St. Louis",1);
		testOptionCount(options,"American Girl Washington, D.C.",1);
		testOptionCount(options,"American Girl Kansas City",1);

		log.pass("Pass");
		endTest();
	}
	
	@Test
	public void inlineScreenShot() throws Exception {
		startTest("inlineScreenShot").setDescription("Insert screenshot as inline image instead of external file");
		log.info("Step 1");

		log.screenshotInline(getDriver()).info();
		
		log.info("Step 3");
		endTest();
	}

	private void testOptionCount(List<WebElement> allOptions, String string, int expected) {
		int count = 0;
		for (WebElement o : allOptions)
			if (o.getText().equals(string)) count++;
		log.info("Look for exactly "+expected+" of '"+string+"'");
		assertEquals(count,expected,"Option '"+string+"'");
	}

}
