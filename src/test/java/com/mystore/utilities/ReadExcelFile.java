package com.mystore.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelFile {

	public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
		try (FileInputStream inputStream = new FileInputStream(fileName);
			 XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

			XSSFSheet excelSheet = workBook.getSheet(sheetName);

			if (excelSheet == null || excelSheet.getRow(rowNo) == null) {
				return "";
			}

			XSSFRow row = excelSheet.getRow(rowNo);
			XSSFCell cell = row.getCell(cellNo);

			if (cell == null) {
				return "";  // Return empty if cell is null
			}

			// Handle different cell types
			switch (cell.getCellType()) {
				case STRING:
					return cell.getStringCellValue();
				case NUMERIC:
					// Convert to string without scientific notation
					return String.valueOf((long) cell.getNumericCellValue());
				case BOOLEAN:
					return String.valueOf(cell.getBooleanCellValue());
				case FORMULA:
					return cell.getCellFormula();
				default:
					return "";
			}

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}


	public static int getRowCount(String fileName, String sheetName) {
		try (FileInputStream inputStream = new FileInputStream(fileName);
			 XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

			XSSFSheet excelSheet = workBook.getSheet(sheetName);
			return (excelSheet != null) ? excelSheet.getLastRowNum() + 1 : 0;

		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getColCount(String fileName, String sheetName) {
		try (FileInputStream inputStream = new FileInputStream(fileName);
			 XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

			XSSFSheet excelSheet = workBook.getSheet(sheetName);
			if (excelSheet == null || excelSheet.getRow(0) == null) {
				return 0;
			}

			return excelSheet.getRow(0).getLastCellNum();

		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
