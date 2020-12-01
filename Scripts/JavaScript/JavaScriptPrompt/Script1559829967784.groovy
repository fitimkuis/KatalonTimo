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

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory


WebUI.executeJavaScript("alert(‘test’)", null)
WebUI.comment("Line after alert")


//another javascript demo
def jsPath = System.getProperty("user.dir")+"/Include/JavaScript/Jsfunctions.js"

ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
try {
  engine.eval(new FileReader(jsPath));
  Invocable invocable = (Invocable) engine;
  Object result;
  result = invocable.invokeFunction("display", 'helloWorld');
  System.out.println(result);
  System.out.println(result.getClass());
  } catch (FileNotFoundException | NoSuchMethodException | ScriptException e) {
	e.printStackTrace();
	}
  
  engine.eval("function composeGreeting(name) {" +"return 'Hello ' + name" +"}");
	Invocable invocable = (Invocable) engine;
	 
	Object funcResult = invocable.invokeFunction("composeGreeting", "baeldung");
	println funcResult
	

def htmlPath = System.getProperty("user.dir")+"/Include/JavaScript/prompt.html"
//CustomKeywords.'readGmail.openWebHtml.openHtmlPage'(htmlPath)

/*Map<String, Object> chromePrefs = new HashMap<String, Object>()
//chromePrefs.put("download.default_directory", downloadPath)
chromePrefs.put("download.prompt_for_download", false)
chromePrefs.put("pdfjs.disabled", true);
System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeOptions options = new ChromeOptions()
//options.addArguments("--headless")
options.setExperimentalOption("prefs", chromePrefs)
WebDriver driver = new ChromeDriver(options)
driver.get(htmlPath);*/

WebUI.openBrowser(htmlPath)

def val = ""
WebUI.click(findTestObject('Object Repository/JavaScript/Page_Input-info/add-user-input'))
long start = System.currentTimeMillis();
 while(true){
	 WebUI.delay(1)
	 long end   = System.currentTimeMillis();
	 float sec = (end - start) / 1000F; 
	 System.out.println(sec + " seconds");
	 if (sec >= 10){
		 break
	 }
 }

WebUI.delay(3)
val = WebUI.getText(findTestObject('Object Repository/JavaScript/Page_Input-info/given-value'))
while (true){
	if (val.equals("")){
		println "no values given !!!"
		break;
	}
	else{
		println val //value read from page
		break
	}
	WebUI.delay(2)
}
WebUI.delay(2)
WebUI.closeBrowser()


  