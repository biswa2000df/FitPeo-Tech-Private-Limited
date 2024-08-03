package com.FitPeo.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.FitPeo.base.TestBase;
import com.FitPoe.Pages.FitPeoHomepage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestUtils extends TestBase{
	
	public ExtentHtmlReporter htmlReport;
	public static ExtentReports extent;
	public static ExtentTest test;
	static String year;
	static String time;


	public TestUtils() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long pageloadtime = 20;
	public static long implicitywait = 20;

	public String takeScreenShot() throws IOException {
		Date date = new Date();
		SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd");
		year = tm.format(date);
		SimpleDateFormat tm1 = new SimpleDateFormat("hh_mm_ss");
		time = tm1.format(date);

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir") + File.separator + "Screenshot" + File.separator + year + File.separator + time + ".png";
		FileUtils.copyFile(srcFile, new File(destFile));
		return destFile;
	}

	public void ScreenshotPathFormat() {
		Date date = new Date();
		SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd");
		year = tm.format(date);
		SimpleDateFormat tm1 = new SimpleDateFormat("hh_mm_ss");
		time = tm1.format(date);
	}

	public void extentReport() throws IOException {
		ScreenshotPathFormat();
		String path = System.getProperty("user.dir") + File.separator + "REPORT_FITPEO";
		String finalPath = path + File.separator + year + File.separator ;
		new File(finalPath).mkdirs();
		htmlReport = new ExtentHtmlReporter(finalPath + "FitPeo_" + time + ".html");

		htmlReport.config().setDocumentTitle("Automation Report");// Title of the report
		htmlReport.config().setReportName("Functional Report");// Name of the report
		htmlReport.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.setSystemInfo("Comapny Name", prop.getProperty("ComapnyName"));
		extent.setSystemInfo("Project Name", "ASSIGNMENT");
		extent.setSystemInfo("Test Lead", "Prabhat Padhy");
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("OS", "Window11");
		extent.setSystemInfo("Tester Name", "Biswajit Sahoo");
		extent.setSystemInfo("Browser", prop.getProperty("BROWSER"));
		

	}

	public void testCaseCreate(String tc) {
		test = extent.createTest(tc);
	}

	public void passTestCase(String passDesc) throws IOException {
		test.log(Status.INFO, MarkupHelper.createLabel(passDesc, ExtentColor.GREEN));
		test.log(Status.PASS, "", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
	}

	public void failTestCase(String failDesc) throws IOException {
		test.log(Status.INFO, MarkupHelper.createLabel(failDesc, ExtentColor.RED));
		test.log(Status.FAIL, "", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
	}
	
	public void infoTestCase(String infoDesc) throws IOException {
		test.log(Status.INFO, MarkupHelper.createLabel(infoDesc, ExtentColor.ORANGE));
		
	}

	public void ExtentFlush() {
		extent.flush();
	}
	
	
	public static void CreateHtmlTable() throws IOException {

		try {

			String htmlTable = System.getProperty("user.dir") + File.separator + "REPORT_FITPEO" + File.separator + year + File.separator;
			
			String filename = htmlTable + "HtmlTable_Report_" + time + ".html";

			FileWriter writer = new FileWriter(filename);

			writer.write("<!DOCTYPE html>\n<html>\n<head>\n");

			writer.write("<style> table { border-collapse: collapse; width: 50%; margin: auto; margin-top: 20px; }");
			writer.write(
					" th, td { border: 1px solid black; padding: 8px; text-align: center; background-color: #E4E5E5; }");
			writer.write("th {  background-color: #E4E5E5; } </style>");
			writer.write("</head>\n<body>\n");

			writer.write("<table border=\"1\">\n");
			writer.write("<tr>"
					+ "<th style=\"text-align:center; border: 1px solid black; background-color:#4CAF50; color: white;\">Project</th>"
					+ "<th style=\"text-align:center; border: 1px solid black; background-color:#1E90FF; color: white;\">Total TCs</th>"
					+ "<th style=\"text-align:center; border: 1px solid black; background-color:#4CAF50; color: white;\">Passed TCs</th>"
					+ "<th style=\"text-align:center; border: 1px solid black; background-color:#FF6347; color: white;\">Failed TCs</th>"
					+ "</tr>");

				writer.write("<tr>" + "<td style=\"text-align:center; border: 1px solid black;\">"
						+ prop.getProperty("ComapnyName") + "</td>"
						+ "<td style=\"text-align:center; border: 1px solid black;\">" + FitPeoHomepage.allTestcase
						+ "</td>" + "<td style=\"text-align:center; border: 1px solid black;\">" + Listener.pass
						+ "</td>" + "<td style=\"text-align:center; border: 1px solid black;\">" + Listener.fail
						+ "</td></tr>");

			

			writer.write("</table>\n");
			writer.write("</body>\n</html>");
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
