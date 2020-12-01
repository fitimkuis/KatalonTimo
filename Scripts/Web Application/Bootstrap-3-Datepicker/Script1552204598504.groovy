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

Calendar c = Calendar.getInstance();
int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
println "Current month count of dates: "+monthMaxDays
int day = c.get(Calendar.DATE);
int validDays = monthMaxDays - day
println "Valid calendar days in month: "+validDays

int startDate = monthMaxDays - validDays
String strStartDate = startDate.toString()

int x = startDate;


int year = 2019;
int month = Calendar.FEBRUARY;
int d = 1;
// We have a new date of 2017-02-01
c.set(year, month, d);
int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
System.out.println("Max Day: " + maxDay);

// Here we want to see what is the days for february on
// a leap year.
c.set(2020, Calendar.FEBRUARY, 1);
maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
System.out.println("Max Day: " + maxDay);

WebUI.openBrowser('')

WebUI.navigateToUrl('https://eonasdan.github.io/bootstrap-datetimepicker/')

//WebUI.click(findTestObject('Object Repository/DatePicker/Page_/select-date-picker'))

/*
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/select-year-month'))
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/select-year-from-table'))
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/select-year'))
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/select-month'))
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/day'))
*/

//select choose a date button
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/select-date-picker'))
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/next-month'))
WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/DatePicker/Page_/prev-month'))
WebUI.delay(1)



List <String> l = new ArrayList<>()
String txtval = x
for (int z=0; z < validDays+1; z++) {
	
	txtval = x++
	println txtval
	
	'Change xpath property to new value'
	LinkToSearch = WebUI.modifyObjectProperty(findTestObject('Object Repository/DatePicker/Page_/day'), 'text','equals',txtval, true)
	WebUI.click(LinkToSearch, FailureHandling.OPTIONAL)

	String date = WebUI.getAttribute(findTestObject('Object Repository/DatePicker/Page_/data-field'), 'value')
	//add date to list
	l.add(date)
	
	//txtval = x++
	
	WebUI.delay(1)
}

println "DEBUG list of dates: "+l

WebUI.closeBrowser()

