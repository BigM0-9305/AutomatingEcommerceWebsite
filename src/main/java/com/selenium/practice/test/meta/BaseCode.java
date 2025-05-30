package com.selenium.practice.test.meta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.practice.test.cart.CartPage;
import com.selenium.practice.test.login.LoginPage;
import com.selenium.practice.test.products.ProductsPage;

public class BaseCode {
	
	WebDriver driver;

	public BaseCode(WebDriver driver) {

		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "ul.nav li a[href='/login']")
	WebElement loginOrRegister;
	
	@FindBy(css = "li a b")
	WebElement loggedInAs;
	
	@FindBy(xpath = "//li/a[@href='/logout']")
	WebElement logoutElement;
	
	@FindBy(xpath = "//li/a[@href='/products']")
	WebElement productsButton;
	
	@FindBy(xpath = "//li/a[@href='/view_cart']")
	WebElement cartButton;
	
	@FindBy(css = "li a[href='/delete_account']")
	WebElement deleteAccountButton;
	
	@FindBy(css = "li a[href='/contact_us']")
	WebElement contactUsButton;
	
	@FindBy(css = "li a[href='/test_cases']")
	WebElement testCasesButton;
	
	@FindBy(css = "div.single-widget h2")
	WebElement subscriptionTitle;
	
	@FindBy(id = "susbscribe_email")
	WebElement subscriptionEmailInput;
	
	@FindBy(id = "subscribe")
	WebElement subscriptionSubmitButton;
	
	@FindBy(css = "div#success-subscribe div.alert-success")
	WebElement subscriptionSuccessMessage;
	
	@FindBy(css = "h2.title b")
	WebElement accountDeletedMessage;
	
	@FindBy(css = "a[data-qa='continue-button']")
	WebElement continueButton;
	
	@FindBy(css = "a[href='#Women']")
	WebElement womenCategoryDropDown;
	
	@FindBy(css = "div#Women li a[href='/category_products/1']")
	WebElement womensSubCategoryDress;
	
	@FindBy(css = "div#Women li a[href='/category_products/2']")
	WebElement womenSubCategoryTops;
	
	@FindBy(css = "div#Women li a[href='/category_products/7']")
	WebElement womenSubCategorySaree;
	
	@FindBy(css = "a[href='#Men']")
	WebElement menCategoryDropDown;
	
	@FindBy(css = "div#Men li a[href='/category_products/3']")
	WebElement menSubCategoryTShirts;
	
	@FindBy(css = "div.features_items h2.title")
	WebElement categoryTitle;
	
	@FindBy(linkText = "POLO")
	WebElement poloBrand;
	
	@FindBy(linkText = "H&M")
	WebElement hAndMBrand;
	
	@FindBy(linkText = "MADAME")
	WebElement madameBrand;
	
	@FindBy(partialLinkText = "MAST")
	WebElement mastAndHarbourBrand;
	
	@FindBy(linkText = "BABYHUG")
	WebElement babyHugBrand;
	
	@FindBy(partialLinkText = "ALLEN")
	WebElement allenSollyJnrBrand;
	
	@FindBy(partialLinkText = "KOOKIE")
	WebElement kookieKidsBrand;
	
	@FindBy(linkText = "BIBA")
	WebElement bibaBrand;
	
	@FindBy(css = "div.brands-name ul.nav-pills li a")
	List<WebElement> brandNameCategories;
	
	@FindBy(css = "div.recommended_items h2.title")
	WebElement recommendedItemsTitle;
	
	@FindBy(css = "div#recommended-item-carousel div.carousel-inner div.col-sm-4 p")
	List<WebElement> recommendedProductNameWebElements;
	
	@FindBy(css = "div#recommended-item-carousel div.carousel-inner div.col-sm-4 a")
	List<WebElement> addToCartButtonsOfRecommendedItems;
	
	@FindBy(css = "div#recommended-item-carousel a[data-slide='next']")
	WebElement nextSlideButton;
	
	@FindBy(css = "button[data-dismiss='modal']")
	WebElement continueShoppingButton;
	
	@FindBy(id = "scrollUp")
	WebElement upArrowButton;
	
