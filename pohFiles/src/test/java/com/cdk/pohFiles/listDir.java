package com.cdk.pohFiles;
import java.io.File;
import de.redsix.pdfcompare.PdfComparator;

public class listDir {

	public static void main(String[] args) throws Exception {
		File path = new File("C:\\Users\\tushar.s\\Desktop\\Script Results\\");
		String[] s1=path.list();

		for(String s2:s1) {	
			
//			File f=new File(path,s2);
				
			String actual = "C:\\Users\\tushar.s\\Desktop\\Script Results\\" + s2 + "\\new.pdf";
			String expected = "C:\\Users\\tushar.s\\Desktop\\Script Results\\" + s2 + "\\old.pdf";
			String result = "C:\\Users\\tushar.s\\Desktop\\Script Results\\" + s2 + "\\result";
			System.out.println("Folder " + s2 + " is processing...");

			new PdfComparator(expected, actual).compare().writeTo(result);
			boolean isEquals = new PdfComparator(expected, actual).compare().writeTo(result);
			if (!isEquals) {
				System.out.println("Differences found!");
				continue;
			}
			System.out.println("Files Matched!");
			
//			System.out.println(actual);
//			System.out.println(expected);
			
		}}}
		
		
		 
		 
		 
		 
//		 list(path);
//	}
	
//	
//public static void list(File dir) {
//	File elements[] = dir.listFiles();
//	for(File element: elements) {
//		System.out.println(element);
//		
//	}
//	
//	
//}
//}
