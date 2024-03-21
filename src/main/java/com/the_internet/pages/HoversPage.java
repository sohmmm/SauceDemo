package com.the_internet.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import com.shared.commonbase.CommonBase;

public class HoversPage extends CommonBase {

	@FindBy(className = "figure")
	private List<WebElement> figures;

	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]")
	private WebElement secondProfile;

	public HoversPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public String hoverOnProfile(int figureNumber) {
		if (figureNumber > figures.size())
			return "";
		
		Actions action = new Actions(getDriver());
		
		WebElement element = figures.get(figureNumber - 1);
		action.moveToElement(element).perform();
		
		String text = element.findElement(By.tagName("h5")).getText();
		
		return text.split(" ")[1];
	}
	
	public void clickViewProfile(int profileNumber) {
		if (profileNumber > figures.size())
			return;
		
		WebElement viewProfileLink = figures.get(profileNumber - 1).findElement(By.tagName("a"));
		viewProfileLink.click();
	}
}
