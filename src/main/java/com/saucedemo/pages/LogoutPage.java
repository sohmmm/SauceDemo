package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.commonbase.CommonBase;

public class LogoutPage extends CommonBase {
	@FindBy (id = "logout_sidebar_link")
	private WebElement logoutButton;
	
	public LogoutPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void clickLogoutButton() {
		logoutButton.click();
	}
	
	public boolean logout() {
		clickLogoutButton();
		
		return getConfig().getProperty("baseUrl").equals(getDriver().getCurrentUrl());
	}
}