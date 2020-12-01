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

//for(int i=0;i<5;i++)
//Runtime.getRuntime().exec("calc");

//Runtime.getRuntime().exec("nircmd cdrom open c:")

WebUI.openBrowser(null)
WebUI.navigateToUrl("https://cgi-lib.berkeley.edu/ex/fup.html")
WebUI.scrollToElement(findTestObject('Object Repository/FileUpload/Page_Sample File Upload Form/input_FiletouploadNotesabou'), 0)
WebUI.doubleClick(findTestObject('Object Repository/FileUpload/Page_Sample File Upload Form/input_FiletouploadNotesabou'))
upload()

def upload(){
Runtime runTime = Runtime.getRuntime()
Process process = runTime.exec("C:\\AutoIT\\upload2.exe")
process.destroy()
}
//WebUI.click(findTestObject('FileUpload/Page_Sample File Upload Form/input'))

WebUI.delay(10)

/*
 WinWaitActive("File Upload")
If WinWaitActive("File Upload") Then
   Send("C:\Users\fitim\Desktop\demo.pdf")
   Sleep (5000)
   Send ("{TAB}")
   Send ("{TAB}")
   Send ("{ENTER}")
EndIf
 
 */

//C:\Users\fitim\Desktop