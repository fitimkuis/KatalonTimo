package spreadsheet

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.kms.katalon.core.annotation.Keyword

public class WriteToFile {

	private static MissingCellPolicy xRow;
	private static XSSFSheet ExcelWSheetX;
	private static XSSFWorkbook ExcelWBookX;
	private static XSSFCell eCellNumbersX;
	private static XSSFCell eCellValuesX;
	private static XSSFRow eRowX;

	private static HSSFSheet ExcelWSheetH;
	private static HSSFWorkbook ExcelWBookH;
	private static HSSFCell eCellNumbers;
	private static HSSFCell eCellValues;
	private static HSSFRow eRowH;
	private static HSSFCell eCellH;

	private String excelpath = System.getProperty("user.dir")+"\\ExcelFiles\\xlsData.xls";
	private String excelpathXlsx = System.getProperty("user.dir")+"\\ExcelFiles\\file_example_XLSX_10.xlsx";
	private String jsonPath = System.getProperty("user.dir")+"\\ExcelFiles\\json_data.xls";

	@Keyword
	public void SpreadSheetWrite(){

		try {
			//create .xls and create a worksheet.
			FileOutputStream fos = new FileOutputStream(excelpath, true);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("SheetName");

			//Create ROW-1
			HSSFRow row1 = worksheet.createRow((short) 0);

			//Create COL-A from ROW-1 and set data
			HSSFCell cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue("First");
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellA1.setCellStyle(cellStyle);

			//Create COL-B from row-1 and set data
			HSSFCell cellB1 = row1.createCell((short) 1);
			cellB1.setCellValue("Second");
			cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB1.setCellStyle(cellStyle);

			//Create COL-C from row-1 and set data
			HSSFCell cellC1 = row1.createCell((short) 2);
			cellC1.setCellValue(true);

			//Create COL-D from row-1 and set data
			HSSFCell cellD1 = row1.createCell((short) 3);
			cellD1.setCellValue(new Date());
			cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("m/d/yy h:mm"));
			cellD1.setCellStyle(cellStyle);

			//Create COL-E from row-1 and set data
			HSSFCell cellE1 = row1.createCell((short) 4);
			cellE1.setCellValue("some data here with red background");
			cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.RED.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellE1.setCellStyle(cellStyle);

			//Save the workbook in .xls file
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Keyword
	public void updateExcelFile(List<String> excel, colIndex){
		FileInputStream fsIP= new FileInputStream(new File(excelpath)); //Read the spreadsheet that needs to be updated

		HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
		HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.

		Cell cell = null; // declare a Cell object

		int ro = 1 //rows will start
		for (String s : excel){
			cell = worksheet.getRow(ro).getCell(colIndex);//if cell is null
			worksheet.createRow(ro).createCell(colIndex).setCellValue(s); //if cell is null then update it to new value
			ro++
		}

		fsIP.close(); //Close the InputStream
		FileOutputStream output_file =new FileOutputStream(new File(excelpath));  //Open FileOutputStream to write updates
		wb.write(output_file); //write changes
	}

	@Keyword
	public void updateExcelFileStartFrom(List<String> excel, int row, colIndex, path){
		FileInputStream fsIP= new FileInputStream(new File(path)); //Read the spreadsheet that needs to be updated

		HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
		HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.

		Cell cell = null; // declare a Cell object

		//int ro = 1 //rows will start
		int ro = row //rows will start
		for (String s : excel){
			cell = worksheet.getRow(ro).getCell(colIndex);//if cell is null
			worksheet.createRow(ro).createCell(colIndex).setCellValue(s); //if cell is null then update it to new value
			ro++
		}

		fsIP.close(); //Close the InputStream
		FileOutputStream output_file =new FileOutputStream(new File(path));  //Open FileOutputStream to write updates
		wb.write(output_file); //write changes
	}

	@Keyword
	public void updateNumberValueXlsx(HashMap<String, String> hmap, colIndexNumbers, colIndexValues)throws FileNotFoundException, IOException, InvalidFormatException{
		FileInputStream fsIP= new FileInputStream(new File(excelpathXlsx)); //Read the spreadsheet that needs to be updated

		ExcelWBookX = new XSSFWorkbook (fsIP); //Access the workbook
		ExcelWSheetX = ExcelWBookX.getSheetAt(0); //Access the worksheet, so that we can update / modify it.

		int ro = 1
		Set set = hmap.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			Map.Entry mentry = (Map.Entry)iterator.next();
			eRowX = ExcelWSheetX.getRow(ro);

			eCellNumbersX = eRowX.getCell(colIndexNumbers, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);//check if cell is null
			eCellValuesX = eRowX.getCell(colIndexValues, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);//check if cell is null
			if (eCellNumbersX == null || eCellValuesX == null) {
				eCellNumbersX = eRowX.createCell(colIndexNumbers);
				eCellValuesX = eRowX.createCell(colIndexValues);
				eCellNumbersX.setCellValue(mentry.getKey());
				eCellValuesX.setCellValue(mentry.getValue());
			} else {
				eCellNumbersX.setCellValue(mentry.getKey());
				eCellValuesX.setCellValue(mentry.getValue());
			}
			ro++
		}

		fsIP.close(); //Close the InputStream
		FileOutputStream output_file =new FileOutputStream(new File(excelpathXlsx));  //Open FileOutputStream to write updates
		ExcelWBookX.write(output_file); //write changes
	}

