package com.selenium.practice.test.testcomponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.practice.test.meta.ExtentReporterClass;

public class NewListener implements ITestListener{
	
	ExtentTest extentTest;
	
	ExtentReports extentReports = ExtentReporterClass.getReporter();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, "Test Passed");
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.SKIP, result.getThrowable());
	} 
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.log(Status.FAIL, result.getThrowable());
	}
	
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
	}
}
