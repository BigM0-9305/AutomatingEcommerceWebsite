package com.selenium.practice.test.confirmation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.practice.test.meta.BaseCode;

public class OrderConfirmationPage extends BaseCode{

	WebDriver  driver;

	public OrderConfirmationPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div.col-sm-9 p")
	WebElement shownOrderConfirmationMessageWebElement;
	
	@FindBy(className = "check_out")
	WebElement downloadInvoiceButton;
	
	@FindBy(css = "a[data-qa='continue-button']")
	WebElement continueButton;
	
	public void orderConfirmation() throws IOException, InterruptedException {
		
		String shownOrderConfirmationMessage = shownOrderConfirmationMessageWebElement.getText();
		
		String actualOrderConfirmationMessage = getOrderConfirmationMessage();
		
		Assert.assertEquals(actualOrderConfirmationMessage, shownOrderConfirmationMessage);
		
		downloadInvoiceButton.click();
		
		Assert.assertTrue(isFilePresent());
		
		continueButton.click();
	}
	
	public void orderConfirmationAndLogoutMethod() throws IOException, InterruptedException {
		
		//Last Page Order Confirmation Page
		
		String shownOrderConfirmationMessage = shownOrderConfirmationMessageWebElement.getText();
		
		String actualOrderConfirmationMessage = getOrderConfirmationMessage();
		
		Assert.assertEquals(actualOrderConfirmationMessage, shownOrderConfirmationMessage);
		
		downloadInvoiceButton.click();
		
		Assert.assertTrue(isFilePresent());
		
		logoutAction();
		
	}
	
	
}
