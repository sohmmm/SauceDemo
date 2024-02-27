package com.saucedemo.commonbase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class CommonBase {
	private static WebDriver driver;
	
	private static Properties config;
	
	public CommonBase() {
		config = new Properties();
		
//		initialize allure properties
		System.setProperty("allure.results.directory", "/reports");
		
		String configFilePath = System.getProperty("user.dir") + "/src/main/java/com/saucedemo/config/config.properties";
		
		try {
			config.load(new FileInputStream(configFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		if (CommonBase.driver != null) {
			return;
		}
		
		String currentBroswer = config.getProperty("browser");
		WebDriver driver = null;
		
		if (currentBroswer.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (currentBroswer.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (currentBroswer.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new SafariDriver();
		}
		
		CommonBase.driver = driver;
	}
	
	public void launchBrowser() {
		String baseUrl = config.getProperty("baseUrl");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(baseUrl);
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public Properties getConfig() {
		return config;
	}
}

