import org.apache.hc.client5.http.classic.HttpClient
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.apache.hc.core5.http.io.entity.StringEntity
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

import com.fasterxml.jackson.databind.ObjectMapper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String downloadFilepath = "C:\\Users\\fitim\\Desktop\\data\\pdf\\"
HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
chromePreferences.put("profile.default_content_settings.popups", 0);
chromePreferences.put("download.prompt_for_download", "false");
chromePreferences.put("download.default_directory", downloadFilepath);
ChromeOptions chromeOptions = new ChromeOptions();
System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())

chromeOptions.addArguments("start-maximized");
chromeOptions.addArguments("disable-infobars");

//HEADLESS CHROME
chromeOptions.addArguments("headless");

chromeOptions.setExperimentalOption("prefs", chromePreferences);
DesiredCapabilities cap = DesiredCapabilities.chrome();
cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

ChromeDriverService driverService = ChromeDriverService.createDefaultService();
ChromeDriver driver = new ChromeDriver(driverService, chromeOptions);

Map<String, Object> commandParams = new HashMap<>();
commandParams.put("cmd", "Page.setDownloadBehavior");
Map<String, String> params = new HashMap<>();
params.put("behavior", "allow");
params.put("downloadPath", downloadFilepath);
commandParams.put("params", params);
ObjectMapper objectMapper = new ObjectMapper();
HttpClient httpClient = HttpClientBuilder.create().build();
String command = objectMapper.writeValueAsString(commandParams);
String u = driverService.getUrl().toString() + "/session/" + driver.getSessionId() + "/chromium/send_command";
HttpPost request = new HttpPost(u);
request.addHeader("content-type", "application/json");
request.setEntity(new StringEntity(command));
try {
	httpClient.execute(request);
	driver.get("https://docs.oracle.com/javaee/7/JEETT.pdf");
	WebUI.delay(30)
	System.out.println("Task complete, please go to save folder to see it.");
	driver.close()
} catch (IOException e2) {
	e2.printStackTrace();
}

//Continue using the driver for automation
//driver.manage().window().maximize();