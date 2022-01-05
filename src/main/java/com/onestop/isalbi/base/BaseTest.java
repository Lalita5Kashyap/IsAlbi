package com.onestop.isalbi.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.onestop.isalbi.config.IsAlbiConstants;
import com.onestop.isalbi.util.EmailUtil;
import com.onestop.isalbi.util.ExcelReader;
import com.onestop.isalbi.util.Reports;

/**
 * @author Lalita Kashyap
 *
 */
public class BaseTest extends Driver {

	public static WebDriver driver;
	public static ExtentTest extentTest;
	public static ExtentReports extentReport;

	/*************************************************************************
	 * Objective: Before Suite processing Parameters: context (ITestContext) Author:
	 * Lalita Kashyap Updated by and when:
	 **************************************************************************/
	@BeforeSuite
	public void beforeSuite(ITestContext context) {

		Constants.suiteName = getSuiteName(context);
		Constants.ResultPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator
				+ "ExtentReports" + File.separator + Reports.currentTime();
		ExcelReader excel = new ExcelReader(getConfigFilePath(this.getClass().getSimpleName().toString()));
		Constants.logger = Logger.getLogger(Constants.suiteName);
		FileHandler fh;

		try {
			String logFileName = System.getProperty("user.dir") + "\\logs\\log_" + Reports.currentTime() + ".log";
			File file = new File(logFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.close();
			file.createNewFile();
			fh = new FileHandler(logFileName);
			Constants.FH = fh;
			Constants.logger.addHandler(fh);
			Constants.logger.setLevel(Level.FINER);
			fh.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String Recording = excel.getCellData("Config", 2, "RecordExecution");
		if (Recording.equalsIgnoreCase("Yes")) {
			try {
				String outpufilepath = Constants.ResultPath;
				String datetime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

				FileWriter fw = new FileWriter(Constants.recordingPath + "\\StartRecording.bat");
				fw.write("Start " + Constants.ffmpegPath
						+ "\\ffmpeg.exe -f gdigrab -i desktop -framerate 10 -vcodec libx264 " + outpufilepath
						+ "\\SeleniumExecution_" + datetime + ".avi");
				fw.close();
				File dir = new File(Constants.recordingPath);

				ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", "StartRecording.bat");
				// pb.directory(dir);
				pb.start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		extentReport = Reports.startReport();
		Constants.extentReport = extentReport;
	}

	/*************************************************************************
	 * Objective: To get the configuration details based on the test names created
	 * Parameters: testName (String) Author: Lalita Kashyap Updated by and when:
	 **************************************************************************/
	public static String getConfigFilePath(String testName) {
		String configPath = "";

		configPath = IsAlbiConstants.ConfigPropertyFilePath;
		Constants.testDataOutputPath = IsAlbiConstants.TestDataOutput;
		Constants.testDataPath = IsAlbiConstants.TestData;

		Constants.prodConfigPropertyFilePath = configPath;
		return configPath;
	}

	/*************************************************************************
	 * Objective: To get class name Parameters: None Author: Lalita Kashyap Updated by
	 * and when:
	 **************************************************************************/

	public String getClassName() {
		return this.getClass().getSimpleName().toString();
	}

	/*************************************************************************
	 * Objective: To get Suite name Parameters: context (ITestContext) Author: Lalita Kashyap
	 *  Updated by and when:
	 **************************************************************************/
	public static String getSuiteName(ITestContext context) {
		return context.getCurrentXmlTest().getSuite().getName();
	}

	// Perform clean up activity post execution
	/*************************************************************************
	 * Objective: After Class processing Parameters: None Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/
	@AfterClass
	public static void afterTest() throws Exception {
		Constants.driver.quit();
		Constants.driver = null;
		Constants.testData.clear();
		Thread.sleep(1000);
		Constants.logger.exiting(Constants.testName, "");
	}

	// To display current running test name
	/*************************************************************************
	 * Objective: Before Method processing Parameters: method (Method) Author: Lalita Kashyap
	 *  Updated by and when:
	 **************************************************************************/
	/*
	 * @BeforeMethod public void beforeTestCase(Method method) { Test test =
	 * method.getAnnotation(Test.class); System.out.println(test.testName());
	 * Constants.testCaseName = test.testName(); }
	 */

	/*************************************************************************
	 * Objective: Before Test Processing Parameters: None Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/
	@BeforeClass
	public void beforeTest() {
		extentTest = Constants.extentReport.createTest(this.getClass().getSimpleName());
		Constants.extentTest = extentTest;
		Constants.testName = this.getClass().getSimpleName();
		Constants.logger.entering(Constants.testName, "");
		ExcelReader excel = new ExcelReader(getConfigFilePath(this.getClass().getSimpleName()));
		String browser = excel.getCellData("Config", 2, "BROWSER");
		String launchType = excel.getCellData("Config", 2, "EXECUTIONTYPE");
		String seleniumSessionType = excel.getCellData("Config", 2, "SELENIUMLAUNCHTYPE");
		Constants.defaultGlobalTimeout = Long.parseLong(excel.getCellData("Config", 3, 2));
		Constants.defaultGlobalStepExecutionDelay = Long.parseLong(excel.getCellData("Config", 4, 2));
		Constants.globalTimeOut = Constants.defaultGlobalTimeout;
		Constants.globalStepExecutionDelay = Constants.defaultGlobalStepExecutionDelay;
		try {
			if (seleniumSessionType.equalsIgnoreCase("newSession")) {
				driver = launchBrowser(browser, launchType);
				Constants.driver = driver;
			} else {
				getCurrentSession();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*************************************************************************
	 * Objective: After Suite Processing Parameters: None Author: Lalita Kashyap
	 * Updated by and when:
	 **************************************************************************/
	@AfterSuite
	public static void afterSuite() throws Exception {
		Thread.sleep(1000);
		Constants.extentReport.attachReporter(Constants.htmlReporter);
		Constants.extentReport.flush();
		// Uncomment below code if Recording is required for your execution on Windows
		// machine
//		Process Pro = Runtime.getRuntime().exec("TASKLIST");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(Pro.getInputStream()));
//		String line;
//		while ((line = reader.readLine()) != null) {
//			if (line.contains("cmd.exe")) {
//				File dir = new File(Constants.recordingPath);
//				ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", "StopRecording.bat");
//				pb.directory(dir);
//				pb.start();
//				Thread.sleep(1000);
//				Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
//				break;
//			}
//		}
		// Read data from excel
		ExcelReader excel = new ExcelReader(IsAlbiConstants.ConfigPropertyFilePath);
		String EmailSentTo = excel.getCellData("EmailSettings", 2, "SENT_TO");
		String EmailSubject = excel.getCellData("EmailSettings", 2, "EMAIL_SUBJECT");
		EmailUtil.sendEmailWithanAttachment(EmailSentTo, EmailSubject, "SimpleEmail Testing Body");
	}

}
