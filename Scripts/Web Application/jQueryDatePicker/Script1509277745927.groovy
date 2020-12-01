import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


WebUI.openBrowser('')

Calendar c = Calendar.getInstance();
int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
println "Current month count of dates: "+monthMaxDays
int day = c.get(Calendar.DATE);
int validDays = monthMaxDays - day
println "Valid calendar days in month: "+validDays

int startDate = monthMaxDays - validDays
String strStartDate = startDate.toString()

int x = startDate;

int y = startDate;
String txtval2 = y
txtval2 = 24

WebUI.navigateToUrl('http://2008.kelvinluck.com/assets/jquery/datePicker/v2/demo/datePicker.html')

//WebUI.click(findTestObject('jQueryDatePicker/Page_jQuery-datePicker/Choose-date'))
myNewObject = new TestObject("myNewObject")
List<String> paths = new ArrayList<>()
path25 = "//div[@id='dp-popup']/div[3]/table/tbody/tr[5]/td"
path26 = "//div[@id='dp-popup']/div[3]/table/tbody/tr[5]/td[2]"
path27 = "//div[@id='dp-popup']/div[3]/table/tbody/tr[5]/td[3]"
path28 = "//div[@id='dp-popup']/div[3]/table/tbody/tr[5]/td[4]"
path29 = "//div[@id='dp-popup']/div[3]/table/tbody/tr[5]/td[5]"
path30 = "//div[@id='dp-popup']/div[3]/table/tbody/tr[5]/td[6]"
path31 = "//div[@id='dp-popup']/div[3]/table/tbody/tr[5]/td[7]"
paths.add(path26)
paths.add(path27)
paths.add(path28)
paths.add(path29)
paths.add(path30)
paths.add(path31)
//myNewObject.addProperty("xpath", ConditionType.EQUALS, path25, true)
//WebUI.click(myNewObject)//use new relative xpath locator

//WebUI.click(findTestObject('Object Repository/jQueryDatePicker/Page_jQuery-datePicker/day'))
//use xpath value
//LinkToSearch2 = WebUI.modifyObjectProperty(findTestObject('jQueryDatePicker/Page_jQuery-datePicker/day'), 'xpath','equals',path, true)
//WebUI.click(LinkToSearch2, FailureHandling.STOP_ON_FAILURE)

//use txt value
//LinkToSearch2 = WebUI.modifyObjectProperty(findTestObject('jQueryDatePicker/Page_jQuery-datePicker/day'), 'text','equals',txtval2, true)
//WebUI.click(LinkToSearch2, FailureHandling.STOP_ON_FAILURE)


int p = 0
List <String> l = new ArrayList<>()
String txtval = x
for (int z=0; z < validDays+1; z++) {
	
	txtval = x++
	println txtval
	
	//select choose a date button
	WebUI.click(findTestObject('jQueryDatePicker/Page_jQuery-datePicker/Choose-date'))
	
	if (Integer.parseInt(txtval) < 25){
	'Change xpath property to new value'
	LinkToSearch = WebUI.modifyObjectProperty(findTestObject('jQueryDatePicker/Page_jQuery-datePicker/day'), 'text','equals',txtval, true)
	WebUI.click(LinkToSearch, FailureHandling.OPTIONAL)
	}
	if (Integer.parseInt(txtval) == 25){
		myNewObject.addProperty("xpath", ConditionType.EQUALS, path25, true)
		WebUI.click(myNewObject)//use new relative xpath locator
	}
	if (Integer.parseInt(txtval) > 25){
		myNewObject.addProperty("xpath", ConditionType.EQUALS, paths.get(p++), true)
		WebUI.click(myNewObject)//use new relative xpath locator
	}
	String date = WebUI.getAttribute(findTestObject('jQueryDatePicker/Page_jQuery-datePicker/getDate'), 'value')
    //add date to list
	l.add(date)
	
	//txtval = x++
	
	//WebUI.delay(1)
}

println "DEBUG list of dates: "+l
WebUI.closeBrowser()