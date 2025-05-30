package com.selenium.practice.test.login;

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

public class LoginPage extends BaseCode{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div.login-form h2")
	WebElement loginTitle;
	
	@FindBy(css = "input[data-qa='login-email']")
	WebElement usernameInput;
	
	@FindBy(css = "input[data-qa='login-password']")
	WebElement passwordInput;
	
	@FindBy(css = "button[data-qa='login-button']")
	WebElement loginButton;
	
	@FindBy(css = "div.login-form form p")
	WebElement incorrectCredentialsMessage;
	
	public void loginActionMethod(String user) throws ClassNotFoundException, SQLException, IOException {
		
		String username = getUsernameAndPassword(user).get("Username");
		
		String password = getUsernameAndPassword(user).get("Password");
		
		enteringLoginAndRegisterPage();
		
		Assert.assertTrue(loginTitle.getText().equals("Login to your account"));
		
		usernameInput.sendKeys(username);
		
		passwordInput.sendKeys(password);
		
		loginButton.click();
		
		Assert.assertEquals(getLoggedInAs(), user);
		
		//logoutAction();
	}
	
	public void loginWithWrongCredentials(String user) throws ClassNotFoundException, SQLException, IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String username = getUsernameAndPassword(user).get("Username");
		
		String password = getUsernameAndPassword(user).get("Password") + "incorrect";
		
		enteringLoginAndRegisterPage();
		
		Assert.assertTrue(loginTitle.getText().equals("Login to your account"));
		
		usernameInput.sendKeys(username);
		
		passwordInput.sendKeys(password);
		
		loginButton.click();
		
		wait.until(ExpectedConditions.visibilityOf(incorrectCredentialsMessage));
		
		Assert.assertEquals(incorrectCredentialsMessage.getText(), getIncorrectCredentialsMessage());
	}
	
	public void logoutVerification(String user) throws ClassNotFoundException, SQLException, IOException {
		
		loginActionMethod(user);
		
		logoutAction();
		
		Assert.assertEquals(driver.getCurrentUrl(), getUrl()+"login");
	}
}
