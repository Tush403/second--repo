package com.cdk.pohFiles;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
 
public class expression3 {

	    public static void main(String[] args) {
	        try {
	            // Step 1: Read the XML file
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse("C:\\Users\\tushar.s\\Desktop\\abc.xml");
	            doc.getDocumentElement().normalize();
	 
	            // Step 2: Parse the XML data
	            // Add your parsing logic here
	            
	            // Step 3: Evaluate the expression
	            String expression = "this.rawValue = (((xfa.record.attributes.root.Deal.GenericFields.T46.value)\"3\")>0)?(xfa.record.attributes.root.Deal.GenericCheckboxes.CB3.value):\"\"";
	          
	            private static String evaluateExpression(String expression) {
	    	        ScriptEngineManager manager = new ScriptEngineManager();
	    	        ScriptEngine engine = manager.getEngineByName("JavaScript");
	    	        try {
	    	            Object result = engine.eval(expression);
	    	            return result.toString();
	    	        } catch (ScriptException e) {
	    	            e.printStackTrace();
	    	            return "Error: Unable to evaluate expression";
	    	        }
	            // Add your expression evaluation logic here
	 
	            // Step 4: Output the result
	            System.out.println("Result of expression evaluation: " + expression);
//	        } catch (Exception e) {
//	            e.printStackTrace();
	        }
	    }
//	}

