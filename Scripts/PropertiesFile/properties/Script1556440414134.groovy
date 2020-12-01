import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.comment(">>> currentTestCaseId=${GlobalVariable.currentTestCaseId}")

int count = 6

//Lists of list
ArrayList<ArrayList<String>> mainArrayList = new ArrayList<ArrayList<String>>();

ArrayList<String> subArrayList;

//create dynamic count of list of lists
for(int i=0;i<count;i++) {
	subArrayList = new ArrayList<String>();
	subArrayList.add("page"+i); // add value to list
	mainArrayList.add(subArrayList);
}
println mainArrayList //print mainList

//mainArrayList.get(0).add("kissa")
//println mainArrayList.get(0).get(0)

//add values to sublist
for (int x = 0; x < mainArrayList.size(); x++){
	mainArrayList.get(x).add("key"+x)
	mainArrayList.get(x).add("value"+x)
}

List <String> values = new ArrayList<>()
//print sublist
for (int i = 0; i < mainArrayList.size(); i++) {
	for (int j = 0; j < mainArrayList.get(i).size(); j++) {
		//System.out.print(mainArrayList.get(i).get(j) + " ");
		values.add(mainArrayList.get(i).get(j))
	}
	//System.out.println();
}

int index = 0
for (String s : values){
	if (index == 3){
		print("\n")
		index = 0
	}
	print s+ " "
	index++
}


/*int ind = 0
for (List <String> l : mainArrayList){
	for(String s : l){
		//print("DEBUG page "+l)
		println(" DEBUG pages " +s )
	}
}*/

//mainArrayList.each{subIter -> subArrayList.each{element -> System.out.println("*** "+element)}};

//list of testcase names
List <String> testCases = new ArrayList<>()
testCases.add("properties")
testCases.add("properties1")
testCases.add("properties2")
testCases.add("properties3")
testCases.add("properties4")

def tcName = GlobalVariable.currentTestCaseId //get current testcase name
String[] parts = tcName.split("/"); //split it to using delimeter /
String one = parts[parts.length-1];
String two = parts[parts.length-2];
String three = parts[parts.length-3];
println three
println two
println one

//loop testcases to find out wanted
for (String s: testCases){
	
	if (one.equals(s)){
		
		println ("DEBUG testcase name: "+one)
	}	
}

/*if (one.equals("properties")){
 
 println ("DEBUG testcase name: "+one)
}*/


def expectedValue = "user4"
def prop = CustomKeywords.'com.properties.PropertiesUtil.GetProperty'(expectedValue)
println "property fetch "+prop

//get all properties
Properties props = new Properties();
props = CustomKeywords.'com.properties.PropertiesUtil.GetAllProperties'()
//println props

// Java 8 , print key and values
//allProps.forEach(key, value) -> System.out.println("Key : " + key + ", Value : " + value));

 /*Set<String> keys = props.stringPropertyNames();
 for (String key : keys) {
	 System.out.println(key + " : " + props.getProperty(key));
 }*/
 
 /*Set<Entry<Object, Object>> entries = props.entrySet();
 for (Entry<Object, Object> entry : entries) {
   System.out.println(entry.getKey() + " : " + entry.getValue());
 }*/
 
 Map<String, String> sortedMap = new TreeMap(props);
 
	 //output sorted properties (key=value)
	 for (String key : sortedMap.keySet()) {
		 System.out.println(key + "=" + sortedMap.get(key));
	 }