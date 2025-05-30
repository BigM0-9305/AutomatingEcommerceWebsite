package com.selenium.practice.test.homePage;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class HomePageTestCases extends TestComponentsClass{

	
	@Test(dataProvider = "getUser")
	public void verifyingSubscriptionTestCase(String user) throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		
		baseCode.submittingEmailForSubscription(user);
		
	}
	
//	@Test
//	public void addingRecommendedProductsToCartTestCase() throws InterruptedException, IOException {
//		
//		List<String> recommendedProducts = baseCode.addingRecommendedProductsToCart();
//		
//		CartPage cartPage = baseCode.goToCart();
//		
//		cartPage.verifyingProductNamesInCart(recommendedProducts);
//	}
	
	@Test
	public void scrollingUpUsingUpArrowButtonOnScreenTestCase() throws InterruptedException {
		
		baseCode.checkingUpArrowButtonScroll();
	}
	
	@Test
	public void scrollingUpWithoutUpArrowButtonOnScreenTestCase() throws InterruptedException {
		
		baseCode.checkingScrollWithoutUpArrowButton();
	}
	
	@DataProvider
	public Object[][] getUser() throws IOException {
		
		String user = getUserName(2);
		
		return new Object[][] {{user}};
		
	}
}
