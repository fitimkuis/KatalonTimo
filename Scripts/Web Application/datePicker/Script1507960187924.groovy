import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://jqueryui.com/datepicker/')

WebUI.waitForJQueryLoad(5)

WebUI.switchToFrame(findTestObject('DatePicker/Page_Datepicker_ jQueryUI2/iframe_demo-frame'),10)

WebUI.click(findTestObject('DatePicker/Page_Datepicker_ jQueryUI2/input_datepicker'))

WebUI.click(findTestObject('DatePicker/Page_Datepicker jQueryUI/a_20'))

CustomKeywords.'datePicker.ReadOnly.readOnlyTrue'()

String txt = WebUI.getText(findTestObject('DatePicker/Page_Datepicker_ jQueryUI2/input_datepicker'))
println ("date is "+txt)
WebUI.verifyTextPresent(txt, false)

WebUI.closeBrowser()

