package com.cdk.pohFiles;
import org.apache.poi.ss.usermodel.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class validate5 {


	    public static void main(String[] args) throws IOException {
	        String inputFile = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx";
	        String outputFile = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input6.xlsx";

	        try (FileInputStream fis = new FileInputStream(inputFile);
	             Workbook workbook = WorkbookFactory.create(fis)) {

	            Sheet sheet = workbook.getSheetAt(0);

	            ScriptEngineManager manager = new ScriptEngineManager();
	            ScriptEngine engine = manager.getEngineByName("js");

	            for (Row row : sheet) {
	                Cell cell = row.getCell(0); // assuming expressions are in the first column

	                if (cell != null && cell.getCellType() == CellType.STRING) {
	                    String expression = cell.getStringCellValue();
	                    boolean isValid = isValidJavaScript(expression, engine);

	                    // Write result to the next column
	                    Cell resultCell = row.createCell(1, CellType.STRING);
	                    resultCell.setCellValue(isValid ? "Valid" : "Invalid");
	                }
	            }

	            // Write output to a new file
	            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
	                workbook.write(fos);
	            }
	        }
	    }

	    private static boolean isValidJavaScript(String expression, ScriptEngine engine) {
	        try {
	            // Attempt to evaluate the expression
	            engine.eval(expression);
	            return true; // If evaluation succeeds, consider it valid
	        } catch (ScriptException e) {
	            return false; // If there's an exception, consider it invalid
	        }
	    }
	}

