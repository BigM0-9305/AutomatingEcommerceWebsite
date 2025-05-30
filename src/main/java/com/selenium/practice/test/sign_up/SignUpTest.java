package com.selenium.practice.test.sign_up;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.practice.test.meta.BaseCode;

public class SignUpTest extends BaseCode{
	
	WebDriver driver;

	public SignUpTest(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[placeholder='Name']")
	WebElement nameInput;
	
	@FindBy(css = "input[data-qa='signup-email']")
	WebElement emailInput;
	
	@FindBy(css = "button[data-qa='signup-button']")
	WebElement signUpbutton;
	
	@FindBy(css = "span input[value='Mr']")
	WebElement maleGenderRadioButton;
	
	@FindBy(css = "span input[value='Mrs']")
	WebElement femaleGenderRadioButton;
	
	@FindBy(css = "input#password")
	WebElement newPasswordInput;
	
	@FindBy(xpath = "//select[@id='days']")
	WebElement daysDropDown;
	
	@FindBy(xpath = "//select[@id='months']")
	WebElement monthDropDown;
	
	@FindBy(xpath = "//select[@id='years']")
	WebElement yearDropDown;
	
	@FindBy(css = "input#optin")
	WebElement optInCheckBox;
	
	@FindBy(id = "first_name")
	WebElement firstNameInput;
	
	@FindBy(id = "last_name")
	WebElement lastNameInput;
	
	@FindBy(id = "address1")
	WebElement addressInput;
	
	@FindBy(id = "country")
	WebElement countryDropDown;
	
	@FindBy(id = "state")
	WebElement stateInput;
	
	@FindBy(id = "city")
	WebElement cityInput;
	
	@FindBy(id = "zipcode")
	WebElement zipCodeInput;
	
	@FindBy(id = "mobile_number")
	WebElement mobileNumberInput;
	
	@FindBy(css = "div.signup-form form p")
	WebElement emailAlreadyExistsMessage;
	
	@FindBy(xpath = "//button[@data-qa='create-account']")
	WebElement createAccountButton;
	
	@FindBy(css = "h2.title b")
	WebElement accountCreatedTitle;
	
	@FindBy(css = "a[data-qa='continue-button']")
	WebElement continueButton;
	

	
	public void signingUp() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String name = getName();
		
		String firstName = getFirstName();
		
		String lastName = getLastName();
		
		String gender = getGender();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.signup-form h2")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.signup-form h2")).getText().equalsIgnoreCase("New User Signup!"));
		
		nameInput.sendKeys(name);
		
		emailInput.sendKeys(getEmail());
		
		signUpbutton.click();
		
		iframeAddRemover();
		
		if(gender.equalsIgnoreCase("male")) {
		
			maleGenderRadioButton.click();
		}
		
		else {
			
			femaleGenderRadioButton.click();
		}
		
		newPasswordInput.sendKeys(getPassword());
		
		Select selectDays = new Select(daysDropDown);
		
		Select selectMonth = new Select(monthDropDown);
		
		Select selectYear = new Select(yearDropDown);
		
		selectDays.selectByValue("10");
		
		selectMonth.selectByValue("1");
		
		selectYear.selectByVisibleText("1997");
		
		optInCheckBox.click();
		
		firstNameInput.sendKeys(firstName);
		
		lastNameInput.sendKeys(lastName);
		
		addressInput.sendKeys(getAddress());
		
		Select countrySelect = new Select(countryDropDown);
		
		countrySelect.selectByVisibleText(getCountry());
		
		stateInput.sendKeys(getState());
		
		cityInput.sendKeys(getCity());
		
		zipCodeInput.sendKeys(getZipCode());
		
		mobileNumberInput.sendKeys(getMobileNumber());
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("window.scrollBy(900,1200);");
		
		wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
		
		createAccountButton.click();
		
		Assert.assertTrue(accountCreatedTitle.getText().equalsIgnoreCase(getAccountCreatedMessage()));
		
		continueButton.click();
		
		Assert.assertTrue(getLoggedInAs().equalsIgnoreCase(name));
	}
	
	public void signingUpAndDeleting() throws Exception {
		
		enteringLoginAndRegisterPage();
		
		signingUp();
		
		deleteAccountAction();
	}
	
	public void signUpWithExistingAccount(String user) throws ClassNotFoundException, SQLException, IOException {
		
		enteringLoginAndRegisterPage();
		
		nameInput.sendKeys(user);
		
		emailInput.sendKeys(getUsernameAndPassword(user).get("Username"));
		
		signUpbutton.click();
		
		Assert.assertEquals(emailAlreadyExistsMessage.getText(), getAccountAlreadyExistsMessage());  
		
	}
	
}
