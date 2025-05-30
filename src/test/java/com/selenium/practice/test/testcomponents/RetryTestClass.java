package com.selenium.practice.test.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestClass implements IRetryAnalyzer{

	private int count = 0;
	
	private final int maxIterations = 3;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count < maxIterations) {
			
			count++;
			
			return true;
		}
		
		return false;
	}

}
