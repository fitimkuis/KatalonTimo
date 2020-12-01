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

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.testng.Assert as Assert
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory

import helper.HelperUtils

CustomKeywords.'helper.HelperUtils.addGlobalVariable'('testURL', 'katalon.com')
println GlobalVariable.testURL


//global variable default value
println "global variable default value "+GlobalVariable.testVariable
//give new value for global variable in testsuite1
CustomKeywords.'com.global.variables.CreateGlobalVariables.addGlobalVariable'('testVariable', 999)
println "new value given in testsuite1 "+GlobalVariable.testVariable

'TestCase variable'
println "default local variable "+var1
int a = 1000
var1 = a
println "value changed "+var1
//import com.kms.katalon.core.testcase.TestCaseFactory.findTestCaseimport
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//WebUI.closeBrowser()

String downloadPath = "C:\\Users\\fitimkuis\\Desktop\\data\\pdf\\"
Map<String, Object> chromePrefs = new HashMap<String, Object>()
chromePrefs.put("download.default_directory", downloadPath)
chromePrefs.put("download.prompt_for_download", false)
chromePrefs.put("pdfjs.disabled", true);
System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeOptions options = new ChromeOptions()
//options.addArguments("--headless")
options.setExperimentalOption("prefs", chromePrefs)
WebDriver driver = new ChromeDriver(options)
driver.get('http://demoaut.katalon.com/')
WebUI.callTestCase(findTestCase('GlobalVarBetweenTestSuites/forTestSuite2'), [('driver') : driver], FailureHandling.CONTINUE_ON_FAILURE)
println ("new value given in testcase 2 "+GlobalVariable.testURL)
