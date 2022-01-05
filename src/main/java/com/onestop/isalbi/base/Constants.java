/**
 * 
 */
package com.onestop.isalbi.base;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * @author Lalita Kashyap
 *
 */
public abstract class Constants {
	
	public static String ResultPath;
	public static String CurrentWindow;
	public static String testDataOutputPath = "";
	public static String suiteName;
	public static String testCaseName;
	public static String testDataPath = "";
	public static String prodConfigPropertyFilePath = "";
	public static String testName;
	public static String GlobalConfigPropertyFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "onestop" + File.separator + "isalbi" + File.separator + "config" + File.separator + "ConfigurationSettings.xlsx";
	public static String recordingPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "onestop" + File.separator + "isalbi" + File.separator + "config" + File.separator + "resources";
	public static String ffmpegPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "intershop" + File.separator + "config" + File.separator + "resources";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static MultiMap<String, ?> testData = new MultiValueMap();
	
	//WebDriver
		public static WebDriver driver;

		//TimeToWait
		public static long globalTimeOut;
		public static long globalStepExecutionDelay;
		public static long defaultGlobalTimeout;
		public static long defaultGlobalStepExecutionDelay;
		
		//Extent Report
		public static ExtentHtmlReporter htmlReporter;
		public static ExtentTest extentTest;
		public static ExtentReporter report;
		public static ExtentReports extentReport;
		
		//Logger
		public static Logger logger;
		public static FileHandler FH = null;
}
