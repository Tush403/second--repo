package com.cdk.pohFiles;
import org.apache.poi.ss.usermodel.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DemoScript_XML2Expre {

	    public static void main(String[] args) {
	        String excelFilePath = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\input.xlsx";
	        String xmlFilePath = "C:\\Users\\tushar.s\\Desktop\\JSExpressions\\data.xml";

	        try {
	            FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
	            Workbook workbook = WorkbookFactory.create(excelFile);
	            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

	            // Iterate through each row in the Excel sheet
	            for (Row row : sheet) {
	                if (row.getRowNum() == 0) {
	                    continue; // Skip header row
	                }

	                // Get the expression from the Excel cell (assuming expression is in the first column)
	                Cell expressionCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                String expression = expressionCell.getStringCellValue().trim();

	                // Process the expression to fetch data from XML
	                String extractedData = fetchDataFromXML(xmlFilePath, expression);

	                // Print the extracted data
	                System.out.println("Expression: " + expression);
	                System.out.println("Extracted Data: " + extractedData);
	                System.out.println();
	            }

	            workbook.close();
	            excelFile.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
	            if (result.length() > 2) {
	                result.setLength(result.length() - 2);
	            }

	            return result.toString();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error fetching data from XML";
	        }
	    }
	}


