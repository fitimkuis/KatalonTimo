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

import com.kms.katalon.core.util.KeywordUtil

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

File dir = new File("C:\\Program Files (x86)\\Jenkins\\jobs\\TestSuiteCollection\\builds");
File max = null;
for (File file : dir.listFiles()) {
    if (file.isDirectory() && (max == null || max.lastModified() < file.lastModified())) {
        max = file;
    }
}
println max

def path = max.toString()+"\\log"
List l = CustomKeywords.'readFile.read.readFileInList'(path)

KeywordUtil log = new KeywordUtil()

  Iterator<String> itr = l.iterator();
  while (itr.hasNext()){
	  
	if (itr.next().contains("Finished:")){
		log.markPassed("Jenkins job done !!!")
		break
	}
	//System.out.println(itr.next());
  }
/*
def path = "C:\\Program Files (x86)\\Jenkins\\jobs\\TestSuiteCollection\\builds\\78\\log"
//Recording test results
//Finished: SUCCESS

List l = CustomKeywords.'readFile.read.readFileInList'(path)

String s = l.get(1);
KeywordUtil log = new KeywordUtil()
log.logInfo("DEBUG value is "+s)

  Iterator<String> itr = l.iterator();
  while (itr.hasNext()){
	  
	if (itr.next().contains("Finished:")){
		log.markPassed("Jenkins job done !!!")
	}
	//System.out.println(itr.next());
  }
 */ 
 
  /*
 //Get the newest file for a specific extension
 //public File getTheNewestFile(String filePath, String ext) {
	  File theNewestFile = null;
	  File dir = new File(filePath);
	  FileFilter fileFilter = new WildcardFileFilter("*." + ext);
	  File[] files = dir.listFiles(fileFilter);
  
	  if (files.length > 0) {
		  //** The newest file comes first
		  Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		  theNewestFile = files[0];
	  }
  
	  return theNewestFile;
  }*/
	