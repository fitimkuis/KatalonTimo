import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable


def filename = "C:\\Users\\fitim\\KatalonProjectFromGit\\KatalonProject\\Include\\tessdata\\eurotext.png"

CustomKeywords.'com.tesseract.image.TesseractUtil.getCaptchInString'(filename)
//print resp

//def ret = CustomKeywords.'com.tesseract.image.TesseractUtil.readImageText'()
//print ret

/*
String dir = System.getProperty("user.dir");
print(dir)

String path = dir.replace("\\", "/");

ITesseract instance;

String datapath = path+"/Include/tessdata";
datapath = datapath.replace("\\", "/");

String expOCRResult = "The (quick) [brown] {fox} jumps!\nOver the";

datapath = "C:\\Users\\fitim\\KatalonProjectFromGit\\KatalonProject\\Include\\tessdata"
def filepath = "C:\\Users\\fitim\\KatalonProjectFromGit\\KatalonProject\\Include\\tessdata\\eurotext.png"

instance = new Tesseract();
instance.setDatapath(datapath);

File image = new File(filepath);

instance.setOcrEngineMode(1);

String expResult = expOCRResult;

String result = instance.doOCR(image);
print(result);

assertEquals(expResult, result.substring(0, expResult.length()));
*/