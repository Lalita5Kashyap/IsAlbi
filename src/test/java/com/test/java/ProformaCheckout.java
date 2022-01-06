package com.test.java;

import org.testng.annotations.Test;

import com.onestop.isalbi.base.BaseTest;
import com.onestop.isalbi.pages.LogInPage;

public class ProformaCheckout extends BaseTest {

	@Test(testName = "TC000 - Launch Application", description = "Launch Application Homepage", priority = 1)
	public void LaunchApplication() {

		LogInPage.SFLaunch("LaunchApplication");
	}

	@Test(testName = "TC001 - Login", description = "Login", priority = 2)
	public void LogInwithAccountNumberandPassword() {

		LogInPage.SFLogin("LaunchApplication");
	}

	@Test(testName = "TC002 - Search SKU", description = "Search SKU", priority = 3)
	public void SearchSKU() {

		LogInPage.SearchProductSKU("LaunchApplication");
	}

}
