/**
 * 
 */
package com.test.java;

import org.testng.annotations.Test;

import com.onestop.isalbi.base.BaseTest;
import com.onestop.isalbi.pages.CheckoutPage;
import com.onestop.isalbi.pages.LogInPage;
import com.onestop.isalbi.pages.OrderSuccessPage;
import com.onestop.isalbi.pages.ShoppingCartPage;

/**
 * @author Lalita Kashyap
 *
 */
public class NonProformaCheckout extends BaseTest {

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

	@Test(testName = "TC006 - Click on Place order", description = "Place order", priority = 7)
	public void PlaceOrder() {

		CheckoutPage.PlaceOrder();
	}

	@Test(testName = "TC007 - Get order number", description = "Get order", priority = 8)
	public void GetOrderNumber() {

		OrderSuccessPage.GetOrder();
	}

}
