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

import helper.HelperUtils

def testUrl = GlobalVariable.testURL
println("TestUrl given in testcase 1 "+testUrl)
//give new value
CustomKeywords.'helper.HelperUtils.addGlobalVariable'('testURL', 'book.com')
println ("variable value changed "+GlobalVariable.testURL)


//get global variable value declared in testsuite 1
int testVar2 = GlobalVariable.testVariable
println "global variable defined in testsuite1 "+testVar2

if (testVar2 == 999){
	println "value moved to testsuite2 "+testVar2
}
else{
	println "value NOT moved to testsuite2 "+testVar2
}

//change value in testsuite 2
CustomKeywords.'com.global.variables.CreateGlobalVariables.addGlobalVariable'('testVariable', 888)
println "value changed in testsuite2 "+GlobalVariable.testVariable

driver.get("http://www.google.com/")
driver.close()



