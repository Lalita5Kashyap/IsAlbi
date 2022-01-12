package com.onestop.isalbi.pages;

import com.onestop.isalbi.base.BrowserActions;
import com.onestop.isalbi.base.Multimaplibraries;
import com.onestop.isalbi.config.IsAlbiConstants;
import com.onestop.isalbi.objects.CheckoutPageObjects;



public class CheckoutPage extends Multimaplibraries {
	static String className = LogInPage.class.getSimpleName();

	public static void PlaceOrder() {
		BrowserActions.isClick(CheckoutPageObjects.customcondition);
		BrowserActions.isSleep();
		BrowserActions.isClick(CheckoutPageObjects.placeorderbutton);
		BrowserActions.isSleep();
	}
	
	public static void FillCCDetails(String TestCase) {
		getTestData(IsAlbiConstants.TestData, className);
		final String CardNumber = getTestDataCellValue(TestCase, "Card Number");
		System.out.println(CardNumber);
		final String ExpiryDate = getTestDataCellValue(TestCase, "Expiry Date");
		System.out.println(ExpiryDate);
		final String cvc = getTestDataCellValue(TestCase, "CVC");
		System.out.println(cvc);
		BrowserActions.isSleep();
		BrowserActions.isFrameSwitch(CheckoutPageObjects.frameswitch);
		BrowserActions.isSleep();
		//BrowserActions.isdefaultFrameSwitch();
		
		BrowserActions.isSetValue(CheckoutPageObjects.cardnumber, CardNumber);
		BrowserActions.isSetValue(CheckoutPageObjects.expirydate, ExpiryDate);
		BrowserActions.isSetValue(CheckoutPageObjects.cvc, cvc);
	}
	
}