package com.cdk.pohFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.redsix.pdfcompare.RenderingException;

public class test1901 {
	
public static void main (String[] args) {

              	 File filePath = new File("C://Users//tushar.s//Desktop//Others//30883-1-FI-Ply1");
              	 
                Set<String> uniqueFieldNames = new LinkedHashSet<>();
 
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    
                    while ((line = bufferedReader.readLine()) != null) {
                        List<String> fieldNamesFromText = processFunction(line);
                        for (String fieldName : fieldNamesFromText) {
                            uniqueFieldNames.add(fieldName);
                        }
                    } 
                }catch (IOException e) {
	                    e.printStackTrace();       
                }  
                try (Workbook workbook = new XSSFWorkbook()) {
    	            Sheet sheet = workbook.createSheet("Sheet1");
    	 
    	            int rowNumber = 0;
    	            for (String value : uniqueFieldNames) {
    	                Row row = sheet.createRow(rowNumber++);
    	                Cell cell = row.createCell(0);
    	                cell.setCellValue(value);
    	            }
    	 
    	            try (FileOutputStream outputStream = new FileOutputStream("C://Users//tushar.s//Desktop//Others//output3.xlsx")) {
    	                workbook.write(outputStream);
    	            }
    	 
    	            System.out.println("Excel file created successfully.");
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
	                     System.out.println(uniqueFieldNames);
	
	}
              
                    public static List<String> processFunction(String line) {
                    	
    	                Pattern pattern = Pattern.compile("(text|if)\\(([^)]+)\\s*;?");
    	 
    	                Matcher matcher = pattern.matcher(line);
    	                List<String> fieldNames = new ArrayList<>();
    	                boolean insideQuotes = false;
    	 
    	                while (matcher.find()) {
    	                    String textGroup = matcher.group(1);
    	                    String ifGroup = matcher.group(2);
    	 
    	                    String[] splitNames;
    	                    if ("if".equals(textGroup.toLowerCase())) {
    	                        splitNames = ifGroup.split(":|\\s|\\+|\\,|\\=|\\<|\\>|\\?|\\-|\\[");
    	                    } else {
    	                        splitNames = line.split(":|\\s|\\+|\\,|\\=|\\<|\\>|\\?|\\-|\\[|\\*|\\/|\\(");
    	                    }
    	 
    	                    for (String splitName : splitNames) {
    	                        if (splitName.startsWith("\"")) {
    	                            insideQuotes = true;
    	                        }
    	 
    	                        // Remove special characters from the split name
    	                        splitName = splitName.replaceAll("[^a-zA-Z0-9 _\"]", "")
    	                        .replace("text", "").replace("eval", "")
    	                        .replace("left", "").replace("if", "")
    	                        .replace("page_layout", "")
    	                        .replace("word", "")
    	                        .replace("chr252", "")
    	                        .replace("len", "")
    	                        .replace("upper", "")
    	                        .replace("right", "")
    	                        .replace("trim", "")
    	                        .replace("replace", "")
    	                        .replace("Null", "")
    	                        .replace("instr", "");
    	 
    	 
    	                        if (insideQuotes && splitName.endsWith("\"")) {
    	                            insideQuotes = false;
    	                            continue;
    	                        }
    	 
    	                        if (insideQuotes) {
    	                            continue;
    	                        }
    	 
    	                        if (splitName.toLowerCase().contains("x")) {
    	                            String check = "X";
    	                            fieldNames.add(check.trim());
    	                        }
    	                        if (splitName.trim().startsWith("format") || splitName.trim().startsWith("XXX")
    	                                || splitName.trim().startsWith("ZZZ") || splitName.startsWith("YY")) {
    	                            continue;
    	                        }
    	                        if (splitName.length() >= 4) {
    	                            splitName = splitName.replace("3OSLBuyerDealerDefined1", "BuyerDealerDefined1");
    	 
    	                            fieldNames.add(splitName.trim());
    	                        }
    	                    }
    	                }
    	                			return fieldNames;
    	            }
    	        }

