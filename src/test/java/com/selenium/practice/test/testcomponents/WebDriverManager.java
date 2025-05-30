package com.selenium.practice.test.testcomponents;

import org.openqa.selenium.WebDriver;

//Using this class to make the web driver instance thread safe
public class WebDriverManager {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        
    	return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        
    	driver.set(driverInstance);
    }

    public static void quitDriver() {
    	
    	WebDriver d = driver.get();
        
    	if (d != null) {
            
    		d.quit();
            
    		driver.remove();
        }
    }
}
