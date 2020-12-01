import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.swing.JFrame
import javax.swing.JOptionPane

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

KeywordUtil log = new KeywordUtil()


JFrame frame = new JFrame("User Input Frame")
frame.requestFocus()
String number = JOptionPane.showInputDialog("frame, Enter the days added to current day!")
int days = Integer.valueOf(number)

WebUI.openBrowser('')
WebUI.navigateToUrl("https://www.sierratec.com/efacilitydemo/IgGrid_CRUD.aspx")

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/btnAddfamily'))

WebUI.click(findTestObject('Object Repository/Spy2/SelectFields/clickGenderField'))
WebUI.click(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/imgArrow'))
WebUI.click(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/a_MALE'))

WebUI.focus(findTestObject('Object Repository/Spy2/SelectFields/clickDatePickerField'))
WebUI.click(findTestObject('Object Repository/Spy2/SelectFields/clickDatePickerField'))

//today
WebUI.sendKeys(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/clickDatePickerField'), Keys.chord(Keys.ARROW_UP))
WebUI.sendKeys(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/clickDatePickerField'), Keys.chord(Keys.ARROW_DOWN, Keys.ENTER))
String today = WebUI.getText(findTestObject('Object Repository/Spy2/SelectFields/clickDatePickerField'))
WebUI.takeScreenshot("C:\\Users\\fitim\\KatalonFromGitHub\\KatalonProject\\sceenshot-today.png")

//log date with order day, month, year
String[] parts = today.split("/");
String year = parts[parts.length-1];
String day = parts[parts.length-2];
String month = parts[parts.length-3];
dayMonthYear = day+"/"+month+"/"+year
println("DEBUG day/month/year: "+dayMonthYear)
log.logInfo("DEBUG day/month/year: "+dayMonthYear)

//today + 5 days
WebUI.click(findTestObject('Object Repository/Spy2/SelectFields/clickDatePickerField'))
WebUI.delay(1)
for (int i = 0; i < days; i++){
	WebUI.sendKeys(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/clickDatePickerField'), Keys.chord(Keys.ARROW_UP))
}
WebUI.sendKeys(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/clickDatePickerField'), Keys.chord(Keys.ENTER))
WebUI.sleep(5)
String todayPlus5 = WebUI.getText(findTestObject('Object Repository/Spy2/SelectFields/clickDatePickerField'))

//log date with order day, month, year
String[] parts2 = todayPlus5.split("/");
String year2 = parts2[parts2.length-1];
String day2 = parts2[parts2.length-2];
String month2 = parts2[parts2.length-3];
dayMonthYear2 = day2+"/"+month2+"/"+year2
println("DEBUG day/month/year: "+dayMonthYear2)
log.logInfo("DEBUG day/month/year: "+dayMonthYear2)

WebUI.takeScreenshot("C:\\Users\\fitim\\KatalonFromGitHub\\KatalonProject\\sceenshot-5days-plus.png")

WebUI.closeBrowser()

/*
 //WebDriver driver = DriverFactory.getWebDriver();
 System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath());
 WebDriver driver = new ChromeDriver()
 String baseUrl = "https://www.sierratec.com/efacilitydemo/IgGrid_CRUD.aspx";
 driver.get(baseUrl);
 driver.manage().window().maximize();
 
 WebUI.click(findTestObject('Object Repository/Spy2/EnterpriseFacilitiesManagementSystem/btnAddfamily'))
 
 //driver.findElement(By.xpath("//input[@id='btnAddfamily']")).click();
 
 driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();//date field
 
 driver.close()
 //driver.manage().window().maximize();
 */

//WebDriver driver = DriverFactory.getWebDriver()
//Actions actions = new Actions(driver);
//actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
//actions.moveByOffset(573, 220).click().build().perform();
// Create the  JavascriptExecutor object

//CustomKeywords.'kms.turing.katalon.plugins.assertj.StringAssert.contains'(text1, text2, false, 'contains', FailureHandling.STOP_ON_FAILURE)

//WebDriver driver = new ChromeDriver();
//String baseUrl = "https://www.sierratec.com/efacilitydemo/sample.html";
//driver.get("https://www.sierratec.com/efacilitydemo/IgGrid_CRUD.aspx")
//driver.get(baseUrl);

//driver.manage().window().maximize();

//driver.findElement(By.xpath("//div[@id='sidebar']/div[2]/ul/li/ul/li[3]/a/span")).click();



//WebUI.sleep(5)


//WebUI.takeScreenshot()

//Actions actions = new Actions(driver);
//actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
//actions.moveByOffset(23, 216).click().build().perform();
//actions.moveByOffset(223, 229).click().build().perform();
// Create the  JavascriptExecutor object

//if (driver instanceof JavascriptExecutor) {
//	((JavascriptExecutor) driver).executeScript("el = document.elementFromPoint(100, 200); el.click();");
//}

//def count = getTotalFrameCountInCurrentPage(driver)
//print(count)

//driver.close()

//getFrameByXpath(driver, "//iframe[@id='main']")


//getFrameByIndex(driver, 1)

//driver.switchTo().frame("main");

//driver.findElement(By.xpath("//input[@id='btnAddfamily']")).click();

/*
public void ClickCanvasElement(IWebDriver driver, By locator, int offsetX, int offsetY)
{
	// Create the  JavascriptExecutor object
	JavascriptExecutor js=(JavascriptExecutor)driver;
	try
	{
		IWebElement element = FindElement(driver, locator);
		Actions actions = new Actions(driver);
		for (int i = 0; i < 10; i++)
		{
			if ((element.Displayed == true && element.Enabled == true) || element == null)
			{
				actions.MoveToElement(element, offsetX, offsetY).Click().Perform();


				IJavaScriptExecutor js = (IJavaScriptExecutor)driver;

				js.ExecuteScript("arguments[0].setAttribute('style', arguments[1]);"element","color: red; border: 3px solid red;");

				break;
			}
			System.Threading.Thread.Sleep(500);
		}
	}
	catch (Exception e)
	{
		Console.WriteLine(e.Message);
	}
}*/

public static void getFrameByXpath(WebDriver driver, String xpath)
{
	By byXPath = By.xpath(xpath);
	
	// Get all web elements by xpath.
	List<WebElement> iframeList = driver.findElements(byXPath);
	
	if(iframeList.size()>0)
	{
		// Get the first web element.
		WebElement iframeElement1 = iframeList.get(0);
		print("****Current view iframes***** "+iframeElement1)
		
		// Switch to it.
		//driver.switchTo().frame(iframeElement1);
		
		int totalFrameCount = getTotalFrameCountInCurrentPage(driver);
		
		System.out.println("There are totaly " + totalFrameCount + " frames exist in current frame with xpath " + xpath);
	}else
	{
		System.out.println("Do not find any web element with xpath " + xpath);
	}
}

public static void getFrameById(WebDriver driver, String frameId)
{
	driver.switchTo().frame(frameId);
	
	int totalFrameCount = getTotalFrameCountInCurrentPage(driver);
	
	System.out.println("There are totaly " + totalFrameCount + " frames exist in current frame with id " + frameId);
}

public static void getFrameByIndex(WebDriver driver, int frameIndex)
{
	// Switch to by index.
	driver.switchTo().frame(frameIndex);
	
	// Check whether the switch action successfully or not.
	int totalFrameCount = getTotalFrameCountInCurrentPage(driver);
	
	System.out.println("There are totaly " + totalFrameCount + " frames exist in current frame which index is " + frameIndex);
}

public static int getTotalFrameCountInCurrentPage(WebDriver driver)
{
	int ret = 0;
	
	By byFrameTag = By.tagName("frame");
	List<WebElement> frameList = driver.findElements(byFrameTag);
	int frameSize = frameList.size();
	
	System.out.println("There are " + frameSize + " frame in current web page.");
	
	By byIFrameTag = By.tagName("iframe");
	List<WebElement> iframeList = driver.findElements(byIFrameTag);
	int iframeSize = iframeList.size();
	
	System.out.println("There are " + iframeSize + " iframe in current web page.");
	
	ret = frameSize + iframeSize;
			
	return ret;
}



/*
WebUI.openBrowser('')

WebUI.maximizeWindow()
WebUI.navigateToUrl('https://www.sierratec.com/efacilitydemo/sample.html')

//Spy
WebUI.click(findTestObject('null'))

//WebUI.clickOffset('Object Repository/Page_Infrajistics Sample/Spy/Page_InfrajisticsWithSpy/clickSample3', 400, 600)

WebUI.delay(5)

//WebUI.switchToFrame(findTestObject('null'))

//WebUI.switchToFrame(findTestObject('Object Repository/Page_Infrajistics Sample/iframe_Infragistic Controls_main'))//switch to frame
WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))


WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Infrajistics Sample/span_Sample3'), 30)//CSS
WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/span_Sample3'))//CSS

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/iframe_Infragistic Controls_main'))

//WebUI.switchToFrame(findTestObject('Object Repository/Page_Infrajistics Sample/iframe_Infragistic Controls_main'))//switch to frame

WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_Infrajistics Sample/input_MALE_btnAddfamily'),30)
WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))

//'btn btn-primary'
//css path html body form#form1 div.row div.col-md-12.col-sm-12 div.form-group div.col-md-12.col-sm-12 div.col-md-12.col-sm-12.cus-row-bgcolor.padding-top-10 div.col-md-12.col-sm-12.text-center input#btnAddfamily.btn.btn-primary
//WebUI.executeJavaScript("return document.getElementByClassName('btn btn-primary')[0].click();", null)
//print(element)

//WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Infrajistics Sample/input_MALE_btnAddfamily'), 30)//CSS
//WebUI.doubleClick(findTestObject('Object Repository/Page_Infrajistics Sample/input_MALE_btnAddfamily'))//CSS

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/td_SNo_enabledcell ig_Selected igg_Selected_1aad64'))

WebUI.setText(findTestObject('Object Repository/Page_Infrajistics Sample/input_SNo_igg_EditCell'), 'code')

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/td_SNo_enabledcell ig_Selected igg_Selected_1aad64'))

WebUI.setText(findTestObject('Object Repository/Page_Infrajistics Sample/input_SNo_igg_EditCell'), 'somevalues')

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/td_SNo_enabledcell ig_Selected igg_Selected_1aad64'))

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/img'))

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/a_FEMALE'))

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/td_SNo_enabledcell ig_Selected igg_Selected_1aad64'))

WebUI.click(findTestObject('Object Repository/Page_Infrajistics Sample/img_SNo_igte_ButtonImg'))
*/
WebUI.closeBrowser()

