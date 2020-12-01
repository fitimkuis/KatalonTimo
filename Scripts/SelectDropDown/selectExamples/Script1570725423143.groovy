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

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


WebUI.openBrowser('')

WebUI.navigateToUrl("http://jedwatson.github.io/react-select/")

WebUI.click(findTestObject('Object Repository/Page_React-SelectExample/Select-arrow'))

WebUI.click(findTestObject('Object Repository/Page_React-SelectExample/div_Vanilla'))

WebUI.click(findTestObject('Object Repository/Page_React-SelectExample/Select-arrow'))

WebUI.click(findTestObject('Object Repository/Page_React-SelectExample/div_Peppermint'))

WebUI.delay(5)

WebUI.closeBrowser()

/*SelTest ss = new SelTest();
ss.start();
ss.quit();*/

public class SelTest {
	
		WebDriver driver;
	
		public SelTest() {
			//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			//driver = DriverFactory.getWebDriver()
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
		public void start() {
			driver.get("http://jedwatson.github.io/react-select/");
			reactSelect("3", "Caramel", "Peppermint");
		}
	
		public void reactSelect(String id, String... values) {
			//  
			By selectDropArrow = By.xpath("//div[@class='Select-control'][span[contains(@id,'react-select-" + id + "')]]/span[@class='Select-arrow-zone']");
			//By selectDropArrow = By.xpath("//div[@id='example']/div/div[2]/div/div/span[4]/span");
			
			WebElement dropDownArrow = driver.findElement(selectDropArrow);
			dropDownArrow.click();
			if (values != null) {
				for (String value : values) {
					//  //*[@id="react-select-3--value-1"]
					WebElement option = driver.findElement(By.xpath("//div[@id='react-select-" + id + "--list']/div[@class='Select-option' and text()='" + value + "']"));
					option.click();
				}
			}
		}
	
		public void quit() {
			driver.quit();
		}
	
		public static void main(String[] args) {
			SelTest ss = new SelTest();
			ss.start();
			ss.quit();
		}
	
	}