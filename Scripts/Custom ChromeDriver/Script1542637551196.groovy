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

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import com.kms.katalon.core.webui.driver.DriverFactory

//Set chromedriver path
/*ChromeOptions options = new ChromeOptions();
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability(ChromeOptions.CAPABILITY, options);
System.setProperty("webdriver.chrome.driver", "C:\\Users\\fitim\\Desktop\\ajuri\\chromedriver.exe");
ChromeDriver driver = new ChromeDriver(capabilities);
DriverFactory.changeWebDriver(driver)*/


//Use Katalon chromedriver
WebUI.openBrowser('')
WebUI.navigateToUrl('http://www.google.com')
WebUI.closeBrowser()

//use external chromedriver
ChromeOptions options = new ChromeOptions();
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability(ChromeOptions.CAPABILITY, options);
System.setProperty("webdriver.chrome.driver", "C:/Users/fitim/bin/chromedriver.exe");
ChromeDriver driver1 = new ChromeDriver(capabilities);
DriverFactory.changeWebDriver(driver1)
driver1.quit()

ChromeDriver driver2 = new ChromeDriver(capabilities);
DriverFactory.changeWebDriver(driver2)
driver2.quit()



WebUI.openBrowser('')
WebUI.navigateToUrl('http://www.google.com')

WebUI.closeBrowser()

/*
def diff = difference("i am a machine", "i am a robot")
println diff


public static String difference(String str1, String str2) {
	if (str1 == null) {
		return str2;
	}
	if (str2 == null) {
		return str1;
	}
	int at = indexOfDifference(str1, str2);
	if (at == -1) {
		return "";
	}
	return str2.substring(at);
}

public static int indexOfDifference(String str1, String str2) {
	if (str1 == str2) {
		return -1;
	}
	if (str1 == null || str2 == null) {
		return 0;
	}
	int i;
	for (i = 0; i < str1.length() && i < str2.length(); ++i) {
		if (str1.charAt(i) != str2.charAt(i)) {
			break;
		}
	}
	if (i < str2.length() || i < str1.length()) {
		return i;
	}
	return -1;
}
*/