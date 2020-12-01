package excelHelper

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

import com.kms.katalon.core.exception.StepErrorException;
import com.kms.katalon.core.exception.StepFailedException;
import com.kms.katalon.core.logging.ErrorCollector;
import com.kms.katalon.core.logging.KeywordLogger;

import com.kms.katalon.core.util.KeywordUtil

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

	FileInputStream fsIP
	HSSFWorkbook wb
	HSSFSheet worksheet
	FileOutputStream outputFile
	KeywordUtil logger

	public ExcelUtil(){
		logger = new KeywordUtil()
	}


	@Keyword
	public void ExcelHelperGreateExcelFileWithColumnsName(String path, String sheetName, List<String>columns){

		//Workbook workbook = new XSSFWorkbook();
		Workbook workbook = new HSSFWorkbook();
		/* CreationHelper helps us create instances of various things like DataFormat,
		 Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
		//CreationHelper createHelper = workbook.getCreationHelper

		File file = new File(path);
		if(file.isFile() && file.exists()) {
			logger.logInfo("File exists already return!");
			return

		} else {
			logger.logInfo("file open successfully.");
		}

		//Create file system using specific name
		outputFile = new FileOutputStream(file);

		Sheet sheet = workbook.createSheet(sheetName);

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for(int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Create Cell Style for formatting Date for xlsx
		//CellStyle dateCellStyle = workbook.createCellStyle();
		//dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		//FileOutputStream fileOut = new FileOutputStream(path);

		workbook.write(outputFile);
		workbook.close();
	}

	@Keyword
	public int ExcelHelperGetColumnCount(String path, String sheetName) throws IOException, InvalidFormatException {

		int noOfColumns = 0
		// Check the file extension
		if (!path.endsWith(".xls")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xls");
		}

		try
		{

			Workbook book = WorkbookFactory.create(new File(path));

			//Get first/desired sheet from the workbook
			Sheet sheet = book.getSheet(sheetName);
			noOfColumns = sheet.getRow(0).getLastCellNum();
		}
		catch(Exception ex){
			logger.logInfo(ex)
		}
		return noOfColumns;
	}

	@Keyword
	public List<String> ExcelHelperRead(int colCount, int start, int end, String path, String sheetName) throws IOException, InvalidFormatException {


		// Check the file extension
		if (!path.endsWith(".xls")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xls");
		}

		List<String> excelValues = new ArrayList<String>();
		int MY_MINIMUM_COLUMN_COUNT = colCount;

		try
		{
			Workbook book = WorkbookFactory.create(new File(path));

			//Get first/desired sheet from the workbook
			Sheet sheet = book.getSheet(sheetName);

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			// Decide which rows to process
			int rowStart = start;
			int rowEnd = end;

			for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
				Row r = sheet.getRow(rowNum);
				if (r == null) {
					// This whole row is empty
					// Handle it as needed
					continue;
				}

				int lastColumn = Math.max(r.getLastCellNum(), MY_MINIMUM_COLUMN_COUNT);
				//System.out.println("last column "+lastColumn);

				for (int cn = 0; cn < lastColumn; cn++) {
					Cell c = r.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					if (c == null) {
						excelValues.add("**No Value**");
						// The spreadsheet is empty in this cell
					} else {
						// Do something useful with the cell's contents
						excelValues.add(dataFormatter.formatCellValue(c));
					}
				}
			}
		}
		catch (Exception ex){
			logger.logInfo(ex)
		}

		return excelValues;
	}

	@Keyword
	public List<String> ExcelHelperUpdateFromList(List<String> excel, int rw, int col, String path, String sheetName) throws IOException, InvalidFormatException {

		// Check the file extension
		if (!path.endsWith(".xls")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xls");
		}

		try
		{
			fsIP= new FileInputStream(new File(path)); //Read the spreadsheet that needs to be updated
			wb = new HSSFWorkbook(fsIP); //Access the workbook
			worksheet = wb.getSheet(sheetName); //Access the worksheet, so that we can update / modify it.

			Row row = worksheet.getRow(rw); //row to update
			if (row == null) {
				row = worksheet.createRow(rw);
			}

			int c = col

			for (String s : excel){

				Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//check if cell is null
				if (cell == null || cell.getCellTypeEnum() == CellType.BLANK){
					cell = row.createCell(c);
					cell.setCellValue(s);
					//worksheet.createRow(rw).createCell(c).setCellValue(s); //if cell is null then update it to new value
				}
				else{
					//cell = row.createCell(c);
					cell.setCellValue(s);
				}
				c++
			}

		}
		catch(Exception ex){
			logger.logInfo(ex)
		}
		finally{
			fsIP.close(); //Close the InputStream
			outputFile =new FileOutputStream(new File(path));  //Open FileOutputStream to write updates
			wb.write(outputFile); //write changes
			outputFile.close();  //close the stream
		}
	}

	@Keyword
	public List<String> ExcelHelperUpdateExactValue(String value, int rw, int col, String path, String sheetName) throws IOException, InvalidFormatException {

		// Check the file extension
		if (!path.endsWith(".xls")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xls");
		}

		try
		{
			fsIP= new FileInputStream(new File(path)); //Read the spreadsheet that needs to be updated
			wb = new HSSFWorkbook(fsIP); //Access the workbook
			worksheet = wb.getSheet(sheetName); //Access the worksheet, so that we can update / modify it.

			Row row = worksheet.getRow(rw); //row to update
			if (row == null) {
				row = worksheet.createRow(rw);
			}


			Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//check if cell is null
			if (cell == null || cell.getCellTypeEnum() == CellType.BLANK){
				cell = row.createCell(col);
				cell.setCellValue(value);
			}
			else{
				cell.setCellValue(value);
			}

		}
		catch(Exception ex){
			logger.logInfo(ex)
		}
		finally{
			fsIP.close(); //Close the InputStream
			outputFile =new FileOutputStream(new File(path));  //Open FileOutputStream to write updates
			wb.write(outputFile); //write changes
			outputFile.close();  //close the stream
		}

	}
}
