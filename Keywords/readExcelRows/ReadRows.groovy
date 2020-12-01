package readExcelRows

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.kms.katalon.core.annotation.Keyword

public class ReadRows {

	public String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\fitim\\Desktop\\data\\readExcelSheet.xls";
	Workbook workbook

	@Keyword
	public int getCountOfRows(String path, int sheetNum){

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		workbook = WorkbookFactory.create(new File(path));
		//Sheet sheet = workbook.getSheet("sheet0");
		Sheet sheet = workbook.getSheetAt(sheetNum);
		int lastRowNum = sheet.getLastRowNum()

		int used = sheet.getRow(0).getPhysicalNumberOfCells();
		println "rows used "+used

		int last = sheet.getRow(0).getLastCellNum();
		println "rows last "+last

		return lastRowNum;
	}

	@Keyword
	public List<String> readExcelRows(int start, int end, String path, int sheetNum) throws IOException, InvalidFormatException{

		SAMPLE_XLSX_FILE_PATH = path;

		List<String> excelValues = new ArrayList<String>();

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		workbook = WorkbookFactory.create(new File(path));

		// Retrieving the number of sheets in the Workbook
		//System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		/*
		 =============================================================
		 Iterating over all the sheets in the workbook (Multiple ways)
		 =============================================================
		 */

		// 1. You can obtain a sheetIterator and iterate over it
		/*Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		 System.out.println("Retrieving Sheets using Iterator");
		 while (sheetIterator.hasNext()) {
		 Sheet sheet = sheetIterator.next();
		 System.out.println("=> " + sheet.getSheetName());
		 }*/

		/*
		 ==================================================================
		 Iterating over all the rows and columns in a Sheet (Multiple ways)
		 ==================================================================
		 */

		// Getting the Sheet at index zero
		//Sheet sheet = workbook.getSheet("sheet0");
		Sheet sheet = workbook.getSheetAt(sheetNum);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// 1. You can obtain a rowIterator and columnIterator and iterate over them
		//System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
		//Iterator<Row> rowIterator = sheet.rowIterator();
		/*while (rowIterator.hasNext()) {
		 Row row = rowIterator.next();
		 // Now let's iterate over the columns of the current row
		 Iterator<Cell> cellIterator = row.cellIterator();
		 while (cellIterator.hasNext()) {
		 Cell cell = cellIterator.next();
		 String cellValue = dataFormatter.formatCellValue(cell);
		 System.out.print(cellValue + "\t");
		 }
		 System.out.println();
		 }*/

		// 2. Or you can use a for-each loop to iterate over the rows and columns
		//System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
		boolean last = false

		boolean actualValue;
		int lastRowNum = sheet.getLastRowNum()
		lastRowNum++
		if(start == lastRowNum){
			last = true
		}
		//System.out.println("sheet rows "+lastRowNum);
		int startRow = 0
		int endRow = 0;
		for (Row row: sheet) {
			for(Cell cell: row) {
				if (startRow >= start && endRow < end && last==false){

					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						excelValues.add("**no value**");
					}else{
						String cellValue = dataFormatter.formatCellValue(cell);
						//System.out.print(cellValue + ",");
						excelValues.add(cellValue);
						//println("********************* Is there added anything ****************")
					}

					/*String cellValue = dataFormatter.formatCellValue(cell);
					 System.out.print(cellValue + ",");
					 excelValues.add(cellValue);*/
				}
				if (last){
					String cellValue = dataFormatter.formatCellValue(cell);
					//System.out.print("last "+cellValue + ",");
					excelValues.add(cellValue);
					//println("********************* Is there added anything ****************")
				}
				//}
			}
			startRow++;
			endRow++;
			//System.out.println();
		}

		// 3. Or you can use Java 8 forEach loop with lambda
		/*System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
		 sheet.forEach(row -> {
		 row.forEach(cell -> {
		 String cellValue = dataFormatter.formatCellValue(cell);
		 System.out.print(cellValue + "\t");
		 });
		 System.out.println();
		 });*/

		// Closing the workbook
		//workbook.close();

		return excelValues;
	}
}
