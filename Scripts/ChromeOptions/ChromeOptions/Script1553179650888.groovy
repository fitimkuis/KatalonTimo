import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.CapabilityType

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


File folder

folder = new File(UUID.randomUUID().toString())
folder.mkdir()

//set chrome options
driver = setChromeOptions(folder)
DriverFactory.changeWebDriver(driver)

//verify from pdf viewer
driver.get('https://pressbooks.com/sample-books/')
driver.findElement(By.xpath("//article[@id='post-2344']/div/ul/li/a")).click()
def url = WebUI.getUrl()
println url
String pdfContent = CustomKeywords.'readPdfFile.verifyPdfContent.readPdfFile'(url)
Assert.assertTrue(pdfContent.contains('The PressBooks version of The Metamorphosis, by Franz Kafka.'))

WebUI.delay(5)

//second way to download file using chrome options
driver.get("http://the-internet.herokuapp.com/download")

//set xpath values & download file(s)
listOfFiles = downloadFiles(driver, folder)
println ("list contains "+listOfFiles)

//check that file(s) are not null
for(File file: listOfFiles){
	Assert.assertNotNull(file);
}

//driver.findElement(By.cssSelector(".example a")).click()
//close browser
driver.close()

List <String> lines = new ArrayList<>()
String downloadPath = folder.getAbsolutePath()

//get pdf file from the folder
String fileName = getFileFromFolder(downloadPath)
String pdfFile = downloadPath+"\\"+fileName
println ("*********file to pdf *********"+pdfFile)

//get pdf contents
lines = openPfdFile(pdfFile)
//check that .pdf contain exact value
//Assert.assertTrue(lines.contains('The PressBooks version of The Metamorphosis, by Franz Kafka.'))


'After test delete files'
//deleteFiles(folder)

public void deleteFiles(File folder){
	for(File file: folder.listFiles()){
		file.delete()
	}
}

public String getFileFromFolder(String downloadPath){
	
	File path = new File(downloadPath)
	File[] files = path.listFiles(new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			// Automatically weeds out directories
			return name.toLowerCase().endsWith(".pdf");
		}
	});

	String fileName = ""
	for (File file : files) {
		System.out.println(file.getName());
		fileName = file.getName()
	}
	
	return fileName
	
	/*File folder2 = new File(downloadPath);
	 File[] listOfFiles = folder2.listFiles();
	 for (File file : listOfFiles) {
		 if (file.isFile()) {
			 if (file.toString().toLowerCase().endsWith(".pdf")) {
				 System.out.println(file.getName());
			 }
		 }
	 }
	 System.out.println("End!!");*/
}

public List<String> openPfdFile(String pdfFile){
	
	List<String> lines = new ArrayList<>()
	
	//PDDocument pdfDocument = PDDocument.load(new File("C:/xxxx/fitim/Desktop/data/file2.pdf"))
	PDDocument pdfDocument = PDDocument.load(new File(pdfFile))
	pdfDocument.getClass();
	if (!pdfDocument.isEncrypted()) {
		
	   PDFTextStripperByArea pdfTextStripperByArea = new PDFTextStripperByArea();
	   pdfTextStripperByArea.setSortByPosition(Boolean.TRUE);

	   PDFTextStripper pdfTextStripper = new PDFTextStripper();

	   String pdfFileInText = pdfTextStripper.getText(pdfDocument);
	  
	   lines = pdfFileInText.split("\\r?\\n");
	   for (String line : lines) {
		   System.out.println(line);
	   }

   }
	return lines
}

public WebDriver setChromeOptions(File folder){
	
	ChromeOptions options = new ChromeOptions();
	String downloadPath = folder.getAbsolutePath()
	//String downloadsPath = System.getProperty("user.home") + "/Downloads";
	println ("downloadpath "+downloadPath)
	
	Map<String, Object> chromePrefs = new HashMap<String, Object>()
	chromePrefs.put("profile.default_content_settings.popups", 0);
	chromePrefs.put("download.default_directory", downloadPath)
	chromePrefs.put("download.prompt_for_download", false)
	chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
	options.addArguments("--headless")
	options.addArguments("--window-size=1920,1080")
	options.addArguments("--test-type")
	options.addArguments("--disable-gpu")
	options.addArguments("--no-sandbox")
	options.addArguments("--disable-dev-shm-usage")
	options.addArguments("--disable-software-rasterizer")
	options.addArguments("--disable-popup-blocking")
	options.addArguments("--disable-extensions")
	options.setExperimentalOption("prefs", chromePrefs)
	DesiredCapabilities cap = DesiredCapabilities.chrome()
	cap.setCapability(ChromeOptions.CAPABILITY, options)
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
	System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
	//System.setProperty("webdriver.chrome.driver", "C:\\Users\\timok1\\Desktop\\chromedriver\\chromedriver.exe")
	WebDriver driver = new ChromeDriver(cap);
	return driver
}

public List<String> downloadFiles(WebDriver driver, File folder){
	
	List <WebElement> ele = driver.findElements(By.xpath("//a[contains(@href, 'download')]"))
	println ("elements are "+ele.size())
	
	TestObject myObject = new TestObject("myObject")
	String path = "//*[@id='content'']/div/a[1]"
	int x = 1
	
	List <String> listOfFiles = new ArrayList<>()
	for(WebElement el : ele){
		//modify object xpath property
		path = "//*[@id='content']/div/a["+x+"]"
		myObject.addProperty("xpath", ConditionType.EQUALS, path, true)
		WebUI.click(myObject)//use new relative xpath locator
		listOfFiles = folder.listFiles()
		WebUI.delay(1)
		x++
	}
	return listOfFiles
}
