package excelHelper

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.List

import org.apache.poi.openxml4j.exceptions.InvalidFormatException
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.text.SimpleDateFormat;

import com.kms.katalon.core.annotation.Keyword

import com.kms.katalon.core.util.KeywordUtil

public class UpdateXlsxFile {

	KeywordUtil logger
	FileOutputStream outputFile
	Workbook workbook

	public UpdateXlsxFile(){
		logger = new KeywordUtil()
	}



	@Keyword
	public void updateXlsxFromList(List<String> excel, int rw, int col, String path, String sheetName, String fileName){

		File file = new File(path);

		workbook = WorkbookFactory.create(file);

		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{
			Sheet sheet = workbook.getSheetAt(0);

			Row row = sheet.getRow(rw); //row to update
			if (row == null) {
				row = sheet.createRow(rw);
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


		String path2 = System.getProperty("user.dir")+"\\ExcelFiles\\xlsData_copy.xlsx";
		// Write the output to the file
		FileOutputStream fileOut = new FileOutputStream(path2);
		workbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		workbook.close();

		//delete original file
		File f = new File(path);

		if(f.delete())
		{
			System.out.println("File deleted successfully");
		}
		else
		{
			System.out.println("Failed to delete the file");
		}

		//rename copied file
		Path source = Paths.get(path2);
		Files.move(source, source.resolveSibling(fileName));
	}

	@Keyword
	public void ExcelHelperUpdateExactValueXlsx(String value, int rw, int col, String path, String sheetName, String fileName) throws IOException, InvalidFormatException {

		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{

			File file = new File(path);
			Workbook workbook = WorkbookFactory.create(file);

			Sheet sheet = workbook.getSheetAt(0);

			Row row = sheet.getRow(rw); //row to update
			if (row == null) {
				row = sheet.createRow(rw);
			}


			Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//check if cell is null
			if (cell == null || cell.getCellTypeEnum() == CellType.BLANK){
				cell = row.createCell(col);
				cell.setCellValue(value);
			}
			else{
				cell.setCellValue(value);
			}

			String path2 = System.getProperty("user.dir")+"\\ExcelFiles\\xlsData_copy.xlsx";
			// Write the output to the file
			FileOutputStream fileOut = new FileOutputStream(path2);
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

			//delete original file
			File f = new File(path);

			if(f.delete())
			{
				System.out.println("File deleted successfully");
			}
			else
			{
				System.out.println("Failed to delete the file");
			}

			//rename copied file
			Path source = Paths.get(path2);
			Files.move(source, source.resolveSibling(fileName));

		}
		catch(Exception ex){
			logger.logInfo(ex)
		}
	}

	@Keyword
	public int ExcelHelperGetColumnCountXlsx(String path, String sheetName) throws IOException, InvalidFormatException {

		int noOfColumns = 0
		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{
			//logger.logInfo("starting to calculate count of columns")
			File file = new File(path);
			Workbook workbook = WorkbookFactory.create(file);

			//Get first/desired sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0); //int sheet 0 1 2 ...
			//Sheet sheet = book.getSheet(sheetName);
			noOfColumns = sheet.getRow(0).getLastCellNum();
			//logger.logInfo("counting ended")
		}
		catch(Exception ex){
			logger.logInfo(ex)
		}
		return noOfColumns;
	}

	@Keyword
	public List<String> ExcelHelperReadXlsx(int colCount, int start, int end, String path, String sheetName) throws IOException, InvalidFormatException {


		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		List<String> excelValues = new ArrayList<String>();
		int MY_MINIMUM_COLUMN_COUNT = colCount;

		try
		{
			File file = new File(path);
			Workbook workbook = WorkbookFactory.create(file);

			//Get first/desired sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0); //int sheet 0 1 2 ...

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
	public void ExcelHelperGreateExcelFileWithColumnsNameXlsx(String path, String sheetName, List<String>columns){

		Cell cell

		//Workbook workbook = new XSSFWorkbook();
		Workbook workbook = new XSSFWorkbook();
		/* CreationHelper helps us create instances of various things like DataFormat,
		 Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
		CreationHelper createHelper = workbook.getCreationHelper()

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
			cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		double value = 25.698
		CellStyle twoDigitFormat = workbook.createCellStyle();
		twoDigitFormat = workbook.createCellStyle();
		twoDigitFormat.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));
		Row cellRow = sheet.createRow(1);
		cell = cellRow.createCell(1);//
		cell.setCellValue(value);
		cell.setCellStyle(twoDigitFormat);

		CellStyle threeDigitFormat = workbook.createCellStyle();
		threeDigitFormat = workbook.createCellStyle();
		threeDigitFormat.setDataFormat(createHelper.createDataFormat().getFormat("0.000"));

		CellStyle commaNumberFormat = workbook.createCellStyle();
		commaNumberFormat = workbook.createCellStyle();
		commaNumberFormat.setDataFormat(createHelper.createDataFormat().getFormat("#,##0"));

		CellStyle twoDigitCommaFormat = workbook.createCellStyle();
		twoDigitCommaFormat = workbook.createCellStyle();
		twoDigitCommaFormat.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));

		// Create Cell Style for formatting Date for xlsx
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(formatter.format(date));
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		cell = cellRow.createCell(0);//
		cell.setCellValue(formatter.format(date));
		cell.setCellStyle(dateCellStyle);

		//FileOutputStream fileOut = new FileOutputStream(path);

		workbook.write(outputFile);
		workbook.close();
	}
}
