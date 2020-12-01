import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory

num = findTestData("Data Files/ExcelDataBind/TestExcel").getValue("Number", 1)
gender = findTestData("Data Files/ExcelDataBind/TestExcel").getValue("Gender", 1)
country = findTestData("Data Files/ExcelDataBind/TestExcel").getValue("Country", 1)
println num
println gender
println country

def data = TestDataFactory.findTestData("Data Files/ExcelDataBind/TestExcel")
println data.rowNumbers
println data.columnNumbers

def columns = data.columnNames
def colNum = data.columnNumbers

println data
int row = 1
int col = 0

//create list of lists
List<List<String>> listOLists = new ArrayList<List<String>>();
for (int i = 0; i < data.rowNumbers; i++) {
	List<String> list = new ArrayList<>();
	listOLists.add(list);
}
// Now you can use lists.get(0) etc to get at each list

//create list of maps
List<Map<String, String>> listOMaps = new ArrayList<Map<String, String>>();
for (int g = 0; g < data.rowNumbers; g++) {
	Map<String, String> map = new HashMap();
	listOMaps.add(map);
}

//loop rows
for (int i = 0; i < data.rowNumbers; i++){
	
	//loop columns
	for (int y = 0; y < data.columnNumbers; y++ ){
		//get column value
		val = findTestData("Data Files/ExcelDataBind/TestExcel").getValue(columns[col], row)
		//add value to the list of list
		listOLists.get(i).add(val)
		
		//add key & value to list of maps
		listOMaps.get(i).put(columns[col], val)
		//myList.get(i).add(hmap.put(columns[i],val))
		col++
			if (col >= colNum){ //if end of column count
				col = 0
			}
		}
	row++
}

//loop data from list of lists
for (int c = 0; c < data.rowNumbers; c++){
	println listOLists.get(c)
}

//loop data from list of maps
for (int k = 0; k < data.rowNumbers; k++){
	println listOMaps.get(k)
}

int r = 1
//loop key & value from the map
for (int h = 0; h < data.rowNumbers; h++){
	
	for (Map.Entry<String, String> entry : listOMaps.get(h).entrySet()) {
	    System.out.println("row "+r+" "+entry.getKey() + ":" + entry.getValue());
	}
	r++
}
