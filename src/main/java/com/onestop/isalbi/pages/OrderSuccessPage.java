package com.onestop.isalbi.pages;

import com.onestop.isalbi.base.BrowserActions;
import com.onestop.isalbi.objects.OrderSuccessPageObjects;

public class OrderSuccessPage {
	static String ordernumber;
	
	
	public static void GetOrder() {
	ordernumber = BrowserActions.isGetText(OrderSuccessPageObjects.ordernumber);
	System.out.println("Your order number is:" +ordernumber);
}
}