	@Keyword
	public void updateNumberValue(HashMap<String, String> hmap, colIndexNumbers, colIndexValues){
		FileInputStream fsIP= new FileInputStream(new File(excelpath)); //Read the spreadsheet that needs to be updated

		ExcelWBookH = new HSSFWorkbook (fsIP); //Access the workbook
		ExcelWSheetH = ExcelWBookH.getSheetAt(0); //Access the worksheet, so that we can update / modify it.

		int ro = 1
		Set set = hmap.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			Map.Entry mentry = (Map.Entry)iterator.next();
			eRowH = ExcelWSheetH.getRow(ro);

			eCellNumbers = eRowH.getCell(colIndexNumbers, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);//check if cell is null
			eCellValues = eRowH.getCell(colIndexValues, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);//check if cell is null
			if (eCellNumbers == null || eCellValues == null) {
				eCellNumbers = eRowH.createCell(colIndexNumbers);
				eCellValues = eRowH.createCell(colIndexValues);
				eCellNumbers.setCellValue(mentry.getKey());
				eCellValues.setCellValue(mentry.getValue());
			} else {
				eCellNumbers.setCellValue(mentry.getKey());
				eCellValues.setCellValue(mentry.getValue());
			}
			ro++
		}

		fsIP.close(); //Close the InputStream
		FileOutputStream output_file =new FileOutputStream(new File(excelpath));  //Open FileOutputStream to write updates
		ExcelWBookH.write(output_file); //write changes
	}

	@Keyword
	public void updateExcelFile2(List<String> excel, colIndex){
		FileInputStream fsIP= new FileInputStream(new File(excelpath)); //Read the spreadsheet that needs to be updated

		ExcelWBookH = new HSSFWorkbook (fsIP); //Access the workbook
		ExcelWSheetH = ExcelWBookH.getSheetAt(0); //Access the worksheet, so that we can update / modify it.

		int ro = 1
		for (String s : excel){

			eRowH = ExcelWSheetH.getRow(ro);

			eCellH = eRowH.getCell(colIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);//check if cell is null
			if (eCellH == null) {
				eCellH = eRowH.createCell(colIndex);
				eCellH.setCellValue(s);
			} else {
				eCellH.setCellValue(s);
			}
			ro++
		}

		fsIP.close(); //Close the InputStream
		FileOutputStream output_file =new FileOutputStream(new File(excelpath));  //Open FileOutputStream to write updates
		ExcelWBookH.write(output_file); //write changes
	}


	@Keyword
	public void writeToExcel(List <String> excel, int rows, int start){

		try {
			//create .xls and create a worksheet.
			FileOutputStream fos = new FileOutputStream(excelpath, true);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("SheetName");
			HSSFRow row;
			HSSFCell cell;
			HSSFCellStyle cellStyle;
			for (int i = start;i < rows; i++){
				row = worksheet.createRow((short) i);
				for(int y = 0; y < excel.size(); y++){
					cell = row.createCell((short) y);
					cell.setCellValue(excel[y]);
					cellStyle = workbook.createCellStyle();
					cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cell.setCellStyle(cellStyle);
				}
			}

			/*
			 //Create ROW-1
			 HSSFRow row1 = worksheet.createRow((short) 0);
			 //Create COL-A from ROW-1 and set data
			 HSSFCell cellA1 = row1.createCell((short) 0);
			 cellA1.setCellValue("First");
			 HSSFCellStyle cellStyle = workbook.createCellStyle();
			 cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 cellA1.setCellStyle(cellStyle);
			 //Create COL-B from row-1 and set data
			 HSSFCell cellB1 = row1.createCell((short) 1);
			 cellB1.setCellValue("Second");
			 cellStyle = workbook.createCellStyle();
			 cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 cellB1.setCellStyle(cellStyle);
			 //Create COL-C from row-1 and set data
			 HSSFCell cellC1 = row1.createCell((short) 2);
			 cellC1.setCellValue(true);
			 //Create COL-D from row-1 and set data
			 HSSFCell cellD1 = row1.createCell((short) 3);
			 cellD1.setCellValue(new Date());
			 cellStyle = workbook.createCellStyle();
			 cellStyle.setDataFormat(HSSFDataFormat
			 .getBuiltinFormat("m/d/yy h:mm"));
			 cellD1.setCellStyle(cellStyle);
			 //Create COL-E from row-1 and set data
			 HSSFCell cellE1 = row1.createCell((short) 4);
			 cellE1.setCellValue("some data here with red background");
			 cellStyle = workbook.createCellStyle();
			 cellStyle.setFillForegroundColor(HSSFColor.RED.index);
			 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 cellE1.setCellStyle(cellStyle);
			 */

			//Save the workbook in .xls file
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	@Keyword
	public void writeToExcelJsonData(List <String> excel, int rows, int count){

		try {
			//create .xls and create a worksheet.
			//File file = new File("C:\\Users\\fitim\\Desktop\\data\\json_data.xls");
			//FileOutputStream fos = null;
			FileOutputStream fos = new FileOutputStream(jsonPath, true);

			// if file doesn't exists, then create it
			/*if (!file.exists()) {
			 file.createNewFile();
			 }*/

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("JsonData");
			HSSFRow row;
			HSSFCell cell;
			HSSFCellStyle cellStyle;

			//for(int x = 0; x < count; x++){
			for (int i = 0;i < rows; i++){
				row = worksheet.createRow((short) i);
				for(int y = 0; y < excel.size(); y++){
					cell = row.createCell((short) y);
					cell.setCellValue(excel[y]);
					cellStyle = workbook.createCellStyle();
					cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cell.setCellStyle(cellStyle);
				}
			}
			//}

			/*
			 //Create ROW-1
			 HSSFRow row1 = worksheet.createRow((short) 0);
			 //Create COL-A from ROW-1 and set data
			 HSSFCell cellA1 = row1.createCell((short) 0);
			 cellA1.setCellValue("First");
			 HSSFCellStyle cellStyle = workbook.createCellStyle();
			 cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 cellA1.setCellStyle(cellStyle);
			 //Create COL-B from row-1 and set data
			 HSSFCell cellB1 = row1.createCell((short) 1);
			 cellB1.setCellValue("Second");
			 cellStyle = workbook.createCellStyle();
			 cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 cellB1.setCellStyle(cellStyle);
			 //Create COL-C from row-1 and set data
			 HSSFCell cellC1 = row1.createCell((short) 2);
			 cellC1.setCellValue(true);
			 //Create COL-D from row-1 and set data
			 HSSFCell cellD1 = row1.createCell((short) 3);
			 cellD1.setCellValue(new Date());
			 cellStyle = workbook.createCellStyle();
			 cellStyle.setDataFormat(HSSFDataFormat
			 .getBuiltinFormat("m/d/yy h:mm"));
			 cellD1.setCellStyle(cellStyle);
			 //Create COL-E from row-1 and set data
			 HSSFCell cellE1 = row1.createCell((short) 4);
			 cellE1.setCellValue("some data here with red background");
			 cellStyle = workbook.createCellStyle();
			 cellStyle.setFillForegroundColor(HSSFColor.RED.index);
			 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 cellE1.setCellStyle(cellStyle);
			 */

			//Save the workbook in .xls file
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

