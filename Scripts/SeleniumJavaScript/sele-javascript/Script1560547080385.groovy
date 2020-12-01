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

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

String downloadPath = "C:\\Users\\fitim\\Desktop\\data\\pdf\\"
Map<String, Object> chromePrefs = new HashMap<String, Object>()
chromePrefs.put("download.default_directory", downloadPath)
chromePrefs.put("download.prompt_for_download", false)
chromePrefs.put("pdfjs.disabled", true);
System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeOptions options = new ChromeOptions()
//options.addArguments("--headless")
options.setExperimentalOption("prefs", chromePrefs)
WebDriver driver = new ChromeDriver(options)

driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//driver.get("http://www.inviul.com");
driver.get("http://demoaut.katalon.com/")

//driver.manage().window().maximize();
//Thread.sleep(1000);

driver.findElement(By.id("btn-make-appointment")).click();
//WebUI.click(findTestObject('LoginPage-of-DemoApplication/a_MakeAppointment'))

String att = driver.findElement(By.id('demo_username_label')).getAttribute("value")
System.out.println("Attribute is "+att );

//Javascript command
JavascriptExecutor js = (JavascriptExecutor)driver;
String text = js.executeScript("return document.getElementById('demo_username_label').innerHTML").toString();
System.out.println("Text on hompage is- "+text );


//JavascriptExecutor js = (JavascriptExecutor)driver;
//String text = js.executeScript("document.querySelector('CSS selectors').disabled = false;")


	  
Thread.sleep(3000);

driver.close();

driver.quit();