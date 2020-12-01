import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://scratch.mit.edu/')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/span_Liity Scratchiin'))

WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Valitse kyttjnimi_username'), 
    'UserRecorded')

WebUI.setEncryptedText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Valitse salasana_password'), 
    'S/0vwLxK2M4itiL3QgmuAPK50VcD91dX86pbFJNjaH8=')

WebUI.setEncryptedText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Vahvista salasana_password-confirm'), 
    'S/0vwLxK2M4itiL3QgmuAPK50VcD91dX86pbFJNjaH8=')

//X 550 Y 400
WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver);
actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
actions.moveByOffset(550, 400).click().build().perform();


/*String xpath = "/html/body/div[1]/div/form/div[9]/div[1]/input"
TestObject to = new TestObject('Object')
to.addProperty('xpath', ConditionType.EQUALS, xpath, true)
WebUI.clickOffset(to, 550, 400)*/

//WebUI.switchToFrame(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'), 30)

WebUI.switchToFrame(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/iframe_html1body1div3div2iframe1_c-77rytku5vrd1'), 20)

//WebUI.switchToFrame(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/iframe__modal-content-iframe mod-registration'), 30)

//WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'))

WebUI.selectOptionByValue(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/select_- Kuukausi -                Tammikuu_3d5432'), 
    '5', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/select_- Vuosi -            201920182017201_4a7fee'), 
    '1960', true)

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Sukupuoli_gender'))

WebUI.selectOptionByValue(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/select_- Maa -                             _393619'), 
    'Finland', true)

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'))

WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_concat(Parent  s or guardian  s email_7815e9'), 
    'mat.pat@gmail.com')

WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Confirm email address_email-confirm'), 
    'mat.pat@gmail.com')

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'))

/*

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en ole_rc-image-tile-33'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en ole_rc-image-tile-33'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en ole_rc-image-tile-33'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/button_Vahvista'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/div_Valitse Vahvista kun uusia kuvia ei en _92037b'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/div_Valitse Vahvista kun uusia kuvia ei en _92037b_1'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/button_Vahvista'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en _b83841'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en _b83841'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en _b83841'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/button_Vahvista'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/div_Valitse Vahvista kun uusia kuvia ei en _92037b_1'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/button_Vahvista'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en _b83841_1'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en _b83841_1'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img_Valitse Vahvista kun uusia kuvia ei en _b83841_1'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/button_Vahvista'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/div_Valitse Vahvista kun uusia kuvia ei en _92037b'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/button_Vahvista'))

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/img__modal-content-close-img'))
*/
