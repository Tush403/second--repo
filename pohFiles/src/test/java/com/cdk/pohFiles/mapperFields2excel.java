package com.cdk.pohFiles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taodigital.pohtool.exception.GenericTextFieldException;
import com.taodigital.pohtool.model.KeyStoneObject;
import com.taodigital.pohtool.model.KeyStoneVariables;
import org.springframework.stereotype.Service;



public class mapperFields2excel {

	    @SuppressWarnings("unchecked")
	    public static void UpdateKeyStoneLogs(KeyStoneObject keyFormObject) throws GenericTextFieldException {
	        File outFile = null;
	        try {
	            outFile = new File("./jsonLogs/keyStoneVariableLogs.json");
	            if (outFile.length() == 0) {
	                outFile.getParentFile().mkdirs();
	                FileWriter emptyWriter = new FileWriter(outFile); // writing back to the file
	                emptyWriter.write("[]");
	                emptyWriter.flush();
	                emptyWriter.close();
	            }
	            JSONParser jsonParser = new JSONParser();
	            JSONArray arr = (JSONArray) jsonParser.parse(new FileReader(outFile));
	            // Check if the form number already exists in the logs
	            for (Object obj : arr) {
	                JSONObject jsonObject = (JSONObject) obj;
	                String existingFormNumber = (String) jsonObject.get("formNumber");
	                if (existingFormNumber != null && existingFormNumber.equals(keyFormObject.getFormNumber())) {
	                    // Merge the fields if the form number matches
	                    JSONArray existingFields = (JSONArray) jsonObject.get("fields");
	                    Set<String> existingFieldNames = new HashSet<>();
	                    // Extract existing field names
	                    for (Object fieldObj : existingFields) {
	                        JSONObject fieldJson = (JSONObject) fieldObj;
	                        String existingFieldName = (String) fieldJson.get("fieldName");
	                        existingFieldNames.add(existingFieldName);
	                    }
	                    // Add new fields if they don't already exist
	                    for (KeyStoneVariables variable : keyFormObject.getfields()) {
	                        String newFieldName = variable.getFieldName();
	                        if (!existingFieldNames.contains(newFieldName)) {
	                            JSONObject newFieldObject = new JSONObject();
	                            newFieldObject.put("fieldName", newFieldName);
	                            existingFields.add(newFieldObject);
	                        }
	                    }
	                    // Save the updated JSON array to the file
	                    FileWriter fileWriter = new FileWriter(outFile);
	                    fileWriter.write(arr.toJSONString());
	                    fileWriter.flush();
	                    fileWriter.close();
	                    return;  // Exit the method since the form number already exists
	                }
	            }
	            // If the form number does not exist, add the new entry
	            JSONObject jsonObj = new JSONObject();
	            jsonObj.put("formNumber", keyFormObject.getFormNumber());
	            JSONArray newFields = new JSONArray();
	            for (KeyStoneVariables variable : keyFormObject.getfields()) {
	                JSONObject fieldObject = new JSONObject();
	                fieldObject.put("fieldName", variable.getFieldName());
	                newFields.add(fieldObject);
	            }
	            jsonObj.put("fields", newFields);
	            arr.add(jsonObj);
	            FileWriter fileWriter = new FileWriter(outFile); // writing back to the file
	            fileWriter.write(arr.toJSONString());
	            fileWriter.flush();
	            fileWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new GenericTextFieldException(outFile + " All keystone Field Logs Are Not Updated");
	        } catch (ParseException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	public  void keystoneJsonObjects (String path, String formNumber)throws GenericTextFieldException {
	 
	        KeyStoneVariables keyVariables=null;
			Set<KeyStoneVariables> keyStoneVariablesLogs=new LinkedHashSet<KeyStoneVariables>();
			KeyStoneObject  keyFormObject=new KeyStoneObject();
	              	 File filePath = new File(path);
	                Set<String> uniqueFieldNames = new LinkedHashSet<>();
	 
	                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
	                    String line;
	                    while ((line = bufferedReader.readLine()) != null) {
	                        List<String> fieldNamesFromText = processFunction(line);
	                        for (String fieldName : fieldNamesFromText) {
	                            uniqueFieldNames.add(fieldName);
	                        }
	                    }
	 
	                    // Create JSON objects for original field names
	                    for (String fieldName : uniqueFieldNames) {
	                        keyVariables=new KeyStoneVariables();
	                        keyVariables.setFieldName(fieldName);
	                       // keyVariables.setFieldType("text");
	                        keyStoneVariablesLogs.add(keyVariables);
	                    }
	                    if (keyStoneVariablesLogs != null && keyStoneVariablesLogs.size()>0) {				
	                        keyFormObject.setfields(keyStoneVariablesLogs);
	                        keyFormObject.setFormNumber(formNumber);
	                        UpdateKeyStoneLogs(keyFormObject);
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	 
	                System.out.println("completed"); // Printing the formatted JSON array
	            }
	            private static List<String> processFunction(String line) {
	                Pattern pattern = Pattern.compile("(text|if)\\(([^)]+)\\s*;?");
	 
	                Matcher matcher = pattern.matcher(line);
	                List<String> fieldNames = new ArrayList<>();
	                boolean insideQuotes = false;
	 
	                while (matcher.find()) {
	                    String textGroup = matcher.group(1);
	                    String ifGroup = matcher.group(2);
	 
	                    String[] splitNames;
	                    if ("if".equals(textGroup.toLowerCase())) {
	                        splitNames = ifGroup.split(":|\\s|\\+|\\,|\\=|\\<|\\>|\\?|\\-|\\[");
	                    } else {
	                        splitNames = line.split(":|\\s|\\+|\\,|\\=|\\<|\\>|\\?|\\-|\\[|\\*|\\/|\\(");
	                    }
	 
	                    for (String splitName : splitNames) {
	                        if (splitName.startsWith("\"")) {
	                            insideQuotes = true;
	                        }
	 
	                        // Remove special characters from the split name
	                        splitName = splitName.replaceAll("[^a-zA-Z0-9 _\"]", "")
	                        .replace("text", "").replace("eval", "")
	                        .replace("left", "").replace("if", "")
	                        .replace("page_layout", "")
	                        .replace("word", "")
	                        .replace("chr252", "")
	                        .replace("len", "")
	                        .replace("upper", "")
	                        .replace("right", "")
	                        .replace("trim", "")
	                        .replace("replace", "")
	                        .replace("Null", "")
	                        .replace("instr", "");
	 
	 
	                        if (insideQuotes && splitName.endsWith("\"")) {
	                            insideQuotes = false;
	                            continue;
	                        }
	 
	                        if (insideQuotes) {
	                            continue;
	                        }
	 
	                        if (splitName.toLowerCase().contains("x")) {
	                            String check = "X";
	                            fieldNames.add(check.trim());
	                        }
	                        if (splitName.trim().startsWith("format") || splitName.trim().startsWith("XXX")
	                                || splitName.trim().startsWith("ZZZ") || splitName.startsWith("YY")) {
	                            continue;
	                        }
	                        if (splitName.length() >= 4) {
	                            splitName = splitName.replace("3OSLBuyerDealerDefined1", "BuyerDealerDefined1");
	 
	                            fieldNames.add(splitName.trim());
	                        }
	                    }
	                }
	 
	                return fieldNames;
	            }
	 
			
	        
}
