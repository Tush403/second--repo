package com.cdk.pohFiles;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class extractMapperFile {

	public static void main(String[] args) {
		try {
			
			BufferedReader reader = new BufferedReader(
			new FileReader("C://Users//tushar.s//Desktop//Others//30883-1-FI-Ply1"));
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Sheet1");
			
//			Set s1=new HashSet();
			
			String line;
			int rowNum = 0;

			while ((line = reader.readLine()) != null) {

				if (line.contains("text")) {
					line = line.replace("text", "").replace("[^a-zA-Z0-9 _\"]", "").
							replace("text", "").replace("(", "").replace("-", "").replace("/", "").replace(")", "")
							.replace("\"", "").replace("[-+^]*", " ").replace(",", "").replace("#", "").replace("<", "")
							.replace(">", "").replace("++", "").replace("MM/DD/YYYY", "").replace("DMMDDYYYY", "")
							.replace("format", "").replace("DEAL", "").replace("CUST", "");
//					s1.add(line);
					 System.out.println(line);
					if (line.contains("+") || (line.contains("?") || (line.contains(":")))) {
						String[] words = line.split(" ");
						List<String> wordList = Arrays.stream(words)
								.map(word -> word.replace("+", "").replace(" ","").replace("?", "").replace(":", "").replace(" ",""))
								.collect(Collectors.toList());
						wordList.forEach(System.out::println);
//						s1.add(wordList);
					
					}
				}
//					if (line.contains("+") || (line.contains("?") || (line.contains(":")))) {
//						line.trim();
//						System.out.println(line);
//					}
//					List<String> myList = new ArrayList<String>(Arrays.asList(line.split(",")));
//					if(myList.contains("+")) {
//						myList.clear();
//					myList.forEach(System.out::println);
					
//				}
//					System.out.println(myList);
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
//