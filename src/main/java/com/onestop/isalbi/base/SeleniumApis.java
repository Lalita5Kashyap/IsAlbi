/**
 * 
 */
package com.onestop.isalbi.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

/**
 * @author Lalita Kashyap
 *
 */
public class SeleniumApis {
	public static String element;
	public static WebElement webElement;
	public static List<WebElement> webElementsList;
	public static int length;

	/*************************************************************************
	 * Objective: Wrapper for the actions performed on the browser Parameters:
	 * action (String), locator (String) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/

	public static void performAction(String action, String locator) {
		WebElement element = null;
		if (locator.indexOf("#//") > 0 && !action.contains("assert")) {
			element = findElement(Constants.driver, locator);

		}
		switch (action) {
		case "javaScriptExecutorClick":
			SeleniumApis.javascriptExecutorClick(Constants.driver, element);
			break;
		case "javaScriptExecutorMouseHover":
			SeleniumApis.javascriptExecutorMouseHover(Constants.driver, element);
			break;
		case "getWindowControl":
			SeleniumApis.getWindowControl(locator);
			break;
		case "navigate":
			SeleniumApis.navigateTo(Constants.driver, locator);
			break;
		case "scrollToElement":
			SeleniumApis.javascriptExecutorScrollToElement(Constants.driver, element);
			break;
		case "setStepExecutionDelay":
			SeleniumApis.setStepExecutionDelay(locator);
			break;
		case "setGlobalTimeOut":
			SeleniumApis.setGlobalTimeOut(locator);
			break;
		case "clearText":
			SeleniumApis.clearText(element);
			break;
		case "editText":
			SeleniumApis.editText(element);
			break;
		case "assertVisible":
			SeleniumApis.assertVisible(locator);
			break;
		case "assertNotVisible":
			SeleniumApis.assertNotVisible(locator);
			break;
		default:
			break;
		}
	}

	/*************************************************************************
	 * Objective: Wrapper for the actions performed on the browser Parameters:
	 * action (String), locator (String), value (String) Author: Pooja Bagga Updated
	 * by and when:
	 **************************************************************************/
	public static void performAction(String action, String locator, String value) {
		WebElement element = findElement(Constants.driver, locator);
		switch (action) {

		case "setValue":
			SeleniumApis.sendKeys(element, value);
			break;
		default:
			break;
		}
	}

	/*************************************************************************
	 * Objective: To pass the value to a specified Web Element Parameters: element
	 * (WebElement), value (String) Author: Pooja Bagga Updated by and when:
	 **************************************************************************/

	public static void sendKeys(WebElement element, String value) {
		try {

			// element.clear();
			element.sendKeys(value);
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
			// element.toString() + "' is present in the DOM, but not able to interact ",
			// true);

		}
	}

