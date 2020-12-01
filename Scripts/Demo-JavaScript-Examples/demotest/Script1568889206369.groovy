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

import com.thoughtworks.selenium.Selenium
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium
import static org.junit.Assert.*
import java.util.regex.Pattern
import static org.apache.commons.lang3.StringUtils.join

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS


Calendar c = Calendar.getInstance();
int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
println "Current month count of dates: "+monthMaxDays
int day = c.get(Calendar.DATE);
println "day "+day
int validDays = monthMaxDays - day
println "Valid calendar days in month: "+validDays

int startDate = monthMaxDays - validDays
String strStartDate = startDate.toString()

int x = startDate;
x++
println "start date "+x

WebUI.openBrowser('https://www.katalon.com/')
def driver = DriverFactory.getWebDriver()
String baseUrl = "https://www.katalon.com/"
selenium = new WebDriverBackedSelenium(driver, baseUrl)
selenium.open("https://katalon-demo-cura.herokuapp.com/")
selenium.click("id=btn-make-appointment")
selenium.type("id=txt-username", "John Doe")
selenium.type("id=txt-password", "ThisIsNotAPassword")
selenium.click("id=btn-login")
selenium.click("id=combo_facility")
selenium.select("id=combo_facility", "label=Hongkong CURA Healthcare Center")
selenium.click("id=combo_facility")
selenium.click("id=chk_hospotal_readmission")
selenium.click("id=radio_program_medicaid")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Visit Date (Required)'])[1]/following::span[1]")
//selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='September 2018'])[1]/following::th[1]")
//selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='October 2018'])[1]/following::th[1]")
//selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='November 2018'])[1]/following::th[1]")
/*selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='December 2018'])[1]/following::th[1]")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='January 2019'])[1]/following::th[1]")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='February 2019'])[1]/following::th[1]")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='March 2019'])[1]/following::th[1]")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='April 2019'])[1]/following::th[1]")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='May 2019'])[1]/following::th[1]")*/
//selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='June 2019'])[1]/following::th[1]")
//selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Sa'])[1]/following::td[32]")

selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Visit Date (Required)'])[1]/following::span[1]")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Sa'])[1]/following::td["+x+"]")

selenium.click("id=txt_comment")
selenium.type("id=txt_comment", "python")
assertEquals("Make Appointment", selenium.getText("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Make Appointment'])[1]/following::h2[1]"));
selenium.click("id=btn-book-appointment")
assertEquals("Appointment Confirmation", selenium.getText("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Make Appointment'])[1]/following::h2[1]"));
selenium.click("link=Go to Homepage")
selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='CURA Healthcare'])[1]/preceding::i[2]")
selenium.click("link=Logout")
selenium.close()
