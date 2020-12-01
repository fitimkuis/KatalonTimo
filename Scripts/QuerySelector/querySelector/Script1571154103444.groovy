import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//Notice the cursor sitting on the blank line at the bottom of the panel? You can test your CSS selectors here. 
//Go to the forum main page, and type this in the Browser console:
//document.querySelector("body").style.backgroundColor = "pink"


System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
WebDriver driver = new ChromeDriver()

String baseUrl = 'http://demo.guru99.com/test/newtours/'

driver.get(baseUrl)

JavascriptExecutor js = (JavascriptExecutor)driver;

String five = '5'

//Selenium Webdriver style
js.executeScript("document.querySelector('body > div:nth-child(${five}) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3) > form > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=text]').value = 'fitimkuis'")
js.executeScript("document.querySelector('body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3) > form > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=text]').style.backgroundColor = 'pink'")

js.executeScript("document.querySelector('body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3) > form > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type=password]').value = 'ModeeMi16'")
js.executeScript("document.querySelector('body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3) > form > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type=password]').style.backgroundColor = 'pink'")

//js.executeScript("document.querySelector('body').style.backgroundColor = 'pink'")

//Katalon style
String jpass = '''document.querySelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3) > form > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type=password]").value = ModeeMi16)'''
String katalon = '''document.querySelector("div#schedulerSch .k-scheduler-content>table td:nth-child(${j}) > span").click()'''
//WebUI.executeJavaScript(jpass, null)

String pass = '''document.querySelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3) > form > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type=text]").style.backgroundColor = pink'''
//WebUI.executeJavaScript(pass, null)

//body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3) > form > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=text]
//js.executeScript("document.querySelector('.pane .object_options .dropdown-toggle span').click();");

WebUI.delay(10)

//driver.close()
driver.quit()
