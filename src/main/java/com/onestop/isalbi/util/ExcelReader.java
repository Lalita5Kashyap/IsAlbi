/**
 * 
 */
package com.onestop.isalbi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

/**
 * This class contain all the reusable methods for create and read excel sheet
 * 
 * @author Lalita Kashyap
 *
 */
public class ExcelReader {

	public static String filename = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Data"; 
	public String path;
	public static FileInputStream fis = null;
	public static FileOutputStream fileOut = null;
	private static XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private static XSSFCell cell = null;
	
	/*************************************************************************
	* Objective: Constructor for ExcelReader
	* Parameters: path (String)
	* Author: Lalita Kashyap
	* Updated by and when:
	**************************************************************************/
	
	public ExcelReader(String path) {
		  this.path = path;
		  try {
			  fis = new FileInputStream(path);
			  workbook = new XSSFWorkbook(fis);
			  sheet = workbook.getSheetAt(0);
			  fis.close();
		  } catch (Exception e) {
			  e.printStackTrace();		  
		  }
	}
	/*************************************************************************
	* Objective: To get the value of a cell based on row number & column name
	* Parameters: sheetName (String), rowNum (int), colName (String)
	* Author: Lalita Kashyap
	* Updated by and when:
	**************************************************************************/
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			
			int index = workbook.getSheetIndex(sheetName);
			if(index == -1)
				return "";
			
			sheet = workbook.getSheetAt(index);
			row =sheet.getRow(rowNum - 1);
			if(row == null)
				return "";
			cell = row.getCell(colNum);
			if(cell == null)
				return "";
			
			if(cell.getCellType() == CellType.STRING)
			return cell.getStringCellValue();
			else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}
				return cellText;
			} else if(cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist in xls";	
		}
	}
	/*************************************************************************
	* Objective: To get the value of a cell based on row number & column name
	* Parameters: sheetName (String), rowNum (int), colName (String)
	* Author: Lalita Kashyap
	* Updated by and when:
	**************************************************************************/
	public String getCellData(String sheetName, int rowNum, String colName) {
		try {
			if (rowNum <= 0)
				return "";
			
			int index = workbook.getSheetIndex(sheetName);
			int col_num = -1;
			if(index == -1)
				return "";
			
			sheet = workbook.getSheetAt(index);
			row =sheet.getRow(0);
			
			for(int i=0; i < row.getLastCellNum(); i++) {
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
					col_num = i;
					break;
				}
			}
			if(col_num == -1)
				return "";
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if(row == null)
				return "";
			cell = row.getCell(col_num);
			if(cell == null)
				return "";
			if(cell.getCellType() == CellType.STRING)
			return cell.getStringCellValue();
			else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}
				return cellText;
			} else if(cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";	
		}
	}
	
	/*************************************************************************
	* Objective: To get the count of rows in a sheet
	* Parameters: sheetName (String)
	* Author: Lalita Kashyap
	* Updated by and when:
	**************************************************************************/
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if(index == -1) {
			return 0;
		}
		else {
			sheet = workbook.getSheetAt(index);
			int rowCount = sheet.getLastRowNum() + 1;
			return rowCount;
		}
	}
	
	
	@SuppressWarnings({ "resource" })
	public static WebDriver CreateExceleFile() throws IOException {
		WebDriver driver = null;

		File file = new File("C:\\Users\\OneStop\\eclipse-workspace\\ISAlbi\\src\\test\\java\\com\\test\\Testdata.xls");
		FileOutputStream fos = new FileOutputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("UserTestData");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("TestCase");
		wb.write(fos);
		return driver;
	}

	public static void ReadExcelSheetData(String TestCaseName) {
		//public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\OneStop\\eclipse-workspace\\ISAlbi\\src\\test\\java\\com\\test\\Testdata.xls";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();
		System.out.println("Last row- "+lastRow);
		for(int i=0; i<=lastRow; i++){
		Row row = sheet.getRow(i);
		int lastCell = row.getLastCellNum();
		for(int j=0; j<lastCell; j++){
		String value = cell.getStringCellValue();
		System.out.println(value);
		}
		System.out.println();
		}
		}
	
}
