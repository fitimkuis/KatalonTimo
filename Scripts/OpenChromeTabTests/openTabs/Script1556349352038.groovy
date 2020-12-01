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

//Add to the body of any test case
WebUI.openBrowser('https://www.katalon.com/')
WebUI.executeJavaScript('window.open();', [])
currentWindow = WebUI.getWindowIndex()
println currentWindow
//Switches tab #1
WebUI.switchToWindowIndex(currentWindow + 1)
WebUI.navigateToUrl('https://www.google.lk')
currentWindow2 = WebUI.getWindowIndex()
println currentWindow2
//Switches tab #0
WebUI.switchToWindowIndex(currentWindow)
currentWindow3 = WebUI.getWindowIndex()
println currentWindow3
WebUI.delay(5)