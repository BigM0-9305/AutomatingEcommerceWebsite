package com.selenium.practice.test.contactuspage;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class ContactUsPageTestCases extends TestComponentsClass{

	@Test(dataProvider = "getUserData")
	public void contactUsFormTestCase(String user) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		
		contactUsPage.contactUsForm(user);
	}
	
	@DataProvider
	public Object[][] getUserData() throws IOException{
		
		return new Object[][] {{getUserName(2)}};
	}
}
