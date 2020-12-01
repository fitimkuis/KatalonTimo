package readFile

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class read {

	@Keyword
	public List<String> readFileInList(String fileName) {
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			// do something
			e.printStackTrace();
		}
		return lines;
	}
}

/*
 BufferedReader br = new BufferedReader(new FileReader(path));
 String txtFile = null;
 String[] strArgs = null;
 try {
 StringBuilder sb = new StringBuilder();
 String line = br.readLine();
 while (line = br.readLine() != null){
 strArgs = line.split("\n\r");
 }
 //println("DEBUG first line "+strArgs[1])
 while (line != null) {
 sb.append(line);
 sb.append(System.lineSeparator());
 line = br.readLine();
 }
 txtFile = sb.toString();
 } finally {
 br.close();
 }
 KeywordLogger log = new KeywordLogger()
 log.logInfo(txtFile)
 // Java program to illustrate reading data from file
 // using nio.File
 //println("DEBUG first line "+strArgs[0])
 //log.logInfo(strArgs[0])
 */
