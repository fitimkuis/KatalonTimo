package excelHelper

import java.text.SimpleDateFormat;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

public class ExcelUtilForXlsx {

	FileInputStream fsIP
	XSSFWorkbook wb
	XSSFSheet worksheet
	FileOutputStream outputFile
	KeywordUtil logger

	public ExcelUtilForXlsx(){
		logger = new KeywordUtil()
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

	@Keyword
	public List<String> getSheetNames(String path){

		Workbook book = WorkbookFactory.create(new File(path));
		List<String> sheetNames = new ArrayList<String>();

		for (int i=0; i<book.getNumberOfSheets(); i++) {
			sheetNames.add( book.getSheetName(i) );
		}
		//book.close()
		return sheetNames
	}

	@Keyword
	public int getRowCount(String path, String sheetName) throws IOException, InvalidFormatException {

		int noOfColumns = 0
		int totalRows = 0
		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{

			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);

			// Access the required test data sheet
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
			XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);

			totalRows = ExcelWSheet.getPhysicalNumberOfRows();

		}
		catch(Exception ex){
			logger.logInfo(ex)
		}

		return totalRows;
	}

	@Keyword
	public int getColumnCount(String path, String sheetName) throws IOException, InvalidFormatException {

		int noOfColumns = 0
		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{

			Workbook book = WorkbookFactory.create(new File(path));

			//Get first/desired sheet from the workbook
			//Sheet sheet = book.getSheetAt(0); //int sheet 0 1 2 ...
			Sheet sheet = book.getSheet(sheetName);
			noOfColumns = sheet.getRow(0).getLastCellNum();
		}
		catch(Exception ex){
			logger.logInfo(ex)
		}

		return noOfColumns;
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

			Workbook book = WorkbookFactory.create(new File(path));

			//Get first/desired sheet from the workbook
			//Sheet sheet = book.getSheetAt(0); //int sheet 0 1 2 ...
			Sheet sheet = book.getSheet(sheetName);
			noOfColumns = sheet.getRow(0).getLastCellNum();
			//book.close()
		}
		catch(Exception ex){
			logger.logInfo(ex)
		}

		return noOfColumns;
	}

	@Keyword
	public String getExactColumnData(String path){

		File file = new File(path);
		Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
		Sheet sheet = workbook.getSheetAt(0);//TestSheet
		int column_index_1 = 0;
		int column_index_2 = 0;
		int column_index_3 = 0;
		int column_index_4 = 0;
		int column_index_5 = 0;
		Row row = sheet.getRow(0);
		for (Cell cell : row) {
			// Column header names.
			switch (cell.getStringCellValue()) {
				case "AccountNumber":
					column_index_1 = cell.getColumnIndex();
					break;
				case "Name":
					column_index_2 = cell.getColumnIndex();
					break;
				case "Amount":
					column_index_3 = cell.getColumnIndex();
				case "Profit":
					column_index_4 = cell.getColumnIndex();
					break;
				case "Account":
					column_index_5 = cell.getColumnIndex();
					break;
			}
		}

		for (Row r : sheet) {
			if (r.getRowNum()==0) continue;//hearders
			Cell c_1 = r.getCell(column_index_1);
			Cell c_2 = r.getCell(column_index_2);
			Cell c_3 = r.getCell(column_index_3);
			Cell c_4 = r.getCell(column_index_4);
			Cell c_5 = r.getCell(column_index_5);
			if (c_1 != null && c_1.getCellType() != Cell.CELL_TYPE_BLANK
			&&c_2 != null && c_2.getCellType() != Cell.CELL_TYPE_BLANK
			&&c_3 != null && c_3.getCellType() != Cell.CELL_TYPE_BLANK
			&&c_4 != null && c_4.getCellType() != Cell.CELL_TYPE_BLANK
			&&c_5 != null && c_5.getCellType() != Cell.CELL_TYPE_BLANK)
			{
				System.out.print("  "+c_1 + "   " + c_2+"   "+c_3+"  "+ c_4+"  "+c_5+"\n");
			}
		}

	}

	@Keyword
	public String getRowColumn(int rowNum, int columnNum, String path, String sheetName) throws IOException, InvalidFormatException {

		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{
			Workbook book = WorkbookFactory.create(new File(path));

			//Get first/desired sheet from the workbook
			Sheet sheet = book.getSheet(sheetName);
			//Sheet sheet = book.getSheetAt(1);

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			if (rowNum == 1){
				rowNum++ //add 1 cause row 1 is headers
			}
			Row r = sheet.getRow(rowNum);//get wanted row
			//column nums start from 0
			Cell c = r.getCell(columnNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (c == null) {
				return ("**No Value**");
				// The spreadsheet is empty in this cell
			}
			else {
				return(dataFormatter.formatCellValue(c));
			}
		}
		catch (Exception ex){
			logger.logInfo(ex)
		}
	}

	@Keyword
	public List<String> ReadFile(int colCount, int start, int end, String path, String sheetName) throws IOException, InvalidFormatException {


		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		List<String> excelValues = new ArrayList<String>();
		int MY_MINIMUM_COLUMN_COUNT = colCount;

		try
		{
			Workbook book = WorkbookFactory.create(new File(path));

			//Get first/desired sheet from the workbook
			Sheet sheet = book.getSheet(sheetName);
			//Sheet sheet = book.getSheetAt(1);

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
	public List<String> ExcelHelperReadXlsx(int colCount, int start, int end, String path, String sheetName) throws IOException, InvalidFormatException {


		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		List<String> excelValues = new ArrayList<String>();
		int MY_MINIMUM_COLUMN_COUNT = colCount;

		try
		{
			Workbook book = WorkbookFactory.create(new File(path));

			//Get first/desired sheet from the workbook
			//Sheet sheet = book.getSheet(sheetName);
			Sheet sheet = book.getSheetAt(1);

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
			//book.close()
		}
		catch (Exception ex){
			logger.logInfo(ex)
		}

		return excelValues;
	}

	@Keyword
	public List<String> ExcelHelperUpdateFromListXlsx(List<String> excel, int rw, int col, String path, String sheetName) throws IOException, InvalidFormatException {

		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{
			fsIP= new FileInputStream(new File(path)); //Read the spreadsheet that needs to be updated
			wb = new XSSFWorkbook(fsIP); //Access the workbook
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
	public List<String> ExcelHelperUpdateExactValueXlsx(String value, int rw, int col, String path, String sheetName) throws IOException, InvalidFormatException {

		// Check the file extension
		if (!path.endsWith(".xlsx")) {
			throw new IllegalArgumentException("Unknown file type. Please use .xlsx");
		}

		try
		{

			////XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
			///Workbook workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
			//FileInputStream inputStream = new FileInputStream(new File(path));
			Workbook workbook = WorkbookFactory.create(new File(path));

			////XSSFSheet sheet = workbook.getSheetAt(0);
			Sheet sheet = workbook.getSheetAt(0);
			//fsIP= new FileInputStream(new File(path)); //Read the spreadsheet that needs to be updated
			//book = new XSSFWorkbook(fsIP); //Access the workbook
			//worksheet = wb.getSheet(sheetName); //Access the worksheet, so that we can update / modify it.

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

			//inputStream.close();
			//workbook.close();

			FileOutputStream outputStream = new FileOutputStream(path);
			//outputFile = new FileOutputStream(new File(path));  //Open FileOutputStream to write updates
			workbook.write(outputStream); //write changes
			//workbook.close();
			outputStream.close();

		}
		catch(Exception ex){
			logger.logInfo(ex)
		}
	}
}
