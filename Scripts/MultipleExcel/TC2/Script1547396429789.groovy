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

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

import internal.GlobalVariable as GlobalVariable

/**
 * This Test Case will output a Excel file which contains a sheet named `TC2`.
 * The sheet contains a pangram in Russian language.
 *
 * Apache POI HSSFWorkbook objct will be prepared by MyTestListener's @BeforeTestSuite method
 * or MyTestListener's @BeforeTestCase method.
 */


HSSFWorkbook wb = (HSSFWorkbook)GlobalVariable.WORKBOOK

// In the workbook, you can do anything you like

// create a sheet named 'TC2'
String sheetName = (String)GlobalVariable.CURRENT_TESTCASE_NAME
HSSFSheet sheet = CustomKeywords.'com.kazurayam.ksbackyard.excel.HSSFSupport.findSheet'(wb, sheetName)

// In the Cell `B2`, write a sentence
HSSFRow row = sheet.createRow(0)
row.createCell(0).setCellValue('В чащах юга жил бы цитрус? Да, но фальшивый экземпляр!')

/*
 * The Excel file will be written by MyTestListener's @AfterTestCase method.
 *
 */