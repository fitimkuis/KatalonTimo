import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.Assert as Assert


import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.testautomationguru.utility.PDFUtil;

//get pdf file from folder which is added under project
String pdfFilePath = System.getProperty("user.dir")+"\\pdfFiles\\2019-04-24_20-10-35.pdf";
PDFUtil pdfUtil = new PDFUtil();
String content = pdfUtil.getText(pdfFilePath,1);
println content
//String pdfTxt = CustomKeywords.'readPdfFile.ReadFromFolder.readPdfFileFromFolder'(pdfFilePath, 1)



/*
FirefoxProfile ffProfile = new FirefoxProfile();
ffProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip;"); 
		
// If download pdf.
ffProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf;"); 
 
ffProfile.setPreference( "browser.download.manager.showWhenStarting", false );
ffProfile.setPreference( "pdfjs.disabled", true );
 
// Create Firefox browser based on the profile just created.
WebDriver ffDriver = new FirefoxDriver(ffProfile);  
 
// For tomcat 9.0.zip.
//ffDriver.get("https://docs.oracle.com/javaee/7/JEETT.pdf");
 
ffDriver.get("https://docs.oracle.com/javaee/7/JEETT.pdf");

// Wait 10 seconds for the process complete. 
Thread.sleep(10000);

System.out.println("Task complete, please go to save folder to see it.");*/
WebUI.closeBrowser()

WebUI.openBrowser('')
WebUI.navigateToUrl('http://www.testingdiaries.com/selenium-webdriver-read-pdf-content/')

WebUI.click(findTestObject('Page_SeleniumWebDriverRead-PDF-Co/a_this-location'))//click pdf link button

WebUI.delay(5)
WebUI.switchToWindowIndex(1) //pdf file url here

WebUI.delay(10)

url = WebUI.getUrl() //get url
println ("***DEBUG URL*** "+url)

String pdfContent = readPdfFile(url)

//String pdfContent = CustomKeywords.'readPdfFile.verifyPdfContent.readPdfFile'(url)

Assert.assertTrue(pdfContent.contains('Open the setting.xml, you can see it is like this:'))

Assert.assertTrue(pdfContent.contains('Please add the following sentence in setting.xml before'))

Assert.assertTrue(pdfContent.contains('You can see that I have modified the setting.xml, and if open the file in IE, it is like this:'))

println('PDF IS GOOD TO GO...\r')

WebUI.switchToWindowIndex(0) //back to main window

WebUI.closeBrowser()


public String readPdfFile(String pdfUrl){
	
			URL TestURL = new URL(pdfUrl);
			BufferedInputStream bis = new BufferedInputStream(TestURL.openStream());
			PDDocument doc = PDDocument.load(bis);
			String pdfText = new PDFTextStripper().getText(doc);
			doc.close();
			bis.close();
			println(pdfText);
			return pdfText;
}

public void testChromeDownloadPopup() throws InterruptedException {
	
		/* Set string variable value to ChromeDriver executable file path.*/
		String chromeDriverPath = "C:\\Users\\fitim\\Desktop\\ajuri\\chromedriver.exe";
		/* Assign chromeDriverPath to system property "webdriver.chrome.driver" */
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		
		Map<String, Object> chromePreferences = new Hashtable<String, Object>();

		chromePreferences.put("profile.default_content_settings.popups", 0);
		chromePreferences.put("download.prompt_for_download", "false");
		
		chromePreferences.put("pdfjs.disabled", true);
		
		/* Set file save to directory. */
		chromePreferences.put("download.default_directory", "C:\\Users\\fitim\\Desktop\\data\\chrome.pdf");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePreferences);

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		  
		//Initiate ChromeDriver
		ChromeDriver cDriver = new ChromeDriver(cap);

		
		/* For tomcat 9.0.zip. */
		//cDriver.get("http://mirror.nexcess.net/apache/tomcat/tomcat-9/v9.0.0.M22/bin/apache-tomcat-9.0.0.M22.zip");
		
		
		/* For pdf.
		 * First check not show pdf in Chrome browser.
		 * */
		//this.setChromeOptions(cDriver);
		cDriver.get("https://docs.oracle.com/javaee/7/JEETT.pdf");
		
		System.out.println("Task complete, please go to save folder to see it.");
		cDriver.close()
	}

/* Change option to not show pdf in browser directly. */
private void setChromeOptions(ChromeDriver cDriver)
{
	/* Go to Chrome configure options page. */
	cDriver.get("chrome://settings");
	
	/* Find the pdf configure section input checkbox.*/
	By pdfSectionBy = By.id("pdf-section");
	WebElement pdfSectionElement = cDriver.findElement(pdfSectionBy);
	/* Find checkbox in configure section. */
	By inputBy = By.tagName("input");
	WebElement pdfSectionInput = pdfSectionElement.findElement(inputBy);
	
	/* If not checked then check it. */
	if(!pdfSectionInput.isSelected())
	{
		pdfSectionInput.click();
	}
}

public void chromeProperties() {
	WebUI.openBrowser()
	    WebDriver driver = DriverFactory.getWebDriver()
		System.setProperty("webdriver.chrome.driver","C:\\Users\\fitim\\Desktop\\ajuri\\chromedriver.exe");
		String downloadFilepath = "C:\\Users\\fitim\\Desktop\\data";
	
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		options.addArguments("--disable-extensions"); //to disable browser extension popup
	
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(cap);
		driver.get("http://www.seleniumhq.org/download/");
		driver.findElement(By.linkText("32 bit Windows IE")).click();
	 }