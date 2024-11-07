package com.cdk.pohFiles;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;


public class expre5 {


	    public static void main(String[] args) {
	        String xmlFilePath = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\data.xml";
	        String expression = "Deal/DealNumber";

	        String extractedData = fetchDataFromXML(xmlFilePath, expression);
	        System.out.println("Extracted Data: " + extractedData);
	    }

	    // Method to fetch data from XML based on expression using XPath
	    private static String fetchDataFromXML(String xmlFilePath, String expression) {
	        try {
	            // Load XML file and create DocumentBuilder
	            File xmlFile = new File(xmlFilePath);
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(xmlFile);

	            // Create XPath object
	            XPathFactory xPathFactory = XPathFactory.newInstance();
	            XPath xPath = xPathFactory.newXPath();

	            // Compile XPath expression
	            XPathExpression xPathExpr = xPath.compile(expression);

	            // Evaluate XPath expression to get NodeList of matching nodes
	            NodeList nodeList = (NodeList) xPathExpr.evaluate(doc, XPathConstants.NODESET);

	            // Process the NodeList to extract data
	            StringBuilder result = new StringBuilder();
	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Element element = (Element) nodeList.item(i);
	                // Assuming the element contains text content
	                String textContent = element.getTextContent();
	                result.append(textContent).append(", ");
	            }

	            // Remove trailing comma and space
	            if (result.length() > 8) {
	                result.setLength(result.length() - 8);
	            }

	            return result.toString();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error fetching data from XML";
	        }
	    }
	}

