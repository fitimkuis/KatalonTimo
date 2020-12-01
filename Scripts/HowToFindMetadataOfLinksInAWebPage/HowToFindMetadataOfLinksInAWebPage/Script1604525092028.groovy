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

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObject as TestObject


/* We start here */
def url = 'https://www.google.fi/'

/* input field for search keyword*/
def tObjInputQ = new TestObject('q').addProperty('xpath', ConditionType.EQUALS,
	"//input[@name='q']")


/* Google Search button **/
def tObjInputBtnK = new TestObject('btnK').addProperty('xpath', ConditionType.EQUALS,	"//input[@name='btnK']")


/* Google Search button */
//def tObjInputBtnK = new TestObject('btnK').addProperty('css', ConditionType.EQUALS,	"#tsf > div:nth-child(2) > div.A8SBwf > div.FPdoLc.tfB0Bf > center > input.gNO89b")
//#tsf > div:nth-child(2) > div.A8SBwf > div.FPdoLc.tfB0Bf > center > input.gNO89b

/* link to the Katalon Offical site */
def tObjAnchorKatalon = new TestObject('katalonOfficial').addProperty('xpath', ConditionType.EQUALS,
	"//a[@href='https://www.katalon.com/']")

/* set of all links in a web page */
def tObjAnchors = new TestObject('allLinks').addProperty('xpath', ConditionType.EQUALS,
	"//a")

// open Google Search page
WebUI.openBrowser('')
WebUI.navigateToUrl(url)
WebUI.verifyElementPresent(tObjInputQ, 10)

// search pages with key 'katalon'
WebUI.setText(tObjInputQ, 'katalon')
WebUI.delay(2)

//WebUI.verifyElementPresent('Object Repository/HowToFindMetadataOfLinksInAWebPage/Page_Google_HowToFindMetadataOfLinksInAWebPage/span_Hyvksyn', 10)
WebUI.click(findTestObject('Object Repository/HowToFindMetadataOfLinksInAWebPage/Page_Google_HowToFindMetadataOfLinksInAWebPage/span_Hyvksyn'))
WebUI.click(findTestObject('Object Repository/HowToFindMetadataOfLinksInAWebPage/Page_Google_HowToFindMetadataOfLinksInAWebPage/input_Poista_btnK'))

//WebUI.click(tObjInputBtnK)


WebUI.waitForElementPresent(tObjAnchorKatalon, 10)

// look up all <a> elements in the search result page
List<WebElement> anchors = WebUI.findWebElements(tObjAnchors, 10)
WebUI.comment("anchors.size(): ${anchors.size()} --- this contains miscellaneous google pages")		// e.g, 117 links

// iterate over all <a> elements in the Search Result page to filter out valid href values
Set<String> links = new HashSet()
anchors.each {
	String linkUrl = it.getAttribute("href")
	if (linkUrl != null && !linkUrl.contains('google')) {
		links.add(linkUrl)
		WebUI.comment(linkUrl)
	}
}
WebUI.comment("links.size(): ${links.size()}")	// external valid links only; e.g, 14 links

// Using Katalon WebService API, we send GET request to check the response.
// We check the HTTP Status Code.
// We also check Location header in case of status=302
def builder = new RestRequestObjectBuilder()
links.each { link ->
	request = builder
		.withRestRequestMethod("GET")
		.withRestUrl(link)
		.build()
	ResponseObject response = WS.sendRequest(request)
	int statusCode = response.getStatusCode()
	WebUI.comment("Request to ${link} was responded with StatusCode ${statusCode}")
	// do anything special you like for each StatusCode values
	switch (statusCode) {
		case 302:
			WebUI.comment("--> was redirected to ${response.getHeaderField('Location')}")
	}
}

// Done
WebUI.closeBrowser()