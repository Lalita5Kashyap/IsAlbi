/**
 * 
 */
package com.onestop.isalbi.config;

import java.io.File;

/**
 * @author Lalita Kashyap
 *
 */
public class IsAlbiConstants {

	public static String ConfigPropertyFilePath = System.getProperty("user.dir") + File.separator + "src" +File.separator + "test"
			+ File.separator + "java" + File.separator + "com" +File.separator + "test" +File.separator + "ISAlbi" + File.separator + "testData" + File.separator
			+ "ConfigurationSettings.xlsx";
	
	public static String TestData = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "java" + File.separator + "com" + File.separator
			+ "test" +  File.separator + "ISAlbi" + File.separator
			+ "testData" + File.separator + "TestData.xls";
	
	public static String TestDataOutput = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator + "zanui"
			+ File.separator + "testData" + File.separator + "testDataOutput"
			+ File.separator;
}
