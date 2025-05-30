package com.selenium.practice.test.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.selenium.practice.test.contact.ContactUsPage;
import com.selenium.practice.test.login.LoginPage;
import com.selenium.practice.test.meta.BaseCode;
import com.selenium.practice.test.sign_up.SignUpTest;
import com.selenium.practice.test.tests.TestCasesPage;

public class TestComponentsClass {

	public BaseCode baseCode;

	public LoginPage loginPage;

	public SignUpTest signUpTest;

	public ContactUsPage contactUsPage;

	public TestCasesPage testCasesPage;

	private HashMap<String, Object> settingChromePrefrences(String downloadPath){
		
		HashMap<String, Object> chromePrefs = new HashMap<>();
		
		chromePrefs.put("profile.default_content_settings.popups", 0);

		chromePrefs.put("download.default_directory", downloadPath);

		chromePrefs.put("safebrowsing.enabled", true);
		
		return chromePrefs;
	}
	
	private HashMap<String, Object> settingEgePrefernces(String downloadPath){
		
		HashMap<String, Object> edgePrefs = new HashMap<>();
		
		edgePrefs.put("profile.default_content_settings.popups", 0);
		
		edgePrefs.put("download.default_directory", downloadPath);
		
		edgePrefs.put("safebrowsing.enabled", true);
		
		return edgePrefs;
	}
	
	private FirefoxProfile settingFirFoxPreferences(String downloadPath) {
		
		FirefoxProfile profile = new FirefoxProfile();
		
		profile.setPreference("browser.download.folderList", 2);
		
		profile.setPreference("browser.download.dir", downloadPath);
		
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/zip,text/csv");
		
		profile.setPreference("pdfjs.disabled", true);
		
		return profile;
	}
	
	public WebDriver browserInitializer() throws IOException {

		WebDriver driver = null;

		String browserName = getBrowserName();
		
		String browserNameInCMD = System.getProperty("browser");

		String downloadPath = System.getProperty("user.dir") + "\\InvoiceDownloads";
		
		if(browserNameInCMD == null) {
			
			if (browserName.equalsIgnoreCase("chrome")) {

				HashMap<String, Object> chromePrefs = settingChromePrefrences(downloadPath);

				ChromeOptions options = new ChromeOptions();
				
				options.setExperimentalOption("prefs", chromePrefs);
				
				driver = new ChromeDriver(options);
			}
			
			else if (browserName.equalsIgnoreCase("edge")) {
				
				HashMap<String, Object> edgePrefs = settingEgePrefernces(downloadPath);

				EdgeOptions options = new EdgeOptions();
				
				options.setExperimentalOption("prefs", edgePrefs);
				
				driver = new EdgeDriver(options);
			} 
			
			else if (browserName.equalsIgnoreCase("firefox")) {
				
				FirefoxProfile profile = settingFirFoxPreferences(downloadPath);

				FirefoxOptions options = new FirefoxOptions();
				
				options.setProfile(profile);
				
				driver = new FirefoxDriver(options);
			}
		}
		
		else {
			
			if(browserNameInCMD.equalsIgnoreCase("chrome")) {
				
				HashMap<String, Object> chromePrefs = settingChromePrefrences(downloadPath);

				ChromeOptions options = new ChromeOptions();
				
				options.setExperimentalOption("prefs", chromePrefs);
				
				driver = new ChromeDriver(options);
			}
			
			else if(browserNameInCMD.equalsIgnoreCase("edge")) {
				
				HashMap<String, Object> edgePrefs = settingEgePrefernces(downloadPath);

				EdgeOptions options = new EdgeOptions();
				
				options.setExperimentalOption("prefs", edgePrefs);
				
				driver = new EdgeDriver(options);
			}
			
			else if(browserNameInCMD.equalsIgnoreCase("firefox")) {
				
				FirefoxProfile profile = settingFirFoxPreferences(downloadPath);

				FirefoxOptions options = new FirefoxOptions();
				
				options.setProfile(profile);
				
				driver = new FirefoxDriver(options);
			}
			
			else if(browserNameInCMD.contains("headless")) {
				
				if(browserNameInCMD.toLowerCase().contains("chrome")) {
					
					HashMap<String, Object> chromePrefs = settingChromePrefrences(downloadPath);

					ChromeOptions options = new ChromeOptions();
					
					options.setExperimentalOption("prefs", chromePrefs);
					
					options.addArguments("--headless=new");  
					
					options.addArguments("--window-size=1920,1080");
					
					driver = new ChromeDriver(options);
					
				}
				
				else if(browserNameInCMD.toLowerCase().contains("edge")) {

					HashMap<String, Object> edgePrefs = settingEgePrefernces(downloadPath);

					EdgeOptions options = new EdgeOptions();
					
					options.setExperimentalOption("prefs", edgePrefs);
					
				    options.addArguments("--headless=new"); // use "new" headless mode
				    
				    options.addArguments("--window-size=1920,1080");
					
					driver = new EdgeDriver(options);
				}
				
				else if(browserNameInCMD.toLowerCase().contains("firefox")) {
					
					FirefoxProfile profile = settingFirFoxPreferences(downloadPath);

					FirefoxOptions options = new FirefoxOptions();
					
				    options.addArguments("--headless");

				    options.addArguments("--width=1920");
				    
				    options.addArguments("--height=1080");
					
					options.setProfile(profile);
					
					driver = new FirefoxDriver(options);
				}
			}
		}

		return driver;
	}

	public String getUserName(int userNumber) throws IOException {
		
		Properties properties = new Properties();
		
		String path = System.getProperty("user.dir")
				+ "/src/main/java/com/selenium/practice/test/globaldata/GlobalData.properties";
		
		FileInputStream propertyFile = new FileInputStream(new File(path));
		
		properties.load(propertyFile);
		
		return properties.getProperty("user" + userNumber);
	}

	public String getProductName(int productNumber) throws IOException {
		
		Properties properties = new Properties();
		
		String path = System.getProperty("user.dir")
				+ "/src/main/java/com/selenium/practice/test/globaldata/GlobalData.properties";
		
		FileInputStream propertyFile = new FileInputStream(new File(path));
		
		properties.load(propertyFile);
		
		return properties.getProperty("product" + productNumber);
	}

	public String getBrowserName() throws IOException {
		
		Properties properties = new Properties();
		
		String path = System.getProperty("user.dir")
				+ "/src/main/java/com/selenium/practice/test/globaldata/GlobalData.properties";
		
		FileInputStream propertyFile = new FileInputStream(new File(path));
		
		properties.load(propertyFile);
		
		return properties.getProperty("browser");
	}

	@BeforeMethod
	public void launchApplication() throws IOException {
		
		WebDriver localDriver = browserInitializer();
		
		WebDriverManager.setDriver(localDriver);

		baseCode = new BaseCode(localDriver);
		
		signUpTest = new SignUpTest(localDriver);
		
		localDriver.manage().window().maximize();
		
		loginPage = baseCode.pageLander();
		
		contactUsPage = new ContactUsPage(localDriver);
		
		testCasesPage = new TestCasesPage(localDriver);
	}

	@AfterMethod
	public void browserKiller() {
		
		WebDriverManager.quitDriver();
	}

	// Optional getter if you want to use driver directly in child classes
	public WebDriver getDriver() {
		
		return WebDriverManager.getDriver();
	}
}