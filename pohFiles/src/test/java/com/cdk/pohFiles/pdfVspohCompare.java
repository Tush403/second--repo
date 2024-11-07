package com.cdk.pohFiles;
import de.redsix.pdfcompare.PdfComparator;

public class pdfVspohCompare {

	public static void main(String[] args) throws Exception {

		for (int i = 1; i < 4; i++) {

			String actual = "C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\" + i + "\\new.pdf";
			String expected = "C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\" + i + "\\old.pdf";
			String result = "C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\" + i + "\\result";
			System.out.println("Folder " + i + " is processing...");

			new PdfComparator(expected, actual).compare().writeTo(result);
			boolean isEquals = new PdfComparator(expected, actual).compare().writeTo(result);
			Thread.sleep(5000);
			if (!isEquals) {
				System.out.println("Differences found!");
				continue;
			
			}
			System.out.println("Files Matched!");
			Thread.sleep(5000);
		}
	}
}