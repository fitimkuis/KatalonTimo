import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def x1 = 2
def x2 = 1

Calendar c = Calendar.getInstance();
Calendar cal = Calendar.getInstance();
Date today = new Date();
Calendar mon = Calendar.getInstance();

int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
println "Current month count of dates: "+monthMaxDays
Date nowDate = new Date();
cal.setTime(nowDate);
cal.add(Calendar.MONTH, 1);
int nextMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
println "Next month count of dates: "+nextMonthDays
int day = c.get(Calendar.DATE);
int validDays = monthMaxDays - day
println "Valid calendar days in month: "+validDays

mon.setTime(today);
int month = mon.get(Calendar.MONTH)+1;
println ("DEBUG "+month)

def start = "//*[@placeholder = 'Start date' and @class = 'ant-calendar-range-picker-input']"
def end = "//*[@placeholder = 'End date' and @class = 'ant-calendar-range-picker-input']"

def css = "#components-date-picker-demo-basic > section.code-box-demo > div > span:nth-child(5)"

def svg = "(.//*[normalize-space(text()) and normalize-space(.)='Examples'])[1]/following::*[name()='svg'][3]"

//next = "//*[@class = 'ant-calendar-next-month-btn' and @title = 'Next month (PageDown)']"

next = "//*[@class'ant-calendar-next-month-btn']"

Random right = new Random();
Random left = new Random();
Random nm = new Random();

def n = 0

def all = 12
if (month > 1){
	n = nm.nextInt(all - month)+1
}


println("DEBUG next month "+n)

WebUI.openBrowser('')

WebUI.navigateToUrl('https://ant.design/components/date-picker/')

WebUI.waitForPageLoad(60)

//for(int i = 0; i < validDays+1; i++){
	
	//#components-date-picker-demo-basic > section.code-box-demo > div > span:nth-child(5)
	
	WebUI.click(makeTO(svg))
	WebUI.delay(2)
		
	WebUI.waitForElementPresent(findTestObject('Object Repository/Page_DatePicker - Ant Design/input_Examples_ant-calendar-range-picker-input'),30)

	//WebUI.click(findTestObject('Object Repository/Page_DatePicker - Ant Design/input_Examples_ant-calendar-range-picker-input'))
	
	WebUI.click(makeCSS(css))
	
	//WebUI.click(findTestObject('Object Repository/Page_DatePicker - Ant Design/div_28'))
	
	def s = right.nextInt((validDays - 2) + 1) + 2;
	def e = left.nextInt((nextMonthDays - 1) + 1) + 1;
	
	//WebUI.click(makeTO(next))
	
	//WebUI.click(makeTO(next))
	
	//WebUI.click(makeTO(next))
	
	for(int y = 0; y < n; y++){
		WebUI.click(makeTO(next))
		WebUI.delay(2)
	}
		
	def xpath = "//*/text()[normalize-space(.)='"+s+"']/parent::*"
			
	def xpath2 = "(.//*[normalize-space(text()) and normalize-space(.)='Sa'])[2]/following::div["+e+"]"
	
	WebUI.delay(2)
	WebUI.click(makeTO(xpath))
	
	//WebUI.click(findTestObject('Object Repository/Page_DatePicker - Ant Design/input_Examples_ant-calendar-range-picker-input'))
	WebUI.delay(1)
	
	WebUI.click(makeTO(xpath2))
			
	WebUI.delay(1)
	def startDate = WebUI.getAttribute(makeTO(start),'value')
	println startDate
	def endDate = WebUI.getAttribute(makeTO(end),'value')
	println endDate
	WebUI.delay(1)
//}

WebUI.closeBrowser()


def TestObject makeCSS(String css){
	TestObject myNewObject = new TestObject()
	myNewObject.setSelectorValue(SelectorMethod.CSS,css) 
	myNewObject.setSelectorMethod(SelectorMethod.CSS)
	return myNewObject
}


def TestObject makeTO(String xpath) {
	TestObject to = new TestObject()
	to.addProperty("xpath", ConditionType.EQUALS, xpath)
	return to
  }


