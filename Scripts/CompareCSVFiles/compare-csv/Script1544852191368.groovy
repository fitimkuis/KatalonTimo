import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



String originalPath = System.getProperty("user.dir")+"\\ExcelFiles\\original.csv";
String comparePath = System.getProperty("user.dir")+"\\ExcelFiles\\compare.csv";
String differencesPath = System.getProperty("user.dir")+"\\ExcelFiles\\differences.csv";

/*
BufferedReader br = new BufferedReader(new FileReader(originalPath));
BufferedReader br2 = new BufferedReader(new FileReader(comparePath));
while ((line = br.readLine()) != null) {
	// use comma as separator
	String[] cols = line.split(",");
	System.out.println("Coulmn 1= " + cols[0] + " , Column 2= " + cols[1]+ " , Column 3= " + cols[2] );
}

while ((line = br2.readLine()) != null) {
	// use comma as separator
	String[] cols = line.split(",",-1);
	System.out.println("Coulmn 1= " + cols[0] + " , Column 2= " + cols[1]+ " , Column 3= " + cols[2] );
}*/


//createCSVFile();
int[] rowsCols = getRowsColsNo(originalPath);
int[] rowsCols2 = getRowsColsNo(comparePath);
System.out.println(rowsCols[0]); //count of rows
System.out.println(rowsCols[1]); //count of columns

File file = new File(differencesPath);
if(file.delete())
{
	System.out.println("File deleted successfully");
}
else
{
	System.out.println("Failed to delete the file");
}
createCSVFile(differencesPath);//create new file

String mess = "original.csv does not match with compare.csv ";
//start from row 0 (header)
writeToDiffFile(differencesPath, null, mess, 0, 0);//write header
String[] strDiff = new String[rowsCols[0]];
for (int i = 0; i < rowsCols[0]; i++) {
	String line = Files.readAllLines(Paths.get(originalPath)).get(i);
	String line2 = Files.readAllLines(Paths.get(comparePath)).get(i);
	String diff = getDiff(line, line2);
	//println diff
	strDiff[0] = diff;
	strDiff[1] = "in row "+i;
	writeToDiffFile(differencesPath, strDiff, null, 1, i);
	
}


public static int[] getRowsColsNo(String fileName) {
	Scanner scanIn = null;
	int rows = 0;
	int cols = 0;
	String InputLine = "";
	try {
		scanIn = new Scanner(new BufferedReader(
				new FileReader(fileName)));
		scanIn.useDelimiter(",");
		while (scanIn.hasNextLine()) {
			InputLine = scanIn.nextLine();
			String[] InArray = InputLine.split(",");
			rows++;
			cols = InArray.length;
		}

	} catch (Exception e) {
		System.out.println(e);
	}
	//scanIn.close();
	return [rows, cols] ;
}

public static void createCSVFile(String fileName) throws IOException {
	//String[] HEADERS = { "author", "title","year"};
	CSVPrinter printer = null;
	FileWriter out = new FileWriter(fileName);
	try {
		printer = new CSVPrinter(out, CSVFormat.DEFAULT);
		//        .withHeader(HEADERS));
		printer.printRecord();
	} catch (IOException e) {
		e.printStackTrace();
	}
	printer.flush();
	out.close();
}


public static void writeToDiffFile(String fileName, String[] values, String val, int counter, int rowNum) throws IOException {
	CSVPrinter csvPrinter;
	//FileWriter out = new FileWriter(fileName);
	Writer writer = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.APPEND);
	//BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.APPEND);
	if(counter == 1) {
		csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
		 //       .withHeader("author", "title", "year"));
		csvPrinter.printRecord(Arrays.asList(values));
	}
	else{
		csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
		csvPrinter.printRecord(val);
	}
	csvPrinter.flush();
	writer.close();
}

public static String getDiff(String str1, String str2){
	String[] strList1 = str1.split(",");
	String[] strList2 = str2.split(",");

	List<String> list1 = Arrays.asList(strList1);
	List<String> list2 = Arrays.asList(strList2);

	// Prepare a union
	List<String> union = new ArrayList<>(list1);
	union.addAll(list2);

	// Prepare an intersection
	List<String> intersection = new ArrayList<>(list1);
	intersection.retainAll(list2);

	// Subtract the intersection from the union
	union.removeAll(intersection);
	List<String> arr = new ArrayList<>();
	for (String s : union) {
		arr.add(s);
	}
	return String.join(",", arr);
}
