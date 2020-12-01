import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By as By
 
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

/*
WebUI.openBrowser('')

WebUI.navigateToUrl('https://plattemo.icounty.com/iRecordWeb2.0/login.aspx')

WebUI.setText(findTestObject('Object Repository/SavePage/Page_iRecord Client Login/input_EMail Address_USERID'), 'GUEST')

WebUI.click(findTestObject('Object Repository/SavePage/Page_iRecord Client Login/input_Password_btnLogin'))

//WebUI.waitForElementClickable(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN', 10))

WebUI.waitForElementClickable(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), 10)

WebUI.setText(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), 'Jones')
WebUI.delay(2)
WebUI.sendKeys(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), Keys.chord(Keys.ENTER))
WebUI.delay(2)

def html = WebUI.getAttribute(findTestObject('Object Repository/SavePage/Page_Real Estate - Search By Name/result-div'), 'innerHTML')
WebUI.delay(2)
new File('myresults.html').append(html)*/

//WebUI.click(findTestObject('Object Repository/SavePage/Page_Real Estate - Search By Name/a_JONES'))
String ExpectedValue = 'JONES'
int row = 3;

WebUI.openBrowser('')
WebUI.navigateToUrl('https://plattemo.icounty.com/iRecordWeb2.0/login.aspx')
WebUI.setText(findTestObject('Object Repository/SavePage/Page_iRecord Client Login/input_EMail Address_USERID'), 'GUEST')
WebUI.click(findTestObject('Object Repository/SavePage/Page_iRecord Client Login/input_Password_btnLogin'))
//WebUI.waitForElementClickable(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN', 10))
WebUI.waitForElementClickable(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), 10)
WebUI.setText(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), 'Jones')
WebUI.delay(2)
WebUI.sendKeys(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), Keys.chord(Keys.ENTER))
WebUI.delay(2)

//CustomKeywords.'save.page.SaveTableToHtml.saveJonesRowsToHtml'()

WebUI.closeBrowser()

//##################################################################
/*
WebDriver driver = DriverFactory.getWebDriver()
//'To locate table'
WebElement Table = driver.findElement(By.xpath('//*[@id="ResultsPanel"]/table'))
//'To locate rows of table it will Capture all the rows available in the table '
List<WebElement> Rows = Table.findElements(By.tagName('tr'))
println('No. of rows: ' + Rows.size())
def rsize = Rows.size()
WebUI.closeBrowser()
for (int i = 0; i < rsize; i++){
WebUI.openBrowser('')
WebUI.navigateToUrl('https://plattemo.icounty.com/iRecordWeb2.0/login.aspx')
WebUI.setText(findTestObject('Object Repository/SavePage/Page_iRecord Client Login/input_EMail Address_USERID'), 'GUEST')
WebUI.click(findTestObject('Object Repository/SavePage/Page_iRecord Client Login/input_Password_btnLogin'))
//WebUI.waitForElementClickable(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN', 10))
WebUI.waitForElementClickable(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), 10)
WebUI.setText(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), 'Jones')
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/SavePage/Page_Real Estate-SearchByName/input_Business-orLastName_LN'), Keys.chord(Keys.ENTER))
//WebUI.delay(1)
driver = DriverFactory.getWebDriver()
//'To locate table'
//Table = driver.findElement(By.xpath('//*[@id="ResultsPanel"]/table'))
//WebUI.delay(1)
driver.findElement(By.xpath('//*[@id="ResultsPanel"]/table/tbody/tr[' + row + ']/td[1]/a')).click()
//new File('myresults.html').append(html)
//WebUI.delay(1)
def html2 = WebUI.getAttribute(findTestObject('Object Repository/SavePage/Page_Real Estate - Search By Name/Jones1Page'), 'innerHTML')
new File('myresults'+row+'.html').append(html2)
WebUI.closeBrowser()
if (row >= 22)
	break
row++
}
*/
//##################################################################

//'Find a matching text in a table and performing action'
//'Loop will execute for all the rows of the table'
//boolean find = false
//boolean one = false

//List<WebElement> Cols = null


		
			/*if (Cols.get(j).getText().equalsIgnoreCase(ExpectedValue)) {
				println Cols.get(j).getText();
				//WebUI.click(findTestObject('Object Repository/SavePage/Page_Real Estate - Search By Name/a_JONES'));
				//'To locate anchor in the expected value matched row to perform action'
				println "rivinumero kohta 1 "+row
				driver.findElement(By.xpath('//*[@id="ResultsPanel"]/table/tbody/tr[' + row + ']/td[1]/a')).click()
				WebUI.delay(2)
				def html2 = WebUI.getAttribute(findTestObject('Object Repository/SavePage/Page_Real Estate - Search By Name/Jones1Page'), 'innerHTML')
				WebUI.delay(2)
				new File('myresults'+row+'.html').append(html2) 
				WebUI.back()
				WebUI.delay(4)
				row++;
				if (row >= 10){
					find = true
					break;
				}
				println "rivinumero kohta 2 "+row
			}*/


//for (int i=0; i > )

//WebUI.delay(4)
//def html2 = WebUI.getAttribute(findTestObject('Object Repository/SavePage/Page_Real Estate - Search By Name/Jones1Page'), 'innerHTML')
//WebUI.delay(2)
//new File('myresults2.html').append(html2)



//static List<WebElement> findWebElements(TestObject to, int timeOut)
// Internal method to find web elements by test object

/*

for (int i = 0; i < Rows.size(); i++) {
//'To locate columns(cells) of that specific row'
	if (find)
		break;
	//println Rows.get(i).getText();
	//println "i arvo "+i
	List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))
	
	for (int j = 0; j < Cols.size(); j++) {
		//'Verifying the expected text in the each cell'
		//println Cols.get(j).getText();
		if (Cols.get(j).getText().equalsIgnoreCase(ExpectedValue)){
			println Cols.get(j).getText();
			//WebUI.delay(2)
			//println "ekassa sarakkeessa "+Cols.get(j).getText();
			//Cols.get(j).findElement(By.tagName('a')).click()
			//Cols.get(j).findElement(By.xpath('//*[@id="ResultsPanel"]/table/tbody/tr[' + row + ']/td[1]/a')).click()
			//WebUI.delay(2)
			//WebUI.back()
			//WebUI.delay(10)
			println row++
		}
*/