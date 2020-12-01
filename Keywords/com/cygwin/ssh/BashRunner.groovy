package com.cygwin.ssh

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;

public class BashRunner {

	private static final Logger log = Logger.getLogger(BashRunner.class.getName());

	public static List<String> run(String command) {

		List<String> arr = new ArrayList<>();

		Runtime run = Runtime.getRuntime();

		try {
			String line= "";
			def strArray = new String[1]
			strArray[0] = 'PATH=C:/cygwin64/bin'
			def strArray2 = new String[3]
			strArray2[0] = "c:\\cygwin64\\bin\\bash.exe"
			strArray2[1] = "-c"
			strArray2[2] = command
			//def env = ["PATH=C:/cygwin64/bin"]
			/////String[] env = new String[]{"PATH=C:/cygwin64/bin"};
			//def exeLine = ["c:\\cygwin64\\bin\\bash.exe","-c",command]
			//Process proc = run.exec(exeLine,env);
			Process proc = run.exec(strArray2[0],strArray2[1],strArray2[2]);
			/////Process proc = run.exec(new String[]{"c:\\cygwin64\\bin\\bash.exe", "-c", command},env);
			proc.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			while (br.ready()) {
				line = br.readLine();
				System.out.println(line);
				arr.add(line);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "failed " + command + " ", e);
		}
		return arr;
	}


	@Keyword
	public static void Main() throws IOException {

		String expectedValue = "height=176";

		//Process p = Runtime.getRuntime().exec(new String[]{"C:\\cygwin64\\bin\\ls.exe", "-la"});
		List<String> arr = new ArrayList<>();
		arr = BashRunner.run("less /home/myinifile.ini");
		//BashRunner.run("cd /home/; ls -la");
		for (String s: arr){
			if(s.equals(expectedValue)){
				System.out.println("Ini file contains expected value: "+s);
				break;
			}
			//System.out.println("list have values: "+s);
		}
	}
}

