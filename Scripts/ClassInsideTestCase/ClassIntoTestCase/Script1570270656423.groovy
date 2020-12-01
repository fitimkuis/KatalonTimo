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

//import TestClass


TestClass test = new TestClass("Katalon")
println(test.getTestString())
test.setTestString("Test Automation")
println(test.getTestString())

TestClass2 test2 = new TestClass2()
//test.setTestString("this is fynny job")
println("DEBUG defined in class1 constructor and called with super constructor in class2 "+test2.getTestString())

TestClass2 test3 = new TestClass2()
test3.setString("For Testers")
println("DEBUG overrided by class2 constructor "+test3.getString())

for (TestClass.Weekdays days : TestClass.Weekdays.values()){
	
	println(days.value+" : "+days)
	test.listString.add(days)
}

println test.listString 

for (CrunchifyEnumExample.Company cName : CrunchifyEnumExample.Company.values()){
	//System.out.println("Company Value: " + cName.value + " - Company Name: " + cName);
	
	switch(cName.value){
		
		case 30:
			println "Company Name: "+cName
			break	
		case 10:
			println "Company Name: "+cName
			break
		case 15:
			println "Company Name: "+cName
			break
		case 20:
			println "Company Name: "+cName
			break
		case 25:
			println "Company Name: "+cName
			break
		default:
			println "no values!!!"
			break
	}
}


public class CrunchifyEnumExample {
	
	   public enum Company {
		   EBAY(30), PAYPAL(10), GOOGLE(15), YAHOO(20), ATT(25);
		   private int value;
	
		   private Company(int value) {
			   this.value = value;
		   }
	   }
   }

public class TestClass{
	
	private static String testString
	
	private static List<String> listString = new ArrayList<>()
	
	public enum Weekdays {
		Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7);
		private int value;
 
		private Weekdays(int value) {
			this.value = value;
		}
	}
	
	public TestClass(){}
	
	public TestClass(String test){
		this.testString = test
	}
	
	public static String getTestString(){
		return this.testString
	}
	
	public static void setTestString(String test){
		this.testString = test
	}
}

public class TestClass2 extends TestClass{
	
	private static String testString2
	
	public TestClass2(){
		//super.testString
		super()
	}
	
	public static void setString(String test){
		this.testString2 = test
		//println("DEBUG testString in setter 2 "+testString2)
	}
	
	public static String getString(){
		return this.testString2
	}	
}
