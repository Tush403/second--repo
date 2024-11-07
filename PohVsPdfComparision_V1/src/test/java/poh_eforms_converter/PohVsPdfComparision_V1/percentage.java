package poh_eforms_converter.PohVsPdfComparision_V1;

import java.io.File;
import java.io.IOException;
import org.testng.annotations.Test;
import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PdfComparator;
import de.redsix.pdfcompare.RenderingException;

public class percentage {

	    @Test
	    public void PdfVsPoh() throws RenderingException, IOException {
	        File path = new File("C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\");
	        String[] folderNames = path.list();

	        if (folderNames == null) {
	            System.out.println("No folders found in the specified directory.");
	            return;
	        }

	        for (String folderName : folderNames) {
	            File folder = new File(path, folderName);
	            if (!folder.isDirectory()) {
	                continue;
	            }

	            String actual = path + File.separator + folderName + File.separator + "pdf.pdf";
	            String expected = path + File.separator + folderName + File.separator + "poh.pdf";
	            String result = "C:\\Users\\tushar.s\\Desktop\\Results" + File.separator + folderName;

	            File resultDir = new File(result);
	            if (!resultDir.exists()) {
	                resultDir.mkdirs();
	            }

	            System.out.println("Processing folder: " + folderName);

	            CompareResult compareResult = new PdfComparator(expected, actual).compare();
	            compareResult.writeTo(result);

	            boolean isEquals = compareResult.isEqual();
	            if (!isEquals) {
	                int differences = compareResult.getDifferencesCount();
	                int totalPages = compareResult.getExpectedNumberOfPages();
	                double matchedPercentage = ((totalPages - differences) / (double) totalPages) * 100;
	                System.out.println("Differences found in folder: " + folderName);
	                System.out.println("Matched Percentage: " + matchedPercentage + "%");
	            } else {
	                System.out.println("Files matched in folder: " + folderName);
	                System.out.println("Matched Percentage: 100%");
	            }
	        }
	    }
	}


