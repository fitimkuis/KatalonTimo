import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String downloadPath = RunConfiguration.getProjectDir() + "/Data/"
println downloadPath
WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()
switch(executedBrowser) {
	case WebUIDriverType.FIREFOX_DRIVER:          // "Firefox"
		System.setProperty('webdriver.gecko.driver', DriverFactory.getGeckoDriverPath())
		FirefoxOptions options = new FirefoxOptions()
				
		options.addPreference('marionette', true)
		options.addPreference('pdfjs.disabled', true)
		options.addPreference('browser.download.folderList', 2)
		options.addPreference('browser.helperApps.alwaysAsk.force', false)
		options.addPreference('browser.download.manager.showWhenStarting', false)
		options.addPreference('browser.download.dir', downloadPath)
		options.addPreference('browser.download.downloadDir', downloadPath)
		options.addPreference('browser.download.defaultFolder', downloadPath)
		options.addPreference('browser.helperApps.neverAsk.saveToDisk', 'application/download, application/octet-stream, text/csv')
		
		WebDriver driver = new FirefoxDriver(options);
		// let Katalon Studio to use the WebDriver created here
		DriverFactory.changeWebDriver(driver)
		driver.get("https://www.google.com")
		break
	default:
		WebUI.openBrowser('')
}