package com.onestop.isalbi.objects;

public class CheckoutPageObjects {
	
	public static String customcondition = "xpath#//input[@id='customCondition']";
	public static String placeorderbutton = "xpath#//button[@class='action primary checkout btn-placeorder']//span[text()='Place Order']";
	public static String cardnumber = "xpath#//div[@class='CardNumberField-input-wrapper']//span";
	public static String expirydate = "xpath#//div[@id='stripe-payments-card-expiry']";
	public static String cvc = "xpath#//input[@placeholder='CVC']";
	public static String frameswitch = "xpath#//iframe[@title='Secure card number input frame']";

}
