package com.cdk.pohFiles;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class expr3 {

	    public static void main(String[] args) {
	        try {
	            
	            File xmlFile = new File("C:\\Users\\tushar.s\\Desktop\\abc.xml");
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document xmlDoc = dBuilder.parse(xmlFile);
	 
	          
	            List<String> expressions = getExpressions();
	 
	           
	            evaluateExpressions(xmlDoc, expressions);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private static List<String> getExpressions() {
	     
	        List<String> expressions = new ArrayList<>();
//	        expressions.add("this.rawValue = (((xfa.record.attributes.root.Deal.GenericFields.T39.value))==\"Y\")?(\"\"\"\".188.656\"Buyer\"\"role==Buyertype==signatureprint_date==nomessage_typeface==Arialmessage_size==10message_weight==regularmessage_style==normal\"):\"\"");
//	        expressions.add("this.rawValue = (((xfa.record.attributes.root.Deal.GenericFields.T46.value)\"3\")>0)?(xfa.record.attributes.root.Deal.GenericCheckboxes.CB3.value):\"\"");
	        expressions.add("record.attributes.root.Deal.Buyer.Id.value|| \"\")");
//	        expressions.add("text((BuyerDriverLicense<>\"\") ? BuyerDriverLicense : Aux4");
	        expressions.add("6 + 7");
//	        expressions.add("let x==8? 8:9");
	        return expressions;
	    }
	 
	    private static void evaluateExpressions(Document xmlDoc, List<String> expressions) throws ScriptException {
	    	Engine engine = Engine.newBuilder()
	                .option("engine.WarnInterpreterOnly", "false")
	                .build();
	    	Context ctx = Context.newBuilder("js").engine(engine).build();
	    	
	    	
//	        ScriptEngineManager manager = new ScriptEngineManager();
//	        ScriptEngine engine = manager.getEngineByName("graal.js");
	    	
	    	
	        for (String expression : expressions) {
	            String modifiedExpression = replaceXMLDataReferences(xmlDoc, expression);
 
				Object result = ctx.eval("js", modifiedExpression);
				
//	                Object result = ctx.eval(modifiedExpression);
				
				System.out.println("Expression: " + expression);
				System.out.println("Result: " + result);
	        }
	    }
	 
	    private static String replaceXMLDataReferences(Document xmlDoc, String expression) {
	        NodeList nodeList = xmlDoc.getElementsByTagName("*");
	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Node node = nodeList.item(i);
	            String nodeName = node.getNodeName();
	            String nodeValue = node.getTextContent();
	            expression = expression.replace("xfa.record.attributes.root.Deal." + nodeName + ".value", nodeValue);
	        }
	        return expression;
	    }
	}