	@FindBy(xpath = "//h2[normalize-space(text())='Full-Fledged practice website for Automation Engineers']")
	List<WebElement> textsOnTopOfDOM;
	
	
	//fetching data from .property file
	public Properties getPropertyData() throws IOException {
		
		Properties properties = new Properties();
		
		String path = System.getProperty("user.dir") + "/src/main/java/com/selenium/practice/test/globaldata/GlobalData.properties";
		
		FileInputStream propertyFile = new FileInputStream(new File(path));
		
		properties.load(propertyFile);
		
		return properties;
	}
	
	public String getUrl () throws IOException {
		
		String url = getPropertyData().getProperty("url");
		
		return url;
	}
	
	public String getName() throws IOException {
		
		String name = getPropertyData().getProperty("name");
		
		return name;
	}
	
	public String getFirstName() throws IOException{
		
		String fullName = getName();
		
		String [] fullNameArray = fullName.split(" ");
		
		String firstName = fullNameArray[0];
		
		return firstName;
	}
	
	public String getLastName() throws IOException{
		
		String fullName = getName();
		
		String [] fullNameArray = fullName.split(" ");
		
		String lastName = fullNameArray[1];
		
		return lastName;
	}
	
	public String getGender() throws IOException {
		
		String gender = getPropertyData().getProperty("gender");
		
		return gender;
	}
	
	public String getEmail() throws Exception {
		
		String emailPart1 = getPropertyData().getProperty("emailpart1");
		
		String emailPart2 = getPropertyData().getProperty("emailpart2");
		
		String email = emailPart1 + System.currentTimeMillis() + emailPart2;
		
		return email;
	}
	
	public String getPassword() throws IOException {
		
		String password = getPropertyData().getProperty("password");
		
		return password;
	}

	public String getAddress() throws IOException {
		
		String address = getPropertyData().getProperty("address");
		
		return address;
	}
	
	public String getState() throws IOException {
		
		String state = getPropertyData().getProperty("state");
		
		return state;
	}
	
	public String getCity() throws IOException {
		
		String city = getPropertyData().getProperty("city");
		
		return city;
	}
	
	public String getZipCode() throws IOException {
		
		String zipCode = getPropertyData().getProperty("zipcode");
		
		return zipCode;
	}
	
	public String getCountry() throws IOException {
		
		String city = getPropertyData().getProperty("country");
		
		return city;
	}
	
	public String getMobileNumber() throws IOException {
		
		String mobileNumber = getPropertyData().getProperty("mobile");
		
		return mobileNumber;
	}
	
	public HashMap<String, String> getTempUserDetails() throws IOException{
		
		HashMap<String, String> tempUserDetails = new HashMap<String, String>();
		
		if(getGender().equalsIgnoreCase("male")) {
			
			tempUserDetails.put("Name", "Mr. " + getName());
		}
		
		else {
			
			tempUserDetails.put("Name", "Mrs. " + getName());
		}
		
		tempUserDetails.put("Address", getAddress());
		
		tempUserDetails.put("StateCityAndCode", getCity() + " " + getState() + " " + getZipCode());
		
		tempUserDetails.put("Country", getCountry());
		
		tempUserDetails.put("MobileNumber", getMobileNumber());
		
		return tempUserDetails;
	}
	
	public String getAccountCreatedMessage() throws IOException {
		
		String message = getPropertyData().getProperty("message");
		
		return message;
	}
	
	public String getDeletedAccountMessage() throws IOException {
		
		String deletedMessage = getPropertyData().getProperty("deletedmessage");
		
		return deletedMessage;
	}
	
	public LoginPage pageLander() throws IOException {
		
		driver.get(getUrl());
		
		LoginPage  loginAction = new LoginPage(driver);
		
		return loginAction;
		
	}
	
	public void windowMaximizer() {
		
		driver.manage().window().maximize();
	}
	
	public void enteringLoginAndRegisterPage() {
		
		loginOrRegister.click();
		
	}
	
	public String getDBUrl() throws IOException {
		
		String dbUrl = getPropertyData().getProperty("dbUrl");
		
		return dbUrl;
	}
	
	public String getDBUsername() throws IOException {
		
		String dbUsername = getPropertyData().getProperty("dbUsername");
		
		return dbUsername;
	}
	
