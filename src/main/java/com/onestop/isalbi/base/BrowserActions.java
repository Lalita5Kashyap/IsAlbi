/**
 * 
 */
package com.onestop.isalbi.base;

import org.openqa.selenium.WebDriver;


/**
 * @author Lalita Kashyap
 *
 */
public class BrowserActions extends SeleniumApis{
	
	
	public static WebDriver driver = Constants.driver;

	/*************************************************************************
	 * Objective: To navigate to specified URL
	 * Parameters: URL (String)
	 * Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/
	public static void isNavigate(String URL) {
		System.out.print(URL+"\n"); 
		performAction("navigate", URL);
	}
	/*************************************************************************
	 * Objective: To Click on the specified Object
	 * Parameters: Locator (String)
	 * Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/
	public static void isClick(String locator) {
		try {
			SeleniumApis.clickWebElement( locator);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*************************************************************************
	 * Objective: Sets the object with specified value
	 * Parameters: Locator (String), Value (String)
	 * Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/

	public static void isSetValue(String locator, String value) { 
		if (value.length() > 0) {
			performAction("setValue", locator, value);
		}
	}
	/*************************************************************************
	 * Objective: Delay the execution of the thread by predefined time
	 * Parameters: None
	 * Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/
	
	public static void isSleep() {
		executionDelay();
		
	}
	/*************************************************************************
	 * Objective: To enter on specified Object
	 * Parameters: Locator (String)
	 * Author: Lalita Kashyap
	 * Updated by and when:
	 * @throws InterruptedException 
	 **************************************************************************/
	public static void pressEnter(String locator)
	{
		try {
			SeleniumApis.enterWebElement(locator);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*************************************************************************
	 * Objective: To return the text present in the object
	 * Parameters: Locator (String)
	 * Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/
	
	public static String isGetText(String locator) {
		return getText(driver, "xpath", locator);
		
	}
	public static void isFrameSwitch(String locator)
	{
		try {
			SeleniumApis.switchFrame(locator);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void isdefaultFrameSwitch()
	{
		SeleniumApis.switchToDefaultFrame();
	}
}
