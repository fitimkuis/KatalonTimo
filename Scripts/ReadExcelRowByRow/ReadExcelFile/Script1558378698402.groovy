import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FilenameUtils

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

def verifyNavMenu(List list, List<String> menuname){
	println list
	println("total menus "+menuname.size())
	for(String s : menuname){
	   println("menuiten name: "+s)
	}
}

def TestObject makeTOwithXPath(String xpath) {
	TestObject to = new TestObject()
	to.addProperty("xpath", ConditionType.EQUALS, xpath)
	return to
}

SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
Timestamp timestamp = new Timestamp(System.currentTimeMillis());
System.out.println(sdf.format(timestamp));
String stamp = sdf.format(timestamp);


List<String> careerNavbarList = new ArrayList<>()
careerNavbarList.add("test1")
careerNavbarList.add("test2")
careerNavbarList.add("test3")
def xpath = "//header[@id='top']/div/a"
TestObject to2 = new TestObject()
to2 = makeTOwithXPath(xpath)
println to2
def list= []
list.add(to2)
//CustomKeywords.‘ciautils.ciaUtilities.verifyNavMenu’(menuListObj, careerNavbarList)
verifyNavMenu(list, careerNavbarList)

def valToExcel = "car"

int addtoRow = 11
int addToColumn = 0

String xlsPath = System.getProperty("user.dir")+"\\ExcelFiles\\"+stamp+"_FileXlsData.xls";
String xlsxPath = System.getProperty("user.dir")+"\\ExcelFiles\\"+stamp+"_FileXlsxData.xlsx";

List<String> headerValues = new ArrayList<String>();
List<String> excelValues = new ArrayList<String>();

List<String> headerValuesXlsx = new ArrayList<String>();
List<String> excelValuesXlsx = new ArrayList<String>();

String sheetName = "SheetName"
String xlsxSheetName = "TestSheet"
String truefalse = "TestSheet2"

String path = System.getProperty("user.dir")+"\\ExcelFiles\\xlsData.xls";

String pathXlsx = System.getProperty("user.dir")+"\\ExcelFiles\\xlsxData.xlsx";

int rowNum = 3
int colNum = 2
//arguments: row number, column number (start from 0), file path, sheetname 
def columnData = CustomKeywords.'excelHelper.ExcelUtilForXlsx.getRowColumn'(rowNum, colNum, pathXlsx, xlsxSheetName)
println ("***DEBUG row "+rowNum+" and column "+colNum+" data: "+columnData)

//CustomKeywords.'excelHelper.ExcelUtilForXlsx.getExactColumnData'(pathXlsx)
 
List<String> sheetNames = new ArrayList<>()
sheetNames = CustomKeywords.'excelHelper.ExcelUtilForXlsx.getSheetNames'(pathXlsx)

for(String name: sheetNames){
	
	println("SheetNames in excel file: "+name)
}

int processRows = 2;  //how many rows to get processed
int start = 0;
int end = 1;

//values to new excel file
List <String> excelTo = new ArrayList<>()
excelTo.add("cat")
excelTo.add("dog")
excelTo.add("pig")

List <String> columns = new ArrayList<>()
columns.add("One")
columns.add("Two")
columns.add("Three")
columns.add("Four")
columns.add("Five")

//verify excel format
String ext = FilenameUtils.getExtension(pathXlsx);


