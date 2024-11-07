package com.cdk.pohFiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class validateExpressions {

	    public static void main(String[] args) {
	        try {
	            // Load the Excel file
	            FileInputStream excelFile = new FileInputStream("C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx");
	            Workbook workbook = new XSSFWorkbook(excelFile);
	            Sheet sheet = workbook.getSheetAt(0);
	 
	            // Regular expression to match valid expressions
	            Pattern pattern = Pattern.compile("YOUR_REGEX_HERE"); // Define your regex here
	 
	            // Iterate over each row in the Excel sheet
	            for (Row row : sheet) {
	                Cell expressionCell = row.getCell(0);
	                if (expressionCell == null || expressionCell.getCellType() == CellType.BLANK) {
	                    continue; // Skip blank cells
	                }
	                String expression = expressionCell.getStringCellValue();
	 
	                // Validate the expression
	                Matcher matcher = pattern.matcher(expression);
	                String validationMessage = matcher.matches() ? "Valid" : "Invalid expression";
	                
	                // Write the validation result to the next column
	                Cell validationCell = row.createCell(1);
	                validationCell.setCellValue(validationMessage);
	            }
	 
	            // Write the updated Excel file
	            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\tushar.s\\Desktop\\JSExpressions\\expressions.xlsx");
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
	 
	            System.out.println("Validation completed. Results written to expressions.xlsx.");
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

