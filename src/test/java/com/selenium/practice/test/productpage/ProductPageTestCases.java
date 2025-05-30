package com.selenium.practice.test.productpage;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.practice.test.products.ProductsPage;
import com.selenium.practice.test.testcomponents.TestComponentsClass;

public class ProductPageTestCases extends TestComponentsClass{

	ProductsPage productsPage;
	
	@Test
	public void viewProductTestCase() throws InterruptedException, IOException {
		
		productsPage = baseCode.getIntoProductsPage();
		
		productsPage.verifyingProductDetails();
	}
	
	@Test(dataProvider = "getProductData")
	public void productSearchBoxTestCase(String productName) throws InterruptedException, IOException {
		
		productsPage = baseCode.getIntoProductsPage();
		
		productsPage.verifyingProductSearch(productName);
	}
	
	@Test
	public void verifyingProductCategoriesTestCase() throws IOException, InterruptedException {
		
		baseCode.verifyingProductsCategory();
	}
	
	@Test
	public void verifyingProductBrandCategorizationTestCase() throws IOException, InterruptedException {
		
		baseCode.getIntoProductsPage();
		
		baseCode.verficationOfBrandCategorization();
	}
	
	@Test(dataProvider = "getUserData")
	public void writingReviewTestCase(String user) throws ClassNotFoundException, InterruptedException, SQLException, IOException {
		
		productsPage = baseCode.getIntoProductsPage();
		
		productsPage.writingAReviewOnProdcut(user);
	}
	
	@DataProvider
	public Object[][] getProductData() throws IOException{
		
		String productName = getProductName(1);
		
		return new Object[][] {{productName}};
	}
	
	@DataProvider
	public Object[][] getUserData() throws IOException {
		
		String user = getUserName(2);
		
		return new Object[][] {{user}};
	}
}
