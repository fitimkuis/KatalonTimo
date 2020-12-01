package com.properties

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import internal.GlobalVariable

public class PropertiesUtil {

	Properties prop
	private String propertiesPath = System.getProperty("user.dir")+"\\Include\\PROPERTIES\\config.properties";

	public void WriteProperties(){

		try {

			OutputStream output = new FileOutputStream(propertiesPath)
			prop = new Properties();

			// set the properties value
			prop.setProperty("db.url", "localhost");
			prop.setProperty("db.user", "mkyong");
			prop.setProperty("db.password", "password");

			// save properties to project root folder
			prop.store(output, null);

			System.out.println(prop);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public String GetProperty(String strProp){

		try{
			InputStream input = new FileInputStream(propertiesPath)
			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("url4"));
			System.out.println(prop.getProperty("user4"));
			System.out.println(prop.getProperty("pass4"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}


		return prop.getProperty(strProp)
	}

	public Properties GetAllProperties(){

		try{
			InputStream input = new FileInputStream(propertiesPath)
			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			/*System.out.println(prop.getProperty("db.url"));
			 System.out.println(prop.getProperty("db.user"));
			 System.out.println(prop.getProperty("db.password"));*/

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop
	}
}
