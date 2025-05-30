package com.selenium.practice.test.loginpage;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class LoginPageTestCases extends TestComponentsClass{

	@Test
	public void signUpTestCase() throws Exception{
		
		signUpTest.signingUpAndDeleting();
	}
	
	@Test(dataProvider = "getUserData")
	public void signInTestCase(String user) throws ClassNotFoundException, SQLException, IOException {
		
		loginPage.loginActionMethod(user);
		
		baseCode.logoutAction();
	}
	
	@Test(dataProvider = "getUserData") 
	public void wrongCredentialSignInTestCase(String user) throws ClassNotFoundException, SQLException, IOException {
		
		loginPage.loginWithWrongCredentials(user);
	}
	
	@Test(dataProvider = "getUserData")
	public void logoutTestCase(String user) throws ClassNotFoundException, SQLException, IOException {
		
		loginPage.logoutVerification(user);
		
	}
	
	@Test(dataProvider = "getUserData", groups = "run")
	public void registeringWithExistingUserTestCase(String user) throws ClassNotFoundException, SQLException, IOException {
		
		signUpTest.signUpWithExistingAccount(user);
	}
	
	@DataProvider
	public Object[][] getUserData() throws IOException{
		
		return new Object[][] {{getUserName(2)}};
	}
}
