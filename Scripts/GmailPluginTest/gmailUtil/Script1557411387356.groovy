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

import java.util.regex.Matcher
import java.util.regex.Pattern

CustomKeywords.'com.testwithhari.katalon.plugins.Gmail.sendEmail'("fitimkuis@gmail.com", "ModeeMi16", "fitimkuis@gmail.com", "This is Test GmailPlugin", "hello, let's testing this gmail plugin!!!")

def count = CustomKeywords.'com.testwithhari.katalon.plugins.Gmail.getEmailsCount'("fitimkuis@gmail.com", "ModeeMi16", "INBOX")
println count

def latest = CustomKeywords.'com.testwithhari.katalon.plugins.Gmail.readLatestEMailBodyContent'("fitimkuis@gmail.com", "ModeeMi16", "INBOX")
println latest

String emailc = CustomKeywords.'com.testwithhari.katalon.plugins.Gmail.readLatestEMailBodyContent'("fitimkuis@gmail.com",
	"ModeeMi16", "INBOX")

Pattern urlPattern = Pattern.compile('\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]', Pattern.CASE_INSENSITIVE)

String urlString = emailc

Matcher matcher = urlPattern.matcher(urlString)

while (matcher.find()) {
	String address = matcher.group()

	println('Got URL: ' + address)
	WebUI.openBrowser(address)
}