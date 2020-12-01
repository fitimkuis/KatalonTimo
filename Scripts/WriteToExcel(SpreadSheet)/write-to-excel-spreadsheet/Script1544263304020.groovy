List <String> excel = new ArrayList <String>();
excel.add("First");
excel.add("Second");
excel.add("Third");
excel.add("Fourth");
excel.add("Fifth");
excel.add("number");
//CustomKeywords.'spreadsheet.WriteToFile.writeToExcel'(excel, 1, 1)

List <String> excel2 = new ArrayList <String>();
Random r = new Random();
//int low = 1947400365
int low = 1000;
int high = 9999;
int val = 0
for (int i = 0;i < 10; i++){
	val = r.nextInt(high-low) + low;
	excel2.add(String.valueOf(val))
	
}

long x = 100000000L;
long y = 999999999L;

//CustomKeywords.'spreadsheet.WriteToFile.updateExcelFile2'(excel2, 2)
HashMap<String, String> hmap = new HashMap<String, String>();

for (int l = 0; l < 15; l++){
	val = r.nextInt(high-low) + low;
	long number = x+((long)(r.nextDouble()*(y-x)));
	hmap.put(String.valueOf(number),String.valueOf(val))
}
CustomKeywords.'spreadsheet.WriteToFile.updateNumberValueXlsx'(hmap, 0, 7)

//CustomKeywords.'spreadsheet.WriteToFile.updateExcelFile'(excel2, 2) //values will change, column number where all update will do

//CustomKeywords.'spreadsheet.WriteToFile.SpreadSheetWrite'()