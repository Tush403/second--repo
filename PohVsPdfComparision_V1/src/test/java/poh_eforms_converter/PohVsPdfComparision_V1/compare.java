package poh_eforms_converter.PohVsPdfComparision_V1;

import java.io.File;
import java.io.IOException;
import org.testng.annotations.Test;
import de.redsix.pdfcompare.PdfComparator;
import de.redsix.pdfcompare.RenderingException;

public class compare {
	
	@Test
	public static void PdfVsPoh() throws RenderingException, IOException {

		File path = new File("C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\");
		String[] folderName = path.list();

		for (String fileName : folderName) {
			File f = new File(path, fileName);
			
			String actual = "C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\" + fileName + "\\pdf.pdf";
			String expected = "C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\" + fileName + "\\poh.pdf";
			String result = "C:\\Users\\tushar.s\\Desktop\\Results\\" + fileName + "";
			System.out.println("Folder " + fileName + " is processing...");

			new PdfComparator(expected, actual).compare().writeTo(result);
			boolean isEquals = new PdfComparator(expected, actual).compare().writeTo(result);
			if (!isEquals) {
				System.out.println("Differences found!");
				continue;
			}
			System.out.println("Files Matched!");

		}
	}
}

