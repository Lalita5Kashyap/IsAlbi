package com.onestop.isalbi.pages;

import com.onestop.isalbi.base.BrowserActions;
import com.onestop.isalbi.objects.ShoppingCartPageObjects;


public class ShoppingCartPage {
	
	public static void ClickonProceedtoCheckout() {
		BrowserActions.isClick(ShoppingCartPageObjects.vieweditcart);
		BrowserActions.isSleep();
}
}