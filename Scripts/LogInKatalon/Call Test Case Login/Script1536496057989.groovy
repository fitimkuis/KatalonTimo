import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

//import com.kms.katalon.core.testcase.TestCaseFactory.findTestCaseimport
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//WebUI.closeBrowser()
WebUI.callTestCase(findTestCase('LogInKatalon/Login'), [('username') : 'John Doe', ('pass') : GlobalVariable.pass], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()

WebUI.callTestCase(findTestCase('LogInKatalon/Login'), [('username') : 'John Doe', ('pass') : 'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM'], 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()

//globalvariable defined as empty
String generatedString = RandomStringUtils.randomAlphanumeric(10)

GlobalVariable.name = generatedString

def message = WebUI.callTestCase(findTestCase('LogInKatalon/Login'), [('name') : GlobalVariable.name], FailureHandling.CONTINUE_ON_FAILURE)

println('DEBUG returned string is***************' + message)

def var = WebUI.callTestCase(findTestCase('ReadTxtFile/read-txt-file'), [('hello') : 'Timo'], FailureHandling.CONTINUE_ON_FAILURE)
println('DEBUG returned value is***************' + var)
