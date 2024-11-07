package com.cdk.pohFiles;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.mvel2.MVEL;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class javascriptExpression {
	
	
 public static void main(String[] args) throws ScriptException {
	 
	         try {
	        
	             File xmlFile = new File("C:\\Users\\tushar.s\\Desktop\\abc.xml\\");
	             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	             Document doc = dBuilder.parse(xmlFile);
	             doc.getDocumentElement().normalize();
	  
	             // Step 2: Parse the XML to extract values based on node paths specified in expressions
	             String dealTypeValue = getValueByNodePath(doc, "root/deal/dealType");
	             String contactDateValue = getValueByNodePath(doc, "deal/deal/contactDate");
	  
	             // Step 3: Store the extracted values in a data structure (e.g., map)
	             Map<String, String> extractedValues = new HashMap<>();
	             extractedValues.put("dealType", dealTypeValue);
	             extractedValues.put("contactDate", contactDateValue);
	  
	             // Step 4: Evaluate expressions using the extracted values
	             var parser = new DOMParser();
	             var xmlDoc = parser.parseFromString(xmlString, "text/xml");
	             List<String> expressionsList = Arrays.asList(
	                     "xfa.record.attribute.root.deal.dealType.value == p",
	                     "xfa.record.attribute.root.deal.contactDate.value == xfa.record.attribute.deal.deal.dealType.value"
	             );
	  
	             for (String expression : expressionsList) {
	                 boolean result = evaluateExpression(expression, extractedValues);
	                 System.out.println("Expression: " + expression + " | Result: " + result);
	             }
	  
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
	  
	     // Method to extract value from XML based on node path
	     private static String getValueByNodePath(Document doc, String nodePath) {
	         try {
	             NodeList nodeList = doc.getElementsByTagName(nodePath);
	             return nodeList.item(0).toString();
//	            		 .getTextContent();
	         } catch (Exception e) {
	             e.printStackTrace();
	             return null;
	         }
	     }
	  
	     // Method to evaluate expressions using extracted values
	     private static boolean evaluateExpression(String expression, Map<String, String> extractedValues) {
	         // Substitute variable values in the expression
	         for (Map.Entry<String, String> entry : extractedValues.entrySet()) {
	             expression = expression.replace("xfa.record.attribute." + entry.getKey() + ".value", entry.getValue());
	         }
	         // Evaluate expression using MVEL library
	         return (boolean) MVEL.eval(expression);
	     }
	 }
