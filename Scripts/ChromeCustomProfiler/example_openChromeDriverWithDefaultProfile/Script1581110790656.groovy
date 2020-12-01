import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kazurayam.webdriverfactory4ks.ChromeProfileFinder
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens Chrome browser with a Profile which is associated with
 * the user data directory 'Default'.
 * The user data directory 'Default' is created when the Chrome is installed,
 * so usually be there.
 * If the directory 'User Data\Default' is not found, an Exception will be thrown.
 */

ChromeOptions options = new ChromeOptions();
options.addArguments("--user-data-dir=C:/Users/fitim/AppData/Local/Google/Chrome/User Data/Default");
options.addArguments("--disable-extensions");
WebDriver driver = new ChromeDriver(options)
driver.get("http://demoaut.katalon.com/");
String profileName = ChromeProfileFinder.getChromeProfileNameByDirectoryName('Default')
WebUI.comment("Directory \'Default\' is associated with Chrome Profile \'${profileName}\'")
WebUI.delay(3)
//.quit()

/*
ChromeDriverFactory cdFactory = new ChromeDriverFactory()
WebDriver driver = cdFactory.openChromeDriverWithProfileDirectory('Default')
assert driver != null
DriverFactory.changeWebDriver(driver)
WebUI.navigateToUrl('http://demoaut.katalon.com/')

String profileName = ChromeProfileFinder.getChromeProfileNameByDirectoryName('Default')
WebUI.comment("Directory \'Default\' is associated with Chrome Profile \'${profileName}\'")

WebUI.delay(3)
WebUI.closeBrowser()*/