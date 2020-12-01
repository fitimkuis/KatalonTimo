import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.configuration.RunConfiguration

//CustomKeywords.'demo.PythonKeywords.helloWorld'(11, 15)

String csv1 = System.getProperty("user.dir")+"\\python\\modules\\file1.csv";
String csv2 = System.getProperty("user.dir")+"\\python\\modules\\file2.csv";
//println csv1
//println csv2

CustomKeywords.'demo.PythonKeywords.helloWorld'(10, 5)

//CustomKeywords.'demo.PythonKeywords.compareTwoCsv'(csv1, csv2)