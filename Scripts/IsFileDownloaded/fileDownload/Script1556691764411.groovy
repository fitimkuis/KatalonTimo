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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.testng.Assert as Assert
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities as DesiredCapabilities
import org.openqa.selenium.remote.CapabilityType as CapabilityType

import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

ChromeOptions options = new ChromeOptions()

//String downloadPath = folder.getAbsolutePath()
//String downloadsPath = System.getProperty("user.home") + "/Downloads";
//println ("downloadpath "+downloadPath)
'Define Custom Path where file needs to be downloaded'
String downloadPath = 'C:\\Users\\fitim\\Desktop\\data'
Map<String, Object> chromePrefs = new HashMap<String, Object>()
chromePrefs.put('profile.default_content_settings.popups', 0)
chromePrefs.put('download.default_directory', downloadPath)
chromePrefs.put('download.prompt_for_download', false)
chromePrefs.put('plugins.plugins_disabled', 'Chrome PDF Viewer')
//options.addArguments("--headless")
options.addArguments('--window-size=1920,1080')
options.addArguments('--test-type')
options.addArguments('--disable-gpu')
options.addArguments('--no-sandbox')
options.addArguments('--disable-dev-shm-usage')
options.addArguments('--disable-software-rasterizer')
options.addArguments('--disable-popup-blocking')
options.addArguments('--disable-extensions')
options.setExperimentalOption('prefs', chromePrefs)
DesiredCapabilities cap = DesiredCapabilities.chrome()
cap.setCapability(ChromeOptions.CAPABILITY, options)
cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())
WebDriver driver = new ChromeDriver(cap)

'Launch a browser and Navigate to URL'
driver.get('http://spreadsheetpage.com/index.php/file/C35/P10/')
'Clicking on a Link text to download a file'
driver.findElement(By.linkText('smilechart.xls')).click()
'Wait for Some time so that file gets downloaded and Stored in user defined path'
WebUI.delay(10)
'Verifying the file is download in the User defined Path'
Assert.assertEquals(isFileDownloaded(downloadPath, 'smilechart.xls'), 'true')
driver.close()

//get latest downloaded file
def f = getLastDownloadedFile(downloadPath)
println "latest downloaded file name is: "+f.getName()
Assert.assertEquals(f.getName(), 'smilechart.xls', 'true')

//delete created file
Assert.assertEquals(deleteFile(downloadPath, 'smilechart.xls'), 'true')

public void getLatest(String downloadPath){
File uploadDirectory = new File(downloadPath);
File[] downloadedFiles = uploadDirectory.listFiles();

Arrays.sort(downloadedFiles, new Comparator<File>() {
	@Override
	public int compare(File fileOne, File fileTwo) {
		println Long.valueOf(fileOne.lastModified()).compareTo(fileTwo.lastModified())
		return Long.valueOf(fileOne.lastModified()).compareTo(fileTwo.lastModified());
	}
});
}

public File getLastDownloadedFile(String downloadPath) {
	File latest = null;
	try {
		File fl = new File(downloadPath);
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		//Sleep to find latest
		Thread.sleep(10000);
		long lastMod = Long.MIN_VALUE;

		for (File file : files) {
			if (file.lastModified() > lastMod) {
				latest = file;
				lastMod = file.lastModified();
			}
		}
	} catch (Exception e) {
		System.out.println("Exception while getting the last download file :"+ e.getMessage());
	}
	println("The last downloaded file is " + latest.getPath());
	return latest;
}

private File lastFileCreated(String dir) {
	File fl = new File(dir);
	File[] files = fl.listFiles(new FileFilter() {
		public boolean accept(File file) {
			return true;
		}
	});

	FileTime lastCreated = null;
	File choice = null;

	for (File file : files) {
		BasicFileAttributes attr=null;
		try {
			attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		}catch (Exception e){
			System.out.println(e);
		}

		if(lastCreated ==null)
			lastCreated = attr.creationTime();

		if (attr!=null&&attr.creationTime().compareTo(lastCreated)==0) {
			choice = file;
		}
	}
	return choice;
}


String deleteFile(String downloadPath, String fileName)
{
	String flag = "false"
	File file = new File(downloadPath+"\\"+fileName);
	  
	if(file.delete())
	{
		System.out.println("File deleted successfully");
		flag = "true"
		return flag
	}
	else
	{
		System.out.println("Failed to delete the file");
		return flag
	}
}

String isFileDownloaded(String downloadPath, String fileName) {
    String flag = 'false'
    File dir = new File(downloadPath)
    'Creating an Array where it will store all the files from that folder'
    File[] dirContents = dir.listFiles()
    println('Total Files Available in the folder are : ' + dirContents.length)
    'Iterating a loop for number of files available in the folder to verify file name in the folder'
    for (int i = 0; i < dirContents.length; i++) {
        println('File Name at 0 is : ' + dirContents[i].getName())
        'Verifying the file name is available in the folder '
        if (dirContents[i].getName().equals(fileName)) {
            'If the file is found then it will return a value as true'
            flag = 'true'
            return flag
        }
    }
    'If the file is found then it will return a value as false'
    return flag
}