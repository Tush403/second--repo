package com.cdk.pohFiles;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class duplicateStrings {

	public static void main(String[] args) {

		        String filePath = "C://Users//tushar.s//Desktop//Others//duplicateStrings.xlsx";

		        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
		            Workbook workbook = new XSSFWorkbook(fileInputStream);
		            Sheet sheet = workbook.getSheetAt(0); 
		          
		            Map<String, Integer> stringCountMap = new HashMap<>();

		        
		            for (Row row : sheet) {
		                for (Cell cell : row) {
		                    if (cell.getCellType() == CellType.STRING) {
		                        String cellValue = cell.getStringCellValue();
		                        stringCountMap.put(cellValue, stringCountMap.getOrDefault(cellValue, 0) + 1);
		                    }
		                }
		            }

		            CellStyle duplicateCellStyle = createDuplicateCellStyle(workbook);
		            for (Row row : sheet) {
		                for (Cell cell : row) {
		                    if (cell.getCellType() == CellType.STRING) {
		                        String cellValue = cell.getStringCellValue();
		                        if (stringCountMap.get(cellValue) > 1) {
		                            cell.setCellStyle(duplicateCellStyle);
		                        }
		                    }
		                }
		            }


		            try (FileOutputStream fileOutputStream = new FileOutputStream("C://Users//tushar.s//Desktop//Others//result.xlsx")) {
		                workbook.write(fileOutputStream);
		            }

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	}
		    private static CellStyle createDuplicateCellStyle(Workbook workbook) {
		        CellStyle duplicateCellStyle = workbook.createCellStyle();
		        duplicateCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		        duplicateCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		        return duplicateCellStyle;
}}