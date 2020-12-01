package com.kazurayam.ksbackyard.excel

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

/**
 * 
 * 
 * @author kazurayam
 *
 */
class HSSFSupport {

	/**
	 * This static method looks up a sheet with the specified name in the workbook.
	 * If a sheet is found, then it will be returned.
	 * If no sheet with the name is found, the a new sheet will be created and returned.
	 * 
	 * @param workbook
	 * @param sheetName
	 * @return
	 */
	static HSSFSheet findSheet(HSSFWorkbook workbook, String sheetName) {
		if (workbook.getSheet(sheetName) != null) {
			return workbook.getSheet(sheetName)
		} else {
			return workbook.createSheet(sheetName)
		}
	}
}
