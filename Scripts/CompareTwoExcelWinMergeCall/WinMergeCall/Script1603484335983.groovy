import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.io.File

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

//import java.nio.file.*

import org.apache.commons.io.FileUtils


//String cmd = "WinMergeU C:\\Users\\fitim\\Desktop\\winmerge\\folder1\\file.txt C:\\Users\\fitim\\Desktop\\winmerge\\folder2 /r /e /f *.ahk /x /xq /s /u /o C:\\Users\\fitim\\Desktop\\winmerge\\output"
//Runtime.getRuntime().exec(cmd)

String downloadPath = "C:/Users/" + System.getProperty("user.name")+ "/Downloads/"
String yesterdayFilePath = "C:/Users/" + System.getProperty("user.name")+ "/Downloads/yesterday/"
String currentFilePath = "C:/Users/" + System.getProperty("user.name")+ "/Downloads/current/"


//get yesterday filepath & filename
def fileLatest = getLastDownloadedFile(downloadPath)

println fileLatest[0]
println fileLatest[1]

String yesterdayFile = fileLatest[0]
String yesterdayFileName = fileLatest[1]

//copy latest file to another directory
copyFile(yesterdayFile, yesterdayFilePath)

//TODO download current file from UI to download folder

//get current file from download folder
fileLatest = getLastDownloadedFile(downloadPath)

println fileLatest[0]
println fileLatest[1]

String currentFile = fileLatest[0]
String currentFileName = fileLatest[1]

//copy latest file to another directory
copyFile(currentFile, currentFilePath)

//compare using WinMerge
//yesterdayFileName = "my_data_file.csv"
//currentFileName = "my_data_file.csv"

println yesterdayFilePath+""+yesterdayFileName
println currentFilePath+""+currentFileName

String cmd = "WinMergeU "+ yesterdayFilePath+""+yesterdayFileName+" "+currentFilePath+ " /r /e /f *.ahk /s /u"
//Runtime.getRuntime().exec(cmd)

String yFilePath = yesterdayFile
String cFilePath = currentFile

String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\fitim\\Desktop\\data\\SampleSuperstore.xls";

def count =  CustomKeywords.'readExcelRows.ReadRows.getCountOfRows'(yFilePath)
println count

int failureCounter = 0

List<String> yesterday = new ArrayList<>()
List<String> today = new ArrayList<>()
boolean isEqual = false
int startRow = 1
int endRow = 2
for (int i = 1; i < 10; i++){ //TODO change value 10 to value of count of used rows in excel sheet 0
	
	//read yesterday
	yesterday = CustomKeywords.'readExcelRows.ReadRows.readExcelRows'(startRow, endRow, yFilePath )
	
	//read current excel
	today = CustomKeywords.'readExcelRows.ReadRows.readExcelRows'(startRow, endRow, cFilePath )
	
	//do diff
	isEqual = compareLists(yesterday, today)
	if (isEqual){
		println("rows ["+startRow+"] are equal")
	}
	else{
		println("rows ["+startRow+"] are not equal")
		failureCounter++
	}
	startRow++
	endRow++
}

//if no failures then remove files from the temp folders
if(failureCounter == 0){
	File yes = new File(yesterdayFilePath)
	File curr = new File(currentFilePath)
	deleteFileFromFolder(yes)
	deleteFileFromFolder(curr)
}

//if test pass then delete temp files from folder tomorrow & current implemnted in listener
public static deleteFileFromFolder(File directory){
	
	FileUtils.cleanDirectory(directory);
}

public static boolean compareLists(List<String> yesterday, List<String>today){
	
	// intersection as set
	Set<String> intersect = new HashSet<String>(yesterday);
	intersect.retainAll(today);
	//System.out.println(intersect.size());
	//System.out.println(intersect);

	// intersection/union as list
	List<String> intersectList = new ArrayList<String>();
	intersectList.addAll(yesterday);
	intersectList.addAll(today);
	intersectList.retainAll(intersect);
	//System.out.println(intersectList);

	// original lists are structurally unmodified
	System.out.println(yesterday);
	System.out.println(today);
	
	boolean isEqual = yesterday.equals(today);
	//System.out.println(isEqual);
	
	return isEqual
}

public static copyFile(String file1, String file2) throws IOException
{
	File srcFile = new File(file1);
	File trgDir = new File(file2);

	FileUtils.copyFileToDirectory(srcFile, trgDir);
}

public static deleteFile(String path)
{
	File file = new File(path);
	  
	if(file.delete())
	{
		System.out.println("File deleted successfully");
	}
	else
	{
		System.out.println("Failed to delete the file");
	}
}


public static List<String> getLastDownloadedFile(String downloadPath) {
	
	List<String> array = new ArrayList<>()
	
	File choice = null;
	try {
		File fl = new File(downloadPath);
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
//Sleep to download file if not required can be removed
		Thread.sleep(2000);
		long lastMod = Long.MIN_VALUE;

		for (File file : files) {
			if (file.lastModified() > lastMod) {
				choice = file;
				lastMod = file.lastModified();
			}
		}
	} catch (Exception e) {
		System.out.println("Exception while getting the last download file :"
				+ e.getMessage());
	}
	array.add(choice)
	array.add(choice.getName())
	//System.out.println("The last downloaded file is " + choice.getPath());
	//System.out.println("The last downloaded file is " + choice.getPath(),true);
	//println array
	return array;
}