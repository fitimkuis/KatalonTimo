package com.compare.two.lists
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class CompareTwoLists {


	@Keyword
	public List <Long> getFirtsSortedList(){

		List <Long> numbers = new ArrayList<>()
		Random r = new Random();
		long xx = 100000000L;
		long yy = 999999999L;
		long number = 0L
		number = xx+((long)(r.nextDouble()*(yy-xx)));
		numbers.add(number)
		int counter = 8
		while (counter >= 0){
			number = xx+((long)(r.nextDouble()*(yy-xx)));
			if(!numbers.contains(number)){
				numbers.add(number)
				counter--
			}
		}

		Collections.sort(numbers, Collections.reverseOrder());
		println ("numbers are in list with reversed order "+numbers)
		return numbers
	}

	@Keyword
	public List <Long> getSecondSortedList(){

		List <Long> numbers = new ArrayList<>()
		Random r = new Random();
		long xx = 100000000L;
		long yy = 999999999L;
		long number = 0L
		number = xx+((long)(r.nextDouble()*(yy-xx)));
		numbers.add(number)
		int counter = 8
		while (counter >= 0){
			number = xx+((long)(r.nextDouble()*(yy-xx)));
			if(!numbers.contains(number)){
				numbers.add(number)
				counter--
			}
		}

		Collections.sort(numbers, Collections.reverseOrder());
		println ("numbers are in list with reversed order "+numbers)
		return numbers
	}

	@Keyword
	public List <Long> getListsCompared(){

		List <Long> one = new ArrayList<>()
		List <Long> two = new ArrayList<>()
		one = getFirtsSortedList()
		two = getSecondSortedList()

		//find missing elements
		//remove all elements from second list
		two.removeAll(one);
		println "missing elements from list two " + two

		//common elements
		one.retainAll(two)
		println "common elements " + one
	}

	@Keyword
	public List <String> getFirstNamesSortedList(){

		List <String> names = new ArrayList<>();
		names.add('Tim');
		names.add('John');
		names.add('David');
		names.add('Tor');

		Collections.sort(names, Collections.reverseOrder());
		return names;
	}

	@Keyword
	public List <String> getSecondNamesSortedList(){

		List <String> names = new ArrayList<>();
		names.add('Tim');
		names.add('John');
		names.add('Mat');
		names.add('Tor');

		Collections.sort(names, Collections.reverseOrder());
		return names;
	}

	@Keyword
	public ArrayList<ArrayList<String>> getNameListsCompared(){

		int n = 3;
		// Here aList is an ArrayList of ArrayLists
		ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String> >(n);

		List <String> listOne = new ArrayList<>();
		List <String> listTwo = new ArrayList<>();
		listOne = getFirstNamesSortedList();
		listTwo = getSecondNamesSortedList();

		//find missing elements from list two
		listOne.removeAll(listTwo);
		//println "missing elements from list two " + listOne;
		aList.add(listOne)

		listOne = getFirstNamesSortedList();
		listTwo = getSecondNamesSortedList();

		//find missing elements from list one
		listTwo.removeAll(listOne);
		//println "missing elements from list one " + listTwo;
		aList.add(listTwo)

		listOne = getFirstNamesSortedList();
		listTwo = getSecondNamesSortedList();

		//get common elements
		listTwo.retainAll(listOne);
		//println "common elements are: " + listTwo;
		aList.add(listTwo)

		/*listOne = getFirstNamesSortedList();
		 listTwo = getSecondNamesSortedList();
		 HashSet<String> set1 = new HashSet<>(Arrays.asList(listOne));
		 HashSet<String> set2 = new HashSet<>(Arrays.asList(listTwo));
		 set1.retainAll(set2);
		 println "name missing from listTwo" + listOne;
		 set2.retainAll(set1);
		 println "name missing from listOne" + listTwo;*/

		listOne = getFirstNamesSortedList();
		listTwo = getSecondNamesSortedList();

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


	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}