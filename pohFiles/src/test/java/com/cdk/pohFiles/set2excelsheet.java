package com.cdk.pohFiles;
import java.io.File;
import java.util.Arrays;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class set2excelsheet extends test1901{
	

	    public static void main (String [] args) {
//	
//	    	File folder = new File("C:\\Users\\tushar.s\\Desktop\\All POH Forms 1701\\");
//	    	 File[] files = folder.listFiles();
//	    	 try {
//				Files.walk(Paths.get("C:\\Users\\tushar.s\\Desktop\\All POH Forms 1701"))
//				 .filter(path -> path.toString().endsWith("Ply1"))
//				 .contains("Ply"))
//				 .forEach(System.out::println);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

	    	         File directory = new File("C:\\Users\\tushar.s\\Desktop\\All POH Forms 1701");
	    	         int folderCount = countFolders(directory);
	    	         System.out.println("Number of folders: " + folderCount);
	    	     }
	    	  
	    	     private static int countFolders(File directory) {
	    	         if (!directory.isDirectory()) {
	    	             return 0;
	    	         }
	    	  
	    	         int folderCount = 0;
	    	         File[] files = directory.listFiles();
	    	         if (files != null) {
	    	             for (File file : files) {
	    	                 if (file.isDirectory()) {
	    	                     folderCount++;
	    	                     folderCount += countFolders(file);
	    	                 }
	    	             }
	    	         }
	    	         return folderCount;
	    	     }
	    	
	    }
	   	     


