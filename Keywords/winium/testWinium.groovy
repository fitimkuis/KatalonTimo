package winium

import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.winium.DesktopOptions
import org.openqa.selenium.winium.WiniumDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

public class testWiniumClass {

	public WiniumDriver d;

	@Keyword
	public void testWiniumExe(){


		//DesktopOptions option = new DesktopOptions();

		Random rand = new Random(System.currentTimeMillis()); // see comments!
		int port = rand.nextInt((9999 - 9000) + 1) + 9000;
		System.out.println("DEBUG port number "+port);
		//WebUI.delay(20)
		//random = Random.generateRandomInteger(1000, 9999);
		//port = 9999

		DesktopOptions options = new DesktopOptions();
		options.setApplicationPath("C:\\Windows\\System32\\calc.exe");
		//options.setApplicationPath("C:\\Windows\\System32\\openfiles.exe");
		//String WiniumEXEpath = System.getProperty("user.dir") + "\\Winium.Desktop.Driver.exe";
		String WiniumEXEpath = "C:\\KatalonStudio\\DataDrivenTestMaster\\Winium.Desktop.Driver.exe";
		File file = new File(WiniumEXEpath);
		if (! file.exists()) {
			throw new IllegalArgumentException("The file " + WiniumEXEpath + " does not exist");
		}
		Runtime.getRuntime().exec(file.getAbsolutePath()+" --port "+port);

		/*try {
			d = new WiniumDriver(new URL("http://localhost:"+port),options);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}*/
		String url = "http://localhost:9999"
		println url
		d = new WiniumDriver(new URL(url), options);

		Thread.sleep(2000);
		d.findElement(By.name("Seitsemän")).click();
		d.findElement(By.name("Plus")).click();
		d.findElement(By.name("Kahdeksan")).click();
		d.findElement(By.name("On yhtä suuri kuin")).click();

		//TestObject to = new TestObject()
		//to = fromElement(d)
		//CustomKeywords.'com.kazurayam.ksbackyard.screenshot.ScreenshotElement.on'(to)

		Thread.sleep(2000);
		String output = d.findElement(By.id("CalculatorResults")).getAttribute("Name");
		System.out.println("Result after addition is: "+output);
		d.findElement(By.name("Sulje Laskin")).click();

		//Thread.sleep(5000);

		//d.quit();

		//d.close();

		/*WiniumDriver driver = null
		 String appPath = "C:/windows/system32/calc.exe"
		 DesktopOptions option = new DesktopOptions()
		 option.setApplicationPath(appPath)
		 option.setDebugConnectToRunningApp(false)
		 option.setLaunchDelay(2)
		 //Winium.Desktop.Driver.exe --port 9000
		 driver = new WiniumDriver(new URL("http://127.0.0.1:9000"),option)
		 Thread.sleep(1000)
		 WebElement window = driver.findElementByClassName("CalcFrame")
		 WebElement menuItem = window.findElement(By.id("MenuBar")).findElement(By.name("View"))
		 menuItem.click()
		 driver.findElementByName("Scientific").click()
		 window.findElement(By.id("MenuBar")).findElement(By.name("View")).click()
		 driver.findElementByName("History").click()
		 window.findElement(By.id("MenuBar")).findElement(By.name("View")).click()
		 driver.findElementByName("History").click()
		 window.findElement(By.id("MenuBar")).findElement(By.name("View")).click()
		 driver.findElementByName("Standard").click()
		 driver.findElementByName("4").click()
		 driver.findElementByName("Add").click()
		 driver.findElementByName("5").click()
		 driver.findElementByName("Equals").click()
		 driver.close()*/
	}


	/*
	 public static void screenshotAftermethod(String action, TestObject testObject){
	 //String a = GlobalVariable.G_Step
	 //String [] b = a.split("-")
	 //int c = b[1].toInteger()
	 //int d = c + 1
	 //GlobalVariable.G_Step = Step-+d
	 new File(RunConfiguration.getReportFolder()+"/SS/")
	 GlobalVariable.RESULTS_DIR = RunConfiguration.getReportFolder()+"/SS/"
	 WebUI.takeScreenshot(GlobalVariable.RESULTS_DIR+GlobalVariable.G_Step+". "+action.toLowerCase().capitalize()+" - "+getScreenshotName(testObject)+".png", FailureHandling.STOP_ON_FAILURE)
	 //WebUI.takeScreenshot("+GlobalVariable.G_Step+". “+action.toLowerCase().capitalize()+” - “+getScreenshotName(testObject)+”.png")
	 }
	 /*
	 public void getScreenshot(WebDriver d) throws Exception
	 {
	 File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
	 //The below method will save the screen shot in d drive with name "screenshot.png"
	 FileUtils.copyFile(scrFile, new File("C:\\Users\\xxxx\\Desktop\\data\\screenshot.png"));
	 }
	 */
	protected String getXPathFromElement(RemoteWebElement element) {
		String elementDescription = element.toString();
		return elementDescription.substring(elementDescription.lastIndexOf("-> xpath: ") + 10, elementDescription.lastIndexOf("]"));
	}

	protected TestObject fromElement(RemoteWebElement element) {
		TestObject testObject = new TestObject();
		testObject.addProperty("xpath", ConditionType.EQUALS, getXPathFromElement(element));
		println testObject
		return testObject;
	}


}
