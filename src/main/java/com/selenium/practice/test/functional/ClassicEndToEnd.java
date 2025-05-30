package com.selenium.practice.test.functional;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.practice.test.meta.BaseCode;

public class ClassicEndToEnd {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		BaseCode baseCode = new BaseCode(driver);
		
		String user = baseCode.getUser1();
		
		baseCode.pageLander();
		
		baseCode.windowMaximizer();
		
		//login test
		String username = baseCode.getUsernameAndPassword(user).get("Username");
		
		String password = baseCode.getUsernameAndPassword(user).get("Password");
		
		driver.findElement(By.cssSelector("li a[href='/login']")).click();
		
		driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys(username);
		
		driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();
		
		Assert.assertEquals(baseCode.getLoggedInAs(), baseCode.getUser1());
		
		//baseCode.logoutAction();
		
//------------------------------------------------------------------------------------------------------------
		
		//Getting into Products Section and adding products to the cart
		
		baseCode.getIntoProductsPage();
		
		baseCode.iframeAddRemover();
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='title text-center']")).getText().equalsIgnoreCase("All Products"));
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));
		
		List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='productinfo text-center']/p"));
		
		List<String> productList = baseCode.getProductList();
		
		List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("div.product-overlay div.overlay-content a.add-to-cart"));
		
		WebElement continueShoppingButton = driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']"));
		
		Actions actions = new Actions(driver);
		
		for(int i = 0; i < productList.size(); i++) {
			
			String productNeeded = productList.get(i);
			
			for(int j = 0; j < productNames.size(); j++) {
				
				String productName = productNames.get(j).getText();
				
				if(productName.equalsIgnoreCase(productNeeded)) {
					
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", products.get(j));
					
					actions.moveToElement(products.get(j)).perform();
					
					Thread.sleep(2000);
					
					addToCartButtons.get(j).click();
					
					wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
					
					continueShoppingButton.click();
					
					Thread.sleep(2500);
					
				}	
			}
		}

//------------------------------------------------------------------------------------------------------------		
		
		//Now Going to the Cart page
		
		baseCode.goToCart();
		
		baseCode.iframeAddRemover();
		
		String productToRemove = baseCode.getProduct2();
		
		List<WebElement> productsInCart = driver.findElements(By.xpath("//tbody/tr/td[@class='cart_description']/h4/a"));
		
		List<String> productNeededList = baseCode.getProductList();
		
		List<WebElement> removeFromCartButtton = driver.findElements(By.xpath("//td[@class='cart_delete']/a/i"));
		
		for(int i = 0; i < productNeededList.size(); i++) {
			
			Assert.assertEquals(productNeededList.get(i), productsInCart.get(i).getText());
		}
		
		//Checking if the remove Product from cart button is working or not (Side Quest)
		for(int i = 0; i< productsInCart.size(); i++) {
			
			if(productsInCart.get(i).getText().equalsIgnoreCase(productToRemove)) {
				
				removeFromCartButtton.get(i).click();
			}
		}
		
		driver.findElement(By.cssSelector("a.check_out")).click();

//------------------------------------------------------------------------------------------------------------		

		//Checkout Page
		
		baseCode.iframeAddRemover();
		
		List<WebElement> deliveryAddress = driver.findElements(By.xpath("//ul[@id='address_delivery']/li"));
		
		List<WebElement> billingAddress = driver.findElements(By.xpath("//ul[@id='address_invoice']/li"));
		
		List<WebElement> productPrices = driver.findElements(By.xpath("//td[@class='cart_total']/p[@class='cart_total_price']"));
		
		WebElement totalPrice = driver.findElement(By.xpath("//tr[4]/td/p[@class='cart_total_price']"));
		
		//Making Sure The billing address and delivery Addresss is the same
		for(int i = 1; i < deliveryAddress.size(); i++) {
			
			Assert.assertEquals(deliveryAddress.get(i).getText(), billingAddress.get(i).getText());
		}
		
		int sum = 0;
		
		for(WebElement productPrice : productPrices) {
			
			int price = Integer.parseInt(productPrice.getText().split(" ")[1]);
			
			sum = sum + price;
			
		}
		
		
		int totalPriceValue = Integer.parseInt(totalPrice.getText().split(" ")[1]);
		
		Assert.assertEquals(sum, totalPriceValue);
		
		//place order button
		driver.findElement(By.xpath("//div/a[@href='/payment']")).click();

//------------------------------------------------------------------------------------------------------------		
		
		//Payment
		
		baseCode.iframeAddRemover();
		
		String cardName = baseCode.getCardDetails(user).get("Name_On_Card");
		
		String cardNumber = baseCode.getCardDetails(user).get("Card_Number");
		
		String cardCVV = baseCode.getCardDetails(user).get("CVV");
		
		String cardExpireDate = baseCode.getCardDetails(user).get("expireDate");
		
		String cardExpireMonth = cardExpireDate.split("/")[0];
		
		String cardExpireYear = "20" + cardExpireDate.split("/")[1];
		
		driver.findElement(By.name("name_on_card")).sendKeys(cardName);
		
		driver.findElement(By.name("card_number")).sendKeys(cardNumber);
		
		driver.findElement(By.name("cvc")).sendKeys(cardCVV);
		
		driver.findElement(By.name("expiry_month")).sendKeys(cardExpireMonth);
		
		driver.findElement(By.name("expiry_year")).sendKeys(cardExpireYear);
		
		driver.findElement(By.id("submit")).click();
		
//------------------------------------------------------------------------------------------------------------		
		
		//Last Page Order Confirmation Page
		
		String shownOrderConfirmationMessage = driver.findElement(By.cssSelector("h2.title b")).getText();
		
		String actualOrderConfirmationMessage = baseCode.getOrderConfirmationMessage();
		
		Assert.assertEquals(actualOrderConfirmationMessage, shownOrderConfirmationMessage);
		
		driver.findElement(By.className("check_out")).click();
		
		baseCode.logoutAction();
				
		Thread.sleep(5000);
		
		driver.quit();

	}

}
