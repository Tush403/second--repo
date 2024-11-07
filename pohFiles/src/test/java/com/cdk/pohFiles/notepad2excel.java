package com.cdk.pohFiles;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class notepad2excel {

	public static void main(String[] args) {
	        try {
	           
	        	List<String> list =new ArrayList<>();
	        	
	            BufferedReader reader = new BufferedReader(new FileReader("C://Users//tushar.s//Desktop//Others//30883-1-FI-Ply1"));
	 
	            Workbook workbook = new XSSFWorkbook();
	            Sheet sheet = workbook.createSheet("Sheet1");
	 
	            String line;
	            int rowNum = 0;
	            while ((line = reader.readLine()) != null) {
	            	
	            	if(line.contains("text")) {
	            		
	            		line = line.replace("text", "").replace("(", "").replace(")", "").replace("\"", "");
	                	list.add(line);
	            		Row row = sheet.createRow(rowNum++);
    	                Cell cell = row.createCell(0);
    	                cell.setCellValue(line);
	                
	            }
	            	if(line.contains("+")) {
	            		line = line.replace("+", "");
	            		String[] arr = line.split(" ");
	            		for(String str : arr) {
	            			
	            			Row row = sheet.createRow(rowNum++);
	    	                Cell cell = row.createCell(0);
	    	                cell.setCellValue(str);
	            		list.add(str);
	    	            }
//	            
//	            	if(line.contains([*&%$])){
//	            	list.add(line);
	            	}
	           
	            	if(line.contains(":") && line.contains("?")){
	            	line.replace(":", "").replace("?", "");
	            	String[] arr1 = line.split(" ");
	            	 
	            	for(String data : arr1){
            			Row row = sheet.createRow(rowNum++);
    	                Cell cell = row.createCell(0);
    	                cell.setCellValue(data);
	            	list.add(data);
	            	 
	            	}
	            	
//	            	Set set = new HashSet();
//	            	set.addAll(list);
//	           	 System.out.println(list);
	            
	            		
	            	}
					/*
					 * Row row = sheet.createRow(rowNum++); Cell cell = row.createCell(0);
					 * cell.setCellValue(line);
					 */
	            }
	 
	            FileOutputStream fos = new FileOutputStream("C://Users//tushar.s//Desktop//Others//output.xlsx");
	            workbook.write(fos);
	 
	            reader.close();
	            fos.close();
	            workbook.close();

	            System.out.println("Conversion successful!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
}}