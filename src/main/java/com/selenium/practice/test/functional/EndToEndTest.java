package com.selenium.practice.test.functional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.practice.test.cart.CartPage;
import com.selenium.practice.test.checkout.CheckOutPage;
import com.selenium.practice.test.confirmation.OrderConfirmationPage;
import com.selenium.practice.test.login.LoginPage;
import com.selenium.practice.test.meta.BaseCode;
import com.selenium.practice.test.payment.CardDetailsPage;
import com.selenium.practice.test.products.ProductsPage;

public class EndToEndTest{

	public static void main(String[] args) throws Exception {
		
		WebDriver driver = new ChromeDriver();
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		BaseCode baseCode = new BaseCode(driver);
		
		String user = baseCode.getUser3();
		
		baseCode.windowMaximizer();
		
		LoginPage loginAction = baseCode.pageLander();
		
		loginAction.loginActionMethod(user);
		
		ProductsPage productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
		
		CartPage cartPage = baseCode.goToCart();
		
		CheckOutPage checkOutPage = cartPage.viewCart();
		
		CardDetailsPage cardDetailsPage  = checkOutPage.verifyingCheckOutDetails();
		
		OrderConfirmationPage orderConfimationPage = cardDetailsPage.enteringCardDetails(user);
		
		orderConfimationPage.orderConfirmationAndLogoutMethod();
		
		Thread.sleep(5000);
		
		driver.quit();
	}

}
