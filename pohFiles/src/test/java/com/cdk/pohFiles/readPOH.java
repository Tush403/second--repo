package com.cdk.pohFiles;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.Color;

public class readPOH {

	public static void main(String[] args) {
		
	
		    File path = new File("C:\\Users\\tushar.s\\Desktop\\All POH Forms 1701");
		    
		    File [] files1 = path.listFiles();
		    for (int i = 0; i < files1.length; i++){
		    	String files = path.getName().toString();
//		        if (files[i].isFile()){ 
		            System.out.println(files1);
		        }
	

//		File file = new File("C:\\Users\\tushar.s\\Desktop\\Script Results\\5\\result.pdf");
//
//		try {
//			PDDocument document = PDDocument.load(file);
//
//			for (PDPage page : document.getPages()) {
//				
//				PDPageContentStream contentStream = new PDPageContentStream(document, page,
//						PDPageContentStream.AppendMode.APPEND, true);
//
//				contentStream.setNonStrokingColor(Color.WHITE);
//
//				PDRectangle pageSize = page.getMediaBox();
//
//				contentStream.fillRect(0, 0, pageSize.getWidth(), pageSize.getHeight());
//
//				contentStream.close();
//			}
//
//			document.save("C:\\Users\\tushar.s\\Desktop\\Script Results\\6\\result.pdf");
//
//			document.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		    }}


