package com.cdk.pohFiles;
	import org.apache.pdfbox.pdmodel.PDDocument;
	import org.apache.pdfbox.rendering.PDFRenderer;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.exception.ImageComparisonException;

import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	 
	public class ImagePdfComparator {
	 
	    public static void main(String[] args) {
	        try {
	            // Load PDF document
	            PDDocument document = PDDocument.load(new File("C:\\Users\\tushar.s\\Desktop\\POH eForms\\4\\new.pdf"));
	 
	            // Convert PDF to BufferedImage
	            PDFRenderer pdfRenderer = new PDFRenderer(document);
	            BufferedImage pdfImage = pdfRenderer.renderImage(0);
	 
	            // Load image file
	            BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources("C:\\Users\\tushar.s\\Desktop\\POH eForms\\4\\old.jpg");
	        //    BufferedImage image = ImageComparison.loadImage(new File("C:\\Users\\tushar.s\\Desktop\\POH eForms\\4\\old.pdf"));
	           
	            // Compare images
	            ImageComparison imageComparison = new ImageComparison(expectedImage, pdfImage);
	            imageComparison.compareImages(); // Throws ImageComparisonException if images are different
	 
	            System.out.println("Images are identical.");
	        } catch (IOException | ImageComparisonException e) {
	            e.printStackTrace();
	        }
	    }
	}
