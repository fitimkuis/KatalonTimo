import java.util.stream.Collectors
import java.util.stream.Stream

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.CSVWriter
import com.opencsv.enums.CSVReaderNullFieldIndicator
//import au.com.bytecode.opencsv.CSVReader

String filepath = "data1.csv"
String outputpath = "dataOut.csv"

//writeColumn()



//WriteCsvFile(filepath) //create new .csv file
//updateCSV(filepath) //update .csv file cell
//appendToCsvFile(filepath) //append to .csv file
//readLines(filepath) //read line by line start from row 1
String row = getSpecificRowFromCsv(filepath, 4) //red specific row
println row
String[] parts = row.split(";")
println parts[3].replace("]", "")


/*List<String[]> dataLines = new ArrayList<>();
def line1 = [ 'John', "Doe", "38", "Comment Data\nAnother line of comment data" ]
def line2 = ["Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\""]
dataLines.add(line1)
dataLines.add(line2)*/
//dataLines.add(new String[] { 'John', "Doe", "38", "Comment Data\nAnother line of comment data" });
//dataLines.add(new String[] { "Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\"" });

/*public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
	
	try {
		File csvOutputFile = new File(CSV_FILE_NAME);
		PrintWriter pw = new PrintWriter(csvOutputFile)
		dataLines.stream()
		  .map(this.convertToCSV)
		  .forEach(pw.println);
	}
	catch (Exception ex){
		
	}
	assertTrue(csvOutputFile.exists());
}*/

public String convertToCSV(String[] data) {
	return Stream.of(data)
	  .map(this.escapeSpecialCharacters)
	  .collect(Collectors.joining(","));
}

public String escapeSpecialCharacters(String data) {
	String escapedData = data.replaceAll("\\R", " ");
	if (data.contains(",") || data.contains("\"") || data.contains("'")) {
		data = data.replace("\"", "\"\"");
		escapedData = "\"" + data + "\"";
	}
	return escapedData;
}


public void WriteCsvFile(String filePath){
	  
	  File file = new File(filePath);
	  
	  if(file.exists()){
		  return
	  }
	  
	  else{  
		  CSVWriter writer = new CSVWriter(new FileWriter(file));
		  		    
	      //Create record
	      String [] record = "4,David,Miller,Australia,30".split(",");
	      //Write the record to file
	      writer.writeNext(record);
	        
	      //close the writer
	      writer.close();
	  }

        System.out.println("End.");
	}

public void appendToCsvFile(String filePath) throws Exception
{
   //String csv = "data1.csv";
   CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));
	 
   String [] record = "3,David,Feezor,USA,40".split(",");
	 
   writer.writeNext(record);
	 
   writer.close();
}

public void readLines(String filePath) throws Exception
{
   //Build reader instance	
	//Start reading from line number 2 (line numbers start from zero)
	CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
	.withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
	.withSkipLines(1).build()
   
   //Read CSV line by line and use the string array as you want
   String[] nextLine;
   while ((nextLine = csvReader.readNext()) != null) {
	  if (nextLine != null) {
		 //Verifying the read data here
		 System.out.println(Arrays.toString(nextLine));
	  }
	}
}

public static String getSpecificRowFromCsv(String filePath, int rowNum) {
	//List<String[]> fileData = null;
	List <String> fileData = new ArrayList<>()
	CSVReader reader = null;
	try {
		reader = new CSVReader(new FileReader(filePath));
		fileData = reader.readAll();
		reader.close();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return fileData.get(rowNum - 1).toString();
}


public static void updateCSV(String fileToUpdate) throws IOException {
	File inputFile = new File(fileToUpdate);

	// Read existing file
	//CSVReader reader = new CSVReader(new FileReader(inputFile), ',' , '"' )
	//FileReader fReader = new FileReader(inputFile)
	//CSVReader reader = new CSVReader(fReader)
	CSVReader reader = new CSVReader(new FileReader("data1.csv"), ',', '"', 1);
	//.withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
	//.build();
	List<String[]> csvBody = reader.readAll();
	// get CSV row column and replace with by using row and column
	for(int i=0; i<csvBody.size(); i++){
		String[] strArray = csvBody.get(i);
		for(int j=0; j<strArray.length; j++){
			if(strArray[j].equalsIgnoreCase("Update_date")){ //String to be replaced
				csvBody.get(i)[j] = "Cat"; //Target replacement
			}
		}
	}
	reader.close();

	// Write to CSV file which is open
	CSVWriter writer = new CSVWriter(new FileWriter(inputFile),',');
	writer.writeAll(csvBody);
	writer.flush();
	writer.close();
}

public static void updateCSV(String filePath, String output, String  replace, int row, int col) throws IOException {
	
	final char SEPARATOR = ";";

	CSVReader reader = new CSVReader(new FileReader(filePath));
	List<String[]> csvBody = reader.readAll();
	csvBody.get(row)[col]=replace;
	reader.close();
	
	CSVWriter writer = new CSVWriter(new FileWriter(output), SEPARATOR, " ");
	
	writer.writeAll(csvBody);

	writer.flush();
	writer.close();
}

public void writeColumn() {
	CSVWriter writer2 = new CSVWriter(new FileWriter("testing.csv"), '\t');

	String[] string = new String[10];
	for(int i = 0; i < 10; i++) {
		string[i] = "Testing";
	}

	writeLines(string, writer2); // This will write a column
	writer2.close();
}
public void writeLines(String[] array, CSVWriter writer) {
	for (final String oneElement : array) {
		writer.writeNext([oneElement].toArray());
		//svWriter.writeNext(new String[]{"Sundar Pichai ♥", "sundar.pichai@gmail.com", "+1-1111111111", "India"});
	}
}


