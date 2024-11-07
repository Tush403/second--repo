package com.cdk.pohFiles;


public class validate13may {

	    public static void main(String[] args) {
	        String expression1 = "(((xfa.record.attributes.root.Deal.GenericFields.T5.value))==\"L\")?  (xfa.record.attributes.root.Deal.GenericCheckboxes.CB1.value):\"\"";
	        String expression2 = "(((xfa.record.attributes.root.Deal.GenericFields.T6.value))==\"O\")?  (xfa.record.attributes.root.Deal.GenericCheckboxes.CB2.value):\"\"";

	        if (isValidSyntax(expression1) && isValidSyntax(expression2)) {
	            System.out.println("Syntax is valid.");
	        } else {
	            System.out.println("Syntax is invalid.");
	        }
	    }

	    public static boolean isValidSyntax(String expression) {
	       
	        String[] parts = expression.split("\\?");

	   
	        if (parts.length != 2) {
	            return false;
	        }


	        String condition = parts[0].trim();
	        String value = parts[1].trim();

	     
	        if (!condition.startsWith("(((xfa.record.attributes.root.") || condition.endsWith(".value)")) {
	            return false;
	        }

	 
	        if (!value.startsWith("(") || !value.endsWith(".value)")) {
	            return false;
	        }

	    
	        if (!condition.contains("==") && !condition.contains("!==")) {
	            return false;
	        }

	        return true;
	    }

	}
