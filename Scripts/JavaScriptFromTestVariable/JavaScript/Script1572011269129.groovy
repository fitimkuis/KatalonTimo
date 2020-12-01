import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject


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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver

//debug this, set breakpoint to line WebUI.executeJavaScript(js, null), able to see variable value in browser console window
WebUI.openBrowser("https://katalon-demo-cura.herokuapp.com/")

println getTestObjectXpath(findTestObject('Object Repository/LoginPage-of-DemoApplication/a_MakeAppointment'))


String j = """var display = function(name) {
	print("Hello, I am a Javascript display function",name);
	return ("display function return",name)
}"""

WebUI.executeJavaScript(j, null)

String js = """
    var myjsvar = '${myvar}';
    console.log('myvar is: '+myjsvar);
    """
WebUI.executeJavaScript(js, null)



def getTestObjectXpath(TestObject obj) {
	return obj.getSelectorCollection().get(SelectorMethod.CSS)
}


def clickUsingJS2(TestObject to) {
	String path = to.getSelectorCollection().get(SelectorMethod.CSS)
	String js = """
    var path = '${path}';
    document.querySelector(path).click();
  """
	WebUI.executeJavaScript(js, null);
  }