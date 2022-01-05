package com.onestop.isalbi.pages;

import com.onestop.isalbi.base.BrowserActions;
import com.onestop.isalbi.objects.CheckoutPageObjects;


public class CheckoutPage {
	
	public static void PlaceOrder() {
		BrowserActions.isClick(CheckoutPageObjects.customcondition);
		BrowserActions.isClick(CheckoutPageObjects.placeorderbutton);
		BrowserActions.isSleep();
}
}