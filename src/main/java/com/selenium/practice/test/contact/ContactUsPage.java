package com.selenium.practice.test.contact;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.practice.test.meta.BaseCode;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class ContactUsPage extends BaseCode{

	WebDriver driver;

	public ContactUsPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "name")
	WebElement contactFormNameInput;
	
	@FindBy(name = "email")
	WebElement contactFormEmailInput;
	
	@FindBy(name = "subject")
	WebElement contactFormSubjectInput;
	
	@FindBy(name = "message")
	WebElement contactFormMessageInput;
	
	@FindBy(name = "upload_file")
	WebElement uploadFileButton;
	
	@FindBy(name = "submit")
	WebElement submitButton;
	
	@FindBy(css = "div.contact-form div.alert-success")
	WebElement successMessage;
	
	public void contactUsForm(String user) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		
		String name = user;
		
		String email = getUsernameAndPassword(user).get("Username");
		
		Lorem lorem = LoremIpsum.getInstance();
		
		String message = lorem.getParagraphs(1, 1);
		
		String subject = getProduct2();
		
		String path = System.getProperty("user.dir") + "\\upload\\invoice.txt";
		
		openingContactUsPage();
		
		iframeAddRemover();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		
		contactFormNameInput.sendKeys(name);
		
		contactFormEmailInput.sendKeys(email);
		
		contactFormSubjectInput.sendKeys(subject);
		
		contactFormMessageInput.sendKeys(message);
		
		uploadFileButton.sendKeys(path);
		
		Thread.sleep(2500);
		
		submitButton.click();
		
		Thread.sleep(2000);
		
		driver.switchTo().alert().accept();
		
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		
		Assert.assertEquals(successMessage.getText(), getContactFormSubmitSuccessMessage());	
	}
}
