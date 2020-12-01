package com.file.size

import java.nio.file.DirectoryNotEmptyException
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths

import org.apache.commons.io.FileUtils

import com.kms.katalon.core.annotation.Keyword

public class FileSizeUtil {
	
	@Keyword
	public double verifyFileSize(File file){
		
		//File file =new File("c:\\java_xml_logo.jpg");
		double bytes
		
		if(file.exists()){
			
			bytes = file.length();
			double kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);
			double gigabytes = (megabytes / 1024);
			double terabytes = (gigabytes / 1024);
			double petabytes = (terabytes / 1024);
			double exabytes = (petabytes / 1024);
			double zettabytes = (exabytes / 1024);
			double yottabytes = (zettabytes / 1024);
			
			/*System.out.println("bytes : " + bytes);
			System.out.println("kilobytes : " + kilobytes);
			System.out.println("megabytes : " + megabytes);
			System.out.println("gigabytes : " + gigabytes);
			System.out.println("terabytes : " + terabytes);
			System.out.println("petabytes : " + petabytes);
			System.out.println("exabytes : " + exabytes);
			System.out.println("zettabytes : " + zettabytes);
			System.out.println("yottabytes : " + yottabytes);*/
		}else{
			 System.out.println("File does not exists!");
		}
		
		return bytes
	}
	
	@Keyword
	public File getLatestFilefromDir(String dirPath){
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
	
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
		   if (lastModifiedFile.lastModified() < files[i].lastModified()) {
			   lastModifiedFile = files[i];
		   }
		}
		return lastModifiedFile;
	}
	
	@Keyword
	public void copyFilesToTempFolder(String source, String destination){
		
		System.out.println("sourcelocation "+source);
		System.out.println("destlocation "+destination);
		
		File sourceLocation= new File(source);
		File targetLocation = new File(destination);
		
		FileUtils.copyDirectory(sourceLocation, targetLocation);
	}
	
	@Keyword
	public void deleteFile(String path){
		  
		// this deletes the file 
		boolean fileSuccessfullyDeleted =  new File(path).delete()  
		println fileSuccessfullyDeleted
	}
}
