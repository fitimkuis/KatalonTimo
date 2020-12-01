package com.ini

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
import org.ini4j.*;
import java.io.File;
import org.apache.commons.configuration.HierarchicalConfiguration
import org.apache.commons.configuration.HierarchicalINIConfiguration
import org.apache.commons.configuration.SubnodeConfiguration


public class IniUtil {

	private String iniPath = System.getProperty("user.dir")+"\\inidata\\myinifile.ini";

	@Keyword
	public List<Object> readInitFile(){

		List <Object> lis = new ArrayList<>()

		Wini ini = new Wini(new File(iniPath));
		int age = ini.get("owner", "age", int.class);
		lis.add(age)
		double height = ini.get("owner", "height", double.class);
		lis.add(height)
		String server = ini.get("database", "server");
		lis.add(server)

		System.out.print("Age: " + age + "\n");
		System.out.print("Height: " + height + "\n");
		System.out.print("Server IP: " + server + "\n");
		return lis
	}

	@Keyword
	public void simpleIni(){
		Properties prop = new Properties();
		//c:\\myapp\\config.ini is the location of the ini file
		//ini file should look like host=localhost
		prop.load(new FileInputStream(iniPath));
		String port = prop.getProperty("port");
		System.out.print("Port: " + port + "\n");
	}

	@Keyword
	public void HierarchicalINIConfigurationExample(){

		HierarchicalINIConfiguration iniConfObj = new HierarchicalINIConfiguration(iniPath);

		// Get Section names in ini file
		Set setOfSections = iniConfObj.getSections();
		Iterator sectionNames = setOfSections.iterator();

		while(sectionNames.hasNext()){

			String sectionName = sectionNames.next().toString();

			SubnodeConfiguration sObj = iniConfObj.getSection(sectionName);
			Iterator it1 =   sObj.getKeys();

			while (it1.hasNext()) {
				// Get element
				Object key = it1.next();
				System.out.print("Key " + key.toString() +  " Value " +
						sObj.getString(key.toString()) + "\n");
			}
		}
	}
}
