package com.selenium.practice.test.cart;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.selenium.practice.test.checkout.CheckOutPage;
import com.selenium.practice.test.meta.BaseCode;
import com.selenium.practice.test.sign_up.SignUpTest;

public class CartPage extends BaseCode{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "ol.breadcrumb li.active")
	WebElement shoppingCartTitle;
	
	@FindBy(xpath = "//tbody/tr/td[@class='cart_description']/h4/a")
	List<WebElement> productsInCart;
	
	@FindBy(css = "a.check_out")
	WebElement checkoutButton;
	
	@FindBy(xpath = "//td[@class='cart_delete']/a/i")
	List<WebElement> removeFromCartButtons;
	
	@FindBy(css = "td.cart_quantity button")
	List<WebElement> productQuantities;
	
	@FindBy(css = "p a[href='/login']")
	WebElement RegisterOrLoginButton;
	
	public CheckOutPage checkingOut() {
		
		checkoutButton.click();
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		
		return checkOutPage;
	}
	
	public CheckOutPage viewCart() throws InterruptedException, IOException {
		
		iframeAddRemover();
		
		Assert.assertEquals(shoppingCartTitle.getText(), getShoppingCartTitle());
		
		List<String> productNeededList = getProductList();
		
		for(int i = 0; i < productNeededList.size(); i++) {
			
			Assert.assertEquals(productNeededList.get(i), productsInCart.get(i).getText());
		}
		
		checkoutButton.click();
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		
		return checkOutPage;
	}
	
	public SignUpTest tryingToCheckoutWithoutRegistering() throws InterruptedException, IOException {
		
		viewCart();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(RegisterOrLoginButton));
		
		RegisterOrLoginButton.click();
		
		SignUpTest signUpTest = new SignUpTest(driver);
		
		return signUpTest;
		
	}
	
	public List<WebElement> gettingProductQuantity() {
		
		return productQuantities;
		
	}
	
	public void removeFromCartAction(String product) throws IOException, InterruptedException {
		
		String productToRemove = product;
		
		iframeAddRemover();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//Checking if the remove Product from cart button is working or not (Side Quest)
		for(int i = 0; i< productsInCart.size(); i++) {
			
			if(productsInCart.get(i).getText().equalsIgnoreCase(productToRemove)) {
				
				removeFromCartButtons.get(i).click();
				
				wait.until(ExpectedConditions.invisibilityOf(productsInCart.get(i)));
			}
			
		}
		
		
		for(WebElement productInCart : productsInCart) {
			
			Assert.assertFalse(productInCart.getText().equals(productToRemove));
		}
	}
	
	public void verifyingProductWebElementsInCart(List<WebElement> products) throws InterruptedException {

		iframeAddRemover();
		
		SoftAssert softAssert = new SoftAssert();
		
		if(products.size() == productsInCart.size()) {
			
			for (int i = 0; i < productsInCart.size(); i++) {

				 softAssert.assertEquals(products.get(i).getText(),productsInCart.get(i).getText());
			}
			
			softAssert.assertAll();
		}

		
		else {
			
			Assert.assertTrue(false, "The Products in cart aren't matching products that were added to cart");
		}
	}
	
	public void verifyingProductNamesInCart(List<String> products) throws InterruptedException {
	
		iframeAddRemover();
		
		SoftAssert softAssert = new SoftAssert();
		
		if(productsInCart.size() == products.size()) {
			
			for(int i = 0; i < productsInCart.size(); i++) {
				
				softAssert.assertEquals(productsInCart.get(i).getText(), products.get(i));
			}
			
			softAssert.assertAll();
		}
		
		else {
			
			Assert.assertTrue(false, "The Products in cart aren't matching products that were added to cart");
		}
		
	}
	
}
