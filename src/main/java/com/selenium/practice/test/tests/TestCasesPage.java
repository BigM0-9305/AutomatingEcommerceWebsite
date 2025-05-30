package com.selenium.practice.test.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.practice.test.meta.BaseCode;

public class TestCasesPage extends BaseCode{

	WebDriver driver;
	
	public TestCasesPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "h2.title b")
	WebElement pageTitle;

	public void verifyingIfUserLandedOnTestCasesPage() throws InterruptedException {
		
		gettingIntoTestCasesPage();
		
		iframeAddRemover();
		
		String title = "TEST CASES";
		
		Assert.assertEquals(pageTitle.getText(), title);
		
	}
}
