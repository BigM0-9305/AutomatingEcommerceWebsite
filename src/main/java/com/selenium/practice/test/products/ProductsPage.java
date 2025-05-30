package com.selenium.practice.test.products;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.practice.test.cart.CartPage;
import com.selenium.practice.test.meta.BaseCode;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class ProductsPage extends BaseCode {

	WebDriver driver;

	public ProductsPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[@class='title text-center']")
	WebElement productsTitle;

	@FindBy(xpath = "//div[@class='productinfo text-center']")
	List<WebElement> products;

	@FindBy(xpath = "//div[@class='productinfo text-center']/p")
	List<WebElement> productNames;

	@FindBy(css = "div.product-overlay div.overlay-content a.add-to-cart")
	List<WebElement> addToCartButtons;

	@FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
	WebElement continueShoppingButton;

	@FindBy(css = "li a[href='/product_details/1']")
	WebElement viewProductButton;

	@FindBy(css = "div.product-information h2")
	WebElement productName;

	@FindBy(css = "div.product-information p:nth-child(3)")
	WebElement productCategory;

	@FindBy(css = "div.product-information span span")
	WebElement productPrice;

	@FindBy(css = "div.product-information p:nth-child(6)")
	WebElement productAvailability;

	@FindBy(css = "div.product-information p:nth-child(7)")
	WebElement productCondition;

	@FindBy(css = "div.product-information p:nth-child(8)")
	WebElement productBrand;

	@FindBy(id = "search_product")
	WebElement productSearchBar;

	@FindBy(id = "submit_search")
	WebElement submitSearchButton;

	@FindBy(id = "quantity")
	WebElement quantityInput;

	@FindBy(css = "button[type='button']")
	WebElement addToCartButtonInsideViewProduct;

	@FindBy(xpath = "//p/a[@href='/view_cart']")
	WebElement viewCartButton;
	
	@FindBy(id = "name")
	WebElement nameInputInsideViewProduct;
	
	@FindBy(id = "email")
	WebElement emailInputInsideViewProduct;
	
	@FindBy(id = "review")
	WebElement reviewInputInsideViewProduct;
	
	@FindBy(id = "button-review")
	WebElement reviewSubmitButton;
	
	@FindBy(css = "div.alert-success span")
	WebElement reviewThankYouMessage;

	public void addingProductsToCart() throws InterruptedException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<String> productList = getProductList();

		iframeAddRemover();

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		Assert.assertTrue(productsTitle.getText().equalsIgnoreCase("All Products"));

		Actions actions = new Actions(driver);

		for (int i = 0; i < productList.size(); i++) {

			String productNeeded = productList.get(i);

			for (int j = 0; j < productNames.size(); j++) {

				String productName = productNames.get(j).getText();

				if (productName.equalsIgnoreCase(productNeeded)) {

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
	}

	public void verifyingProductDetails() throws InterruptedException, IOException {

		iframeAddRemover();

		Assert.assertTrue(productsTitle.getText().equalsIgnoreCase("All Products"));

		viewProductButton.click();

		iframeAddRemover();

		Assert.assertEquals(productName.getText(), getFirstProductName());

		Assert.assertTrue(productCategory.getText().contains(getFirstProductCategory()));

		Assert.assertEquals(productPrice.getText(), getFirstProductPrice());

		Assert.assertTrue(productAvailability.getText().contains(getFirstProductAvailability()));

		Assert.assertTrue(productCondition.getText().contains(getFirstProductCondition()));

		Assert.assertTrue(productBrand.getText().contains(getFirstProductBrand()));

	}

	public CartPage increasingProductQuantity(int quantity) throws InterruptedException {

		iframeAddRemover();

		viewProductButton.click();

		iframeAddRemover();

		quantityInput.clear();

		quantityInput.sendKeys(Integer.toString(quantity));

		addToCartButtonInsideViewProduct.click();

		Thread.sleep(2000);

		viewCartButton.click();

		CartPage cartPage = new CartPage(driver);

		return cartPage;

	}

	public void verifyingProductSearch(String product) throws InterruptedException, IOException {

		iframeAddRemover();
		
		Assert.assertTrue(productsTitle.getText().equalsIgnoreCase("All Products"));

		String partialProductName = product.split(" ")[0];

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		productSearchBar.sendKeys(partialProductName);

		submitSearchButton.click();

		Assert.assertEquals(productsTitle.getText(), getSearchedProductTitle());

		wait.until(
				ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("div.productinfo p"))));

		List<WebElement> productsDisplayed = driver.findElements(By.cssSelector("div.productinfo p"));

		for (WebElement productShown : productsDisplayed) {

			String name = productShown.getText();

			Assert.assertTrue(name.contains(partialProductName));
		}
	}
	
	public List<String> getDisplayedProducts() {
		
		List<String> displayedProducts = new ArrayList<String>();
		
		 for(int i =0; i< productNames.size(); i++) {
			 
			 displayedProducts.add(productNames.get(i).getText());
		 }
		 
		 return displayedProducts;
	}

	public void addingDisplayedProductsToCart() throws InterruptedException {

		iframeAddRemover();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		Actions actions = new Actions(driver);

		for (int j = 0; j < addToCartButtons.size(); j++) {
			
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", products.get(j));

			actions.moveToElement(products.get(j)).perform();

			Thread.sleep(2000);

			addToCartButtons.get(j).click();

			wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));

			continueShoppingButton.click();

			Thread.sleep(2500);
		}
	}
	
	public void writingAReviewOnProdcut(String user) throws InterruptedException, ClassNotFoundException, SQLException, IOException {
		
		Lorem lorem = LoremIpsum.getInstance();
		
		String review = lorem.getParagraphs(1, 1);
		
		iframeAddRemover();
		
		Assert.assertTrue(productsTitle.getText().equalsIgnoreCase("All Products"));
		
		viewProductButton.click();
		
		iframeAddRemover();
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		executor.executeScript("arguments[0].scrollIntoView(true);", nameInputInsideViewProduct);
		
		nameInputInsideViewProduct.sendKeys(user);
		
		emailInputInsideViewProduct.sendKeys(getUsernameAndPassword(user).get("Username"));
		
		reviewInputInsideViewProduct.sendKeys(review);
		
		reviewSubmitButton.click();
		
		Assert.assertEquals(reviewThankYouMessage.getText(), getReviewSubmittedMessage());
		
	}

}
