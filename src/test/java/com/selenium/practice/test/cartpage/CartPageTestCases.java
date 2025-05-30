package com.selenium.practice.test.cartpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.practice.test.cart.CartPage;
import com.selenium.practice.test.products.ProductsPage;
import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class CartPageTestCases extends TestComponentsClass{

	CartPage cartPage;
	
	@Test(dataProvider = "getUser")
	public void subscriptionVerification(String user) throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		
		cartPage = baseCode.goToCart();
		
		baseCode.submittingEmailForSubscription(user);
	}
	
	@Test
	public void verfyingProductsInCart() throws InterruptedException, IOException {
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
		
		cartPage = baseCode.goToCart();
		
		cartPage.viewCart();
	}
	
	@Test
	public void verifyingProductQuantityTestCase() throws InterruptedException, NumberFormatException, IOException {
		
		int quantity = baseCode.getProductQuantity();
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		cartPage = productsPage.increasingProductQuantity(quantity);
		
		List<WebElement> productQuantities = cartPage.gettingProductQuantity();
		
		for(WebElement productQuantity : productQuantities) {
			
			int quantityInCart = Integer.parseInt(productQuantity.getText());
			
			Assert.assertEquals(quantityInCart, quantity);
		}
		
		
	}
	
	@Test(dataProvider = "getProduct")
	public void removingProductsFromCartTestCase(String prodcut) throws InterruptedException, IOException {
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
		
		cartPage = baseCode.goToCart();
		
		cartPage.removeFromCartAction(prodcut);
		
	}
	
	@DataProvider
	public Object[][] getUser() throws IOException{
	
		String user = getUserName(2);
		
		return new Object[][] {{user}};
	}
	
	@DataProvider
	public Object[][] getProduct() throws IOException {
		
		String product = getProductName(2);
		
		return new Object[][] {{product}};
	}
}
