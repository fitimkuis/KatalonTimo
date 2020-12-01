import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.List

import com.kms.katalon.core.annotation.Keyword
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

KeywordLogger logger = new KeywordLogger()

//count of items in a list
int n = 0;

List <String> arr = new ArrayList<>()
List <String> arr2 = new ArrayList<>()
//AA-1, AA-10, AA-2, AA-2 (1)
arr.add("AA-1")
arr.add("AA-10")
arr.add("AA-2")
arr.add("AA-2 (1)")
//arr.add("BB")

arr2.add("AA-10")
arr2.add("AA-1")
arr2.add("AA-2 (1)")
arr2.add("AA-2")

//print arr
//print arr2


//Collections.sort(arr, Collections.reverseOrder());
Collections.sort(arr2, Collections.reverseOrder());

//print arr
//print arr2

//Collections.sort(arr, Collections.reverseOrder());
Collections.sort(arr2);

print arr
print arr2



// Here aList is an ArrayList of ArrayLists
ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String> >(n);

aList = getNameListsCompared(arr, arr2)


if (aList.get(0).size > 0)
{
	logger.logFailed("Missing elements from list two: ")
	print ("Missing elements from list two: ")
	for (int a = 0; a < aList.get(0).size(); a++){
		print "missing name: "+aList.get(0).get(a)
		logger.logFailed("missing name: "+aList.get(0).get(a))
	}
}
else{
	logger.logInfo("No missing elements from list two: ")
	print ("No missing elements from list two: ")
}

if (aList.get(1).size() > 0){
	logger.logFailed("Missing elements from list one: ")
	print ("Missing elements from list one: ")
	for (int b = 0; b < aList.get(1).size(); b++){
		print "missing name: "+aList.get(1).get(b)
		logger.logFailed("missing name: "+aList.get(1).get(b))
	}
}
else{
	logger.logInfo("No missing elements from list one: ")
	print ("No missing elements from list one: ")
}


public ArrayList<ArrayList<String>> getNameListsCompared(List<String> arr, List<String> arr2){
	
			int n = 0;
	
			if (arr.size() > arr2.size()){
				n = arr.size()
			}
			else{
				n = arr2.size()
			}
			// Here aList is an ArrayList of ArrayLists
			ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String> >(n);
	
			List <String> listOne = new ArrayList<>();
			List <String> listTwo = new ArrayList<>();
			listOne = arr
			listTwo = arr2
	
			//find missing elements from list two
			listOne.removeAll(listTwo);
			//println "missing elements from list two " + listOne;
			aList.add(listOne)
	
			listOne = arr
			listTwo = arr2
	
			//find missing elements from list one
			listTwo.removeAll(listOne);
			//println "missing elements from list one " + listTwo;
			aList.add(listTwo)
	
			listOne = arr
			listTwo = arr2
	
			//get common elements
			listTwo.retainAll(listOne);
			//println "common elements are: " + listTwo;
			aList.add(listTwo)
	

			listOne = arr
			listTwo = arr2
	
			HashSet<String> set = new HashSet<String>();
			for (int i = 0; i < listOne.size; i++)
			{
				for (int j = 0; j < listTwo.size; j++)
				{
					if(listOne[i].equals(listTwo[j]))
					{
						set.add(listOne[i]);
					}
				}
			}
	
			//System.out.println("Common names are: "+set);
			return aList
		}

