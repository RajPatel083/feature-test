package com.mattel.Base;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ParallelExtentManager {
	static ExtentReports extent;
	static String fileBaseName = "ParallelReport";
	static String reportPath;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			String dateName = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss").format(new Date());
			reportPath = System.getProperty("user.dir")+"/Report/" + fileBaseName + dateName + "/";
			extent = new ExtentReports(reportPath + fileBaseName + ".html", true);
		}
		return extent;
	}
	
	public static void setFileBaseName(String name) {
		fileBaseName = name;
	}

	public static String getAbsolutePath() {
		return reportPath;
	}
}
