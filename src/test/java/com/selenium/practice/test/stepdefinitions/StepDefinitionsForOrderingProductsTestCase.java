package com.selenium.practice.test.stepdefinitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.selenium.practice.test.cart.CartPage;
import com.selenium.practice.test.checkout.CheckOutPage;
import com.selenium.practice.test.confirmation.OrderConfirmationPage;
import com.selenium.practice.test.payment.CardDetailsPage;
import com.selenium.practice.test.products.ProductsPage;
import com.selenium.practice.test.sign_up.SignUpTest;
import com.selenium.practice.test.testcomponents.TestComponentsClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsForOrderingProductsTestCase extends TestComponentsClass {
	
	public ProductsPage productsPage;
	
	public CartPage cartPage;
	
	public CheckOutPage checkOutPage;
	
	public CardDetailsPage cardDetailsPage;
	
	public OrderConfirmationPage confirmationPage;
	
	public SignUpTest signUpTestObj;
	
	private static List <String> displayedProducts;
	
	@Given("You are on the Ecommerce Page")
	public void You_are_on_the_Ecommerce_Page() throws IOException{
		
		launchApplication();
	}
	
	@Given("I am on the login or sign-up page")
	public void I_am_on_the_login_or_sign_up_page(){
		
		baseCode.enteringLoginAndRegisterPage();
	}
	
	@When("^I login as this user (.+)$")
	public void I_login_as_this_user(String user) throws ClassNotFoundException, SQLException, IOException {
		
		loginPage.loginActionMethod(user);
	}
	
	@Then("I go to the products page and add a few products to cart")
	public void I_go_to_the_products_page_and_add_a_few_products_to_cart() throws InterruptedException, IOException {
		
		productsPage = baseCode.getIntoProductsPage();
		
		productsPage.addingProductsToCart();
	}
	
	@Then("^go ahead and order them using the card details of (.+) and logout$")
	public void go_ahead_and_order_them_using_the_card_details_of_and_logout(String user) throws InterruptedException, IOException, SQLException {
		
		cartPage = baseCode.goToCart();
		
		checkOutPage = cartPage.viewCart();
		
		cardDetailsPage = checkOutPage.verifyingCheckOutDetails();
		
		confirmationPage = cardDetailsPage.enteringCardDetails(user);
		
		confirmationPage.orderConfirmationAndLogoutMethod();
		
		browserKiller();
	}
	
	@Given("I am on the Products page")
	public void I_am_on_the_Products_page() {
		
		productsPage = baseCode.getIntoProductsPage();
	}
	
	@When("I go to the cart page and try to checkout without registering, I am asked to register, so I go register")
	public void going_to_cart_and_checking_out_without_registering() throws Exception {
		
		cartPage = baseCode.goToCart();
		
		signUpTestObj = cartPage.tryingToCheckoutWithoutRegistering();
		
		signUpTestObj.signingUp();
	}
	
	@Then("after registering I go back to the cart page and try to checkout")
	public void after_registering_I_go_back_to_the_cart_page_and_try_to_checkout() throws InterruptedException, IOException {
		
		cartPage = baseCode.goToCart();
		
		checkOutPage = cartPage.viewCart();
		
		cardDetailsPage = checkOutPage.verifyingCheckOutDetails();
		
		confirmationPage = cardDetailsPage.enteringCardDetailsOfTempUser();
		
		confirmationPage.orderConfirmation();
	}
	
	@Then("delete account")
	public void deleting_account() throws IOException {
		
		baseCode.deleteAccountAction();
		
		browserKiller();
	}
	
	@When("I signup")
	public void I_signup() throws Exception {
		
		signUpTest.signingUp();
	}
	
	@Then("I go to the cart page and checkout")
	public void I_go_to_the_cart_page_and_checkout() throws InterruptedException, IOException {
		
		cartPage = baseCode.goToCart();
		
		checkOutPage = cartPage.viewCart();
		
		cardDetailsPage = checkOutPage.verifyingCheckOutDetails();
		
		confirmationPage = cardDetailsPage.enteringCardDetailsOfTempUser();
		
		confirmationPage.orderConfirmation();
	}
	
	@When("^I search for a product (.+) and add the searched product to cart$")
	public void ordering_searched_product(int productNumber) throws IOException, InterruptedException {
		
		String productName = getProductName(productNumber);
		
		productsPage = baseCode.getIntoProductsPage();
		
		productsPage.verifyingProductSearch(productName);
		
		displayedProducts = productsPage.getDisplayedProducts();
		
		productsPage.addingDisplayedProductsToCart();
	}
	
	@Then("I go to the cart page and verify the products in cart")
	public void go_to_the_cart_page_and_verify_the_products_in_cart() throws InterruptedException {
		
		cartPage = baseCode.goToCart();
		
		cartPage.verifyingProductNamesInCart(displayedProducts);
	}
	
	@Then("^go back to the login and signup page and login with this user (.+)$")
	public void go_back_to_the_login_and_signup_page_and_login_with_this_user(String user) throws ClassNotFoundException, SQLException, IOException {
		
		loginPage.loginActionMethod(user);
	}
	
	@Then("^verify the products in cart again and go ahead and order them using the card details of (.+) and logout$")
	public void verifying_and_checking_out_products(String user) throws InterruptedException, IOException, SQLException {
		
		cartPage = baseCode.goToCart();
		
		cartPage.verifyingProductNamesInCart(displayedProducts);
		
		checkOutPage = cartPage.checkingOut();
		
		cardDetailsPage = checkOutPage.verifyingCheckOutDetails();
		
		confirmationPage = cardDetailsPage.enteringCardDetails(user);
		
		confirmationPage.orderConfirmationAndLogoutMethod();
		
		browserKiller();
	}
}
