package poh_eforms_converter.PohVsPdfComparision_V1;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
 
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Delta;
import com.github.difflib.patch.Patch;


public class highlightDiff {
	  
	  @Test
	 
	    public void testPDFComparison() {

	        try {
	            
	            String originalText = extractTextFromPDF("C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\1113\\poh.pdf");
	            String revisedText = extractTextFromPDF("C:\\Users\\tushar.s\\Desktop\\UpdatedTC\\1026\\pdf.pdf");
	 
	            
	            List<String> originalWords = Arrays.asList(originalText.split("\\s+"));
	            List<String> revisedWords = Arrays.asList(revisedText.split("\\s+"));
	 
	            System.out.println("pdf"+revisedWords);
	            System.out.println("poh"+originalWords);
	            
	            Patch<String> patch = DiffUtils.diff(originalWords, revisedWords);
	            List<AbstractDelta<String>> deltas = patch.getDeltas();
	 
	 
	            int totalWords = originalWords.size();
	            int changes = deltas.size();
	            double matchPercentage = ((totalWords - changes) / (double) totalWords) * 100;
	 
	          
	            System.out.println("Match Percentage: " + matchPercentage + "%");
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private String extractTextFromPDF(String filePath) throws IOException {
	        try (PDDocument document = PDDocument.load(new File(filePath))) {
	            PDFTextStripper pdfStripper = new PDFTextStripper();
	            return pdfStripper.getText(document);
	        }
	    }
	}
	
	
