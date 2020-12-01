package read.pdf.from.disk

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import static com.google.common.collect.MapDifference.ValueDifference;

//import java.io;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.MapDifference.*;
//import com.kms.katalon.core.util.KeywordUtil;

public class ReadPdfFileAndVerify {
	
	@Keyword
	public static void pdfVerify(){
		readPdfFile("")
		
	}
}

public class ReadPdf {
	
		static List <String> rules = new ArrayList<>();
	
		static Map<String, String> expectedValues = new HashMap<>();
	
		static String[] lines;
	
		//private static final Logger log = LoggerFactory.getLogger(MapDiffUtil.class);
	
		//KeywordUtil logger = new KeywordUtil();
	
		public static void readPdfFile(String pdfFilePath) throws IOException {
	
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
			writeTxtFile(content);
	
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
					System.out.println("Found value: " + m.group(0));
					System.out.println("Found value: " + m.group(1)); //rule
					//rule1 = m1.group(1).replaceAll("\\s","");
					System.out.println("Found value: " + m.group(2));
					System.out.println("Found value: " + m.group(3)); //rule x
					rule = m.group(3).replaceAll("\\s", "");
					rules.add(rule);
					System.out.println("Found value: " + m.group(4)); //outcome
					//outcome1 = m1.group(4).replaceAll("\\s","")
					System.out.println("Found value: " + m.group(5)); //outcome x
					outcome = m.group(5).replaceAll("\\s", "");
					rulesOutcomes.put(rule, outcome);
					if (m.group(5).replaceAll("\\s", "").equals(searchString)) {
						//.info("value is wrong should be Godkänt");
					}
				} else {
					//System.out.println("NO MATCH");
				}
			}
	
		}
	
		public static void writeTxtFile(List<String> list) throws IOException {
			try {
				FileWriter writer = new FileWriter("temp.txt", false);
				for (String s : list) {
					writer.write(s);
					writer.write("\r\n");   // write new line
				}
				writer.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	
		/*public static String readLineByLineJava8(String filePath)
		{
			StringBuilder contentBuilder = new StringBuilder();
			try
			{
				Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)
				stream.forEach(s -> contentBuilder.append(s).append(" ").append("\n"));
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
			try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
			{
	
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
		}*/
	
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

public class ReadStringFromFileLineByLine {
	
		public static String readLineByLineRemoveUnwantedLines(String path) {
	
			String newPath = "temp.txt";
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
				System.out.println("Contents of file:");
				//System.out.println(stringBuffer.toString().trim().replaceAll("(?m)(?s)^Beslutsunderlag.*Datum Kreditregel ID Beskrivning Handläggare Kommentar Resultat$", ""));
				//.replaceAll("Beslutsunderlag(\\s|\\S)*('Datum Kreditregel ID Beskrivning Handläggare Kommentar Resultat')", ""));
				//TODO write to file
				BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(newPath)));
				//bwr.write((stringBuffer.toString().trim().replaceAll("(?m)(?s)^Beslutsunderlag.*Datum Kreditregel ID Beskrivning Handläggare Kommentar Resultat$", ""));
				//flush the stream
				bwr.flush();
				//close the stream
				bwr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			return newPath;
		}
	}
	