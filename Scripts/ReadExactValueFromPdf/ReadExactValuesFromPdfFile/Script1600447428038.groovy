import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScreenshot'('C:\\Users\\fitim\\KatalonProjectFromGit\\KatalonProject\\pdfFilesfullscreen.png', FailureHandling.OPTIONAL)


PDDocument document = PDDocument.load(new File("C:\\Users\\fitim\\Desktop\\pdf\\pdfcontent.pdf"))
	
	document.getClass();

	if (!document.isEncrypted()) {

		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);

		PDFTextStripper tStripper = new PDFTextStripper();

		String pdfFileInText = tStripper.getText(document);
		//println("Text:" + pdfFileInText);

		// split by whitespace
		def lines = pdfFileInText.split("\\r?\\n");
		//println("Textlines:" + lines);
		//define list of lists
		List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();

		for (String line : lines) {
			println line
			//create dynamic list
			ArrayList<String> l = new ArrayList<>();
			l.add(line);
			listOfLists.add(l);
		}

		def word = listOfLists.get(0).get(0).split(" ");
		println(word[3]);
		def word1 = listOfLists.get(1).get(0).split(" ");
		println(word1[5]);
		def word2 = listOfLists.get(2).get(0).split(" ");
		println(word2[5]);

		List<String> total = new ArrayList<>();
		//start from line 1 cause 0 line is header line
		for(int i = 1; i < listOfLists.size()-1; i++){
			def wd = listOfLists.get(i).get(0).split(" ");
			//add here index what you will need
			total.add(wd[5]);
		}
		println(total);
	}
			