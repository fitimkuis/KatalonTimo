package sin.generation

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


import internal.GlobalVariable

public class JavaSinGenerator {

	@Keyword
	public String getSinAsString() {
		String MySin = WebUI.executeJavaScript('{var validPrefix = new Array(1, 2, 3, 4, 5, 6, 7, 9);var length = 9;var sin = new Array(length);sin[0] = validPrefix[Math.floor(Math.random() * validPrefix.length)];var index = 1;while (index < length - 1) {sin[index] = Math.floor(Math.random() * 9);index++;} var sum = 0;var pos = 1; while (pos < length - 1){var odd = sin[pos] * 2;sum += odd > 9 ? odd - 9 : odd;sum += sin[pos - 1]; pos += 2;}var checkdigit = ((Math.floor(sum / 10) + 1) * 10 - sum) % 10;sin[length - 1] = checkdigit;var MySin = (sin.join(""));return MySin;}', null)
	}
}
