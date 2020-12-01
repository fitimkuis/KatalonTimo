package compareFiles

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

public class CompareCsvFileColumns {

	@Keyword
	public void compare_cvs(){
		ArrayList<String> resultList = new ArrayList<>();
		ArrayList<String> linesList = new ArrayList<>();
		// Input of file which needs to be parsed
		String csvFile = "C:\\Users\\fitim\\Desktop\\data\\verify.csv";
		BufferedReader csvReader;
		FileWriter writer;

		// Data split by ',' in CSV file
		String csvSplitBy = ",";
		try {
			// Read the CSV file into an ArrayList array for easy processing.
			String line;
			csvReader = new BufferedReader(new FileReader(csvFile));
			while ((line = csvReader.readLine()) !=null) {
				linesList.add(line);
			}
			csvReader.close();
		}
		catch (IOException e) { e.printStackTrace(); }

		// Process each CSV file line which is now contained within
		// the linesList list Array
		for (int i = 0; i < linesList.size(); i++) {
			String[] data = linesList.get(i).split(csvSplitBy);
			String col1 = data[0];
			String col2 = data[1];
			String col3YesNo = data[2];
			//int col4Value = Integer.parseInt(data[3]);  //WAS THIS
			double col4Value = Double.parseDouble(data[3]); // *** SHOULD BE ***
			String col5Unknown = data[4];

			// Determine if Column 1 and Column 2 data for the
			// current line is unique to the entire CSV file.
			boolean columns1And2AreUnique = true;
			for (int j = 0; j < linesList.size(); j++) {
				String[] tmp = linesList.get(j).split(csvSplitBy);
				// Make sure we don't process the same line we are on...
				if (j != i) {
					if (col1.equals(tmp[0]) || col2.equals(tmp[1])) {
						columns1And2AreUnique = false;
						break;
					}
				}
			}
			if (columns1And2AreUnique && col3YesNo.equalsIgnoreCase("no") && col4Value >= 12.0) {
				resultList.add(linesList.get(i));
			}
		}

		// Display the determined results from the CSV file.
		if (resultList.isEmpty()) {
			System.out.println("There could be no data results gathered from the supplied\n"
					+ "CSV file which meets the required criteria.");
		}
		else {
			System.out.println("Column 1\tColumn 2\tColumn 3\tColumn 4\tColumn 5");
			System.out.println("================================================"
					+ "========================\n");
			String padString = "        "; //Used for simple space padding in display

			for (int i = 0; i < resultList.size(); i++) {
				String[] tmp = resultList.get(i).split(csvSplitBy);
				System.out.println(tmp[0] + padString.substring(tmp[0].length()) + "\t"
						+ tmp[1] + padString.substring(tmp[1].length()) + "\t"
						+ tmp[2] + padString.substring(tmp[2].length()) + "\t"
						+ tmp[3] + padString.substring(tmp[3].length()) + "\t"
						+ tmp[4]);

			}
			try
			{
				writer=new FileWriter("C:\\Users\\fitim\\Desktop\\data\\verified.csv");
				for (int i = 0; i < resultList.size(); i++) {
					String[] tmp = resultList.get(i).split(csvSplitBy);
					System.out.println("*****DEBUG value***** "+tmp[i])
					writer.append(""+tmp[0]+",");
					writer.append(""+tmp[1]+",");
					writer.append(""+tmp[2]+",");
					writer.append(""+tmp[3]+",");
					writer.append(""+tmp[4]+",");
					writer.append('\n');
				}
				writer.flush();
				writer.close();

			} catch(Exception e){

			}

		}
	}
	@Keyword
	public void titles(){

		ArrayList<String> resultList = new ArrayList<>();
		ArrayList<String> linesList = new ArrayList<>();
		// Input of file which needs to be parsed
		String csvFile = "C:\\Users\\fitim\\Desktop\\data\\titles.csv";
		BufferedReader csvReader;
		FileWriter writer;

		// Data split by ',' in CSV file
		String csvSplitBy = ",";
		try {
			// Read the CSV file into an ArrayList array for easy processing.
			String line;
			csvReader = new BufferedReader(new FileReader(csvFile));
			while ((line = csvReader.readLine()) !=null) {
				linesList.add(line);
			}
			csvReader.close();
		}
		catch (IOException e) { e.printStackTrace(); }

		// Process each CSV file line which is now contained within
		// the linesList list Array
		for (int i = 0; i < linesList.size(); i++) {
			String[] data = linesList.get(i).split(csvSplitBy);
			String col1 = data[0];
			String col2 = data[1];
			String col3 = data[2];
			//int col4Value = Integer.parseInt(data[3]);  //WAS THIS
			//double col4Value = Double.parseDouble(data[3]); // *** SHOULD BE ***
			//String col5Unknown = data[4];

			// Determine if Column 1 and Column 2 data for the
			// current line is unique to the entire CSV file.
			boolean columns1And2AreUnique = true;
			for (int j = 0; j < linesList.size(); j++) {
				String[] tmp = linesList.get(j).split(csvSplitBy);
				// Make sure we don't process the same line we are on...
				if (j != i) {
					if (col1.equals(tmp[0]) || col2.equals(tmp[1])) {
						columns1And2AreUnique = false;
						break;
					}
				}
			}
			if (columns1And2AreUnique && col3.equalsIgnoreCase("no")) {
				resultList.add(linesList.get(i));
			}
		}

		// Display the determined results from the CSV file.
		if (resultList.isEmpty()) {
			System.out.println("There could be no data results gathered from the supplied\n"
					+ "CSV file which meets the required criteria.");
		}
		else {
			System.out.println("Column 1\tColumn 2\tColumn 3\tColumn 4\tColumn 5");
			System.out.println("================================================"
					+ "========================\n");
			String padString = "        "; //Used for simple space padding in display

			for (int i = 0; i < resultList.size(); i++) {
				String[] tmp = resultList.get(i).split(csvSplitBy);
				System.out.println(tmp[0] + padString.substring(tmp[0].length()) + "\t"
						+ tmp[1] + padString.substring(tmp[1].length()) + "\t"
						+ tmp[2] + padString.substring(tmp[2].length()));

			}
			try
			{
				writer=new FileWriter("C:\\Users\\fitim\\Desktop\\data\\tiles_verified.csv");
				for (int i = 0; i < resultList.size(); i++) {
					String[] tmp = resultList.get(i).split(csvSplitBy);
					System.out.println("*****DEBUG value***** "+tmp[i])
					writer.append(""+tmp[0]+",");
					writer.append(""+tmp[1]+",");
					writer.append(""+tmp[2]+",");
					writer.append('\n');
				}
				writer.flush();
				writer.close();

			} catch(Exception e){

			}

		}

	}
}
