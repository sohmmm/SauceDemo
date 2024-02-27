package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.commonbase.CommonBase;

public class CheckoutPage extends CommonBase {
	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(id = "finish")
	private WebElement finishButton;

	@FindBy(id = "first-name")
	private WebElement firstNameInputField;

	@FindBy(id = "last-name")
	private WebElement lastNameInputField;

	@FindBy(id = "postal-code")
	private WebElement zipCodeInputField;

	@FindBy(className = "complete-header")
	private WebElement checkoutCompleteMessage;

	@FindBy(className = "summary_subtotal_label")
	private WebElement subTotal;

	@FindBy(className = "summary_tax_label")
	private WebElement tax;

	@FindBy(className = "summary_total_label")
	private WebElement grandTotal;

	public CheckoutPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void enterFirstName(String firstName) {
		firstNameInputField.clear();
		firstNameInputField.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lastNameInputField.clear();
		lastNameInputField.sendKeys(lastName);
	}

	public void enterZipCode(String zipCode) {
		zipCodeInputField.clear();
		zipCodeInputField.sendKeys(zipCode);
	}

	public void enterCheckoutDetails(String firstName, String lastName, String zipCode) {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterZipCode(zipCode);
	}

	public void clickContinueButton() {
		continueButton.click();
	}

	public void clickCancelButton() {
		cancelButton.click();
	}

	public void clickFinishButton() {
		finishButton.click();
	}

	public String getCheckoutCompleteMessage() {
		return checkoutCompleteMessage.getText();
	}
	
	public double splitPrice(String price) {
		return Double.parseDouble(price.split("\\$", 2)[1]);
	}

	public double getSubTotal() {
		return splitPrice(subTotal.getText().split(" ")[2]);
	}

	public double getTax() {
		return splitPrice(tax.getText().split(" ")[1]);
	}
	
	public double getGrandTotal() {
		return splitPrice(grandTotal.getText().split(" ")[1]);
	}
}