package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.shared.commonbase.CommonBase;
import com.shared.utils.Product;

public class CartPage extends CommonBase {
	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	@FindBy(className = "cart_item")
	private List<WebElement> cartItems;

	public CartPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void clickCheckoutButton() {
		checkoutButton.click();
	}

	public int getCartItemCount() {
		return cartItems.size();
	}

	public Product[] getAllProducts() {
		int totalItems = getCartItemCount();
		Product[] products = new Product[totalItems];

		if (totalItems <= 0)
			return products;

		for (int i = 0; i < totalItems; i++) {
			WebElement item = cartItems.get(i);

			String[] details = item.getText().split("\n");

			products[i] = new Product(details[1], details[2], details[3]);
		}

		return products;
	}

	public double getTotalPrice() {
		Product[] allProducts = getAllProducts();
		
		double total = 0.0;
		for (Product product : allProducts) {
			double currentPrice = Double.parseDouble(product.getPrice().split("\\$", 2)[1]);
			
			total += currentPrice;
		}

		return total;
	}
}
