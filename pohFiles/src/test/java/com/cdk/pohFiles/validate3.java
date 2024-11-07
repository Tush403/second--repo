package com.cdk.pohFiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;


public class validate3 {
	
	    public static void main(String[] args) {
	        try {
	            // Load the Excel file
	            FileInputStream excelFile = new FileInputStream("C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx");
	            Workbook workbook = new XSSFWorkbook(excelFile);
	            Sheet sheet = workbook.getSheetAt(0);
	 
	            // Regular expression patterns
	            Pattern ternaryPattern = Pattern.compile("(\\w+\\s*\\?\\s*\\w+\\s*:\\s*\\w+)");
	            Pattern ifElsePattern = Pattern.compile("if\\s*\\(\\s*.+\\s*\\)\\s*\\{\\s*.+\\s*\\}\\s*else\\s*\\{\\s*.+\\s*\\}");
	 
	            // Iterate over each row in the Excel sheet
	            for (Row row : sheet) {
	                Cell expressionCell = row.getCell(0);
	                if (expressionCell == null || expressionCell.getCellType() == CellType.BLANK) {
	                    continue; // Skip blank cells
	                }
	                String expression = expressionCell.getStringCellValue();
	 
	                // Validate the expression
	                List<String> errors = new ArrayList<>();
	                boolean isValid = validateExpression(expression, errors, ternaryPattern, ifElsePattern);
	 
	                // Write the validation result to the next column
	                Cell validationCell = row.createCell(1);
	                if (isValid) {
	                    validationCell.setCellValue("Valid");
	                } else {
	                    // Concatenate all errors into a single string
	                    StringBuilder errorMessage = new StringBuilder();
	                    for (String error : errors) {
	                        errorMessage.append(error).append("; "); // Separate errors by semicolon
	                    }
	                    validationCell.setCellValue(errorMessage.toString());
	                }
	            }
	 
	            // Write the updated Excel file
	            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input3.xlsx");
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
	 
	            System.out.println("Validation completed. Results written to expressions.xlsx.");
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private static boolean validateExpression(String expression, Pattern... patterns) {
	        boolean isValid = false;
	        for (Pattern pattern : patterns) {
	            Matcher matcher = pattern.matcher(expression);
	            if (matcher.find()) {
	                isValid = true; // Expression matches at least one pattern
	                break;
	            }
	        }
	        return isValid; // Expression matches at least one pattern
	    }
	 
	    private static boolean validateExpression(String expression, List<String> errors, Pattern... patterns) {
	        boolean isValid = false;
	        for (Pattern pattern : patterns) {
	            Matcher matcher = pattern.matcher(expression);
	            if (!matcher.find()) {
	                isValid = false; // Expression does not match the pattern
	                errors.add("Invalid syntax for pattern: " + pattern.pattern()); // Add error message
	            } else {
	                isValid = true; // Expression matches at least one pattern
	                break;
	            }
	        }
	        return isValid; // Expression matches at least one pattern
	    }
	}
