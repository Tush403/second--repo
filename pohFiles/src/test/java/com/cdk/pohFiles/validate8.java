package com.cdk.pohFiles;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class validate8 {
	
  
	    public static boolean validateExpression(String expression) {
	      
	        String exp1 = "^[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+\\s*=\\s*\\(\\(xfa\\.[a-zA-Z0-9_]+\\.attributes\\.root\\.[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+\\.value==null\\)\\?\"N/A\":\"\"\\)$";
       
	        String exp2 = "^this\\.rawValue\\s*=\\s*\\(\"Deal#:\"\\s*\\+\\s*\"\"\\s*\\+\\s*\\(xfa\\.record\\.attributes\\.root\\.Deal\\.DealNumber\\.value\\s*\\|\\|\\s*\"\"\\)\\)$";

	        
	        String combinedexp = "(" + exp1 + ")|(" + exp2 + ")";

	        
	        Pattern pattern = Pattern.compile(combinedexp);

	     
	        Matcher matcher = pattern.matcher(expression);

	    
	        return matcher.matches();
	    }

	    public static void main(String[] args) {
	        String inputFile = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx";
	        String outputFile = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\output.xlsx";

	        try (FileInputStream inputStream = new FileInputStream(inputFile);
	             XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

	            XSSFSheet sheet = workbook.getSheetAt(0); 
	            int columnToRead = 0;
	            int columnToWrite = 1; 

	            List<String> expressions = new ArrayList<>();

	           
	            for (Row row : sheet) {
	                Cell cell = row.getCell(columnToRead);

	                if (cell != null && cell.getCellType() == CellType.STRING) {
	                    String expression = cell.getStringCellValue().trim();
	                    expressions.add(expression);
	                }
	            }

	        
	            int rowIndex = 0;
	            for (String expression : expressions) {
	                boolean isValid = validateExpression(expression);
	                Row row = sheet.getRow(rowIndex);
	                Cell resultCell = row.createCell(columnToWrite);
	                resultCell.setCellValue(isValid ? "Valid" : "Invalid");
	                rowIndex++;
	            }

	            
	            workbook.write(outputStream);
	            System.out.println("Validation completed successfully. Validation results saved in '" + outputFile + "'.");

	        } catch (Exception e) {
	            System.err.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}


