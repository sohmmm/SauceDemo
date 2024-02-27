package com.saucedemo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Sheet;
import com.saucedemo.commonbase.CommonBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.LogoutPage;
import com.saucedemo.pages.NavbarPage;
import com.saucedemo.pages.ProductDetailsPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.Product;
import com.saucedemo.utils.Utils;

public class ProductsTest extends CommonBase {
	private LoginPage loginPage;
	private ProductsPage productsPage;
	private ProductDetailsPage productDetailsPage;
	private CheckoutPage checkoutPage;
	private NavbarPage navbarPage;
	private CartPage cartPage;
	private LogoutPage logoutPage;

//	Sheets
	private Sheet productsDetailsSheet;
	private Sheet checkoutDetailsSheet;

	private Product[] productsToAdd;

	@BeforeSuite
	public void setUp() {
		initialize();
		launchBrowser();
	}

	@BeforeTest
	public void launch() {
		loginPage = new LoginPage();
		productsPage = new ProductsPage();
		productDetailsPage = new ProductDetailsPage();
		checkoutPage = new CheckoutPage();
		navbarPage = new NavbarPage();
		cartPage = new CartPage();
		logoutPage = new LogoutPage();

		productsDetailsSheet = Utils.getDataFromExcel("products_details.xlsx", "Products Details");
		checkoutDetailsSheet = Utils.getDataFromExcel("checkout_details.xlsx", "Checkout details");

		productsToAdd = new Product[productsDetailsSheet.getPhysicalNumberOfRows() - 1];
		for (int i = 1; i <= productsToAdd.length; i++) {
			productsToAdd[i - 1] = new Product(productsDetailsSheet.getRow(i).getCell(0).getStringCellValue(), "",
					productsDetailsSheet.getRow(i).getCell(2).getStringCellValue());
		}
	}

	@Test(priority = 1, enabled = true)
	public void testProductListingPageUrl() {
		String username = "standard_user";
		String password = "secret_sauce";

		loginPage.login(username, password);

		String expectedProductsUrl = "https://www.saucedemo.com/inventory.html";
		String actualProductUrl = getDriver().getCurrentUrl();

		Assert.assertEquals(actualProductUrl, expectedProductsUrl);
	}

	@Test(priority = 2, dependsOnMethods = { "testProductListingPageUrl" })
	public void testProductDetailsPageUrl() {
		productsPage.openProductDetails(productsToAdd[0].getName(), productsToAdd[0].getPrice());

		String expectedDetailsUrl = "https://www.saucedemo.com/inventory-item.html";
		String actualDetailsUrl = getDriver().getCurrentUrl();
		boolean status = actualDetailsUrl.contains(expectedDetailsUrl);

		Assert.assertTrue(status, "Not product details page");
	}

	@Test(priority = 3, dependsOnMethods = { "testProductDetailsPageUrl" })
	public void testProductIsSame() {
		String actualTitle = productDetailsPage.getProductName();
		String actualPrice = productDetailsPage.getPrice();

		Assert.assertEquals(actualTitle, productsToAdd[0].getName());
		Assert.assertEquals(actualPrice, productsToAdd[0].getPrice());
	}

	@Test(priority = 4, dependsOnMethods = { "testProductIsSame" })
	public void testCartItemsCountBeforeCheckout() {
		productDetailsPage.clickAddToCartButton();
		productDetailsPage.clickBackToProductsButton();
//
		productsPage.addProductToCart(productsToAdd[1].getName(), productsToAdd[1].getPrice());
//
		int expectedCount = productsToAdd.length;
		int actualCount = navbarPage.getCartItemCount();
		Assert.assertEquals(actualCount, expectedCount);
	}

	@Test(priority = 5, dependsOnMethods = { "testCartItemsCountBeforeCheckout" })
	public void testProductsInCartSameAsAdded() {
		navbarPage.clickCartIcon();

		Product[] actualProducts = cartPage.getAllProducts();
		for (int i = 0; i < actualProducts.length && i < productsToAdd.length; i++) {
			String expectedProductName = productsToAdd[i].getName(), expectedProductPrice = productsToAdd[i].getPrice();
			String actualProductName = actualProducts[i].getName(), actualProductPrice = actualProducts[i].getPrice();

			Assert.assertEquals(actualProductName, expectedProductName);
			Assert.assertEquals(actualProductPrice, expectedProductPrice);
		}
	}

	@Test(priority = 6, dependsOnMethods = { "testProductsInCartSameAsAdded" })
	public void testCheckoutAmount() {
		double expectedSubTotal = cartPage.getTotalPrice();
		cartPage.clickCheckoutButton();

		String firstName = checkoutDetailsSheet.getRow(1).getCell(0).getStringCellValue();
		String lastName = checkoutDetailsSheet.getRow(1).getCell(1).getStringCellValue();
		String pincode = checkoutDetailsSheet.getRow(1).getCell(2).toString();

		checkoutPage.enterCheckoutDetails(firstName, lastName, pincode);
		checkoutPage.clickContinueButton();

		double actualSubTotal = checkoutPage.getSubTotal();
		double actualTax = checkoutPage.getTax();

		double expectedGrandTotal = Utils.add(expectedSubTotal, actualTax);
		double actualGrandTotal = checkoutPage.getGrandTotal();

		Assert.assertEquals(actualSubTotal, expectedSubTotal);
		Assert.assertEquals(actualGrandTotal, expectedGrandTotal);
	}

	@Test(priority = 7, dependsOnMethods = { "testCheckoutAmount" })
	public void testCheckoutMessage() {
		checkoutPage.clickFinishButton();

		String expectedCheckoutMsg = "Thank you for your order!";
		String actualCheckoutMsg = checkoutPage.getCheckoutCompleteMessage();

		Assert.assertEquals(actualCheckoutMsg, expectedCheckoutMsg);
	}

	@Test(priority = 8, dependsOnMethods = { "testCheckoutMessage" })
	public void testCartItemsCountAfterCheckout() {
		navbarPage.clickCartIcon();
		int actualItemCount = cartPage.getCartItemCount();
		int expectedItemCount = 0;

		Assert.assertEquals(actualItemCount, expectedItemCount);
	}

	@Test(priority = 9, dependsOnMethods = { "testCartItemsCountAfterCheckout" })
	public void testLogoutSuccess() {
		navbarPage.clickBurgerMenu();
		boolean status = logoutPage.logout();

		Assert.assertTrue(status, "Logout failed!");
	}

	@AfterSuite
	public void tearDown() {
		getDriver().quit();
	}
}
