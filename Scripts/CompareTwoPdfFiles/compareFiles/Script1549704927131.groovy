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

import com.testautomationguru.utility.PDFUtil;

String one = "one";
String two = "two";
String three = one+two;
println three;

//http://www.testautomationguru.com/introducing-pdfutil-to-compare-pdf-files-extract-resources/
PDFUtil pdfUtil = new PDFUtil();

String file1="C:/Users/fitim/Desktop/data/file1.pdf";
String file2="C:/Users/fitim/Desktop/data/file2.pdf";

// compares the pdf documents returns a boolean
// true if both files have same content. false otherwise.
def comp = pdfUtil.compare(file1, file2);
println comp

//returns the pdf content - all pages
//pdfUtil.getText("c:/sample.pdf");
 
// returns the pdf content from page number 1
String file_1 = pdfUtil.getText("C:/Users/fitim/Desktop/data/file1.pdf",1);
String file_2 = pdfUtil.getText("C:/Users/fitim/Desktop/data/file2.pdf",1);

def diff1 = CustomKeywords.'readPdfFile.verifyPdfContent.findNotMatching'(file_1, file_2)
def diff2 = CustomKeywords.'readPdfFile.verifyPdfContent.findNotMatching'(file_2, file_1)

String message = "pages have differences file1 have words "+diff1+ " which are not in file2, file2 instead have "+diff2

println "pages have differences file1 have words "+diff1+ " which are not in file2, file2 instead have "+diff2

CustomKeywords.'readPdfFile.verifyPdfContent.writeDfferences'(message, "C:/Users/fitim/Desktop/data/file3.pdf")

 
// returns the pdf content from page number 5 to 8
//pdfUtil.getText("c:/sample.pdf", 5, 8);

// returns the pdf content from page number 2
String page2 = pdfUtil.getText("C:/Users/fitim/Desktop/data/sample.pdf",2);
println page2

def diff0 = CustomKeywords.'readPdfFile.verifyPdfContent.findNotMatching'(file_1, file_1)
println "pages are identical "+diff0



def diff = CustomKeywords.'readPdfFile.verifyPdfContent.getDiffrence'("i am a machine robot", "i am a robot")
println ("difference between string1 and string2: string2 has "+diff)

