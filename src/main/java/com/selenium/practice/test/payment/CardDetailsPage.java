package com.selenium.practice.test.payment;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.practice.test.confirmation.OrderConfirmationPage;
import com.selenium.practice.test.meta.BaseCode;

public class CardDetailsPage extends BaseCode{

	WebDriver driver;

	public CardDetailsPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "name_on_card")
	WebElement nameOnCardInput;
	
	@FindBy(name = "card_number")
	WebElement cardNumberInput;
	
	@FindBy(name = "cvc")
	WebElement ccvInput;
	
	@FindBy(name = "expiry_month")
	WebElement cardExpiryMonthInput;
	
	@FindBy(name = "expiry_year")
	WebElement cardExpiryYearInput;
	
	@FindBy(id = "submit")
	WebElement commencePaymentButton;
	
	public OrderConfirmationPage enteringCardDetails(String user) throws InterruptedException, IOException, SQLException {
		
		iframeAddRemover();
		
		String cardName = getCardDetails(user).get("Name_On_Card");
		
		String cardNumber = getCardDetails(user).get("Card_Number");
		
		String cardCVV = getCardDetails(user).get("CVV");
		
		String cardExpireDate = getCardDetails(user).get("expireDate");
		
		String cardExpireMonth = cardExpireDate.split("/")[0];
		
		String cardExpireYear = "20" + cardExpireDate.split("/")[1];
		
		nameOnCardInput.sendKeys(cardName);
		
		cardNumberInput.sendKeys(cardNumber);
		
		ccvInput.sendKeys(cardCVV);
		
		cardExpiryMonthInput.sendKeys(cardExpireMonth);
		
		cardExpiryYearInput.sendKeys(cardExpireYear);
		
		commencePaymentButton.click();
		
		OrderConfirmationPage orderConfimationPage = new OrderConfirmationPage(driver);
		
		return orderConfimationPage;
		
	}
	
	public OrderConfirmationPage enteringCardDetailsOfTempUser() throws IOException {
		
		String cardName = getName();
		
		String cardNumber = getTempCardNumber();
		
		String cardCVV = getTempCardCVV();
		
		String cardExpiryDate = getTempCardExpiryDate();
		
		String cardExpireMonth = cardExpiryDate.split("/")[0];
		
		String cardExpireYear = "20" + cardExpiryDate.split("/")[1];
		
		nameOnCardInput.sendKeys(cardName);
		
		cardNumberInput.sendKeys(cardNumber);
		
		ccvInput.sendKeys(cardCVV);
		
		cardExpiryMonthInput.sendKeys(cardExpireMonth);
		
		cardExpiryYearInput.sendKeys(cardExpireYear);
		
		commencePaymentButton.click();
		
		OrderConfirmationPage orderConfimationPage = new OrderConfirmationPage(driver);
		
		return orderConfimationPage;
	}
	
	
}
