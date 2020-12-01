import static com.google.common.collect.MapDifference.ValueDifference;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.MapDifference.*;
import com.kms.katalon.core.util.KeywordUtil

import read.pdf.from.disk.ReadPdf

KeywordUtil logger = new KeywordUtil();


Map<String, String> expectedValues = new HashMap<>();
List <String> rules = new ArrayList<>();
def lines;

def tempFileName = "temp.txt"
def tempFilePath = System.getProperty("user.dir")+"\\pdfFiles\\"
def temp = tempFilePath+tempFileName


String number = "1.000";
Double doubleNumber = Double.parseDouble(number);
println(doubleNumber);
def integer = doubleNumber.intValue();
println(integer);

//TODO read pdf file
String pdfFilePath = System.getProperty("user.dir")+"\\pdfFiles\\Kreditbeslutsfil_500786.pdf";
ReadPdf.readPdfFile(pdfFilePath, temp);



//TODO remove unwanted lines from the start of file and write to new txt file
String path = ReadPdf.readLineByLineRemoveUnwantedLines(temp);

//TODO parse txt file read all lines to string
String strFile = ReadPdf.readLineByLineJava8( path );

//add here which unwanted lines (strings) need to remove
String remove1 =  " Skandia 106 55 Stockholm Telefon: 08 788 10 00 skandia.se 3/5Intern SidaKlassificering";
String remove2 = " Skandia 106 55 Stockholm Telefon: 08 788 10 00 skandia.se 4/5Intern SidaKlassificering";
String remove3 = "/5InternSidaKlassificering";
String remove4 = " 3.2 Kreditbeslut Datum Handläggare Kommentar Resultat";
String remove5 = "13:12 System Avslag Skandia 106 55 Stockholm Telefon: 08 788 10 00 skandia.se 5/5Intern SidaKlassificering";
String remove6 = "adress - 199003122455";

//split file with date
String rulePattern = "([\\d-\\d]+)";
Pattern r = Pattern.compile(rulePattern);
String splitDate = "";
//for (String line : lines) {
Matcher m = r.matcher(strFile);
if (m.find()) {
	//System.out.println("Found value: " + m.group(0));
	splitDate = m.group(0);
}

def splitted = strFile.split(splitDate+" ");

FileWriter writer = new FileWriter("temp.txt", false);
for (String s: splitted){
	//System.out.println(s.replace("\n", "").replace("\r", "").replace(remove1, "").replace(remove2,"").replace(remove3,"").replace(remove4,"").replace(remove5,"").replace(remove6,""));
	writer.write(s.replace("\n", "").replace("\r", "").replace(remove1, "").replace(remove2,"").replace(remove3,"").replace(remove4,"").replace(remove5,"").replace(remove6,""));
	writer.write("\n");   // write new line
}
writer.close();

//TODO read txt file temp.txt to regex
String ret = ReadPdf.readTxtFile("temp.txt");
//System.out.println(ret);

lines = ret.split("(\r\n|\r|\n)", -1);

//String[] spaceMoved = {};
List <String> spaceMoved = new ArrayList<>();

for (String s : lines){
	String res = ReadPdf.trimTrailingBlanks(s);
	if (!res.equals("")) {
		spaceMoved.add(res);
	}
}

/*System.out.println("DEBUG space moved ");
for(String s : spaceMoved){
	System.out.println(s);
}*/

Map<String, String> rulesOutcomes = new HashMap<>();


//TODO working one regex
Map<String, String> testValues = new HashMap<>();
rulePattern = "(?m)^(\\d+:\\d+)\\s(\\w+\\d+).* System (\\w.*)";
// Create a Pattern object
r = Pattern.compile(rulePattern);
// Now create matcher object.
for (String line : spaceMoved) {
	m = r.matcher(line);
	if (m.find()) {
		/*System.out.println("Found value: " + m.group(0));
		System.out.println("Found value: " + m.group(1));
		System.out.println("Found value: " + m.group(2));
		System.out.println("Found value: " + m.group(3));*/
		testValues.put(m.group(2),m.group(3));
		rulesOutcomes.put(m.group(2),m.group(3));
	}
}
//System.out.println(testValues);

List<String> ruleList = new ArrayList<>();
List<String> outList = new ArrayList<>();

