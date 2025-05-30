package com.selenium.practice.test.testcasespage;

import org.testng.annotations.Test;

import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class TestCasesPage_TestCases extends TestComponentsClass{

	@Test
	public void pageLandingVerificationTestCase() throws InterruptedException {
		
		testCasesPage.verifyingIfUserLandedOnTestCasesPage();
	}
	
}
