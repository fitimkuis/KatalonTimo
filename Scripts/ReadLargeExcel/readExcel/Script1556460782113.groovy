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

//KeywordUtil log = new KeywordUtil()

List<String> excelValues = new ArrayList<String>();
excelPath = "C:\\Users\\fitim\\Desktop\\data\\SampleSuperstore.xls"
int start = 9994
int end = 9995
excelValues = CustomKeywords.'readExcelRows.ReadRows.readExcelRows'(start, end, excelPath)
start++
System.out.println("fetch Row "+start)
for (String temp : excelValues) {
	System.out.print(temp + "\t");
	//println(temp + "\t")
}
rowId = excelValues.get(0)
println rowId
orderId = excelValues.get(1)
println orderId