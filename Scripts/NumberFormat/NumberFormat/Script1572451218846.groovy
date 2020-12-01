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

import com.test.classvariable.ClassVariable;

String strVal = "123.456"
String strVal2 = "321.654"
double dVal = Double.parseDouble(strVal);
double dVal2 = Double.parseDouble(strVal2);

double dVal3 = dVal + dVal2

import java.text.DecimalFormat;
DecimalFormat money = new DecimalFormat ("0.00");
System.out.println(money.format(dVal3));

import com.kms.katalon.util.CryptoUtil

def originalText = 'mypassword'
println("Original text: ${originalText}")

def encryptedText = CryptoUtil.encode(CryptoUtil.getDefault(originalText))
println("Encrypted text: ${encryptedText}")


def decryptedText = (CryptoUtil.decode(CryptoUtil.getDefault(encryptedText)))
println("Decrypted text: ${decryptedText}")



ClassVariable cv = new ClassVariable()
double a = 123.55
double b = 321.77
double c = cv.getSum(a,b)
cv.setDval(c)
println (cv.getDval())


