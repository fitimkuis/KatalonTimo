import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

'used default profile'
WebUI.openBrowser('')

KeywordLogger log = new KeywordLogger()

log.logInfo('yourMsg')

WebUI.comment('myMessage')

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/a_MakeAppointment'))

//TODO recorded way to add test to inputbox
//WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'), 'John Doe')
WebUI.delay(1)

//TODO use javascript to add inputbox value
WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'), 
    30)

//TODO add text to inputbox
WebUI.executeJavaScript('arguments[0].value=\'John Doe\';', Arrays.asList(element))

//TODO set readOnly property to true
def x = WebUI.executeJavaScript('return document.getElementById(\'txt-username\').readOnly=true;', null)

println('DEBUG readOnly value x ' + x)

def y = WebUI.executeJavaScript('return document.getElementsByClassName(\'form-control\')[0].readOnly=false;', null)

println('DEBUG readOnly value y ' + y)

//TODO is it editable or not
def z = WebUI.executeJavaScript('return document.getElementsByClassName(\'form-control\')[0].readOnly;', null)

if (z) {
    println('DEBUG object is NOT editable ' + z)
} else {
    println('DEBUG object is editable ' + z)
}

//WebElement element2 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'),30)
//def resp = WebUI.executeJavaScript("arguments[0].readOnly='true';", Arrays.asList(element2))
//println ("DEBUG readOnly value "+resp)
//TODO check inputbox readonly/editable
boolean notEditable = WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'), 
    'John Doe', FailureHandling.OPTIONAL)

println('DEBUG boolean value ' + notEditable)

/*
 //TODO check inputbox readonly/editable
boolean notEditable = WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'), "John Doe", FailureHandling.OPTIONAL )
println ("DEBUG boolean value "+notEditable)

//TODO get inputbox value to variable
def txt = WebUI.getAttribute(findTestObject("Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username"), "value")
if (txt.length() > 0){
	println("DEBUG txt field value: "+txt)
}
else{
	throw new com.kms.katalon.core.exception.StepErrorException('Value required')
}
*/
WebUI.setEncryptedText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Password_password'), 
    GlobalVariable.pass)

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/button_Login'))

WebUI.selectOptionByValue(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/select_TokyoCURAHealthcareC'), 
    'Seoul CURA Healthcare Center', true)

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Applyforhospitalreadm'))

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Medicaid_programs'))

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/div_VisitDate(Required)_inpu'))

WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_VisitDate(Required)_vi'), 
    '15/02/2090')

WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/textarea_Comment_comment'), 'moi')

WebUI.verifyElementPresent(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/button_BookAppointment'), 
    30, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/button_BookAppointment'))

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/a_GotoHomepage'))

WebUI.closeBrowser()

