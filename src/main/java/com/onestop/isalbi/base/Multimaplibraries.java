/**
 * 
 */
package com.onestop.isalbi.base;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.onestop.isalbi.util.ExcelReader;

/**
 * @author Lalita kashyap
 *
 */
public class Multimaplibraries {

	/*************************************************************************
	 * Objective: To read the data present in a excel sheet Parameters: testDataPath
	 * (String), sheetName (String) Author: Lalita kashyap Updated by and when:
	 **************************************************************************/
	@SuppressWarnings("deprecation")
	public static void getTestData(String testDataPath, String sheetName) {
		try {
			Constants.testData.clear();
			ExcelReader excel = new ExcelReader(testDataPath);
			FileInputStream fis = new FileInputStream(testDataPath);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);
			int colNum = row.getLastCellNum();
			System.out.println("Total Number of Columns: " + colNum);
			int TD_Rowcount = excel.getRowCount(sheetName);
			for (int i = 1; i <= TD_Rowcount; i++) {
				for (int j = 1; j < colNum; j++) {
					String columnName = excel.getCellData(sheetName, j, 1);
					String testDataName = excel.getCellData(sheetName, i, "TestCase");
					String testDataValue = excel.getCellData(sheetName, i, columnName);
					Constants.testData.put(testDataName, testDataValue);
				}
			}
			System.out.println("Adding TestData to multimap completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*************************************************************************
	 * Objective: To get value of each cell based on Test Case & column name
	 * Parameters: TestCaseName (String), colName (String) Author: Lalita kashyap
	 * Updated by and when:
	 **************************************************************************/
	public static String getTestDataCellValue(String testCaseName, String colName) {
		String value = null;
		ArrayList<String> object = Multimaplibraries.getTestDataRowValue(testCaseName);
		ArrayList<String> testcase = Multimaplibraries.getTestDataRowValue("TestCase");
		int index = -1;
		for (int i = 0; i < testcase.size(); i++) {
			if (testcase.get(i).equals(colName)) {
				index = i;
				break;
			}
		}
		try {
			value = object.get(index);
		} catch (Exception e) {
			e.printStackTrace();
			value = null;
			// Reports.ExtentReportLog(Constants.testCaseName, Status.FAIL,
			// "Column '" + colName + "' does not exist in test data file", true);
		}
		return value;
	}

	/*************************************************************************
	 * Objective: To get the data of a complete row based on scenario name
	 * Parameters: scenarioName (String) Author: Lalita kashyap Updated by and when:
	 **************************************************************************/

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<String> getTestDataRowValue(String scenarioName) {
		ArrayList<String> object = null;
		try {
			object = (ArrayList<String>) Constants.testData.get(scenarioName);
		} catch (Exception e) {
			System.out.println("There is no object by the name: " + scenarioName);
		}
		return object;
	}

}
