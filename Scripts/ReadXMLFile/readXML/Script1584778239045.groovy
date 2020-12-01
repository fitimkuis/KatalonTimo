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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path
import java.nio.file.Paths



try {
	
	String pdfFilePath = System.getProperty("user.dir")+"\\Include\\xmlFiles\\stuff.xml";
	
	File fXmlFile = new File(pdfFilePath);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	NodeList nList = doc.getElementsByTagName("report");
	

	def slurper = new XmlSlurper()
	def xml = slurper.parse(fXmlFile)
	def reportElementList = xml.depthFirst().findAll {
		it.name() == 'report'
	} 
	println "number of <report> element: " + reportElementList.size()
	
	reportElementList.each { rp ->
		println "R4: ${rp.R4}"
		println "R5: ${rp.R5}"
		println "R6: ${rp.R6}"
		println "R7: ${rp.R7}"
		println "R10: ${rp.R10}"
	}
	

	List<String> r4 = new ArrayList<>();
	List<String> r5 = new ArrayList<>();
	List<String> r6 = new ArrayList<>();
	List<String> r7 = new ArrayList<>();

	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			System.out.println("R4 : " + eElement.getElementsByTagName("R4").item(0).getTextContent());
			r4.add(eElement.getElementsByTagName("R4").item(0).getTextContent());
			System.out.println("R5 : " + eElement.getElementsByTagName("R5").item(0).getTextContent());
			r5.add(eElement.getElementsByTagName("R5").item(0).getTextContent());
			System.out.println("R6 : " + eElement.getElementsByTagName("R6").item(0).getTextContent());
			r6.add(eElement.getElementsByTagName("R6").item(0).getTextContent());
			System.out.println("R7 : " + eElement.getElementsByTagName("R7").item(0).getTextContent());
			r7.add(eElement.getElementsByTagName("R7").item(0).getTextContent());
		}
	}
	int counter = 0;
	int nodeR4 = 0;
	System.out.println(r4);
	for(String s : r4){
		nodeR4 += Integer.valueOf(s);
		counter++;
	}
	System.out.println("NODE4 sum: "+nodeR4);

	int nodeR5 = 0;
	System.out.println(r5);
	for(String s : r5){
		nodeR5 += Integer.valueOf(s);
		counter++;
	}
	System.out.println("NODE5 sum: "+nodeR5);

	int nodeR6 = 0;
	System.out.println(r6);
	for(String s : r6){
		nodeR6 += Integer.valueOf(s);
		counter++;
	}
	System.out.println("NODE6 sum: "+nodeR6);

	int nodeR7 = 0;
	System.out.println(r7);
	for(String s : r7){
		nodeR7 += Integer.valueOf(s);
		counter++;
	}
	System.out.println("NODE7 sum: "+nodeR7);

	int sum = nodeR4+nodeR5+nodeR6+nodeR7;
	System.out.println("number of R nodes :"+counter+" nodes SUM: "+sum);
} catch (Exception e) {
	e.printStackTrace();
}