package com.cdk.pohFiles;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class validate1605 {



	   
	    private static final String EXPRESSION_REGEX = "\\(\\(\\w+\\.\\w+\\.\\w+\\.\\w+\\.\\w+\\.\\w+\\.T\\d+\\.value\\)==\"\\d+\"\\)\\?\\s*\\(\\w+\\.\\w+\\.\\w+\\.\\w+\\.\\w+\\.\\w+\\.CB\\d+\\.value\\):\"\"";

	  
	    public static boolean validateExpression(String expression) {
	        Pattern pattern = Pattern.compile(EXPRESSION_REGEX);
	        Matcher matcher = pattern.matcher(expression);
	        return matcher.matches();
	    }

	    
	    public static void main(String[] args) {
	        String[] expressions = {
	            "((xfa.record.attributes.root.Deal.GenericFields.T49.value)==\"1\")?  (xfa.record.attributes.root.Deal.GenericCheckboxes.CB2.value):\"\"",
	            "((xfa.record.attributes.root.Deal.GenericFields.T50.value)==\"2\")?  (xfa.record.attributes.root.Deal.GenericCheckboxes.CB3.value):\"\"",
	            "((xfa.record.attributes.root.Deal.GenericFields.T51.value)==\"3\")?  (xfa.record.attributes.root.Deal.GenericCheckboxes.CB4.value):\"\"",
	            "((xfa.record.attributes.root.Deal.GenericFields.T52.value)==\"4\")?  (xfa.record.attributes.root.Deal.GenericCheckboxes.CB5.value):\"\"",
	            "(((xfa.record.attributes.root.Deal.GenericFields. T53.value)==\"5\"))?  (xfa.record.attributes.root.Deal.GenericCheckboxes.CB6.value):\"\""
	        };

	        for (String expression : expressions) {
	            boolean isValid = validateExpression(expression);
	            System.out.println("Expression: " + expression + " is " + (isValid ? "valid" : "invalid"));
	        }
	    }
	}






