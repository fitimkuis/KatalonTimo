import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.winium.DesktopOptions
import org.openqa.selenium.winium.WiniumDriver

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WiniumDriver d;

//C:\KatalonStudio>Winium.Desktop.Driver.exe --port 9000
//Starting Windows Desktop Driver on port 9000
//Spy object by ObjectSpy tool

Random rand = new Random(System.currentTimeMillis()); // see comments!
int port = rand.nextInt((9999 - 9000) + 1) + 9000;
port = 9999
System.out.println("DEBUG port number "+port);
//WebUI.delay(20)
//random = Random.generateRandomInteger(1000, 9999);

DesktopOptions options = new DesktopOptions();
options.setApplicationPath("C:\\Windows\\System32\\openfiles.exe");
//String WiniumEXEpath = System.getProperty("user.dir") + "\\Winium.Desktop.Driver.exe";
String WiniumEXEpath = "C:\\KatalonStudio\\DataDrivenTestMaster\\Winium.Desktop.Driver.exe";
File file = new File(WiniumEXEpath);
if (! file.exists()) {
	throw new IllegalArgumentException("The file " + WiniumEXEpath + " does not exist");
}
Runtime.getRuntime().exec(file.getAbsolutePath()+" --port "+port);
//Runtime.getRuntime().exec(file.getAbsolutePath());
//Runtime.getRuntime().exec(file);
try {
	d = new WiniumDriver(new URL("http://localhost:"+port),options);
} catch (MalformedURLException e) {
	e.printStackTrace();
}

WebUI.openBrowser(null)
WebUI.navigateToUrl("http://the-internet.herokuapp.com/upload")
//LaunchLocalBrowser("chrome","http://the-internet.herokuapp.com/upload");//use your own code to launch browser
//driver.findElement(By.id("file-upload")).click();
//String file = System.getProperty("user.dir") + "\\lib\\Testdata.txt";
String filename = "C:\\Users\\fitim\\demo.pdf";
WebUI.click(findTestObject('Object Repository/FileUpload/Page_TheInternet/input_FileUploader_file'))

WebUI.delay(2)

//d.findElementByName("Osoite: Timo Kuisma:").sendKeys("C:\\AutoIT");
//d.findElementByName("File name:").sendKeys(filename);
d.findElementByName("Tiedostonimi:").sendKeys(filename);
d.findElementByXPath("//*[@Name='Peruuta']//preceding-sibling::*[@Name='Avaa']").click();
//d.findElementByXPath("//*[@Name='Cancel']//preceding-sibling::*[@Name='Open']").click();
WebUI.click(findTestObject('Object Repository/FileUpload/Page_TheInternet/input_FileUploader_file-submi'))
//driver.findElement(By.id("file-submit")).click();
WebUI.delay(10)
WebUI.closeBrowser()
//d.close()
//WebUI.delay(10)