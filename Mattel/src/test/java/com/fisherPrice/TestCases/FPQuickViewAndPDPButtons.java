package com.fisherPrice.TestCases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mattel.Utility.PageHelper;

public class FPQuickViewAndPDPButtons extends TestBaseFP {

	@BeforeTest
	@Parameters({"browser"})
	public void startBrowser(@Optional("chrome") String browser) throws Exception {	
		setBrowser(browser);
	}

	@Test(priority=1,groups={"regression"})
	public void FPQuickViewButtonTest() throws Exception {
		startTest("FPQuickViewButtonTest")
		.assignCategory("regression")
		.assignAuthor("Kit Hoover")
		.setDescription("Verify Quickview Buttons (<a href=\"https://mattel.cprime.com/jira/browse/OWL-5842\" target=_blank>OWL-5842</a>)");
		
		String url = configStringBuilder("FPURL","en-us/fp/mickey-roadster-racers");
		log.info("Navigate to Index Page:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);
		
		PageHelper helper = new PageHelper(getDriver());

		log.info("Click on quick view");
		helper.getElement(By.cssSelector("a.t_quick_view")).click();
		
		log.info("Check button label for 'FIND AT RETAIL'");
		String wtb = helper.getElement(By.cssSelector("#ps_WTB")).getText();
		assertEquals(wtb.toUpperCase(),"FIND AT RETAIL");
		
		log.info("Check button label for 'GET IT ON SALE'");
		String sa = helper.getElement(By.cssSelector("#ps_SA")).getText();
		assertEquals(sa.toUpperCase(),"GET IT ON SALE");
		
		log.pass("Pass");
		endTest();
	}
	
	@Test(priority=2,groups={"regression"})
	public void FPButtonTestPDP() throws Exception {
		startTest("FPButtonTestPDP")
		.assignCategory("regression")
		.assignAuthor("Kit Hoover")
		.setDescription("Verify PDP Buttons (<a href=\"https://mattel.cprime.com/jira/browse/OWL-5842\" target=_blank>OWL-5842</a>)");

		String url = configStringBuilder("FPURL","FPProductCarPath");
		log.info("Navigate to PDP:");
		log.info("<a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
		getDriver().get(url);
		
		PageHelper helper = new PageHelper(getDriver());

		log.info("Check button label for 'FIND AT RETAIL'");
		String wtb = helper.getElement(By.cssSelector("#ps_WTB")).getText().toUpperCase();
		assertEquals(wtb,"FIND AT RETAIL");
		
		log.info("Check button label for 'GET IT ON SALE'");
		String sa = helper.getElement(By.cssSelector("#ps_SA")).getText().toUpperCase();
		assertEquals(sa,"GET IT ON SALE");
		
		log.pass("Pass");
		endTest();
	}
	
	
}
