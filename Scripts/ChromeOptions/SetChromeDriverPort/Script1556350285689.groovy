import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

boolean useProxy = true;
ChromeOptions options = new ChromeOptions().addArguments(
//		'--headless',
		'--no-sandbox',
		'--disable-extensions',
		'--proxy-bypass-list=localhost');
if (useProxy) {
	options.addArguments("--proxy-server=http://localhost:8088");
}
//System.setProperty("webdriver.chrome.driver", "C:\\Users\\fitim\\Desktop\\ajuri\\chromedriver.exe");
WebDriver driver = new ChromeDriver(options);
driver.get("http://www.google.com");
WebUI.delay(5)