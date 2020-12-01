import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// open browser and navigate to the AUT
WebUI.openBrowser('')
WebUI.setViewPortSize(1024, 768)
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
WebUI.delay(1)


WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(2)
// highlight a specific element
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.on'(findTestObject('Page_CURA Healthcare Service_top/h1_CURA Healthcare Service'))
WebUI.delay(2)
//List<WebElement> el = driver.findElements(By.cssSelector("*"));
List<WebElement> el = driver.findElements(By.xpath("*"));
for ( WebElement e : el ) {
	print(e.getText());
	WebElement textDemo = driver.findElement(By.xpath("//*[normalize-space(text())="+e.getText()+"]"));
	 if(textDemo.isDisplayed())
	 {
		 System.out.println("Element found using text");
	 }
	 else{
		 System.out.println("Element not found");
	 }
}


// modify WebUI.* keywords which take TestObject as arg0
// so that they call Highlight.on() automatically
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.pandemic'()

WebUI.click(findTestObject('Page_CURA Healthcare Service_top/a_Make Appointment'), FailureHandling.CONTINUE_ON_FAILURE)

JavascriptExecutor js = (JavascriptExecutor) driver;






String color = driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).getCssValue("color");
String backcolor = driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).getCssValue("background-color");
System.out.println(color);
System.out.println(backcolor);
if(!color.equals(backcolor)){
	System.out.println("Text is highlighted!")
}
else{
	System.out.println("Text is not highlighted!")
}

WebElement desiredElement = driver.findElement(By.xpath("//a[@id='btn-make-appointment']"));
WebElement activeElement = driver.switchTo().activeElement();

 /*
Select select = new Select(driver.findElement(By.xpath("//a[@id='btn-make-appointment']")));
WebElement option = select.getFirstSelectedOption();
String defaultItem = option.getText();
System.out.println(defaultItem );*/

//js.executeScript("arguments[0].setAttribute('style','outline: dashed red;');", element);
//def ret = js.executeScript("return window.getSelection().toString();");
//println ("************** "+ret)

WebUI.delay(1)

WebUI.setText(findTestObject('Page_CURA Healthcare Service_login/input_Username_username'), 'John Doe', FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.setEncryptedText(findTestObject('Page_CURA Healthcare Service_login/input_Password_password'), 'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM', FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.click(findTestObject('Page_CURA Healthcare Service_login/button_Login'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

//WebUI.selectOptionByValue(findTestObject('Page_CURA Healthcare Service_appointment/select_Facility'),
//    'Hongkong CURA Healthcare Center', false)
//WebUI.delay(1)

//WebUI.selectOptionByLabel(findTestObject('Page_CURA Healthcare Service_appointment/select_Facility'),
//	'Seoul CURA Healthcare Center', false)
//WebUI.delay(1)

WebUI.selectOptionByIndex(findTestObject('Page_CURA Healthcare Service_appointment/select_Facility'), 0, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)


WebUI.click(findTestObject('Page_CURA Healthcare Service_appointment/input_Apply for hospital readm'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.click(findTestObject('Page_CURA Healthcare Service_appointment/input_Medicaid_programs'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.setText(findTestObject('Page_CURA Healthcare Service_appointment/input_Visit Date (Required)_vi'), '01/12/34', FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.setText(findTestObject('Page_CURA Healthcare Service_appointment/textarea_Comment_comment'), 'This is a comment', FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.click(findTestObject('Page_CURA Healthcare Service_appointment/button_Book Appointment'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.click(findTestObject('Page_CURA Healthcare Service_summary/a_Go to Homepage'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(1)

WebUI.closeBrowser()