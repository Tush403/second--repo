package com.cdk.pohFiles;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class validate4 {
	 
	    public static void main(String[] args) {
	        // Path to the input Excel file containing expressions
	        String inputExcelFilePath = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx";
	        // Path to the output Excel file with validation results
	        String outputExcelFilePath = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\output.xlsx";
	 
	        try {
	            // Read expressions from input Excel file
	            List<String> expressions = readExpressionsFromExcel(inputExcelFilePath);
	 
	            // Validate expressions and write validation result to output Excel file
	            writeValidationResultToExcel(expressions, outputExcelFilePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private static List<String> readExpressionsFromExcel(String filePath) throws IOException {
	        List<String> expressions = new ArrayList<>();
	        FileInputStream excelFile = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(excelFile);
	        Sheet sheet = workbook.getSheetAt(0);
	        for (Row row : sheet) {
	            Cell cell = row.getCell(0);
	            if (cell != null && cell.getCellType() == CellType.STRING) {
	                expressions.add(cell.getStringCellValue());
	            }
	        }
	        workbook.close();
	        return expressions;
	    }
	 
	    private static void writeValidationResultToExcel(List<String> expressions, String filePath) throws IOException {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Validation Results");
	 
	        Pattern expressionPattern = Pattern.compile("\\bthis\\.rawValue\\s*=\\s*(\\(.*?\\)|\\w+|\"[^\"]*\"|'[^']*')(\\s*\\(.*?\\))?\\s*;");
	 
	        int rowIndex = 0;
	        for (String expression : expressions) {
	            Row row = sheet.createRow(rowIndex);
	            Cell expressionCell = row.createCell(0);
	            expressionCell.setCellValue(expression);
	 
	            Cell validationResultCell = row.createCell(1);
	            Matcher matcher = expressionPattern.matcher(expression);
	            if (matcher.matches()) {
	                validationResultCell.setCellValue("Valid");
	            } else {
	                validationResultCell.setCellValue("Invalid");
	            }
	 
	            rowIndex++;
	        }
	 
	        FileOutputStream outputStream = new FileOutputStream(filePath);
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }
	}

