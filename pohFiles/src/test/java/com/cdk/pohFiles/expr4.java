package com.cdk.pohFiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;


public class expr4 {

	    public static void main(String[] args) {
	        try {
	            // Load and parse the XML file
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse("C:\\Users\\tushar.s\\Desktop\\JSExpressions\\data.xml");
	 
	            // Create XPath object
	            XPath xpath = XPathFactory.newInstance().newXPath();
	 
	            // Define your XPath expressions
	            String[] expressions = {
	                "(root.Deal.GenericFields.T22.value)==\"Y\"?(eval(eval(xfa.record.attributes.root.Deal.GenericFields.T21.value))\"N/A\")):(\"\")",
	            //    "(((eval(xfa.record.attributes.root.Deal.GenericFields.T80.value)==0? 0 :(eval(xfa.record.attributes.root.Deal.GenericFields.T81.value)))))",
	                // Add more expressions as needed
	            };
	 
	            // Evaluate each XPath expression
	            for (String expression : expressions) {
	                // Compile the XPath expression
	                XPathExpression expr = xpath.compile(expression);
	 
	                // Evaluate the XPath expression against the document
	                Object result = expr.evaluate(document, XPathConstants.NODESET);
	 
	                // Process the results
	                if (result instanceof NodeList) {
	                    NodeList nodes = (NodeList) result;
	                    for (int i = 0; i < nodes.getLength(); i++) {
	                        System.out.println(nodes.item(i).getNodeValue());
	                        // Process each node as needed
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
