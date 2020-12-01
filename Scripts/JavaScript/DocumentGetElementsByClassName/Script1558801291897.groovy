import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/*
 @Keyword
static clickJS (TestObject to, int timeout) {
    WebUI.waitForElementVisible(to, timeout)
    try {
        WebUI.click(to)
    }
    catch (Exception e) {
        WebDriver driver = DriverFactory.getWebDriver()
        WebElement element = WebUiCommonHelper.findWebElement(to, timeout)
        JavascriptExecutor executor = ((driver) as JavascriptExecutor)
        executor.executeScript('arguments[0].click()', element)
    }
    throw(e)
}
CustomKeyords.'yourPackage.yourClass.clickJS'(findTestObject("yourObject"))

((JavascriptExecutor) DriverFactory.webDriver).executeScript('Your JavaScript-Code', argument);
 */


WebUI.openBrowser('')

WebUI.navigateToUrl('https://eonasdan.github.io/bootstrap-datetimepicker/')

WebUI.click(findTestObject('Object Repository/DatePicker/Page_/select-date-picker'))

WebUI.delay(5)

//clicked day 26
//WebUI.click(findTestObject('Object Repository/DatePicker/Page_/day-object'))
'Change xpath property to new value'
txtval = '27'
LinkToSearch = WebUI.modifyObjectProperty(findTestObject('Object Repository/DatePicker/Page_/day-object'), 'text','equals', txtval, true)
WebUI.click(LinkToSearch, FailureHandling.OPTIONAL)

//findTestObject('Object Repository/DatePicker/Page_/day-object')
//WebElement element =  WebUiCommonHelper.findWebElement(findTestObject(findTestObject('Object Repository/DatePicker/Page_/day-object'),30))
//WebElement element =  WebUiCommonHelper.findWebElement(findTestObject(findTestObject('Object Repository/DatePicker/Page_/day'),30))
//WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))

//WebUI.executeJavaScript("document.getElementsByClassName('datepicker-days')", null)
////div[@id='datetimepicker1']/div/ul/li/div/div/table/tbody/tr[5]/td

WebUI.delay(5)

//id("datetimepicker1")/div[@class="bootstrap-datetimepicker-widget dropdown-menu bottom"]/ul[@class="list-unstyled"]/li[@class="collapse in"]/div[@class="datepicker"]/div[@class="datepicker-days"]/table[@class="table-condensed"]/tbody[1]/tr[5]/td[@class="day weekend"]