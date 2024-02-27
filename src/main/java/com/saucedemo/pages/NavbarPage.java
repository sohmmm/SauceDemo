package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.commonbase.CommonBase;

public class NavbarPage extends CommonBase {
	@FindBy (id = "react-burger-menu-btn")
	private WebElement burgerMenu;
	
	@FindBy(className = "shopping_cart_link")
	private WebElement cartIcon;

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartItemCount;
	
	public NavbarPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void clickCartIcon() {
		cartIcon.click();
	}

	public int getCartItemCount() {
		int count = Integer.parseInt(cartItemCount.getText());
		return count;
	}
	
	public void clickBurgerMenu() {
		burgerMenu.click();
	}
}
