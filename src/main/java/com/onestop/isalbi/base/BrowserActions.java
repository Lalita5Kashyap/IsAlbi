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
}
