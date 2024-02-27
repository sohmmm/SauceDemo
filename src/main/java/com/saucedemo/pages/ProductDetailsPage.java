package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.commonbase.CommonBase;

public class ProductDetailsPage extends CommonBase {
	@FindBy(className = "inventory_details_name")
	private WebElement productTitle;

	@FindBy(className = "inventory_details_desc")
	private WebElement productDescription;

	@FindBy(className = "inventory_details_price")
	private WebElement productPrice;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addToCartButton;

	@FindBy(id = "back-to-products")
	private WebElement backToProductsButton;

	public ProductDetailsPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public String getProductName() {
		return productTitle.getText();
	}

	public String getProductDescription() {
		return productDescription.getText();
	}

	public String getPrice() {
		return productPrice.getText();
	}

	public void clickAddToCartButton() {
		addToCartButton.click();
	}

	public void clickBackToProductsButton() {
		backToProductsButton.click();
	}
}