/*
if (ext.equals("xls")){
	println "processing .xls file"
	String fileName = "xlsData.xls"
	//Get the Latest excel file from folder
	String filePath = System.getProperty("user.dir")+"\\ExcelFiles\\"
	//def latestExcelFile = CustomKeywords.'excelHelper.GetLatestExcelFile.getTheNewestFile'(filePath)
	//println latestExcelFile
	
	//create xls
	CustomKeywords.'excelHelper.ExcelUtil.ExcelHelperGreateExcelFileWithColumnsName'(xlsPath, xlsxSheetName, columns)//create new excel
	//update excel from list
	CustomKeywords.'excelHelper.ExcelUtil.ExcelHelperUpdateFromList'(excelTo, addtoRow, addToColumn, path, sheetName)//add or update value to cell
	//update exact value
	CustomKeywords.'excelHelper.ExcelUtil.ExcelHelperUpdateExactValue'(valToExcel, addtoRow, addToColumn, path, sheetName)//add or update value to cell
	//get count of columns
	int countOfColums = CustomKeywords.'excelHelper.ExcelUtil.ExcelHelperGetColumnCount'(path, sheetName)//get count of columns
	//get header values
	headerValues = CustomKeywords.'excelHelper.ExcelUtil.ExcelHelperRead'(countOfColums, start, end, path, sheetName)//get header values
	
	start = 1;
	end = 2;
	
	for (int x = 0; x < processRows; x++) {
		excelValues = CustomKeywords.'excelHelper.ExcelUtil.ExcelHelperRead'(headerValues.size(), start, end, path, sheetName)
		int i = 0;
	
		for (String s : headerValues) {
			if (excelValues.get(i).equals("**No Value**")) {
				System.out.println("Row " + start + " Header " + headerValues.get(i) + " has not value ");
			} else {
				System.out.println("Row " + start + " Header " + headerValues.get(i) + " has value " + excelValues.get(i));
			}
			i++;
		}
		start++; //increase start & end to get data rows
		end++;
	}
}
else{
	println "processing .xlsx file"
	String fileName = "xlsxData.xlsx"
	//latestExcelFile = CustomKeywords.'excelHelper.GetLatestExcelFile.getTheNewestFile'(filePath)
	//create xlsx
	CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperGreateExcelFileWithColumnsNameXlsx'(xlsxPath, xlsxSheetName, columns)//create new excel
	//add data from the list
	CustomKeywords.'excelHelper.UpdateXlsxFile.updateXlsxFromList'(excelTo, addtoRow, addToColumn, pathXlsx, xlsxSheetName, fileName)
	//update exact value
	CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperUpdateExactValueXlsx'(valToExcel, addtoRow, addToColumn, pathXlsx, xlsxSheetName, fileName)//add or update value to cell
	
	//int cols = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperGetColumnCountXlsx'(xlsxPath, xlsxSheetName)
	//println cols

	int countOfColumsXlsx = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperGetColumnCountXlsx'(pathXlsx, sheetName)//get count of columns
	headerValuesXlsx = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperReadXlsx'(countOfColumsXlsx, start, end, pathXlsx, sheetName)//get header values
	
	start = 1;
	end = 2;
	
	for (int z = 0; z < processRows; z++) {
		excelValuesXlsx = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperReadXlsx'(headerValuesXlsx.size(), start, end, pathXlsx, sheetName)
		int y = 0;
	
		for (String s : headerValuesXlsx) {
			if (excelValuesXlsx.get(y).equals("**No Value**")) {
				System.out.println("Row " + start + " Header " + headerValuesXlsx.get(y) + " has not value ");
			} else {
				System.out.println("Row " + start + " Header " + headerValuesXlsx.get(y) + " has value " + excelValuesXlsx.get(y));
			}
			y++;
		}
		start++; //increase start & end to get data rows
		end++;
	}
}
*/

//true or false value
start = 0;
end = 1;
//int counter = CustomKeywords.'excelHelper.ExcelUtilForXlsx.getColumnCount'(pathXlsx, truefalse)
///int counter = CustomKeywords.'excelHelper.ExcelUtilForXlsx.ExcelHelperGetColumnCountXlsx'(pathXlsx, truefalse)
//println ("DEBUG******************* "+counter)

//int countOfColumsXlsx = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperGetColumnCountXlsx'(pathXlsx, truefalse)//get count of columns
///headerValuesXlsx = CustomKeywords.'excelHelper.ExcelUtilForXlsx.ReadFile'(counter, start, end, pathXlsx, truefalse)
//headerValuesXlsx = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperReadXlsx'(counter, start, end, pathXlsx, truefalse)//get header values
//headerValuesXlsx = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperReadXlsx'(counter, start, end, pathXlsx, truefalse)//get header values
//println ("DEBUG******************* "+headerValuesXlsx)

//processRows = 1;  //how many rows to get processed
//start = 1;
//end = 2;
int counter = 0
int rowCount = 0
for(String sheet: sheetNames){
	//header count
	start = 0;
	end = 1;
	counter = CustomKeywords.'excelHelper.ExcelUtilForXlsx.ExcelHelperGetColumnCountXlsx'(pathXlsx, sheet)
	println ("DEBUG*******************Columns "+counter)
	headerValuesXlsx = CustomKeywords.'excelHelper.ExcelUtilForXlsx.ReadFile'(counter, start, end, pathXlsx, sheet)
	println ("DEBUG*******************Header Values "+headerValuesXlsx)
	rowCount = CustomKeywords.'excelHelper.ExcelUtilForXlsx.getRowCount'(pathXlsx, sheet)
	rowCount--
	println ("DEBUG*******************Rows "+rowCount)
	//row count
	start = 1;
	end = 2;
	for (int z = 0; z < rowCount; z++) {
		excelValuesXlsx = CustomKeywords.'excelHelper.ExcelUtilForXlsx.ReadFile'(headerValuesXlsx.size(), start, end, pathXlsx, sheet)
		//excelValuesXlsx = CustomKeywords.'excelHelper.UpdateXlsxFile.ExcelHelperReadXlsx'(headerValuesXlsx.size(), start, end, pathXlsx, truefalse)
		int y = 0;
	
		for (String s : headerValuesXlsx) {
			if (excelValuesXlsx.get(y).equals("**No Value**")) {
				System.out.println("Row " + start + " Header " + headerValuesXlsx.get(y) + " has not value ");
			} else {
				System.out.println("Row " + start + " Header " + headerValuesXlsx.get(y) + " has value " + excelValuesXlsx.get(y));
			}
			y++;
		}
		start++; //increase start & end to get data rows
		end++;
	}
}




