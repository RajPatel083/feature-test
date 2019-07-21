package com.americangirl.TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import com.mattel.Base.TestBase;

public class AGGooglePLAFeed extends TestBase {

	private String productCode = "BKD31";
	private String productTitle = "Samantha&#8217;s Accessories";
	private String productAge = "8+";
	private String cssSelectorInventoryStatus = "span[id^=InventoryStatus_Online]";
	
	private Elements outOfStock;
	private Elements backordered;
	
	@Test	
	public void verifyAGGooglePLAFeed() throws Exception {
		startTest("AGGooglePLAFeed")
		.assignCategory("ag","xml","regression")
		.setDescription("Verify Google PLA Feed");
		
		String url = configStringBuilder("AGURL","GoogleFeedURLFragment");
		String message;
		
		log.info("Parse XML from: <a href=\""+url+"\" target=\"_blank\">"+url+"</a>");
	    Document doc = Jsoup.connect(url).parser(Parser.xmlParser()).maxBodySize(0).get();
	    
	    message = "Search for "+productCode;
	    Element item = doc.select("ns2|id:contains("+productCode+")").first();
	    assertNotNull(item, message);
	    log.pass(message);
	    
	    Element entry = item.parent();
	    
	    message = "Check title for: "+productTitle;
	    assertEquals(entry.select("title").text(),productTitle,message);
	    log.pass(message);
	    
	    message = "Check legal age for: "+productAge;
	    assertEquals(entry.select("ns3|legal_age").text(),productAge,message);
	    log.pass(message);
	    
	    log.info((outOfStock = doc.select("ns2|availability:contains(out of stock)")).size() + " items out of stock");
	    log.info((backordered = doc.select("ns2|availability:contains(preorder)")).size() + " items on backorder");
	    log.info(doc.select("entry").size() + " total entries");

		endTest();
	}
	
	@Test(dependsOnMethods="verifyAGGooglePLAFeed")
	public void checkNoLongerAvailable() {
		startTest("AGCheckNoLongerAvailable")
		.assignCategory("ag","Jsoup","regression")
		.setDescription("Check all PDP of out of stock items");
		
		assertEquals(checkListForInventoryStatus(outOfStock, "no longer available"), 0);
		
		endTest();
	}
	
	@Test(dependsOnMethods="verifyAGGooglePLAFeed")
	public void checkBackorders() {
		startTest("AGCheckBackorders")
		.assignCategory("ag","Jsoup","regression")
		.setDescription("Check all PDP of backordered items");
		
		assertEquals(checkListForInventoryStatus(backordered, "backordered"), 0);
		
		endTest();
	}
	
	public int checkListForInventoryStatus (Elements list, String expected) {
		int countFail = 0;
		String message = "";
		
		for (Element item : list) {
				try {
					Element entry = item.parent();
					String code = entry.select("ns2|id").text();
					String url = entry.select("ns2|link").text();
					
					message = "<a href=\""+url+"\" target=\"_blank\">"+code+"</a> -- ";
					
					Document nla = Jsoup.connect(url).validateTLSCertificates(false).get();
					
					Element status = nla.select(cssSelectorInventoryStatus).first();
					String statusText = status.text();
				
					assertEquals(statusText.toLowerCase(), expected.toLowerCase());
					log.pass(message+statusText);
				
				} catch (Throwable e) {
					
					countFail++;
					log.failure(message + e.getMessage());
				
				}
				flushReport();
		}
		return countFail;
	}
}