	/*************************************************************************
	 * Objective: To put cursor at the end of text from a web element Parameters:
	 * element (WebElement) Author: Lalita Kashyap Updated by and when:
	 * 
	 * @param
	 **************************************************************************/
	public static void editText(WebElement element) {
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.ARROW_RIGHT);
	}

	/*************************************************************************
	 * Objective: Assert if locator is not visible Parameters: OR (String) Author:
	 * Lalita Kashyap Updated by and when:
	 **************************************************************************/
	public static void assertNotVisible(String OR) {

		setGlobalTimeOut("3");
		try {
			if (!isVisible(OR)) {
				// Reports.ExtentReportLog("assert not visible", Status.PASS, "Element " +
				// element.toString() + "is not visible", false);
			} else {
				// Reports.ExtentReportLog("assert not visible", Status.FAIL, "Element " +
				// element.toString() + "is visible", true);
			}
		} catch (Exception e) {
			// Reports.ExtentReportLog("Run time error during assert not visible",
			// Status.FAIL, e.getMessage(), true);
		}
		setGlobalTimeOut("5");

	}

	/*************************************************************************
	 * Objective: To set the global time out parameter for specified seconds
	 * Parameters: delayInSeconds (String) Author: Lalita Kashyap Updated by and
	 * when:
	 **************************************************************************/
	public static void setGlobalTimeOut(String delayInSeconds) {
		Constants.globalTimeOut = Long.parseLong(delayInSeconds);
	}

	/*************************************************************************
	 * Objective: To verify if an element is visible Parameters: OR (String) Author:
	 * Lalita Kashyap Updated by and when:
	 **************************************************************************/

	public static boolean isVisible(String OR) {
		try {
			boolean isVisible;
			Thread.sleep(Constants.globalStepExecutionDelay);
			WebDriver driver = Constants.driver;
			String[] locatorType = OR.split("#");
			String xpath = locatorType[1];
			WebElement element = driver.findElement(By.xpath(xpath));
			if (element != null) {
				if (element.isDisplayed()) {
					isVisible = true;
				} else {
					isVisible = false;
				}
			} else {
				isVisible = false;
			}
			return isVisible;
		} catch (Exception e) {
			return false;

		}

	}

	/*************************************************************************
	 * Objective: Assert if the locator is visible Parameters: OR (String) Author:
	 * Lalita Kashyap Updated by and when:
	 **************************************************************************/

	public static void assertVisible(String OR) {
		try {
			if (isVisible(OR)) {
				// Reports.ExtentReportLog("Assert Visible", Status.PASS, "Element is visible",
				// true);
			} else {
				// Reports.ExtentReportLog("Assert Visible", Status.FAIL, "Element " +
				// element.toString() + " is not visible", true);
			}
		} catch (Exception e) {
			// Reports.ExtentReportLog("Run time error during assert visible", Status.FAIL,
			// e.getMessage(), true);
		}

	}

	/*************************************************************************
	 * Objective: To clear the text from a web element Parameters: element
	 * (WebElement) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/
	public static void clearText(WebElement element) {
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
	}

	/*************************************************************************
	 * Objective: To set the execution delay for a particular step Parameters:
	 * delayInMilliSeconds (String) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/
	public static void setStepExecutionDelay(String delayInMilliSeconds) {
		Constants.globalStepExecutionDelay = Long.parseLong(delayInMilliSeconds);

	}

	/*************************************************************************
	 * Objective: Java script executor to scroll to a specified element Parameters:
	 * driver (WebDriver), element (WebElement) Author: Lalita Kashyap Updated by
	 * and when:
	 **************************************************************************/
	public static void javascriptExecutorScrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	/*************************************************************************
	 * Objective: To navigate to the specified URL Parameters: driver (WebDriver),
	 * URL (String) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/
	public static void navigateTo(WebDriver driver, String URL) {
		driver.navigate().to(URL);
	}

	/*************************************************************************
	 * Objective: To get the control of the Window specified Parameters: winTitle
	 * (String) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/

	public static void getWindowControl(String winTitle) {
		WebDriver driver = Constants.driver;
		Set<String> allWindows = driver.getWindowHandles();
		try {
			for (String currentWindow : allWindows) {
				driver.switchTo().window(currentWindow);
				driver.manage().window().maximize();
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window with Title '" + winTitle + "' is not available");
			e.printStackTrace();
		}
	}

	/*************************************************************************
	 * Objective: Java script executor for mouse hover Parameters: driver
	 * (WebDriver), element (WebElement) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/
	public static void javascriptExecutorMouseHover(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String strJavaScript = "var element = arguments[0];"
					+ "var mouseEventObj = document.createEvent('MouseEvents');"
					+ "mouseEventObj.initEvent('mouseOver', true, true);" + "element.dispatchEvent(mouseEventObj);";
			js.executeScript(strJavaScript, element);
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
			// element.toString() + "is present in the DOM but not able to interact", true);
		}
	}

	/*************************************************************************
	 * Objective: Java script executor for click on web element Parameters: driver
	 * (WebDriver), element (WebElement) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/

	public static void javascriptExecutorClick(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
			// element.toString() + "' is available in the DOM but not available for
			// interaction", true);
		}

	}

	/*************************************************************************
	 * Objective: To find the element for a specified locator Parameters: driver
	 * (WebDriver), locator (String) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/

	public static WebElement findElement(WebDriver driver, String locator) {

		try {
			try {
				Thread.sleep(Constants.globalStepExecutionDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
			String[] locatorType = locator.split("#");
			String type = locatorType[0];
			String OR = locatorType[1];
			WebDriverWait wait = new WebDriverWait(driver, Constants.globalTimeOut);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR)));
			switch (type) {
			case "id":
				webElement = driver.findElement(By.id(OR));
				break;
			case "xpath":
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].removeAttribute('disabled','disabled')",
						driver.findElement(By.xpath(OR)));
				js.executeScript("arguments[0].setAttribute('style','border: 2px solid red;');",
						driver.findElement(By.xpath(OR)));
				webElement = driver.findElement(By.xpath(OR));
				break;
			case "className":
				webElement = driver.findElement(By.className(OR));
				break;
			case "tagName":
				webElement = driver.findElement(By.tagName(OR));
				break;
			case "linktext":
				webElement = driver.findElement(By.linkText(OR));
				break;
			case "name":
				webElement = driver.findElement(By.name(OR));
				break;
			}
			return webElement;
		} catch (NoSuchElementException e) {
			e.printStackTrace();

			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
			// locator + "' is not found in the application", true);
			try {
				// BaseTest.afterTest();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
			// locator + "' is not visible in the application", true);
			try {
				// BaseTest.afterTest();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		} catch (TimeoutException e) {
			e.printStackTrace();
			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
			// locator + "' is not found in the specified time", true);
			try {
				// BaseTest.afterTest();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}

	/*************************************************************************
	 * Objective: To capture the screenshot and place it in specified location
	 * appending timestamp to it Parameters: driver (WebDriver), Node (String)
	 * Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/

	public static String CaptureScreenShot(WebDriver driver, String Node) {
		String newPath = null;
		try {
			String location = Node;
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = Constants.ResultPath;
			newPath = path + File.separator + location + "_" + timeStamp() + ".png";
			FileUtils.copyFile(src, new File(newPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newPath;

	}

	/*************************************************************************
	 * Objective: To get the current timestamp Parameters: None Author: Lalita
	 * Kashyap Updated by and when:
	 **************************************************************************/

	public static String timeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());

	}

	/*************************************************************************
	 * Objective: To click the specified web element Parameters: element
	 * (WebElement) Author: Lalita Kashyap Updated by and when:
	 * 
	 * @throws InterruptedException
	 **************************************************************************/

	public static void clickWebElement(String locator) throws InterruptedException {
		WebDriver driver = Constants.driver;
		String[] locatorType = locator.split("#");
		String path = locatorType[1];
		try {
			WebElement element = driver.findElement(By.xpath(path));
			Thread.sleep(2000);
			element.click();
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
			// element.toString() + "' is present in the DOM, but not able to interact ",
			// true);
		}
	}

	/*************************************************************************
	 * Objective: Halts or sleeps the execution thread by the time specified
	 * Parameters: None Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/
	public static void executionDelay() {
		try {
			Thread.sleep(Constants.defaultGlobalTimeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*************************************************************************
	 * Objective: To press enter key Parameters: None Author: Lalita Kashyap Updated
	 * by and when:
	 **************************************************************************/
	public static void enterWebElement(String locator) throws InterruptedException {
		WebDriver driver = Constants.driver;
		String[] locatorType = locator.split("#");
		String path = locatorType[1];
		{
			try {
				WebElement element = driver.findElement(By.xpath(path));
				Thread.sleep(2000);
				element.sendKeys(Keys.ENTER);
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
				// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL, "Element '" +
				// element.toString() + "' is present in the DOM, but not able to interact ",
				// true);
			}
		}
	}

	/*************************************************************************
	 * Objective: To get the text of specified element for all the locator types
	 * Parameters: driver (WebDriver), type (String), OR (String) Author: Lalita
	 * Kashyap Updated by and when:
	 **************************************************************************/

	// Get text
	public static String getText(WebDriver driver, String type, String OR) {
		String text = "";
		String[] locatorType = OR.split("#");
		String path = locatorType[1];
		// String type = locatorType[0];
		switch (type) {
		case "id":
			text = driver.findElement(By.id(path)).getText();
			break;
		case "xpath":
			text = driver.findElement(By.xpath(path)).getText();
			break;
		case "className":
			text = driver.findElement(By.className(path)).getText();
			break;
		case "tagName":
			text = driver.findElement(By.tagName(path)).getText();
			break;
		case "linktext":
			text = driver.findElement(By.linkText(path)).getText();
			break;
		case "name":
			text = driver.findElement(By.name(path)).getText();
			break;
		}
		return text;
	}
	/*************************************************************************
	 * Objective: To switch to the specified frame
	 * Parameters: frameTitle (String)
	 * Author: Lalita Kashyap
	 * Updated by and when:
	 * @throws InterruptedException 
	 **************************************************************************/

	public static void switchFrame(String locator) throws InterruptedException {
		WebDriver driver = Constants.driver;
		String[] locatorType = locator.split("#");
		String path = locatorType[1];
		try {
			WebElement element=driver.findElement(By.xpath(path));
			Thread.sleep(2000);
			driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	/*************************************************************************
	 * Objective: To switch to the default frame
	 * Parameters: None
	 * Author: Pooja Bagga
	 * Updated by and when:
	 **************************************************************************/

	public static void switchToDefaultFrame() {
		WebDriver driver = Constants.driver;
		try {
			driver.switchTo().defaultContent();
		} catch (NoSuchFrameException e) {
			e.printStackTrace();;
		}
	}
}
