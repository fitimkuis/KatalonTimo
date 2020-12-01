import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

import org.apache.commons.io.FileUtils

class TestStatusListener {
	
	String tomorrowFilePath = "C:/Users/" + System.getProperty("user.name")+ "/Downloads/tomorrow/"
	String currentFilePath = "C:/Users/" + System.getProperty("user.name")+ "/Downloads/current/"
	
	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		if (testCaseContext.getTestCaseStatus() != 'PASSED') {
			// do whatever clean ups
			println("temp files are not deleted cause test FAILED!!!")

		}
		else{
			File tomorrow = new File(tomorrowFilePath)
			File current = new File(currentFilePath)
			//deleteFileFromFolder(tomorrow)
			//deleteFileFromFolder(current)
		}
	}
	
	public static deleteFileFromFolder(File directory){
		
		FileUtils.cleanDirectory(directory);
	}
}