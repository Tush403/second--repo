package com.cdk.pohFiles;
import java.util.regex.*;



public class validate7 {
	


	    // Method to validate the expression
	    public static boolean validateExpression(String expression) {
	        // Define the regular expression for matching the expected structure
	        String regex = "^[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+\\s*=\\s*\\(\\(xfa\\.[a-zA-Z0-9_]+\\.attributes\\.root\\.[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+\\.value==null\\)\\?\"N/A\":\"\"\\)$";

	        // Create a Pattern object
	        Pattern pattern = Pattern.compile(regex);

	        // Create a Matcher object
	        Matcher matcher = pattern.matcher(expression);

	        // Check if the expression matches the expected structure
	        return matcher.matches();
	    }

	    public static void main(String[] args) {
	        // Example expression to validate
	        String expression = "this.rawValue = ((xfa.record.attributes.root.Deal.CoBuyer.CoBuyerFullName.value==null)?\"N/A\":\"\")";

	        // Validate the expression
	        boolean isValid = validateExpression(expression);

	        // Print the result
	        System.out.println("Expression: " + expression);
	        System.out.println("Is Valid: " + isValid);
	    }
	}

