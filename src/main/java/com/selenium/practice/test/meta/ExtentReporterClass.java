package com.selenium.practice.test.meta;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterClass {
	
	public static ExtentReports getReporter() {
		
		String path = System.getProperty("user.dir") + "//TestReport//report.html";
		
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
		
		extentSparkReporter.config().setReportName("E-Commerce Website Testing Results");
		
		extentSparkReporter.config().setDocumentTitle("TEST RESULTS");
		
		ExtentReports extentReports = new ExtentReports();
		
		extentReports.attachReporter(extentSparkReporter);
		
		extentReports.setSystemInfo("Tester", "Roshan Shaik");
		
		return extentReports;
		
	}

}
