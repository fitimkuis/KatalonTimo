import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kazurayam.webdriverfactory4ks.ChromeDriverFactory
import com.kazurayam.webdriverfactory4ks.ChromeProfileFinder
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens Chrome browser with pre-defined Profile named 'Katalon'.
 * If you do not have 'Katalon' profile in your Chrome, an Exception will be thrown.
 */
//System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeOptions options = new ChromeOptions();
options.addArguments("--user-data-dir=C:/Users/fitim/AppData/Local/Google/Chrome/User Data/Profile 1");
options.addArguments("--disable-extensions");
WebDriver driver = new ChromeDriver(options)
//Webdriver driver = new ChromeDriverFactory(options)
driver.get("http://demoaut.katalon.com/");
WebUI.delay(3)
//String profileName = ChromeProfileFinder.getChromeProfileNameByDirectoryName('Katalon')
//WebUI.comment("Directory \'Katalon\' is associated with Chrome Profile \'${profileName}\'")
//driver.quit()


/*
ChromeDriverFactory cdFactory = new ChromeDriverFactory()
WebDriver driver = cdFactory.openChromeDriverWithProfile('Katalon')  // THIS IS THE MAGIC
assert driver != null
DriverFactory.changeWebDriver(driver)
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(3)
WebUI.closeBrowser()
*/

