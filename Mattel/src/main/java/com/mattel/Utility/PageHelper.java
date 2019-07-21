package com.mattel.Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

public class PageHelper {
	
	private WebDriver pageDriver;
	
	public PageHelper (WebDriver driver) {
		this.pageDriver = driver;
	}

	//Wait For Page To Load 
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(pageDriver, 40);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
	
	//To get an element 
	public WebElement getElement(final By by) {

		// Waiting 100 seconds for an element to be present on the page
		// checking for its presence once every 2 seconds.

		Wait<WebDriver> wait = new FluentWait<WebDriver>(pageDriver)
				.withTimeout(Duration.ofSeconds(80))
				.pollingEvery(Duration.ofMillis(200)) 
				.ignoring(NoSuchElementException.class);

		//Wait For Page To Load 
		waitForPageLoaded();
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
		return element;
	}
	 
	 // Wait for element to be clickable 
	public boolean waitForClickable(WebElement element)
	{
		boolean bool=false;
		try{
			WebDriverWait wait = new WebDriverWait(pageDriver,10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			bool=true;
		}
		catch(Exception e)
		{
			bool=false;
			e.printStackTrace();
		}
		return bool;
	} 
	
	public WebElement getClickable (By by) {
		WebElement element = getElement(by);
		waitForClickable(element);
		return element;
	}

	///// Javascript /////
	
	public Object doJavascript (String script) {
		return doJavascript(script, "");
	}

	public Object doJavascript (String script, Object... args) {
		return ((JavascriptExecutor)pageDriver).executeScript(script, args);
	}

	public void scrollBy (int x, int y) {
		String script = "window.scrollBy("+x+","+y+")";
		doJavascript(script);
	}
	
	public WebElement scrollTo(WebElement element) {
		String js = "arguments[0].scrollIntoView(true);"
				+"window.scrollBy(0, -window.innerHeight/2);";
		doJavascript(js,element);
		return element;
	}
	
	public WebElement scrollHilite(WebElement element) {
		String js = "arguments[0].scrollIntoView(true);"
				+"window.scrollBy(0, -window.innerHeight/2);"
				+"arguments[0].setAttribute('style', 'background: pink; border: 2px solid crimson;');";
		doJavascript(js,element);
		return element;
	}
	
}
