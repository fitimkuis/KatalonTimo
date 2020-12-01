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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver

//WebDriver driver = new FirefoxDriver();
// declaration and instantiation of objects/variables
//System.setProperty("webdriver.firefox.marionette","C:\\Users\\fitim\\KatalonStudio\\KatalonProject\\geckodriver.exe");
//WebDriver driver = new FirefoxDriver();
//comment the above 2 lines and uncomment below 2 lines to use Chrome
//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
WebDriver driver = new ChromeDriver()

String baseUrl = 'http://demo.guru99.com/test/newtours/'

String expectedTitle = 'Welcome: Mercury Tours'

String actualTitle = ''

// launch Fire fox and direct it to the Base URL
driver.get(baseUrl)

// get the actual value of the title
actualTitle = driver.getTitle()

/*
 * compare the actual title of the page with the expected one and print
 * the result as "Passed" or "Failed"
 */
if (actualTitle.contentEquals(expectedTitle)) {
    System.out.println('Test Passed!')
} else {
    System.out.println('Test Failed')
}

WebUI.callTestCase(findTestCase('ChromeDriverTest/UseDriverInSecondTestCase'), [('driver') : driver], FailureHandling.CONTINUE_ON_FAILURE)

//close Fire fox
driver.close()
