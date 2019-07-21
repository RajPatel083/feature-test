package com.mattel.Base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {


//Creating a method getScreenshot and passing two parameters 
//driver and screenshotName
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		//below line is just to append the date format with the screenshot name to avoid duplicate names 
		String dateName = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String relativeDest = "screens/" + screenshotName + dateName + ".png";
		File finalDestination = new File(ParallelExtentManager.getAbsolutePath() + relativeDest);
		FileUtils.copyFile(source, finalDestination);

		//Returns the captured file path

		return relativeDest;
	}
	
}
