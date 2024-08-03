package com.FitPoe.Pages;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FitPeo.Utils.ActionKeywords;
import com.FitPeo.base.TestBase;

public class FitPeoHomepage extends TestBase{

	public FitPeoHomepage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	ActionKeywords actionKeywords = new ActionKeywords();
	
	@FindBy(xpath="//div[contains(text(),'Revenue Calculator')]")
	WebElement ClickOnRevenuCalculatorTab;
	
	public void clickOnRevenuCalculatorTab() {
		actionKeywords.clickElement(ClickOnRevenuCalculatorTab);
	}
	

	public static int allTestcase = 0;
	
	@FindBy(xpath="//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-sy3s50']")
	WebElement Slider;
	

public void scroll(JavascriptExecutor js) {
	js.executeScript("arguments[0].scrollIntoView(true);", Slider);
}
	
	public void PerformSliding() {
		Actions act = new Actions(driver);
		act.dragAndDropBy(Slider, 94, 0).perform();
	}
	
	public boolean isSlide() {
		return actionKeywords.isDisplay(Slider);
	}
	
	
	@FindBy(xpath = "//input[@id = ':r0:']")
	WebElement sliderTextField;

	
	public void sliderValue() {
		
		actionKeywords.clickElement(sliderTextField);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", sliderTextField);
		  actionKeywords.sendKeysElement(sliderTextField, "820");	
	}
	
	@FindBy(xpath = "//input[@id = ':r1:']")
	WebElement sliderTextFieldAlternate;

	
	public void sliderValueAlternate() {
		
		actionKeywords.clickElement(sliderTextFieldAlternate);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", sliderTextFieldAlternate);
		  actionKeywords.sendKeysElement(sliderTextFieldAlternate, "820");	
	}
	
	
	
	public void updateSliderValue(String value) {	
		actionKeywords.clickElement(sliderTextField);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", sliderTextField);
		  actionKeywords.sendKeysElement(sliderTextField, value);	
	}
	
	public void updateSliderValueAlternative(String value) {	
		actionKeywords.clickElement(sliderTextFieldAlternate);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", sliderTextFieldAlternate);
		  actionKeywords.sendKeysElement(sliderTextFieldAlternate, value);	
	}
	
	
	@FindBy(xpath = "//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-sy3s50']//input")
	WebElement sliderTextFieldValue;
	
	public String SliderTextFieldValue() {
		return actionKeywords.GetAttribute(sliderTextFieldValue);
	}
	
	public ArrayList seleteTheseCPT_Value() {
	    ArrayList<String> al = new ArrayList<String>();
        al.add("CPT-99091");
        al.add("CPT-99453");
        al.add("CPT-99454");
        al.add("CPT-99474");
        return al;
	}
	
	
	@FindBy(xpath = "(//p[@class = 'MuiTypography-root MuiTypography-body1 inter css-hocx5c'])[4]")
	WebElement all_Patients_Per_Month;
	
	public String Total_Recurring_Reimbursement_for_all_Patients_Per_Month() {
		actionKeywords.clickElement(all_Patients_Per_Month);
		return actionKeywords.GetText(all_Patients_Per_Month);
	}
	
	public void CountAllTestcase(int allTest) {
		allTestcase = allTest;
	}

	
	
}
