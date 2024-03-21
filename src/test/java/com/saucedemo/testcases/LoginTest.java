package com.saucedemo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.shared.commonbase.CommonBase;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.LogoutPage;
import com.saucedemo.pages.NavbarPage;
import com.shared.utils.Utils;

public class LoginTest extends CommonBase {
	private LoginPage loginPage;
	private LogoutPage logoutPage;
	private NavbarPage navbarPage;
	
	
	@BeforeSuite
	public void setup() {
		initialize();
		launchBrowser(null);
	}
	
	@BeforeTest
	public void launch() {
		loginPage = new LoginPage();
		logoutPage = new LogoutPage();
		navbarPage = new NavbarPage();
	}
	
	@DataProvider(name = "LoginCredentials")
	public String[][] loginCredentials() {
		String[][] data = Utils.getExcelDataAsArray("user_credentials.xlsx", "Credentials");

		return data;
	}
	
	@Test (priority = 1, dataProvider = "LoginCredentials")
	public void testLogin(String username, String password) {		
		boolean loginSucess = loginPage.login(username, password);
		
		if (loginSucess) {
			navbarPage.clickBurgerMenu();
			logoutPage.logout();
		}
		
		Assert.assertTrue(loginSucess, "Failed to login:");
	}
	
	@AfterSuite
	public void tearDown() {
		getDriver().quit();
	}
}