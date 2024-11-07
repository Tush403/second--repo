package com.cdk.pohFiles;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
 

public class pohVSpdf{

	    public static void main(String[] args) {
	        try {
	            // Load the Word document
	            FileInputStream fis = new FileInputStream("C://Users//tushar.s//Desktop//Others//0500-1-FI-Ply1");
	            XWPFDocument document = new XWPFDocument(fis);
	 
	            // Create a new Excel workbook
	            Workbook workbook = new XSSFWorkbook();
	            Sheet sheet = workbook.createSheet("Sheet1");
	 
	            // Extract text content from Word document and write to Excel
	            int rowNum = 0;
	            for (XWPFPictureData picture : document.getAllPictures()) {
	                XWPFPictureData pictureData = picture.getPackagePart().getPackage().getParts().iterator().next().getPackage().getPartsByRelationshipType(fis);
	                byte[] bytes = pictureData.getData();
	                // Handle picture data if needed
	                // ...
	 
	                // Example: Write picture data to Excel (you might need to customize this)
	                Row row = sheet.createRow(rowNum++);
	                Cell cell = row.createCell(0);
	                cell.setCellValue(true);
	            }
	 
	            // Save the Excel file
	            FileOutputStream fos = new FileOutputStream("C://Users//tushar.s//Desktop//Others//output.xlsx");
	            workbook.write(fos);
	 
	            // Close streams
	            fis.close();
	            fos.close();
	            workbook.close();
	 
	            System.out.println("Conversion successful!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
