import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('https://datatables.net/examples/data_sources/dom')
WebDriver driver3 = DriverFactory.getWebDriver()
WebElement table = driver3.findElement(By.xpath('//table/tbody'))
'Identify table web element based on column headers'
table = CustomKeywords.'kms.turing.katalon.plugins.helper.table.HTMLTableHelper.identifyTableByColumnHeaders'(['Name','Position','Office','Age','Start date','Salary'], 10, FailureHandling.CONTINUE_ON_FAILURE)
"Verify a row display inside the table using cells info"
println table
//Map< String,String> hm =  new HashMap< String,String>(); 
//hm = CustomKeywords.'kms.turing.katalon.plugins.helper.table.HTMLTableHelper.verifyRowDisplayed'(table, ["Name":"Airi Satoul", "Position":"Accountant", "Office":"Tokyo"],FailureHandling.CONTINUE_ON_FAILURE)
//println hm
WebUI.closeBrowser()



String ExpectedValue = 'Airi Satou'

List<String> page1 = new ArrayList<>()
List<String> page2 = new ArrayList<>()
List<String> page3 = new ArrayList<>()
List<String> page4 = new ArrayList<>()
List<String> page5 = new ArrayList<>()
List<String> page6 = new ArrayList<>()

List<ArrayList> lArray = new ArrayList<ArrayList>();//Create a list of lists
lArray.add(page1)
lArray.add(page2)
lArray.add(page3)
lArray.add(page4)
lArray.add(page5)
lArray.add(page6)

/*WebDriver driver = DriverFactory.getWebDriver()
'To locate table'
WebElement Table = driver.findElement(By.xpath('//table/tbody'))
'To locate rows of table it will Capture all the rows available in the table'
List<Double> rows_table = Table.findElements(By.tagName('tr'))*/

'To calculate no of rows In table'
//int rows_count = rows_table.size()
myObject = new TestObject("myObject")
//WebUI.closeBrowser()
//Loop paginate
pagi = 1
index = 0

//  //div[@id='example_paginate']/span/a[2]
List<String> list2 = new ArrayList<>()
WebUI.openBrowser('')
WebUI.navigateToUrl('https://datatables.net/examples/data_sources/dom')
WebDriver driver2 = DriverFactory.getWebDriver()
'To locate paginate buttons'
list2 = driver2.findElements(By.xpath("//a[contains(@class, 'paginate_button')]"))
println list2.size()
count = list2.size() - 2 //remove previous and next from the count
WebUI.closeBrowser()

for (int i = 0; i < count; i++){
	
	WebUI.openBrowser('')
	WebUI.navigateToUrl('https://datatables.net/examples/data_sources/dom')
	
	listHref = WebUI.getAllLinksOnCurrentPage(true, [])
	println listHref

//modify paginate object xpath property	
path = "//div[@id='example_paginate']/span/a["+pagi+"]"
myObject.addProperty("xpath", ConditionType.EQUALS, path, true)
WebUI.click(myObject)//use new relative xpath locator
WebUI.delay(1)


//get all table cells data to the list array
WebDriver driver = DriverFactory.getWebDriver()
'To locate table'
WebElement Table = driver.findElement(By.xpath('//table/tbody'))
'To locate rows of table it will Capture all the rows available in the table'
List<Double> rows_table = Table.findElements(By.tagName('tr'))
int rows_count = rows_table.size()

'Loop will execute for all the rows of the table'
Loop: for (int row = 0; row < rows_count; row++) {
    'To locate columns(cells) of that specific row'
    List<Double> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

    'To calculate no of columns(cells) In that specific row'
    int columns_count = Columns_row.size()

    //println((('Number of cells In Row ' + row) + ' are ') + columns_count)

    'Loop will execute till the last cell of that specific row'
    for (int column = 0; column < columns_count; column++) {
        'It will retrieve text from each cell'
        String celltext = Columns_row.get(column).getText()

        //println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
		
		lArray.get(index).add(celltext)
		
		/*if (pagi == 1){
			page1.add(celltext)
		}
		else if (pagi == 2){
			page2.add(celltext)
		}
		else if (pagi == 3){
			page3.add(celltext)
		}
		else if (pagi == 4){
			page4.add(celltext)
		}
		else if (pagi == 5){
			page5.add(celltext)
		}
		else{
			page6.add(celltext)
		}*/

        'Checking if Cell text is matching with the expected value'
        if (celltext == ExpectedValue) {
            'Getting the Country Name if cell text i.e Company name matches with Expected value'
            println('Text present in row number 3 is: ' + Columns_row.get(row).getText())
			//Colums_row.click()

            'After getting the Expected value from Table we will Terminate the loop'
            //Loop: break
      }
    }
}
pagi++
index++
WebUI.delay(10)
WebUI.closeBrowser()
WebUI.delay(10)
}
println page1
println page2
println page3
println page4
println page5
println page6

boolean ans = page1.contains("Ashton Cox");
if (ans)
System.out.println("The list contains name");
else
System.out.println("The list does not contains name");



/*
//List<WebElement> cells = row.findElements(By.xpath(".//*[local-name(.)='th' or local-name(.)='td']"));
WebUI.click(findTestObject('TableValues/html-table/td_Airi-Satou'))

//attribute = WebUI.getAttribute(findTestObject('TableValues/html-table/td_Airi-Satou'), 'value')
txt = WebUI.getText(findTestObject('TableValues/html-table/td_Airi-Satou'))

println(txt)

WebUI.click(findTestObject('TableValues/html-table/td_Accountant'))

WebUI.click(findTestObject('TableValues/html-table/td_Tokyo'))

WebUI.click(findTestObject('TableValues/html-table/td_Angelica-Ramos'))*/