package com.saucedemo.utils;

import java.io.FileInputStream;
import java.math.BigDecimal;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {

	public static Sheet getDataFromExcel(String filename, String sheetName) {
		String excelFilePath = System.getProperty("user.dir") + "\\resources\\testdata\\" + filename;
		FileInputStream inputStream;
		Workbook workbook;
		Sheet sheet = null;

		try {
			inputStream = new FileInputStream(excelFilePath);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);

			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sheet;
	}
	
	public static String[][] getExcelDataAsArray(String filename, String sheetname) {
		Sheet sheet = getDataFromExcel(filename, sheetname);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
				
		String[][] result = new String[rows - 1][cols];
		
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return result;
	}
	
	public static double add(double num1, double num2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(num2));
        BigDecimal bd2 = new BigDecimal(Double.toString(num1));
        return bd1.add(bd2).doubleValue();
    }
}