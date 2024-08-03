package com.FitPeo.Utils;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.FitPeo.base.TestBase;

public class ActionKeywords extends TestBase {

	public ActionKeywords() throws IOException {
		super();
	}

	public static boolean isDisplay(WebElement ele) {
		return ele.isDisplayed();
	}

	public static void clickElement(WebElement ele) {
		ele.click();
	}

	public static void sendKeysElement(WebElement ele, String element) {
		ele.sendKeys(element);
	}
	
	public String GetAttribute(WebElement ele) {
		return ele.getAttribute("value");
	}
	
	public String GetText(WebElement ele) {
		return ele.getText();
	}

	

}
