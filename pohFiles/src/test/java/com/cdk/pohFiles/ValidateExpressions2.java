package com.cdk.pohFiles;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateExpressions2 {
 
	    public static void main(String[] args) {
	        try {
	            // Load the Excel file
	            FileInputStream excelFile = new FileInputStream("C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx");
	            Workbook workbook = new XSSFWorkbook(excelFile);
	            Sheet sheet = workbook.getSheetAt(0);
	 
	            // Regular expression patterns
	            Pattern ternaryPattern = Pattern.compile("(\\w+\\s*\\?\\s*\\w+\\s*:\\s*\\w+)");
	            Pattern ifElsePattern = Pattern.compile("if\\s*\\(\\s*.+\\s*\\)\\s*\\{\\s*.+\\s*\\}\\s*else\\s*\\{\\s*.+\\s*\\}");
	            Pattern nestedIfPattern = Pattern.compile("if\\s*\\(\\s*.+\\s*\\)\\s*\\{\\s*if\\s*\\(\\s*.+\\s*\\)\\s*\\{\\s*.+\\s*\\}\\s*\\}");
	            Pattern logicalOperatorPattern = Pattern.compile("(\\w+\\s*&&\\s*\\w+)|(\\w+\\s*\\|\\|\\s*\\w+)");
	            Pattern comparisonOperatorPattern = Pattern.compile("(\\w+\\s*===\\s*\\w+)|(\\w+\\s*!==\\s*\\w+)|(\\w+\\s*>=\\s*\\w+)|(\\w+\\s*<=\\s*\\w+)|(\\w+\\s*<\\s*\\w+)|(\\w+\\s*>\\s*\\w+)");
	            Pattern functionCallPattern = Pattern.compile("(\\w+\\s*\\(\\s*\\))");
	 
	            // Iterate over each row in the Excel sheet
	            for (Row row : sheet) {
	                Cell expressionCell = row.getCell(0);
	                if (expressionCell == null || expressionCell.getCellType() == CellType.BLANK) {
	                    continue; // Skip blank cells
	                }
	                String expression = expressionCell.getStringCellValue();
	 
	                // Validate the expression
	                String validationMessage = "Valid";
	                if (!validateExpression(expression, ternaryPattern, ifElsePattern, nestedIfPattern, logicalOperatorPattern, comparisonOperatorPattern, functionCallPattern)) {
	                    validationMessage = "Invalid expression";
	                }
	 
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
	 
	    private static boolean validateExpression(String expression, Pattern... patterns) {
	        for (Pattern pattern : patterns) {
	            Matcher matcher = pattern.matcher(expression);
	            if (matcher.find()) {
	                return true; // Expression matches at least one pattern
	            }
	        }
	        return false; // Expression does not match any pattern
	    }
	}
	
