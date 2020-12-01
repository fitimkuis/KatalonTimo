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
