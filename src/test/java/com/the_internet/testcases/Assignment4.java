package com.the_internet.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.shared.commonbase.CommonBase;
import com.the_internet.pages.HoversPage;
import junit.framework.Assert;

public class Assignment4 extends CommonBase {
	private HoversPage hoversPage;
	
	@BeforeSuite
	public void setup() {
		initialize();
	}
	
	@BeforeClass
	public void launch() {
		hoversPage = new HoversPage();
		
		String url = "https://the-internet.herokuapp.com/hovers";
		launchBrowser(url);
	}
	
	@Test (priority = 1)
	public void testHover() {
		int itemNumber = 2;
		
		String expectedDetails = "user2";
		String actualDetails = hoversPage.hoverOnProfile(itemNumber);
		
		Assert.assertEquals(actualDetails, expectedDetails);
		
		hoversPage.clickViewProfile(itemNumber);
		String expectedUrl = "https://the-internet.herokuapp.com/users/2";
		String actualUrl = getDriver().getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@AfterSuite
	public void tearDown() {
		getDriver().quit();
	}
}