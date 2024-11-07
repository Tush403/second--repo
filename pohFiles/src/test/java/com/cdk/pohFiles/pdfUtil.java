package com.cdk.pohFiles;
import org.testng.annotations.Test;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;


public class pdfUtil { 

//	public static void main(String[] args)  
	@Test
	public void recall () {
        String expectedPDF = "C:\\Users\\tushar.s\\Desktop\\POH eForms\\5\\old.pdf";
        String actualPDF = "C:\\Users\\tushar.s\\Desktop\\POH eForms\\5\\new.pdf";
	   
        PDFUtil pu=new PDFUtil();
			try {
				pu.setCompareMode(CompareMode.VISUAL_MODE);
				pu.highlightPdfDifference(true);
				pu.setImageDestinationPath("C:\\Users\\tushar.s\\Desktop\\POH eForms\\5\\");
				pu.compare(expectedPDF, actualPDF);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
	}}
