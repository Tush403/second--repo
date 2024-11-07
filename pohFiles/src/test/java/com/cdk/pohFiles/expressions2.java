package com.cdk.pohFiles;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class expressions2 {

	    public static void main(String[] args) {
	        // Load XML file
	        File xmlFile = new File("C:\\Users\\tushar.s\\Desktop\\abc.xml");
	        System.out.println(xmlFile);
	        // Load expressions from Excel file
	        Map<String, String> expressions = loadExpressionsFromExcel("C:\\Users\\tushar.s\\Desktop\\abc.xlsx");
	        System.out.println(expressions);
	        // Evaluate expressions
	        for (Map.Entry<String, String> entry : expressions.entrySet()) {
	            String expression = entry.getKey();
	            String value = entry.getValue();
	            String result = evaluateExpression(expression);
	            System.out.println("Expression: " + expression);
	            System.out.println("Result: " + result);
	            // Search for value in XML file
	            // You can implement this part
	        }
	    }
	 
	    private static Map<String, String> loadExpressionsFromExcel(String filePath) {
	        // Implement loading expressions from Excel file
	        // Return a map with expressions and their corresponding values
	        return new HashMap<>();
	    }
	 
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
	    }
	}

