package save.page

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

import org.openqa.selenium.Keys

import org.openqa.selenium.By as By

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import internal.GlobalVariable

public class SaveTableToHtml {

	@Keyword
	public void saveJonesRowsToHtml(){

		boolean logout = false

		WebDriver driver = DriverFactory.getWebDriver()
		//'To locate table'
		WebElement Table = driver.findElement(By.xpath('//*[@id="ResultsPanel"]/table'))
		//'To locate rows of table it will Capture all the rows available in the table '
		List<WebElement> Rows = Table.findElements(By.tagName('tr'))

		String ExpectedValue = 'JONES'
		//String ExpectedValue = 'KISSA'
		int row = 3;
		boolean find = false
		List<WebElement> Cols = null

		for (int i = 0; i < Rows.size(); i++) {
			if (logout){
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
				Table = driver.findElement(By.xpath('//*[@id="ResultsPanel"]/table'))
				//'To locate rows of table it will Capture all the rows available in the table '
				Rows = Table.findElements(By.tagName('tr'))
				logout = false
			}

			//'To locate columns(cells) of that specific row'

			//println Rows.get(i).getText();
			//println "i arvo "+i
			Cols = Rows.get(i).findElements(By.tagName('td'))
			for (int j = 0; j < Cols.size(); j++) {
				//'Verifying the expected text in the each cell'
				println Cols.get(j).getText();
				if (Cols.get(j).getText().equalsIgnoreCase(ExpectedValue)){
					println "Solu nro "+j+ " solun teksti "+Cols.get(j).getText();
					//WebUI.delay(2)
					//println "ekassa sarakkeessa "+Cols.get(j).getText();
					Cols.get(j).findElement(By.tagName('a')).click()
					//Cols.get(j).findElement(By.xpath('//*[@id="ResultsPanel"]/table/tbody/tr[' + row + ']/td[1]/a')).click()
					//WebUI.delay(2)
					//WebUI.click(findTestObject('Object Repository/SavePage/Page_Real Estate - Summary/a_Logout'))
					//WebUI.delay(2)
					WebUI.closeBrowser()
					//WebUI.back()
					//WebUI.delay(2)
					println row++
					logout = true
				}
			}
		}
	}
}
