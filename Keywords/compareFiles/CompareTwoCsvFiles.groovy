package compareFiles

import org.apache.commons.collections.CollectionUtils
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord

import com.kms.katalon.core.annotation.Keyword
import com.opencsv.CSVWriter

public class CompareTwoCsvFiles {

	@Keyword
	public void compareTwoCsvFiles(){

		String path="C:\\Users\\fitim\\Desktop\\data\\";
		String file1="csv1.csv";
		String file2="csv2.csv";
		String file3="differences.csv";



		ArrayList al1=new ArrayList();
		ArrayList al2=new ArrayList();
		ArrayList al3=new ArrayList();

		BufferedReader CSVFile1 = new BufferedReader(new FileReader(path+file1));
		String dataRow1 = CSVFile1.readLine();
		while (dataRow1 != null) {
			String[] dataArray1 = dataRow1.split(",");
			for (String item1:dataArray1) {
				al1.add(item1);
			}

			dataRow1 = CSVFile1.readLine(); // Read next line of data.
		}
		println("DEBUG csv1**** "+al1)
		CSVFile1.close();

		BufferedReader CSVFile2 = new BufferedReader(new FileReader(path+file2));
		String dataRow2 = CSVFile2.readLine();
		while (dataRow2 != null) {
			String[] dataArray2 = dataRow2.split(",");
			for (String item2:dataArray2) {
				al2.add(item2);
			}
			dataRow2 = CSVFile2.readLine(); // Read next line of data.
		}
		println("DEBUG csv2**** "+al2)
		CSVFile2.close();

		for(String bs:al2) {
			println("DEBUG removed from csv1**** "+bs)
			al1.removeAll(bs);
			al3.add(bs)
		}

		println("DEBUG csv3**** "+al1)
		int size=al1.size();
		System.out.println(size);

		try {
			FileWriter writer=new FileWriter(path+file3);
			while(size!=0) {
				size--;
				writer.append(""+al1.get(size));
				println("DEBUG **** "+al1.get(size))
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Keyword
	public void Compare()throws IOException {

		String path="C:\\Users\\fitim\\Desktop\\data\\";
		String file1="csv1.csv";
		String file2="csv2.csv";
		String file3="differences.csv";

		int lineNumber = 0

		List<String> differ = new ArrayList<>();

		//Set<String> linesToCompare = new HashSet<>();
		List<String> csvFile1 = new ArrayList<>();
		List<String> csvFile2 = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path+file1))
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				csvFile1.add(Arrays.asList(values));
				//String[] splitted = line.split(",");
				//csvFile1.add(splitted[0]);
				//linesToCompare.add(splitted[0] + splitted[1] + splitted[2] + splitted[8]);
			}
			println("DEBUG **** file1 "+csvFile1)

			BufferedReader reader1 = new BufferedReader(new FileReader(path+file2));
			String line1;
			List<String> ar = new ArrayList<>()
			String joined = ""
			while ((line1 = reader1.readLine()) != null) {
				String[] values2 = line1.split(",");
				csvFile2.add(Arrays.asList(values2));
				//String[] splitted1 = line1.split(",");
				//csvFile2.add(splitted1[0]);
				//String joined = splitted[0] + splitted[1] + splitted[2] + splitted[8];
			}

			println("DEBUG **** file2 "+csvFile2)

			/*for (String A: csvFile2) {
			 if (csvFile1.contains(A))
			 csvFile1.remove(A);
			 }*/
			//System.out.println("output");
			//System.out.println(csvFile1);

			List<String> list = new ArrayList<>(CollectionUtils.disjunction(csvFile1, csvFile2));
			//println list.size()
			//println list
			String listString = "";

			for (String s : list)
			{
				listString += s+",";
			}

			//String listString = String.join(", ", list);
			//System.out.println(listString);

			String d1 = listString.replace("[", " ");
			String d2 = d1.replace("]"," ")
			System.out.println(d2);

			String[] spiltted = d2.split(";")
			System.out.println(spiltted);

			File file = new File(path+file3);
			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);
			writer.writeNext("differences in this row")
			writer.writeNext(spiltted);
			// closing writer connection
			writer.close();

			/*List<String> f1 = new ArrayList<>()
			 List<String> f2 = new ArrayList<>()
			 f1 = compareArraysF2(csvFile1, csvFile2)
			 f2 = compareArraysF1(csvFile2, csvFile2)*/

		}catch(Exception ex2){
			println ex2
		}
	}

	public List<String> compareArraysF2(List<String> file1, List<String> file2){
		//remove all elements from second list
		file2.removeAll(file1);
		System.out.println("missing from file1: "+file2);
		return file2
	}
	public List<String> compareArraysF1(List<String> file1, List<String> file2){
		//remove all elements from second list
		file1.removeAll(file2);
		System.out.println("missing from file2: "+file1);
		return file1
	}

	@Keyword
	public void readCsvFile(){

		String path="C:\\Users\\fitim\\Desktop\\data\\";
		String file1="csv1.csv";
		String file2="csv2.csv";
		String file3="differences.csv";

		//Path currentRelativePath = Paths.get("");
		//String currentPath = currentRelativePath.toAbsolutePath().toString();
		//String csvFile = currentPath + "/pathInYourProject/test.csv";
		String csvFile = path+file1;

		Reader inn;
		Iterable<CSVRecord> records = null;
		try
		{
			inn = new FileReader(csvFile);
			CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(';' as char)
			//CSVParser parser = new CSVParser(new FileReader(csvFile), format);
			//records = CSVFormat.RFC4180.withHeader().withDelimiter(';' as char)
			//records = CSVFormat.EXCEL.withHeader().parse(inn); // header will be ignored
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		for (CSVRecord record : records) {
			String line = "";
			for ( int i=0; i < record.size(); i++)
			{
				if ( line == "" )
					line = line.concat(record.get(i));
				else
					line = line.concat("," + record.get(i));
			}
			System.out.println("read line: " + line);
		}

	}

}
