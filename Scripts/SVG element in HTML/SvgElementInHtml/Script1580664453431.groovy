import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.highcharts.com/demo/line-basic')

// checking if a usual HTML node is accessible
//WebUI.waitForElementPresent(
//	findTestObject('Page_Basic line/a_Basic line'), 5, FailureHandling.OPTIONAL)
//WebUI.verifyElementPresent(
//	findTestObject('Page_Basic line/a_Basic line'), 5, FailureHandling.OPTIONAL)

// checking if SVG node is accessible by a Namespace-ignorant XPath; this fails
WebUI.verifyElementPresent(findTestObject('Page_Basic line/svg_namespace-ignorant'), 5, FailureHandling.OPTIONAL)

// checking if SVG node is accessible by a Namespace-aware XPath; this succeeds  //div[@id="container"]/div/*[namespace-uri() = "http://www.w3.org/2000/svg" and local-name()="svg"]
WebUI.verifyElementPresent(findTestObject('Page_Basic line/svg_ns-uri_local-name'), 5, FailureHandling.OPTIONAL)

WebUI.verifyElementPresent(findTestObject('Page_Basic line/svg_local-name'), 5, FailureHandling.OPTIONAL) //div[@id="container"]/div/*[local-name()="svg"]

WebUI.verifyElementPresent(findTestObject('Page_Basic line/svg_highcharts-title'), 5, FailureHandling.OPTIONAL) //div[@id="container"]/div/*[local-name()="svg"]/*[local-name()="text" and @class="highcharts-title"]

WebUI.verifyElementText(findTestObject('Page_Basic line/svg_highcharts-title'),
	"Solar Employment Growth by Sector, 2010-2016",
	FailureHandling.OPTIONAL)

def title = WebUI.getText(findTestObject('Page_Basic line/svg_highcharts-title'))
WebUI.comment("SVT Title: ${title}")

WebUI.closeBrowser()