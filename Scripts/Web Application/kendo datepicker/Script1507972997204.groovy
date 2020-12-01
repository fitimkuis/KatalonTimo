import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demos.telerik.com/kendo-ui/datetimepicker/index')

//WebUI.click(findTestObject('Kendo/Page_KendoUI-Date-and-Time-picker/span_k-icon-k-i-calendar'))

//WebUI.click(findTestObject('Kendo/Page_KendoUI-Date-and-Time-picker/a_20'))

/*
def response = WebUI.getText(findTestObject('Kendo/Page_KendoUI-Date-and-Time-picker/input_datetimepicker'))
println "field value is: "+response
if (!response){
	throw new com.kms.katalon.core.exception.StepErrorException('Value required')
}
else{
	println("DEBUG txt field value: "+response)
}*/


WebUI.takeScreenshot("C:\\Users\\fitim\\Desktop\\Katalon Studio\\screenshots\\picture.png")

WebUI.closeBrowser()

