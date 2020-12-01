import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper 
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.openBrowser('')


//WebUI.executeJavaScript("document.getElementsByClassName('DayPicker-Day')[13].click()")

KeywordUtil log = new KeywordUtil()
log.logInfo("yourMsg")

WebUI.comment("myMessage")

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/a_MakeAppointment'))

//WebUI.executeJavaScript("return document.getElementById('login').innerHTML", false).toString()
//System.out.println("Text on hompage is- "+text );

//TODO recorded way to add test to inputbox
//WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'), 'John Doe')
WebUI.delay(1)
//TODO use javascript to add inputbox value
WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'),30) 
//TODO add text to inputbox
WebUI.executeJavaScript("arguments[0].value='John Doe';", Arrays.asList(element))

//TODO set readOnly property to true
def x = WebUI.executeJavaScript("return document.getElementById('txt-username').readOnly=true;", null)
println ("DEBUG readOnly value x "+x)

/*
WebDriver driver = DriverFactory.getWebDriver()
JavascriptExecutor js = ((driver) as JavascriptExecutor)
def archiveID = js.executeScript("return document.getElementById('txt-username').innerHTML;")
println(archiveID)*/

def y = WebUI.executeJavaScript("return document.getElementsByClassName('form-control')[0].readOnly=false;", null)
println ("DEBUG readOnly value y "+y)

//TODO is it editable or not
def z = WebUI.executeJavaScript("return document.getElementsByClassName('form-control')[0].readOnly;", null)
if (z){
	println ("DEBUG object is NOT editable "+z)
}
else{
	println ("DEBUG object is editable "+z)
}

//WebElement element2 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'),30)
//def resp = WebUI.executeJavaScript("arguments[0].readOnly='true';", Arrays.asList(element2))
//println ("DEBUG readOnly value "+resp)


//TODO check inputbox readonly/editable
boolean notEditable = WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Username_username'), "John Doe", FailureHandling.OPTIONAL )
println ("DEBUG boolean value "+notEditable)


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
    'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/button_Login'))

WebUI.selectOptionByValue(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/select_TokyoCURAHealthcareC'), 
    'Seoul CURA Healthcare Center', true)

//TODO check inputbox readonly/editable
clickable = WebUI.verifyElementClickable(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Applyforhospitalreadm'))
if(clickable){
	println ("DEBUG**** clickable "+clickable)
	
}else{
	println ("DEBUG**** not clickable "+clickable)
}

WebDriver driver = DriverFactory.getWebDriver()

//List<WebElement> elements = driver.findElements(By.xpath("//checkbox"));
/////List<WebElement> elements = driver.findElements(By.xpath("//input[@type='checkbox']"));
List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col-sm-offset-5 col-sm-4']/label"));
//println ("**************DEBUG************** "+elements)

ArrayList<String> chxClickable = new ArrayList<String>()
ArrayList<String> chxNotClickable = new ArrayList<String>()
for (WebElement el: elements){
	clickable = WebUI.verifyElementClickable(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Applyforhospitalreadm'))
	if(clickable){
		println ("DEBUG**** clickable "+clickable)
		println"${el.getText()}"
		chxClickable.add("${el.getText()}")
		//println "${el.getAttribute('value')}"
		//println "${el.getTagName()}"
	
	}else{
		chxNotClickable.add("${el.getText()}")
		println ("DEBUG**** not clickable "+clickable)
		//println "${el.getAttribute('value')}"
	}
}

for (String s : chxClickable){
	
	println ("here are all enabled checkboxes "+s)
}

for (String k : chxNotClickable){
	
	println ("here are all Not enabled checkboxes "+k)
}
/*
List<String> chxList = new ArrayList<>()
//this line collects all the checkboxes, enabled and disabled
//make sure to use the object with the locator of "type"
java.util.List<WebElement> checkBox = WebUiCommonHelper.findWebElements(findTestObject("Object Repository/CheckBox"), 30)
//checks if the checkbox is enabled then it will be clicked otherwise it will be ignored. . .
for (int index = 0; index <= checkBox.size(); index++)
{
	 if (checkBox[index].getAttribute("disabled") != null)
	 {
			// do nothing
	 }
	 else
	 {
		   checkBox[index].click()
		   //chxList.add("${checkBox[index].getText()}")
		   //println ("**************DEBUG************** "+"${checkBox[index].getText()}")
	 }
}*/

boolean editable = WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Applyforhospitalreadm'))
/*if(editable){
	println ("DEBUG editable "+editable)
}else{
	println ("DEBUG not editable "+editable)
}*/


//WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Applyforhospitalreadm'))

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_Medicaid_programs'))

WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/div_VisitDate(Required)_inpu'))

WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_VisitDate(Required)_vi'), 
    '15/02/2090')

WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/textarea_Comment_comment'), 'moi')

WebUI.verifyElementPresent(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/button_BookAppointment'), 30, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/button_BookAppointment'))

//////WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/a_GotoHomepagexx'))

WebUI.closeBrowser()