for (Map.Entry<String,String> entry : rulesOutcomes.entrySet()){
	//System.out.println("Key = " + entry.getKey() +", Value = " + entry.getValue());
	ruleList.add(entry.getKey().toString());
}

/*rulePattern = "([\\d:\\d]+)\\s([CR\\d+]+)";
// Create a Pattern object
r = Pattern.compile(rulePattern);
// Now create matcher object.
for (String line : spaceMoved) {
	m = r.matcher(line);
	if (m.find()) {
		//System.out.println("Found value: " + m.group(0));
		//System.out.println("Found value: " + m.group(1)); //rule
		//rule1 = m1.group(1).replaceAll("\\s","");
		//System.out.println("Found value: " + m.group(2));
		ruleList.add(m.group(2));
	}
}*/

/*rulePattern = "Godkänt|Avslag";
// Create a Pattern object
r = Pattern.compile(rulePattern);
// Now create matcher object.
for (String line : spaceMoved) {
	m = r.matcher(line);
	if (m.find()) {
		//System.out.println("Found value: " + m.group(0));
		outList.add(m.group(0));
	}
}*/

//for (int i = 0; i < outList.size(); i++){
//	rulesOutcomes.put(ruleList.get(i), outList.get(i));
//}

//expected values are done by rulelist "Godkänt" if i modulo 2 == 0 else "Avslag"
int i = 0;
for (String ru : ruleList){
	if (i % 2 == 0) {
		expectedValues.put(ru, "Godkänt");
	}
	else{
		expectedValues.put(ru, "Avslag");
	}
	i++;
}

//compare maps
//Assert.assertTrue("Maps should be unequal", MapDiffUtil.validateEqual(rulesOutcomes, expectedValues, "map1", "map2"));
Assert.assertFalse("Maps should be unequal", MapDiffUtil.validateEqual(rulesOutcomes, expectedValues, "map1", "map2"));



public class ReadPdf {
	
	
		static List <String> rules = new ArrayList<>();
		
		static def lines;
	
		private static final Logger log = LoggerFactory.getLogger(MapDiffUtil.class);
			
