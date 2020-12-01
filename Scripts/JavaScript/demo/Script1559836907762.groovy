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

WebUI.openBrowser('')

WebUI.navigateToUrl('file:///C:/Users/fitim/KatalonFromGitHub/KatalonProject/Include/JavaScript/prompt.html')

WebUI.click(findTestObject('Object Repository/JavaScript/Page_Input-demo/button_Click-me'))

def text = WebUI.getText(findTestObject('Object Repository/JavaScript/Page_Input-info/given-value'))

//def text = WebUI.getText(findTestObject('Object Repository/JavaScript/Page_Input-demo/p_5'))
print text

/*
TestObject to = findTestObject("Object Repository/myobj121")
WebElement element = WebUiCommonHelper.findWebElement(to, 30)
String value = findTestData("mydbfile").getValue("c10", i) //get value from database
String js = "arguments[0].value = '${value}';" //drefine javascript
WebUI.executeJavaScript(js, Arrays.asList(element)) //execute javascript

*/