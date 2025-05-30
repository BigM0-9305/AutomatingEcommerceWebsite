package com.selenium.practice.test.start_to_end;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.practice.test.cart.CartPage;
import com.selenium.practice.test.checkout.CheckOutPage;
import com.selenium.practice.test.confirmation.OrderConfirmationPage;
import com.selenium.practice.test.payment.CardDetailsPage;
import com.selenium.practice.test.products.ProductsPage;
import com.selenium.practice.test.sign_up.SignUpTest;
import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class StartToEnd extends TestComponentsClass{

	@Test(dataProvider = "getData")
	public void endToEndTestCase(String user) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		
		loginPage.loginActionMethod(user);
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
		
		CartPage cartPage = baseCode.goToCart();
		
		CheckOutPage checkOutPage = cartPage.viewCart();
		
		CardDetailsPage cardDetailsPage = checkOutPage.verifyingCheckOutDetails();
		
		OrderConfirmationPage confirmationPage = cardDetailsPage.enteringCardDetails(user);
		
		confirmationPage.orderConfirmationAndLogoutMethod();
		
	}
	
	@Test
	public void tryingToOrderWithoutRegisteringTestCase() throws Exception {
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
		
		CartPage cartPage = baseCode.goToCart();
		
		SignUpTest signUpTest = cartPage.tryingToCheckoutWithoutRegistering();
		
		signUpTest.signingUp();
		
		baseCode.goToCart();
		
		CheckOutPage checkOutPage = cartPage.viewCart();
		
		CardDetailsPage cardDetailsPage = checkOutPage.verifyingCheckOutDetails();
		
		OrderConfirmationPage confirmationPage =  cardDetailsPage.enteringCardDetailsOfTempUser();
		
		confirmationPage.orderConfirmation();
		
		baseCode.deleteAccountAction();
	}
	
	@Test
	public void tryingToOrderAfterRegistering() throws Exception {
		
		baseCode.enteringLoginAndRegisterPage();
		
		signUpTest.signingUp();
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
		
		CartPage cartPage = baseCode.goToCart();
		
		CheckOutPage checkOutPage = cartPage.viewCart();
		
		CardDetailsPage cardDetailsPage =  checkOutPage.verifyingCheckOutDetails();
		
		OrderConfirmationPage confirmationPage = cardDetailsPage.enteringCardDetailsOfTempUser();
		
		confirmationPage.orderConfirmation();
		
		baseCode.deleteAccountAction();
		
	}
	
	@Test(dataProvider = "getData")
	public void searchedProductsTestCase(String user) throws InterruptedException, IOException, ClassNotFoundException, SQLException {
		
		String product = getProductName(2);
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.verifyingProductSearch(product);
		
		List<String> displayedProducts = productsPage.getDisplayedProducts();
		
		productsPage.addingDisplayedProductsToCart();
		
		CartPage cartPage = baseCode.goToCart();
		
		cartPage.verifyingProductNamesInCart(displayedProducts);
		
		baseCode.enteringLoginAndRegisterPage();
		
		loginPage.loginActionMethod(user);
		
		baseCode.goToCart();
		
		cartPage.verifyingProductNamesInCart(displayedProducts);
		
		CheckOutPage checkOutPage = cartPage.checkingOut();
		
		CardDetailsPage cardDetailsPage =  checkOutPage.verifyingCheckOutDetails();
		
		OrderConfirmationPage confirmationPage =  cardDetailsPage.enteringCardDetails(user);
		
		confirmationPage.orderConfirmationAndLogoutMethod();
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		String user = getUserName(3);
		
		return new Object[][] {{user}};
	}
}
