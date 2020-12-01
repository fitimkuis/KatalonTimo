import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions as Actions

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html")

//WebUI.click(findTestObject('Object Repository/Page_SelectMultipleValues/select_VolvoSaabOpelAudi'))


WebDriver driver = DriverFactory.getWebDriver()

WebElement element = driver.findElement(By.id("multi-select"))

List rows = element.findElements(By.xpath("//*[@id='multi-select']//option"))

System.out.println(rows.size());

for (String s : rows){
	println s
}


Actions actions = new Actions(DriverFactory.getWebDriver());
actions.keyDown(Keys.LEFT_CONTROL)
.click(rows.get(0))
.click(rows.get(1))
.click(rows.get(2))
.click(rows.get(3))
.click(rows.get(4))
.click(rows.get(5))
.click(rows.get(6))
.click(rows.get(7))
.keyUp(Keys.LEFT_CONTROL)
.build()
.perform();

WebUI.delay(5)

WebUI.closeBrowser()