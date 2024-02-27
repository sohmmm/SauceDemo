package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.commonbase.CommonBase;
import com.saucedemo.utils.Product;

public class ProductsPage extends CommonBase {
	@FindBy(className = "inventory_item")
	private List<WebElement> allProducts;

	@FindBy(className = "inventory_item_price")
	private WebElement sauceLabsBackpackPrice;

	@FindBy(id = "add-to-cart-sauce-labs-bike-light")
	private WebElement bikeLightAddToCartButton;

	public ProductsPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	private WebElement getProduct(String name, String price) {
		for (WebElement element : allProducts) {
			String[] splittedText = element.getText().split("\n");
			String currentName = splittedText[0];
			String currentPrice = splittedText[2];

			if (name.equals(currentName) && price.equals(currentPrice)) {
				return element;
			}
		}

		return null;
	}
	
	public void addProductToCart(String name, String price) {
		WebElement item = getProduct(name, price);

		if (null != item) {
			WebElement addToCart = item.findElement(By.tagName("button"));
			addToCart.click();
		}
	}

	public void removeProductFromCart(String name, String price) {
		WebElement product = getProduct(name, price);

		if (null != product) {
			WebElement removeFromCartButton = product.findElement(By.tagName("button"));
			removeFromCartButton.click();
		}
	}
	
	public void openProductDetails(String name, String price) {
		WebElement product = getProduct(name, price);

		if (null != product) {
			WebElement linkToDetails = product.findElement(By.tagName("a"));
			linkToDetails.click();
		}
	}
}