	public String getDBPassword() throws IOException {
		
		String dbPassword = getPropertyData().getProperty("dbPassword");
		
		return dbPassword;
	}
	
	public String getUser1() throws IOException {
		
		String user1 = getPropertyData().getProperty("user1");
		
		return user1;
	}
	
	public String getUser2() throws IOException {
		
		String user2 = getPropertyData().getProperty("user2");
		
		return user2;
	}
	
	public String getUser3() throws IOException {
		
		String user3 = getPropertyData().getProperty("user3");
		
		return user3;
	}
	
	public HashMap<String, String> getUsernameAndPassword(String user) throws ClassNotFoundException, SQLException, IOException {
		
		String username = null;
		
		String password = null;
		
		String dbQuery = "select * from E_Commerce_Users where Name = ?";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(getDBUrl(), getDBUsername(), getDBPassword());
		
		PreparedStatement preparedStatement = connection.prepareStatement(dbQuery);
		
		preparedStatement.setString(1, user);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			
			username = resultSet.getString("Email");
			
			password = resultSet.getString("Password");
		}
		
		HashMap<String, String> usernameAndPassword = new HashMap<String, String>();
		
		usernameAndPassword.put("Username", username);
		
		usernameAndPassword.put("Password", password);
		
		return usernameAndPassword;
		
	}
	
	public String getLoggedInAs() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(loggedInAs));
		
		String user = loggedInAs.getText();
		
		return user;	
		
	}
	
	public ProductsPage getIntoProductsPage() {
		
		productsButton.click();
		
		ProductsPage productsPage = new ProductsPage(driver);
		
		return productsPage;
	}
	
	public void logoutAction() {
		
		logoutElement.click();
	}
	
	public CartPage goToCart() {
		
		cartButton.click();
		
		CartPage cartPage = new CartPage(driver);
		
		return cartPage;
	}
	
	public List<String> getProductList() throws IOException{
		
		List<String> productList = new ArrayList<String>();
		
		productList.add(getPropertyData().getProperty("product1"));
		
		productList.add(getPropertyData().getProperty("product2"));
		
		productList.add(getPropertyData().getProperty("product3"));
		
		return productList;
	}
	
	public String getProduct1() throws IOException {
		
		String product1 = getPropertyData().getProperty("product1");
		
		return product1;
	}
	
	public String getProduct2() throws IOException {
		
		String product2 = getPropertyData().getProperty("product2");
		
		return product2;
	}
	
	public String getProduct3() throws IOException {
		
		String product3 = getPropertyData().getProperty("product3");
		
		return product3;
	}
	
	public void iframeAddRemover() throws InterruptedException {
		
		Thread.sleep(3000);
		
		List<WebElement> iframeAdList = driver.findElements(By.cssSelector("div.grippy-host"));
		
		if(!iframeAdList.isEmpty() && iframeAdList.get(0).isDisplayed()) {
			
			iframeAdList.get(0).click();
		}
		
		Thread.sleep(2000);
	}
	
	public HashMap<String, String> getCardDetails(String user) throws IOException, SQLException {
		
		String nameOnCard = null;
		
		String cardNumber = null;
		
		String cvc = null;
		
		String expireDate = null;
		
		String dbQuery = "select * from E_Commerce_Users where Name = ?";
		
		Connection connection = DriverManager.getConnection(getDBUrl(), getDBUsername(), getDBPassword());
		
		PreparedStatement preparedStatement = connection.prepareStatement(dbQuery);
		
		preparedStatement.setString(1, user);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			
			nameOnCard = resultSet.getString("Name");
			
			cardNumber = resultSet.getString("Card_Number");
			
			cvc = resultSet.getString("CVV");
			
			expireDate = resultSet.getString("Expiry");
		}
		
		HashMap<String, String> cardDetails =  new HashMap<String, String>();
		
		cardDetails.put("Name_On_Card", nameOnCard);
		
		cardDetails.put("Card_Number", cardNumber);
		
		cardDetails.put("CVV", cvc);
		
		cardDetails.put("expireDate", expireDate);
		
		return cardDetails;
	}
	
	public String getOrderConfirmationMessage() throws IOException {
		
		String  message = getPropertyData().getProperty("orderconfirmmessage");
		
		return message;
	}
	
	public String getIncorrectCredentialsMessage() throws IOException {
		
		String message = getPropertyData().getProperty("incorrectcredentialsmessage");
		
		return message;
	}
	
	public void deleteAccountAction() throws IOException {
		
		deleteAccountButton.click();
		
		Assert.assertTrue(accountDeletedMessage.getText().equalsIgnoreCase(getDeletedAccountMessage()));
		
		continueButton.click();
	}
	
	public String getAccountAlreadyExistsMessage() throws IOException {
		
		String message = getPropertyData().getProperty("emailalreadyexistsmessage");
		
		return message;
	}
	
	public void openingContactUsPage() {
		
		contactUsButton.click();
		
	}
	
	public String getContactFormSubmitSuccessMessage() throws IOException {
		
		String message = getPropertyData().getProperty("sumbitsuccessmessage");
		
		return message;
		
	}
	
	public void gettingIntoTestCasesPage() {
		
		testCasesButton.click();
	}
	
	public String getFirstProductName() throws IOException {
		
		String name = getPropertyData().getProperty("firstproductname");
		
		return name;
	}
	
	public String getFirstProductCategory() throws IOException {
		
		String category = getPropertyData().getProperty("firstproductcategory");
		
		return category;
	}
	
	public String getFirstProductPrice() throws IOException {
		
		String price = getPropertyData().getProperty("firstproductprice");
		
		return price;
	}
	
	public String getFirstProductAvailability() throws IOException {
		
		String availability = getPropertyData().getProperty("firstproductavailability");
		
		return availability;
	}
	
	public String getFirstProductCondition() throws IOException {
		
		String condition = getPropertyData().getProperty("firstproductcondition");
		
		return condition;
	}
	
	public String getFirstProductBrand() throws IOException {
		
		String brand = getPropertyData().getProperty("firstproductbrand");
		
		return brand;
	}
	
	public String getSubscriptionSuccessMessage() throws IOException {
		
		String message = getPropertyData().getProperty("subscriptionsuccessmessage");
		
		return message;
	}
	
	public void submittingEmailForSubscription(String user) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		
		iframeAddRemover();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView(true);", subscriptionEmailInput);
		
		String email = getUsernameAndPassword(user).get("Username");
		
		subscriptionEmailInput.sendKeys(email);
		
		subscriptionSubmitButton.click();
		
		Assert.assertEquals(subscriptionSuccessMessage.getText(), getSubscriptionSuccessMessage());
		
	}
	
	public int getProductQuantity() throws NumberFormatException, IOException {
		
		int quantity = Integer.parseInt(getPropertyData().getProperty("productquantity"));
		
		return quantity;
	}
	
	public String getShoppingCartTitle() throws IOException {
		
		String title = getPropertyData().getProperty("shoppingcarttitle");
		
		return title;
	}
	
	public String getTempCardNumber() throws IOException {
		
		String number = getPropertyData().getProperty("cardnumber");
		
		return number;
	}
	
	public String getTempCardCVV() throws IOException {
		
		String cvv = getPropertyData().getProperty("cvc");
		
		return cvv;
	}
	
	public String getTempCardExpiryDate() throws IOException {
		
		String date = getPropertyData().getProperty("expiredate");
				
		return date;
	}
	
	public String getCheckoutTextAreaMessage() throws IOException {
		
		String message = getPropertyData().getProperty("checkouttextareamessage");
		
		return message;
	}
	
	public String getWomenDressCategoryTitle() throws IOException {
		
		String title = getPropertyData().getProperty("womendresstitle");
		
		return title;
	}
	
	public String getWomenTopsCategoryTitle() throws IOException {
		
		String title = getPropertyData().getProperty("womentopstitle");
		
		return title;
	}
	
	public String getWomenSareeCategoryTitle() throws IOException {
		
		String title = getPropertyData().getProperty("womensareetitle");
		
		return title;
	}
	
	public String getMenTShirtCategoryTitle() throws IOException {
		
		String title = getPropertyData().getProperty("mentshirttile");
		
		return title;
	}
	
	
	public String getPoloBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("polobrandtitle");
		
		return title;
	}
	
	public String getHAndMBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("handmbrandtitle");
		
		return title;
	}
	
	public String getMadameBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("madamebrandtitle");
		
		return title;
	}
	
	public String getMastAndHarbourBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("mastandharbourbrandtitle");
		
		return title;
	}
	
	public String getBabyHugBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("babyhugbrandtitle");
		
		return title;
	}
	
	public String getAllenSollyJnrBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("allensollyjnrbrandtitle");
		
		return title;
	}
	
	public String getKookieKidsBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("kookiekidsbrandtitle");
		
		return title;
	}
	
	public String getBibaBrandTitle() throws IOException {
		
		String title = getPropertyData().getProperty("bibabrandtitle");
		
		return title;
	}
	
	public List<String> getAllBrandTitles() throws IOException {
		
		List<String> brandTitles = new ArrayList<String>();
		
		brandTitles.add(getPoloBrandTitle());
		
		brandTitles.add(getHAndMBrandTitle());
		
		brandTitles.add(getMadameBrandTitle());
		
		brandTitles.add(getMastAndHarbourBrandTitle());
		
		brandTitles.add(getBabyHugBrandTitle());
		
		brandTitles.add(getAllenSollyJnrBrandTitle());
		
		brandTitles.add(getKookieKidsBrandTitle());
		
		brandTitles.add(getBibaBrandTitle());
		
		return brandTitles;
	}
	
	public void selectingWomenDressCategory() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		executor.executeScript("arguments[0].scrollIntoView(true);", womenCategoryDropDown);
		
		//For Dress:-
		womenCategoryDropDown.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(womensSubCategoryDress));
		
		//Thread.sleep(2000);
		
		womensSubCategoryDress.click();
		
	}
	
	public void selectingWomenTopsCategory() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//For Tops:-
		womenCategoryDropDown.click();
		
		wait.until(ExpectedConditions.visibilityOf(womenSubCategoryTops));
		
		womenSubCategoryTops.click();
		
	}
	
	public void selectingWomenSareeCategory() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//For Saree:-
		womenCategoryDropDown.click();
		
		wait.until(ExpectedConditions.visibilityOf(womenSubCategorySaree));
		
		womenSubCategorySaree.click();
	
	}
	
	public void selectingMenTShirtsCategory() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//For TShirts
		menCategoryDropDown.click();
		
		wait.until(ExpectedConditions.visibilityOf(menSubCategoryTShirts));
		
		menSubCategoryTShirts.click();
	
	}
	
	public void verifyingProductsCategory() throws IOException, InterruptedException {
		
		String dressTitle = getWomenDressCategoryTitle();
		
		String topsTitle = getWomenTopsCategoryTitle();
		
		String sareeTitle = getWomenSareeCategoryTitle();
		
		String tShirtTitle = getMenTShirtCategoryTitle();
		
		iframeAddRemover();
		
		selectingWomenDressCategory();
		
		Assert.assertTrue(categoryTitle.getText().contains(dressTitle));
		
		selectingWomenTopsCategory();
		
		Assert.assertTrue(categoryTitle.getText().contains(topsTitle));
		
		selectingWomenSareeCategory();
		
		Assert.assertTrue(categoryTitle.getText().contains(sareeTitle));
		
		selectingMenTShirtsCategory();
		
		Assert.assertTrue(categoryTitle.getText().contains(tShirtTitle));
		
	}
	
	public void verficationOfBrandCategorization() throws IOException, InterruptedException {
		
		iframeAddRemover();
		
		for(int i = 0; i < brandNameCategories.size(); i++) {
			
			if(brandNameCategories.size() == getAllBrandTitles().size()) {
				
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				
				executor.executeScript("arguments[0].scrollIntoView(true);", brandNameCategories.get(i));
				
				brandNameCategories.get(i).click();
				
				iframeAddRemover();
				
				Assert.assertEquals(categoryTitle.getText(), getAllBrandTitles().get(i));
			}
		}		
	}
	
	public String getSearchedProductTitle() throws IOException {
		
		String title = getPropertyData().getProperty("searchedproductstitle");
		
		return title;
	}
	
	public String getReviewSubmittedMessage() throws IOException {
		
		String message = getPropertyData().getProperty("reviewthankyoumessage");
		
		return message;
	}
	
	public String getRecommendedTitle() throws IOException {
		
		String title = getPropertyData().getProperty("recommendeditemstitle");
		
		return title;
	}
	
	public List<String> addingRecommendedProductsToCart() throws InterruptedException, IOException {
		
		List<String> recommendedProductNames = new ArrayList<String>();
		
		iframeAddRemover();
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		executor.executeScript("arguments[0].scrollIntoView(true);", recommendedItemsTitle);
		
		Assert.assertEquals(recommendedItemsTitle.getText(), getRecommendedTitle());
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		for(int i = 0 ; i < recommendedProductNameWebElements.size(); i++) {
			
			if(recommendedProductNameWebElements.get(i).isDisplayed()) {
				
				recommendedProductNames.add(recommendedProductNameWebElements.get(i).getText());
				
				addToCartButtonsOfRecommendedItems.get(i).click();
				
				wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
				
				continueShoppingButton.click();
			}
			
			else if(!(recommendedProductNameWebElements.get(i).isDisplayed())) {
				
				nextSlideButton.click();
				
				wait.until(ExpectedConditions.visibilityOf(recommendedProductNameWebElements.get(i)));
				
				recommendedProductNames.add(recommendedProductNameWebElements.get(i).getText());
				
				addToCartButtonsOfRecommendedItems.get(i).click();
				
				wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
				
				continueShoppingButton.click();
			}
		}
		
		return recommendedProductNames;
		
	}
	
	public String getInvoiceDownloadLocation() throws IOException {
		
		String path = System.getProperty("user.dir") + getPropertyData().getProperty("filedownloadpath");
		
		return path;
	}
	
	public boolean isFilePresent() throws IOException, InterruptedException {
		
		Thread.sleep(5000);
		
		File file = new File(getInvoiceDownloadLocation());
		
		return file.exists();
	}
	
	public void checkingUpArrowButtonScroll() throws InterruptedException {
		
		iframeAddRemover();
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		executor.executeScript("arguments[0].scrollIntoView(true);", subscriptionTitle);
		
		upArrowButton.click();
		
		Thread.sleep(2000);
		
		Long scrollPostion = (long) executor.executeScript("return window.pageYOffset;");
		
		Assert.assertEquals(scrollPostion, 0);
		
		String script = "var elem = arguments[0];"
				+ "if (!elem) return false;"
				+ "var rect = elem.getBoundingClientRect();"
				+ "return ("
				+ "	rect.top >= 0 && "
				+ "	rect.left >= 0 && "
				+ "	rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && "
				+ "	rect.right <= (window.innerWidth || document.documentElement.clientWidth)"
				+ ");";
		
		boolean anyVisible = false;
		
		for(WebElement titleElement : textsOnTopOfDOM) {
			
			Boolean isVisible = (Boolean) executor.executeScript(script, titleElement);
			
			if(isVisible != null && isVisible) {
				
				anyVisible = true;
				
				break;
			}
		}
		
		Assert.assertTrue(anyVisible);
	}
	
	public void checkingScrollWithoutUpArrowButton() throws InterruptedException {
		
		iframeAddRemover();
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		executor.executeScript("arguments[0].scrollIntoView(true);", subscriptionTitle);
		
		//Scrolls to the top
		executor.executeScript("window.scrollTo(0, 0);");
		
		Thread.sleep(2000);
		
		String script = "var elem = arguments[0];"
				+ "if (!elem) return false;"
				+ "var rect = elem.getBoundingClientRect();"
				+ "return ("
				+ "	rect.top >= 0 &&"
				+ "	rect.left >= 0 &&"
				+ "	rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&"
				+ "	rect.right <= (window.innerWidth || document.documentElement.clientWidth)"
				+ ");";
		
		boolean anyVisible = false;
		
		for(WebElement titleWebElement : textsOnTopOfDOM) {
			
			Boolean isVisible = (Boolean) executor.executeScript(script, titleWebElement);
			
			if(isVisible != null && isVisible) {
				
				anyVisible = true;
				
				break;
			}
		}
		
		Assert.assertTrue(anyVisible);
		
	}
}
