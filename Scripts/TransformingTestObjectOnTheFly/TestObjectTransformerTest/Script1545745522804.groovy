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

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature

/**
 * TestObjectTransformerTest
 */

def performTransformingTestObject(TestObject source) {
	assert source != null
	println "------------------------------------------------------------------------------"
	println "source is " + CustomKeywords.'my.TestObjectFormatter.format'(source)

	// transform a Test Object into another as Michal.Pachuski wanted
	// see https://forum.katalon.com/t/customizing-xpath-generation/15801
	TestObject target =
		CustomKeywords.'my.TestObjectTransformer.toMichalPachuckiXpath'(source,
			FailureHandling.STOP_ON_FAILURE)

	assert target != null
	println "target is " + CustomKeywords.'my.TestObjectFormatter.format'(target)
}

performTransformingTestObject(findTestObject("Page_15801_testbed/button_staticId2"))
performTransformingTestObject(findTestObject("Page_15801_testbed/button_EVEREST"))
performTransformingTestObject(findTestObject("Page_15801_testbed/button_KILIMANJARO"))




// stringify fully by Jackson
//ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
//println "target is \n" + mapper.writeValueAsString(target)