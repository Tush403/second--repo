package com.cdk.pohFiles;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;


public class apachePDFbox {

	public static void main(String[] args) {
		
		for (int i = 1; i < 6; i++) {
		        // Provide the file paths for the PDFs you want to compare
		        String actual = "C:\\Users\\tushar.s\\Desktop\\POH eForms\\" + i + "\\old.pdf";
		        String expected = "C:\\Users\\tushar.s\\Desktop\\POH eForms\\" + i + "\\new.pdf";

		        try {
		            String text1 = extractTextFromPDF(actual);
		            String text2 = extractTextFromPDF(expected);

		            if (text1.equals(text2)) {
		                System.out.println("The PDF files inside folder "+ i +" are identical.");
		            } else {
		                System.out.println("The PDF files inside folder "+ i +" are different.");
		            }
		        } catch (IOException e) {
		            System.err.println("An error occurred while processing the PDF files: " + e.getMessage());
		        }
		    }
	}
		    private static String extractTextFromPDF(String filePath) throws IOException {
		        PDDocument document = PDDocument.load(new File(filePath));
		        PDFTextStripper textStripper = new PDFTextStripper();
		        String text = textStripper.getText(document);
		        document.close();
		        return text;
}}