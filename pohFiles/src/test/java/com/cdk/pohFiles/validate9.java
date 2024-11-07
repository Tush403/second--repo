package com.cdk.pohFiles;
import org.graalvm.polyglot.*;


public class validate9 {

	    public static void main(String[] args) {
	        String expression = "this.rawValue = (((xfa.record.attributes.root.Deal.DealType.value==\"P\")?xfa.record.attributes.root.Deal.RebateList.Rebate6.Desc.value:xfa.record.attributes.root.Deal.RebateList.Rebate6.Description.value))";
	        boolean isValid = validateJavaScriptExpression(expression);
	        System.out.println("Is the expression valid? " + isValid);
	    }

	    public static boolean validateJavaScriptExpression(String expression) {
	        try (Context context = Context.create()) {
	            context.eval("js", expression);
	            return true; // If evaluation succeeds, the expression is syntactically valid
	        } catch (PolyglotException e) {
	            // Expression evaluation failed, indicating syntax error
	            return false;
	        }
	    }
	}


