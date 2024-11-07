package com.cdk.pohFiles;

public class validate1005 {


	    public static void main(String[] args) {
	        String jsExpression = "(((xfa.record.attributes.root.Deal.GenericFields.T16.value) = \"B\" || (xfa.record.attributes.root.Deal.GenericFields.T16.value) == \"BC\" ) ? xfa.record.attributes.root.Deal.Buyer.BuyerCSZ.value : ((xfa.record.attributes.root.Deal.GenericFields.T16.value) == \"C\" || (xfa.record.attributes.root.Deal.GenericFields.T16.value) == \"CB\" ) ? xfa.record.attributes.root.Deal.CoBuyer.CoBuyerCSZ.value : ((xfa.record.attributes.root.Deal.GenericFields.T16.value) == \"O\" ) ? (xfa.record.attributes.root.Deal.GenericFields.T17.value) : xfa.record.attributes.root.Deal.Buyer.BuyerCSZ.value)";

	        String result = checkSyntax(jsExpression);
	        System.out.println(result);
	    }

	    public static String checkSyntax(String expression) {
	        // Count opening and closing parentheses
	        int openParentheses = 0;
	        int closeParentheses = 0;

	        // Iterate over each character in the expression
	        for (char c : expression.toCharArray()) {
	            if (c == '(') {
	                openParentheses++;
	            } else if (c == ')') {
	                closeParentheses++;
	            }
	        }

	        // Check if the number of opening and closing parentheses match
	        if (openParentheses == closeParentheses) {
	            // Check if '?' and ':' are present and in correct order
	            int questionMarkIndex = expression.indexOf('?');
	            int colonIndex = expression.indexOf(':');
	            
	            if (questionMarkIndex != -1 && colonIndex != -1 && questionMarkIndex < colonIndex) {
	                return "Syntax is correct!";
	            } else {
	                return "Syntax error: Missing or misplaced '?' or ':'";
	            }
	        } else {
	            return "Syntax error: Mismatched parentheses!";
	        }}}
	  

