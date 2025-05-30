package com.selenium.practice.test.checkout;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.practice.test.meta.BaseCode;
import com.selenium.practice.test.payment.CardDetailsPage;

public class CheckOutPage extends BaseCode{

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//ul[@id='address_delivery']/li")
	List<WebElement> deliveryAddress;
	
	@FindBy(css = "ul.item li.address_firstname")
	WebElement deliveryAddressName;
	
	@FindBy(css = "ul.item li.address_address1:nth-child(4)")
	WebElement deliveryAddressUserAddress;
	
	@FindBy(css = "ul.item li.address_state_name")
	WebElement deliveryAddressStateCityAndZipCode;
	
	@FindBy(css = "ul.item li.address_country_name")
	WebElement deliveryAddressCountry;
	
	@FindBy(css = "ul.item li.address_phone")
	WebElement deliveryAddressMobileNumber;
	
	@FindBy(xpath = "//ul[@id='address_invoice']/li")
	List<WebElement> billingAddress;
	
	@FindBy(css = "ul.alternate_item li.address_firstname")
	WebElement billingAddressName;
	
	@FindBy(css = "ul.alternate_item li.address_address1:nth-child(4)")
	WebElement billingAddressUserAddress;
	
	@FindBy(css = "ul.alternate_item li.address_state_name")
	WebElement billingAddressStateCityAndZipCode;
	
	@FindBy(css = "ul.alternate_item li.address_country_name")
	WebElement billingAddressCountry;
	
	@FindBy(css = "ul.alternate_item li.address_phone")
	WebElement billingAddressMobileNumber;
	
	@FindBy(xpath = "//td[@class='cart_total']/p[@class='cart_total_price']")
	List<WebElement> productPrices;
	
	@FindBy(xpath = "//td/p[@class='cart_total_price']")
	List<WebElement> totalPrice;
	
	@FindBy(xpath = "//div/a[@href='/payment']")
	WebElement placeOrderButton;
	
	@FindBy(name = "message")
	WebElement messageTextArea;
	
	
	public CardDetailsPage verifyingCheckOutDetails() throws InterruptedException, IOException {
		
		iframeAddRemover();
		
		boolean deliveryAddressVsBillingAddress = deliveryAddress.size() == billingAddress.size();
		
		if(deliveryAddressVsBillingAddress) {
			
			//Making Sure The billing address and delivery Addresss is the same
			for(int i = 1; i < deliveryAddress.size(); i++) {
				
				Assert.assertEquals(deliveryAddress.get(i).getText(), billingAddress.get(i).getText());
			}
			
		}
		
		else {
			
			Assert.assertTrue(false, "delivery address and billing address don't match");
		}
		
		int sum = 0;
		
		//Verifying whether the prices add up
		for(WebElement productPrice : productPrices) {
			
			int price = Integer.parseInt(productPrice.getText().split(" ")[1]);
			
			sum = sum + price;
			
		}
		
		int totalPriceValue = Integer.parseInt(totalPrice.getLast().getText().split(" ")[1]);
		
		Assert.assertEquals(sum, totalPriceValue);
		
		messageTextArea.sendKeys(getCheckoutTextAreaMessage());
		
		//place order button
		placeOrderButton.click();
		
		CardDetailsPage cardDetailsPage = new CardDetailsPage(driver);
		
		return cardDetailsPage;
	}
	
	public void checkingTempUsersDetails() throws InterruptedException, IOException {
		
		iframeAddRemover();

		//checking delivery address
		Assert.assertEquals(deliveryAddressName.getText(), getTempUserDetails().get("Name"));
		
		Assert.assertEquals(deliveryAddressUserAddress.getText(), getTempUserDetails().get("Address"));
		
		Assert.assertEquals(deliveryAddressStateCityAndZipCode.getText(), getTempUserDetails().get("StateCityAndCode"));
		
		Assert.assertEquals(deliveryAddressCountry.getText(), getTempUserDetails().get("Country"));
		
		Assert.assertEquals(deliveryAddressMobileNumber.getText(), getTempUserDetails().get("MobileNumber"));
		
		//checking billing address
		Assert.assertEquals(billingAddressName.getText(), getTempUserDetails().get("Name"));
		
		Assert.assertEquals(billingAddressUserAddress.getText(), getTempUserDetails().get("Address"));
		
		Assert.assertEquals(billingAddressStateCityAndZipCode.getText(), getTempUserDetails().get("StateCityAndCode"));
		
		Assert.assertEquals(billingAddressCountry.getText(), getTempUserDetails().get("Country"));
		
		Assert.assertEquals(billingAddressMobileNumber.getText(), getTempUserDetails().get("MobileNumber"));
		
		verifyingCheckOutDetails();
	}
	
	
}
