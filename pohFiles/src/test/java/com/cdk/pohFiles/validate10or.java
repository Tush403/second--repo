package com.cdk.pohFiles;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validate10or {


	    public static void main(String[] args) {
	        String[] expressions = {
	            "(((xfa.record.attributes.root.Deal.GenericFields.T6.value|| \"\"))+((xfa.record.attributes.root.Deal.GenericFields.T6.value|| \"\")))",
	           
	        };

	        // Define the pattern for the expression structure
	        Pattern pattern = Pattern.compile("\\(\\(.*?\\)\\+\\(.*?\\)\\)");

	        for (String expression : expressions) {
	            Matcher matcher = pattern.matcher(expression);
	            if (matcher.matches()) {
	                System.out.println("Expression structure is valid: " + expression);
	            } else {
	                System.out.println("Expression structure is invalid: " + expression);
	            }
	        }
	    }
	}

