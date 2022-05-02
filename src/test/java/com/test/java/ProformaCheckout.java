package com.test.java;

import org.testng.annotations.Test;

import com.onestop.isalbi.base.BaseTest;
import com.onestop.isalbi.pages.CheckoutPage;
import com.onestop.isalbi.pages.LogInPage;
import com.onestop.isalbi.pages.ShoppingCartPage;

public class ProformaCheckout extends BaseTest {

	@Test(testName = "TC000 - Launch Application", description = "Launch Application Homepage", priority = 1)
	public void LaunchApplication() {

		LogInPage.SFLaunch("Checkout flow for Proforma customer");
	}

	/**@Test(testName = "TC001 - Login", description = "Login", priority = 2)
	public void LogInwithAccountNumberandPassword() {

		LogInPage.SFLogin("Checkout flow for Proforma customer");
	}

	@Test(testName = "TC002 - Search SKU", description = "Search SKU", priority = 3)
	public void SearchSKU() {

		LogInPage.SearchProductSKU("Checkout flow for Proforma customer");
	}
	@Test(testName = "TC003 - Add to cart product", description = "Add product", priority = 4)
	public void Addproduct() {

		LogInPage.Addproductincart();
	}

	@Test(testName = "TC004 - Click on mini cart", description = "Click Mini Cart", priority = 5)
	public void Clickminicart() {

		LogInPage.ClickonMiniCart();
	}

	@Test(testName = "TC005 - Click on Proceed to checkout", description = "Click proceed to checkoutr in shopping cart", priority = 6)
	public void ProceedtoCheckout() {

		ShoppingCartPage.ClickonProceedtoCheckout();
	}
	@Test(testName = "TC006 - Enter card details", description = "Card details entered", priority = 7)
	public void EnterCCDetails() {

		CheckoutPage.FillCCDetails("Checkout flow for Proforma customer");
	}**/

}
