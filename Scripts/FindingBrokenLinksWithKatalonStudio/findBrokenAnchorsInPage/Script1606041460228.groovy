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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebElement

import com.kazurayam.ksbackyard.LinkTestUtils
import com.kms.katalon.core.testobject.ConditionType


// String pageUrl is passed by the caller, or will default to the value defined in the Variables tab of this testcase

WebUI.openBrowser("")
WebUI.navigateToUrl(pageUrl)
WebUI.delay(5)   // wait until the page gets stable


// find all <a> elements with non-null href attribute
TestObject tObjAnchors =
	new TestObject("all anchors").addProperty("xpath", ConditionType.EQUALS,"//a")
List<WebElement> anchors = WebUI.findWebElements(tObjAnchors, 10)
List<String> hrefs =
	anchors.stream()
		.filter { we -> we.getAttribute('href') != null }
		.map { we -> we.getAttribute('href') }
		.collect()

// prepare buffer for messages
StringBuilder messageBuffer = new StringBuilder()
messageBuffer.append("*** All <a> elements in ${pageUrl} ***${System.lineSeparator}")

// Now we do the job!
int brokenLinksCount = LinkTestUtils.findBrokenLinks(hrefs, messageBuffer)

// print message
print(messageBuffer.toString())

WebUI.closeBrowser()
return brokenLinksCount