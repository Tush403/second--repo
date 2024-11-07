package com.cdk.pohFiles;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

import com.itextpdf.forms.xfa.XfaForm;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.xmp.XmlDomWriter;
import ch.qos.logback.core.pattern.parser.Node;

public class set2excel {
	
	
	    public static void main(String[] args) throws IOException, DOMException {
	    	
	        String inputPdf = "input_form.pdf";
	        String outputPdf = "output_form_filled.pdf";
	        String xmlData = "data.xml";
	 
	        // Read the existing PDF form
	        PdfReader reader = new PdfReader(inputPdf);
	 
	        // Create the output PDF
	        FileOutputStream fos = new FileOutputStream(outputPdf);
	 
	        // Fill XFA form with XML data
	        XfaForm xfa = new XfaForm();
	        Document document = xfa.getDomDocument();
	        Document xmlDocument = XmlDomWriter.convertToXml(xfa.getDatasetsNode());
	 
	        // Import XML data
	        Document xmlDataDocument = XmlDomWriter.createDocument(xmlData);
	        Node importedNode = xmlDocument.importNode(xmlDataDocument.getDocumentElement(), true);
	        xmlDocument.getDocumentElement().appendChild(importedNode);
	 
	        // Update the XFA form with imported XML data
	        xfa.setDatasetsNode(xmlDocument.getDocumentElement());
	        xfa.write(reader, fos);
	 
	        // Close the streams
	        fos.close();
	        reader.close();
	    }
	}

	    }
	}

