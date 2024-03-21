package com.the_internet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.shared.commonbase.CommonBase;

public class UploadPage extends CommonBase {
	
	@FindBy(id = "file-upload")
	WebElement fileUpload;
	
	@FindBy (id = "file-submit")
	private WebElement fileSubmit;
	
	@FindBy (id = "uploaded-files")
	private WebElement uploadedFile;
	
	public UploadPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void selectFile(String filePath) {
		fileUpload.sendKeys(filePath);
	}
	
	public void clickUploadFile() {
		fileSubmit.click();
	}
	
	public String getUploadedFileName() {
		return uploadedFile.getText();
	}
}
