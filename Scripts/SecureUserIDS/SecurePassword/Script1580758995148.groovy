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

//protected password
String myPassword = "myPassword123";

// Generate Salt. The generated value can be stored in DB.
String salt = CustomKeywords.'com.password.encrypt.PasswordUtils.getSalt'(30)

// Protect user's password. The generated value can be stored in DB.
String mySecurePassword = CustomKeywords.'com.password.encrypt.PasswordUtils.generateSecurePassword'(myPassword, salt)
// Print out protected password
System.out.println("My secure password = " + mySecurePassword);
System.out.println("Salt value = " + salt);

//verify provided password
// User provided password to validate
providedPassword = "myPassword123";

// Encrypted and Base64 encoded password read from database
String securePassword = "HhaNvzTsVYwS/x/zbYXlLOE3ETMXQgllqrDaJY9PD/U=";

// Salt value stored in database
salt = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";

boolean passwordMatch = CustomKeywords.'com.password.encrypt.PasswordUtils.verifyUserPassword'(providedPassword, securePassword, salt);

if(passwordMatch)
{
	System.out.println("Provided user password " + providedPassword + " is correct.");
} else {
	System.out.println("Provided password is incorrect");
}