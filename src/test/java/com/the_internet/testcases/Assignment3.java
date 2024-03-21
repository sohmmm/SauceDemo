package com.the_internet.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.shared.commonbase.CommonBase;
import com.the_internet.pages.UploadPage;

public class Assignment3 extends CommonBase {
	private UploadPage uploadPage;

	@BeforeSuite
	public void setup() {
		initialize();
	}

	@BeforeClass
	public void check() {
		uploadPage = new UploadPage();
		
		String url = "https://the-internet.herokuapp.com/upload";
		launchBrowser(url);
	}

	@Test (priority = 1)
	public void testUpload() {
		String filePath = System.getProperty("user.dir")+"\\resources\\upload.txt";
		
		uploadPage.selectFile(filePath);
		uploadPage.clickUploadFile();
		
		String expectedFileName = "upload.txt";
		String actualFileName = uploadPage.getUploadedFileName();
		
		Assert.assertEquals(actualFileName, expectedFileName);
	}

	@AfterSuite
	public void tearDown() {
		getDriver().quit();
	}
}