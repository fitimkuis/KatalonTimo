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


File folder = new File("C:/Users/fitim/Desktop/files");
File[] listOfFiles = folder.listFiles();

List<String> files = new ArrayList<>()
Map<String, String> map = new HashMap<String, String>();




int i = 1

for (File file : listOfFiles) {
	if (file.isFile()) {
		String fileName = "f"
		//System.out.println(file.getName());
		//String path = file.getAbsolutePath();
		//System.out.println(file.getAbsolutePath());
		//map.put(file.getName(),file.getAbsolutePath())
		fileName = fileName+i
		map.put(fileName,file.getAbsolutePath())
		i++
	}
}

//Iterator<String> iterator = map.keySet().iterator();

Map<String, String> treeMap = new TreeMap<String, String>(map);

ind = 1

List<String> fArray = new ArrayList<>()
List<String> nameArray = new ArrayList<>()

for (String s : treeMap){
	
	def file = treeMap.get("f"+ind)
	fArray.add(file)
	println("String path: "+file)
	ind++
}

println fArray
println("first filename in array: "+fArray.get(0))

int x = 0
for (String s : fArray){
	def file1 = new File(s)
	println file1
	x++
}


//System.out.println("The Value is: " + treeMap.get("f1"));

//def firstFile = treeMap.get("f1")
//println("String first path: "+firstFile)

printMap(treeMap);

/*while(iterator.hasNext()){
	String key   = iterator.next();
	String value = map.get(key);
	println("key: "+key+" value: "+value)
}*/

public static void printMap(Map<String,String> map) {
	Set s = map.entrySet();
	Iterator it = s.iterator();
	while ( it.hasNext() ) {
	   Map.Entry entry = (Map.Entry) it.next();
	   String key = (String) entry.getKey();
	   String value = (String) entry.getValue();
	   System.out.println(key + " => " + value);
	}//while
	System.out.println("========================");
}//printMap

