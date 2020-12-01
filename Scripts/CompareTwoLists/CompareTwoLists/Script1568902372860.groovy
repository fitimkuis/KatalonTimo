import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.logging.KeywordLogger

CustomKeywords.'com.compare.two.lists.CompareTwoLists.getListsCompared'()

KeywordLogger logger = new KeywordLogger()
int n = 3; 
  
// Here aList is an ArrayList of ArrayLists 
ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String> >(n);

aList = CustomKeywords.'com.compare.two.lists.CompareTwoLists.getNameListsCompared'()


if (aList.get(0).size > 0)
{
	logger.logFailed("Missing elements from list two: ")
	print ("Missing elements from list two: ")
	for (int a = 0; a < aList.get(0).size(); a++){
		print "missing name: "+aList.get(0).get(a)
		logger.logFailed("missing name: "+aList.get(0).get(a))
	}
}

if (aList.get(1).size() > 0){
	logger.logFailed("Missing elements from list one: ")
	print ("Missing elements from list one: ")
	for (int b = 0; b < aList.get(1).size(); b++){
		print "missing name: "+aList.get(1).get(b)
		logger.logFailed("missing name: "+aList.get(1).get(b))
	}
}

logger.logInfo("Common names are: ")
print ("Common names are: ")
for (int c = 0; c < aList.get(2).size(); c++){
	print "common name "+aList.get(2).get(c)
	logger.logInfo("common name: "+aList.get(2).get(c))
}

/*for (int i = 0; i < aList.size(); i++) {
	if (i == 0){
		logger.logInfo("Missing elements from list two: ")
	}
	else if (i == 1){
		logger.logInfo("Missing elements from list one: ")
	}
	else{
		logger.logInfo("Common names are: ")
	}
	for (int j = 0; j < aList.get(i).size(); j++) {
		logger.logInfo(aList.get(i).get(j) + " ");
		print(aList.get(i).get(j) + " ");
	}
	System.out.println();
}*/