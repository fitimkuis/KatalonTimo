import static org.junit.Assert.*

import java.text.SimpleDateFormat

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory

/*
String line = "2013-10-27T13:00:00.325234Z";
DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
LocalDateTime time = LocalDateTime.parse(line, dateFormat);
Timestamp ts = Timestamp.valueOf(time);
System.out.println(ts);
*/
/*
String line = "Mon Jan 01 00:00:00 EET 2020";
DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy HH:mm:ss zzz").withLocale( Locale.ENGLISH );
LocalDateTime time = LocalDateTime.parse(line, dateFormat);
Timestamp ts = Timestamp.valueOf(time);
System.out.println(ts);
*/

SimpleDateFormat sdf = new SimpleDateFormat('dd/mm/yy')
Date parsedDate = null
String sentDate = '9/1/20'
parsedDate = sdf.parse(sentDate);
println (parsedDate)
sdf = new SimpleDateFormat('dd/mm/YYYY')
String sDate = sdf.format(parsedDate)
println (sDate)

//Thu Jan 09 00:01:00 EET 2020
//09/01/2020

def folder = RunConfiguration.getReportFolder()
print folder

/*WebUI.openBrowser('https://www.katalon.com/')
def driver = DriverFactory.getWebDriver()
String baseUrl = "https://www.katalon.com/"
selenium = new WebDriverBackedSelenium(driver, baseUrl)*/

Map<String, Object> chromePrefs = new HashMap<String, Object>()
//chromePrefs.put("download.default_directory", downloadPath)
chromePrefs.put("download.prompt_for_download", false)
chromePrefs.put("pdfjs.disabled", true);
System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeOptions options = new ChromeOptions()
//options.addArguments("--headless")
options.setExperimentalOption("prefs", chromePrefs)
WebDriver driver = new ChromeDriver(options)
driver.get("https://www.katalon.com/");
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("return prompt('Please enter the rate for price reduction')").toString();

//println ${doc}
/*
//selenium.runScript("return prompt('Please enter the rate for price reduction'),r")
//System.out.println("${r}");
// selenium.()
// selenium.()

//runScript (return prompt('Please enter the rate for price reduction',r))
//echo ${r}


String command = "python /c start python C:\\Users\\fitim\\Desktop\\data\\ssn.py";
println command
Process p = Runtime.getRuntime().exec(command);

//def ssn = CustomKeywords.'ssn.setSsn.getSsn'(1)
//println ssn

List<String> resp = new ArrayList<>(Arrays.asList("available", "available", "available", "available"))
println resp[0]

String spam = "20190319_200741_1553018936546"
spam1 = spam.substring(0,8)
spam2 = spam.substring(9,15)
println spam1
println spam2

WebUI.openBrowser('')

WebUI.navigateToUrl('https://eonasdan.github.io/bootstrap-datetimepicker/')

WebUI.click(findTestObject('Object Repository/DatePicker/Page_/REMOVE/Page_/span_Minimum Setup_glyphicon glyphicon-calendar'))

WebUI.click(findTestObject('DatePicker/Page_/select-year-month'))

WebUI.click(findTestObject('DatePicker/Page_/select-year'))

WebUI.click(findTestObject('DatePicker/Page_/select-year-from-table'))

WebUI.click(findTestObject('DatePicker/Page_/select-month'))

WebUI.click(findTestObject('Object Repository/DatePicker/Page_/REMOVE/Page_/td_10'))

WebUI.closeBrowser()


*/


