/**
 * 
 */
package com.onestop.isalbi.pages;

import com.onestop.isalbi.base.BrowserActions;
import com.onestop.isalbi.base.Multimaplibraries;
import com.onestop.isalbi.config.IsAlbiConstants;
import com.onestop.isalbi.objects.HomePageObjects;

/**
 * @author Lalita kashyap
 *
 */
public class LogInPage extends Multimaplibraries {
	static String className = LogInPage.class.getSimpleName();

	public static void SFLaunch(String TestCase) {
		getTestData(IsAlbiConstants.TestData, className);
		final String url = getTestDataCellValue(TestCase, "URL");
		BrowserActions.isNavigate(url);
		BrowserActions.isSleep();
	}

	public static void SFLogin(String TestCase) {
		getTestData(IsAlbiConstants.TestData, className);
		BrowserActions.isClick(HomePageObjects.myAccount);
		BrowserActions.isClick(HomePageObjects.SigIn);
		final String username = getTestDataCellValue(TestCase, "UserName");
		final String password = getTestDataCellValue(TestCase, "Password");
		BrowserActions.isSetValue(HomePageObjects.username, username);
		BrowserActions.isSetValue(HomePageObjects.password, password);
		BrowserActions.isClick(HomePageObjects.siginbutton);
		BrowserActions.isSleep();
	}

	public static void SearchProductSKU(String TestCase) {
		getTestData(IsAlbiConstants.TestData, className);
		final String productsku = getTestDataCellValue(TestCase, "Product SKU");
		BrowserActions.isSetValue(HomePageObjects.productsku, productsku);
		BrowserActions.pressEnter(HomePageObjects.productsku);
		BrowserActions.isSleep();
	}

	public static void Addproductincart() {
		BrowserActions.isClick(HomePageObjects.addtocartbutton);
		BrowserActions.isSleep();
	}

	public static void ClickonMiniCart() {
		BrowserActions.isClick(HomePageObjects.minicarticon);
		BrowserActions.isClick(HomePageObjects.vieweditcart);
		BrowserActions.isSleep();
	}

}