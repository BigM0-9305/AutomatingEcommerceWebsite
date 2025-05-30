package com.selenium.practice.test.checkoutpage;

import org.testng.annotations.Test;

import com.selenium.practice.test.cart.CartPage;
import com.selenium.practice.test.checkout.CheckOutPage;
import com.selenium.practice.test.confirmation.OrderConfirmationPage;
import com.selenium.practice.test.payment.CardDetailsPage;
import com.selenium.practice.test.products.ProductsPage;
import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class CheckOutPageTestCases extends TestComponentsClass{

	@Test
	public void checkingUsersDetailsAfterSigningUpTestCase() throws Exception {
		
		baseCode.enteringLoginAndRegisterPage();
		
		signUpTest.signingUp();
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
		
		CartPage cartPage = baseCode.goToCart();
		
		CheckOutPage checkOutPage =  cartPage.viewCart();
		
		checkOutPage.checkingTempUsersDetails();
		
		baseCode.deleteAccountAction();
	}
	
	@Test
	public void downloadingInvoiceAfterPurchase() throws Exception {
		
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
}