		public static String readLineByLineRemoveUnwantedLines(String path) {
			
			String newPath = path;
			try {
				File file = new File(path);
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuffer.append(line);
					stringBuffer.append("\n");
				}
				fileReader.close();
				//System.out.println("Contents of file:");
				//String removeString = '(?m)(?s)^Beslutsunderlag.*Datum Kreditregel ID Beskrivning Handläggare Kommentar Resultat$'
				//System.out.println(stringBuffer.toString().trim().replaceAll("(?m)(?s)^Beslutsunderlag.*Datum Kreditregel ID Beskrivning Handläggare Kommentar Resultat$", ""));
				//.replaceAll("Beslutsunderlag(\\s|\\S)*('Datum Kreditregel ID Beskrivning Handläggare Kommentar Resultat')", ""));
				//TODO write to file
				BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(newPath)));
				bwr.write((stringBuffer.toString().trim().replaceAll('(?m)(?s)^Beslutsunderlag.*Datum Kreditregel ID Beskrivning Handläggare Kommentar Resultat$', "")));
				//flush the stream
				bwr.flush();
				//close the stream
				bwr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			return newPath;
		}
	
		public static void readPdfFile(String pdfFilePath, String path) throws IOException {
	
			//String pdfFilePath = System.getProperty("user.dir") + "\\pdfFiles\\Kreditbeslutsfil_500786.pdf";
	
			List<String> content = new ArrayList<>();
	
			String text = "";
			PDDocument document = PDDocument.load(new File(pdfFilePath));
			if (!document.isEncrypted()) {
				PDFTextStripper stripper = new PDFTextStripper();
				text = stripper.getText(document);
				//System.out.println("Text:" + text);
				content.add(text);
			}
			document.close();
	
			//System.out.println("DEBUG**********"+content);
			//TODO write to txt file
			writeTxtFile(content, path);
	
			lines = text.split("(\r\n|\r|\n)", -1);
	
	
			//System.out.println(lines);
	
			//for testing purpose
			//Map<String, String> rules = new HashMap<>();
	
			//String pattern = '^.*Person ([\\S\\s])([\\S\\s])([\\S\\s]+)';
			//String pattern = '([CR\\d]+) * Description ([\\S\\s]) Person ([\\S\\s])([\\S\\s]+)';
			String pattern = "([\\S\\s]+) ([\\S\\s]+) ([CR\\d]+) - ([\\d]+) System ([\\S\\s]+)";
			String searchString = "Avslag";
			String rule = "";
			String outcome = "";
			Map<String, String> rulesOutcomes = new HashMap<>();
	
			// Create a Pattern object
			Pattern r = Pattern.compile(pattern);
	// Now create matcher object.
			for (String line : lines) {
				Matcher m = r.matcher(line);
				if (m.find()) {
					//System.out.println("Found value: " + m.group(0));
					//System.out.println("Found value: " + m.group(1)); //rule
					//rule1 = m1.group(1).replaceAll("\\s","");
					//System.out.println("Found value: " + m.group(2));
					//System.out.println("Found value: " + m.group(3)); //rule x
					rule = m.group(3).replaceAll("\\s", "");
					rules.add(rule);
					//System.out.println("Found value: " + m.group(4)); //outcome
					//outcome1 = m1.group(4).replaceAll("\\s","")
					//System.out.println("Found value: " + m.group(5)); //outcome x
					outcome = m.group(5).replaceAll("\\s", "");
					rulesOutcomes.put(rule, outcome);
					//if (m.group(5).replaceAll("\\s", "").equals(searchString)) {
					//	log.info("value is wrong should be Godkänt");
					//}
				//} else {
					//System.out.println("NO MATCH");
				}
			}
	
		}
	
		public static void writeTxtFile(List<String> list, String path) throws IOException {
			try {
				FileWriter writer = new FileWriter(path, false);
				for (String s : list) {
					writer.write(s);
					writer.write("\r\n");   // write new line
				}
				writer.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	
		public static String readLineByLineJava8(String filePath)
		{
			StringBuilder contentBuilder = new StringBuilder();
			try
			{
				Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)
				stream.forEach{s -> contentBuilder.append(s).append(" ").append("\n")};
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return contentBuilder.toString();
		}
	
		private static String readTxtFile(String filePath)
		{
			StringBuilder contentBuilder = new StringBuilder();
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(filePath))
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null)
				{
					contentBuilder.append(sCurrentLine).append("\n");
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return contentBuilder.toString();
		}
	
		public static String trimTrailingBlanks( String str)
		{
			int len = 0;
			if( str == null)
				return null;
			if (!str.equals(" ")) {
				len = str.length();
				for (; len > 0; len--) {
					if (!Character.isWhitespace(str.charAt(len - 1)))
						break;
				}
			}
			return str.substring( 0, len);
		}
}


public class MapDiffUtil {
	
	private static KeywordUtil logg = new KeywordUtil()

	private static final Logger log =
			LoggerFactory.getLogger(MapDiffUtil.class);

	public static <K, V> boolean validateEqual(
			Map<K, V> map1, Map<K, V> map2,
			String map1Name, String map2Name) {

		final MapDifference<K, V> diff = Maps.difference(map1, map2);

		if (diff.areEqual()) {
			def error = "Maps "+map1Name+" and "+map2Name+" contain exactly the same name/value pairs"
			logg.markWarning(error)
			log.info("Maps '{}' and '{}' contain exactly the same name/value pairs", map1Name, map2Name);
			return true;

		} else {
			logKeys(diff.entriesOnlyOnLeft(), map1Name, map2Name);
			logKeys(diff.entriesOnlyOnRight(), map2Name, map1Name);
			logEntries(diff.entriesDiffering(), map1Name, map2Name);
			return false;
		}
	}

	private static <K, V> void logKeys(
			Map<K, V> mapSubset, String n1, String n2) {
		if (not(mapSubset.isEmpty())) {
			logg.markWarning("Keys found in "+n1+" but not in "+n2+" : "+mapSubset.keySet());
			log.error("Keys found in {} but not in {}: {}",n1, n2, mapSubset.keySet());
		}
	}

	private static <K, V> void logEntries(
			Map<K, ValueDifference<V>> differing,
			String n1, String n2) {
		if (not(differing.isEmpty())) {
			logg.markFailed("Differing values found {key="+n1+"-value,"+n2+"-value}: "+differing);
			log.error("Differing values found {key={}-value,{}-value}: {}",	n1, n2, differing);
		}
	}

	private static boolean not(boolean b) {
		return !b;
	}
}