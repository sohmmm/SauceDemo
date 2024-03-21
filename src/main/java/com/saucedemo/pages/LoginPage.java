package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.shared.commonbase.CommonBase;

public class LoginPage extends CommonBase {
	@FindBy(id = "user-name")
	private WebElement usernameElement;

	@FindBy(id = "password")
	private WebElement passwordElement;
	
	@FindBy(id = "login-button")
	private WebElement loginButton;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void enterUsername(String username) {
		usernameElement.clear();
		usernameElement.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordElement.clear();
		passwordElement.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public boolean login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		
		clickLogin();
		
		return !getConfig().getProperty("baseUrl").equals(getDriver().getCurrentUrl());
	}
}