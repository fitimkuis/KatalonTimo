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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.configuration.RunConfiguration


String temp = RunConfiguration.getProjectDir() + "/temp/"
//get the file directory
String imgDir = RunConfiguration.getProjectDir() + "/images/"

//copy files to temp folder
CustomKeywords.'com.file.size.FileSizeUtil.copyFilesToTempFolder'(imgDir, temp)


def fileSize1 = ""
def fileSize2 = ""
//get the latest file from the temp directory
File file = CustomKeywords.'com.file.size.FileSizeUtil.getLatestFilefromDir'(temp)

if (file == null) {
	println("File does not exists!")
}
else{
	fileSize1 = CustomKeywords.'com.file.size.FileSizeUtil.verifyFileSize'(file)
	println ("file size in bytes "+fileSize1)
}

String path = file.getAbsolutePath();
println("AbsolutePath is  "+path)
CustomKeywords.'com.file.size.FileSizeUtil.deleteFile'(path)

//get second file and verify sizes
file = CustomKeywords.'com.file.size.FileSizeUtil.getLatestFilefromDir'(temp)
println("latest file in a directory "+file)

if (file == null) {
	println("File does not exists!")
}
else{
	fileSize2 = CustomKeywords.'com.file.size.FileSizeUtil.verifyFileSize'(file)
	println ("DEBUG file size in bytes "+fileSize2)
}

//verify if files size match
if (fileSize1 != fileSize2){
	
	println ("DEBUG file sizes are different file1: "+ fileSize1+ " file2: "+fileSize2)

	}
else{
	println ("DEBUG files are same size!!!")
}




