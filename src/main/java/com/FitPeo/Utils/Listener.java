package com.FitPeo.Utils;

import org.testng.*;


public class Listener implements ITestListener {

	public static int pass = 0;
	public static int fail = 0;

	public void onTestSuccess(ITestResult result) {
		pass++;
	}

	public void onTestFailure(ITestResult result) {
		fail++;
	}

}
