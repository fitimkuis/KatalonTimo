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

import com.kms.katalon.core.util.KeywordUtil

import BashRunner

KeywordUtil log = new KeywordUtil()

String expectedValue = "height=176";

	//Process p = Runtime.getRuntime().exec(new String[]{"C:\\cygwin64\\bin\\ls.exe", "-la"});
	List<String> arr = new ArrayList<>();
	String command1 = "less"
	String command2 = "/home/myinifile.ini"
	BashRunner bash = new BashRunner();
	arr= bash.getIniFile(command1, command2);
	String joinedString = Arrays.toString(arr);
	
	boolean ans = joinedString.contains(expectedValue);
	
	if (ans){
		System.out.println("The list contains expected value: "+ expectedValue);
		log.markPassed("The list contains expected value: "+ expectedValue)
	}
	else{
		System.out.println("The list does not contains expected value: "+expectedValue);
		log.markFailed("The list does not contains expected value: "+expectedValue)
	}

	/*for (String s: arr){
		if(s.equals(expectedValue)){
			System.out.println("Ini file contains expected value: "+s);
			break;
		}
	}*/