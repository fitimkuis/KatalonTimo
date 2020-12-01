import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public static boolean validateDates(String start, String end) {
	try {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(start, dateFormat);
		//LocalDate endDate = LocalDate.parse(end, dateFormat);
		LocalDate current = LocalDate.now();
		println current
		if(startDate.isEqual(current)){
			println("dates are same")
			return true
		}
		else{
			println("dates are not same")
			return false
		}
		//return (startDate.isEqual(current) || startDate.isAfter(current)) && endDate.isAfter(startDate);
	}catch(DateTimeParseException ex) {
		ex.printStackTrace();
	}
}

boolean val = validateDates("2020-02-18","2020-02-18")
println ("DEBUG dates verify "+val)

Calendar c = Calendar.getInstance();
int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
println "Current month count of dates: "+monthMaxDays
int day = c.get(Calendar.DATE);
int validDays = monthMaxDays - day
println "Valid calendar days in month: "+validDays

LocalDate date0 = LocalDate.now().minusDays(1);
println ("DEBUG current date minus day "+date0)


int startDate = monthMaxDays - validDays
String strStartDate = startDate.toString()

int x = startDate;

WebUI.openBrowser('')

WebUI.navigateToUrl('https://material.angularjs.org/latest/demo/datepicker')

myNewObject = new TestObject("myNewObject")
myNewObject1 = new TestObject("myNewObject1")
myNewObject2 = new TestObject("myNewObject2")
myNewObject3 = new TestObject("myNewObject3")

'Change xpath property to new value'
def css = "body > main > md-content > div.docs-ng-view.layout-padding.ng-scope.flex-noshrink > docs-demo:nth-child(1) > div > div > section > demo-include > div > md-content > div:nth-child(1) > div:nth-child(1) > md-datepicker > div > button"

def month = 2
def xpath_20 = "//td[@id='md-2-month-2020-1-20']/span"


List <String> l = new ArrayList<>()
String txtval = x
for (int z=0; z < validDays+1; z++) {
	txtval = x++ //increase start date
	println txtval
	def css_inputBox = "body > main > md-content > div.docs-ng-view.layout-padding.ng-scope.flex-noshrink > docs-demo:nth-child(1) > div > div > section > demo-include > div > md-content > div:nth-child(1) > div:nth-child(1) > md-datepicker > div > input"
	
	myNewObject.addProperty("css", ConditionType.EQUALS, css, true)
	WebUI.click(myNewObject)
	
	WebUI.delay(2)
	def xpath_day = "//td[@id='md-"+month+"-month-2020-1-"+txtval+"']/span"
	
	month++
	println month
	myNewObject1.addProperty("xpath", ConditionType.EQUALS, xpath_day, true)
	WebUI.click(myNewObject1)
	
	WebUI.delay(2)
	
	myNewObject3.addProperty("css", ConditionType.EQUALS, css_inputBox, true)
	def date = WebUI.getAttribute(myNewObject3, 'value')
	l.add(date)
	println date
	WebUI.delay(2)
}
WebUI.closeBrowser()
println l

//LinkToSearch = WebUI.modifyObjectProperty(findTestObject('jQueryDatePicker/Page_jQuery-datePicker/day'), 'text','equals',txtval, true)
//WebUI.click(LinkToSearch, FailureHandling.OPTIONAL)


