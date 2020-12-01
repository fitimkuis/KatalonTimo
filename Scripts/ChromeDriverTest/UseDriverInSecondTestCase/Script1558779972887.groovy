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

String baseUrl = "https://www.google.com/";
String expectedTitle = "Google";
String actualTitle = "";

// launch Fire fox and direct it to the Base URL
driver.get(baseUrl);

// get the actual value of the title
actualTitle = driver.getTitle();

/*
 * compare the actual title of the page with the expected one and print
 * the result as "Passed" or "Failed"
 */
if (actualTitle.contentEquals(expectedTitle)){
	System.out.println("Test Passed!");
} else {
	System.out.println("Test Failed");
}

//close Fire fox
//driver.close();