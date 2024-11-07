package com.cdk.pohFiles;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.jexl3.*;

import java.io.*;

public class validate6 {


	    public static boolean validateExpression(String expression, JexlContext context) {
	        try {
	            JexlEngine jexl = new JexlBuilder().create();
	            JexlExpression jexlExpression = jexl.createExpression(expression);
	            Object result = jexlExpression.evaluate(context);

	            if (result instanceof Boolean && (Boolean) result) {
	                return true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    public static void main(String[] args) {
	        String excelFilePath = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx";
	        String sheetName = "Sheet1"; // Name of the sheet containing expressions
	        int expressionColumnIndex = 0; // Column index containing expressions (zero-based)
	        int resultColumnIndex = 1; // Column index to write validation result (zero-based)

	        try (InputStream inputStream = new FileInputStream(new File(excelFilePath));
	             Workbook workbook = new XSSFWorkbook(inputStream)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) {
	                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the workbook.");
	            }

	            JexlContext context = new MapContext();
	            // Set context variables (replace with actual values from your data source)
	            context.set("DealType", "P");
	            context.set("ContractDate", "2024-04-24");
	            context.set("Rebate5Amount", 100.0);
	            context.set("Rebate6Desc", "Description");
	            context.set("Rebate6Amount", 150.0);

	            for (Row row : sheet) {
	                Cell expressionCell = row.getCell(expressionColumnIndex);
	                if (expressionCell == null || expressionCell.getCellType() != CellType.STRING) {
	                    continue; // Skip if the cell is empty or not a string
	                }

	                String expression = expressionCell.getStringCellValue().trim();
	                boolean isValid = validateExpression(expression, context);

	                Cell resultCell = row.createCell(resultColumnIndex, CellType.STRING);
	                resultCell.setCellValue(isValid ? "Valid" : "Invalid");
	            }

	            // Write back to the Excel file
	            try (OutputStream outputStream = new FileOutputStream(excelFilePath)) {
	                workbook.write(outputStream);
	            }

	            System.out.println("Validation complete. Results written to the Excel file.");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

