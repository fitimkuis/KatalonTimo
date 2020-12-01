import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testdata.InternalData as InternalData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

InternalData data = findTestData('Login/LoginTestData')

for (def index : (0..data.getRowNumbers() - 1)) {
    'Invoke Browser and Navigate to Demo Application'
    WebUI.openBrowser('')
    WebUI.navigateToUrl(GlobalVariable.URL_Application)
    'Wait till the page load'
    //WebUI.waitForPageLoad(10)´
	WebUI.click(findTestObject('LoginPage-of-DemoApplication/a_MakeAppointment'))
    'Enter the value of username (From Internal Test Data)'
	WebUI.setText(findTestObject('LoginPage-of-DemoApplication/input_username'), data.internallyGetValue("user", index))	
    'Enter the value of password (From Internal Test Data)'
    WebUI.setText(findTestObject('LoginPage-of-DemoApplication/input_password'), data.internallyGetValue("pass", index))
    'Click on Login Button\\r\\n'
    WebUI.click(findTestObject('LoginPage-of-DemoApplication/button_Login'))
    //WebUI.verifyElementPresent(findTestObject('MakeAppointmentPage/btn_BookAppointment'), GlobalVariable.SmallTimeout)
    'Close the Browser'
    WebUI.closeBrowser()
}

