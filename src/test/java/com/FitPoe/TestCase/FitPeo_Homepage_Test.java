package com.FitPoe.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import com.FitPeo.Utils.ActionKeywords;
import com.FitPeo.Utils.Listener;
import com.FitPeo.Utils.TestUtils;
import com.FitPeo.base.TestBase;
import com.FitPoe.Pages.FitPeoHomepage;

@Listeners(com.FitPeo.Utils.Listener.class)
public class FitPeo_Homepage_Test extends TestBase {

	public FitPeo_Homepage_Test() throws IOException {
		super();
	}

	FitPeoHomepage fitPeoHomepage;
	TestUtils testUtils;
	ActionKeywords actionKeywords;
	JavascriptExecutor js;
	double globalTotalRecurring = 0;
	

	@BeforeTest
	public void setUp() throws IOException {

		InitializeBrowser();

		fitPeoHomepage = new FitPeoHomepage();
		testUtils = new TestUtils();
		actionKeywords = new ActionKeywords();
		testUtils.extentReport();
		js = (JavascriptExecutor) driver;

	}

	@Test(priority = 1)
	public void HomePage() throws IOException {
		testUtils.testCaseCreate("Tc_01");
		driver.get(prop.getProperty("FitPeo_Homepage_URL"));

		String actual = driver.getTitle();
		String excepted = prop.getProperty("Title");

		try {
			Assert.assertEquals(actual, excepted);
			testUtils.passTestCase("Title matched");
		} catch (Exception e) {
			testUtils.failTestCase("Title matched");
		}

	}

	@Test(priority = 2)
	public void ClickOnRevenuCalculatorTab() throws IOException {
		testUtils.testCaseCreate("Tc_02");
		fitPeoHomepage.clickOnRevenuCalculatorTab();
		testUtils.infoTestCase("click on the Revenu Calculator Tab");

	}

	@Test(priority = 3)
	public void Scroll_Down_to_the_Slider_section() throws IOException {
		testUtils.testCaseCreate("Tc_03");

		js.executeScript("window.scrollTo(0, 400);");
		testUtils.infoTestCase("Scroll Down to the Slider section");

	}

	@Test(priority = 4)
	public void Adjust_the_Slider() throws Exception {
		testUtils.testCaseCreate("Tc_04");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, 400);");
		// Here i perform slider value
		fitPeoHomepage.PerformSliding();
		Thread.sleep(1000);
		try {
			fitPeoHomepage.sliderValue();
		} catch (Exception e) {
			fitPeoHomepage.sliderValueAlternate();
		}

		try {
			if (fitPeoHomepage.isSlide())
				testUtils.passTestCase("slider Slide to 820");
		} catch (Exception e) {
			testUtils.failTestCase("Slider not slide");
		}

	}

	@Test(priority = 5)
	public void Update_the_Text_Field() throws Exception {
		testUtils.testCaseCreate("Tc_05");

		Thread.sleep(1000);
		fitPeoHomepage.updateSliderValue(prop.getProperty("updateSliderValue")); 
		try {
			fitPeoHomepage.updateSliderValue(prop.getProperty("updateSliderValue")); 
		} catch (Exception e) {
			fitPeoHomepage.updateSliderValueAlternative(prop.getProperty("updateSliderValue")); 
		}
		try {
			if (fitPeoHomepage.isSlide())
				testUtils.passTestCase("Slider Value Update");
		} catch (Exception e) {
			testUtils.failTestCase("Slider Value not Update");
		}

	}

	@Test(priority = 6)
	public void Validate_Slider_Value() throws Exception {
		testUtils.testCaseCreate("Tc_06");

		Thread.sleep(1000);
		String actual = fitPeoHomepage.SliderTextFieldValue();
		String excepted = prop.getProperty("SliderTextFieldValue");

		try {
			Assert.assertEquals(actual, excepted);
			testUtils.passTestCase("Slider's position is updated to reflect the value 560");
		} catch (Exception e) {
			testUtils.failTestCase("slider's position is not updated to reflect the value 560");
		}

	}

	@Test(priority = 7)
	public void Select_CPT_Codes() throws Exception {
		testUtils.testCaseCreate("Tc_07");
		js.executeScript("window.scrollTo(0, 300);");

		ArrayList<String> al = fitPeoHomepage.seleteTheseCPT_Value();

		List<WebElement> CPT_CodesElements = driver
				.findElements(By.xpath("//div[@class = \"MuiBox-root css-4o8pys\"]/p[1]"));

		int i = 0;
		double TotalRecurring = 0;
		String doublePattern = "^-?\\d*\\.\\d+$";
		for (WebElement CPT_CodeElement : CPT_CodesElements) {
			i++;

			String CPT_Code = CPT_CodeElement.getText();

			if (al.contains(CPT_Code)) {
				Thread.sleep(1000);
				try {
					driver.findElement(By.xpath("(//input[@class = 'PrivateSwitchBase-input css-1m9pwf3'])[" + i + "]"))
							.click();
					testUtils.passTestCase("thse checkboxes select CPT-99091, CPT-99453, CPT-99454, and CPT-99474.");
				} catch (Exception e) {
					testUtils.failTestCase(
							"some checkboxes are not select CPT-99091, CPT-99453, CPT-99454, and CPT-99474.");
				}
				// Total Recurring
				WebElement RecurringElementValue = driver.findElement(By.xpath(
						"(//span[@class = 'MuiTypography-root MuiTypography-body1 MuiFormControlLabel-label css-1s3unkt'])["
								+ i + "]"));
				String RequringValue = RecurringElementValue.getText();

				if (RequringValue.matches(doublePattern)) {
					TotalRecurring = TotalRecurring + Double.parseDouble(RequringValue);
				} else {
					TotalRecurring = TotalRecurring + Integer.parseInt(RequringValue);
				}
			}
		}
		globalTotalRecurring = TotalRecurring;

	}

	@Test(priority = 8)
	public void Validate_Total_Recurring_Reimbursement() throws Exception {
		testUtils.testCaseCreate("Tc_08");
		testUtils.infoTestCase("Total Recurring Reimbursement: " + globalTotalRecurring);
	}

	@Test(priority = 9)
	public void Total_Recurring_Reimbursement_for_all_Patients_Per_Month() throws Exception {
		testUtils.testCaseCreate("Tc_09");
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, -200);");
		fitPeoHomepage.updateSliderValue("820");
		js.executeScript("window.scrollTo(0, 500);");
		String actual = fitPeoHomepage.Total_Recurring_Reimbursement_for_all_Patients_Per_Month();
		Thread.sleep(2000);
		String excepted = prop.getProperty("Total_Recurring_Reimbursement_for_all_Patients_Per_Month");

		try {
			Assert.assertEquals(actual, excepted);
			testUtils.passTestCase("Total_Recurring_Reimbursement_for_all_Patients_Per_Month = " + actual);
		} catch (Exception e) {
			testUtils.failTestCase("Failed to match Total_Recurring_Reimbursement_for_all_Patients_Per_Month");
		}

	}

	@AfterClass
	public void AfterClass() throws IOException {
		int allTC = Listener.pass + Listener.fail;
		fitPeoHomepage.CountAllTestcase(allTC);
		testUtils.CreateHtmlTable();
	}

	@AfterSuite
	public void tearDown() {
		testUtils.ExtentFlush();
		driver.quit();
	}

}
