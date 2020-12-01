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
import com.kms.katalon.core.testobject.ConditionType


//search case-insensitivity text with xpath
// //*[matches(text(), ‘^some text$’, ‘i’)]
//xpath= //*[matches(text(), '(.+?)', 'i'), "some text"]
//text() is the ‘attribute’ you’re checking
//‘(.+?)’ is a regex pattern to match
//‘i’ is a flag indicating case-insensitivity

WebUI.openBrowser('')

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

//WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/a_MakeAppointment'))//

//show existing one xpath
//WebUI.waitForElementVisible(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/xpath'))//

myPredefinedObject = findTestObject('xpathTestRepository/Page_CURA-HealthcareService/xpath')
println ("**********DEBUG*************"+myPredefinedObject)

//get object xpath properties
myPredefinedObject2 = findTestObject('xpathTestRepository/Page_CURA-HealthcareService/a_MakeAppointment')
def properties = myPredefinedObject2.getXpaths()
println ("**********DEBUG XPATH PROPERTIES*************"+properties)
for (def prop : properties){
	println("name: "+prop.name+ "  value "+prop.value)
}

println ("**********DEBUG PROPERTIES*************"+properties)
def attributes = myPredefinedObject2.properties
for (def att : attributes){
	println("name: "+att.name+ "  value "+att.value)
}



// Create a new object programmatically
myNewObject = new TestObject("myNewObject")
// Add property to Test Object, a property is defined by:
//  property name,
//  condition type,
//  property value,
//  a boolean value to indicate if the property will be used to identify the object during execution
myNewObject.addProperty("xpath", ConditionType.EQUALS, "//header[@id='top']/div/a", true)

//def testObject = findTestObject('xpathTestRepository/Page_CURA-HealthcareService/xpath', [('id') : '{btn-make-appointment}'])

//change xpath
//new_xpath = WebUI.modifyObjectProperty(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/xpath'),'xpath', 'equals', "//a[contains(text(),'Make Appointment')]",true )
///WebUI.click(new_xpath)//use new xpath locator
WebUI.click(myNewObject)//use new relative xpath locator

//WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/xpath'))//

WebUI.setText(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/input_Username_username'), 
    'John Doe')

WebUI.setEncryptedText(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/input_Password_password'), 
    'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')

WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/button_Login'))

WebUI.selectOptionByValue(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/select_TokyoCURA-HealthcareC'), 
    'Hongkong CURA Healthcare Center', true)

WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/input_Apply-for-hospital-readm'))

WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/input_Medicaid_programs'))

WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/div_VisitDate(Required)_inpu'))

WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/td_30'))

WebUI.setText(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/textarea_Comment_comment'), 
    'this is comment')

WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/button_BookAppointment'))

WebUI.click(findTestObject('xpathTestRepository/Page_CURA-HealthcareService/h2_AppointmentConfirmation'))

WebUI.verifyTextPresent('Make Appointment', false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()
