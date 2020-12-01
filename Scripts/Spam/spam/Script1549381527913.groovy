import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable

KeywordUtil log = new KeywordUtil()



def first = "04/22/2020"
def second = "04/22/2020"

print(WebUI.verifyMatch(first, second, false))

log.logInfo("this is info step")
log.markFailed("this step is failed")
log.markError("step has error")

log.logInfo("DEBUG first variable to verify: "+first)
log.logInfo("DEBUG second variable to verify: "+second)

print(WebUI.verifyMatch(first, second, false))

WebUI.openBrowser('https://www.google.com')

if(!WebUI.verifyElementPresent(findTestObject("asd"), 1, FailureHandling.OPTIONAL)){
	log.logInfo("Element not found but test still passes")
}

def res = CustomKeywords.'com.two.keyword.Keyword1.getString'("Katalon")
//def test = CustomKeywords.'com.two.keyword.Keyword2.returnFirstKeywordString'()
println res

log.logInfo("this is info step")
log.markFailed("this step is failed")
log.markError("step has error")



String test = "JSESSIONID=2334456678SHG888;path=rttt.com/backeben;secured";
//Pattern p = Pattern.compile("(\\w+)");
Pattern p = Pattern.compile("JSESSIONID=([^;]+)");
Matcher m = p.matcher(test);
while(m.find()) {
	def splitted = m.group().split("=");
	System.out.println(splitted[1]);
}



/*
//just do a simple click
WebUI.click(findTestObject("your_dropdown-image"))
WebUI.waitForElementVisible(findTestObject("your_dropdown-Item_MyAccount"), 30)
WebUI.click(findTestObject("your_dropdown-Item_MyAccount"))

//this will collect the dropdown items once it becomes visible in the website
java.util.List<WebElement> dropDownItems = WebUiCommonHelper.findElements(findTestObject("your_testObject"), 30)

//it will be stored in your list of webelement like this
//0 = My Account
//1 = Logout

//to click that "My Account", do a click event using the list's index
dropDownItems[0].click()
*/

println RunConfiguration.getExecutionSource().substring(RunConfiguration.getExecutionSource().toString().lastIndexOf("\\")+1)
println RunConfiguration.getExecutionSourceName()
println RunConfiguration.executionSource
println RunConfiguration.getExecutionSourceId()
println RunConfiguration.getExecutionSourceDescription()

def ret = GlobalVariable.long_paramer_name_described_more_words_needed_here
println ret

def String numberFromString(String str) {
	String numbers= str.replaceAll("[^0-9]", "")
	}

println numberFromString("Quote # 39281 created successfully")


def s = "Quote # 39281 created successfully"
System.out.println(s.substring(8,13));


HashMap<String, Integer> hm = new HashMap<>();
hm.put("Banana", 1);
hm.put("Orange", 2);
hm.put("Banana", 3);
hm.put("Banana", 4);

System.out.println("The set is: " + hm.keySet());


List <Long> numbers = new ArrayList<>()
Random r = new Random();
long xx = 100000000L;
long yy = 999999999L;
long number = 0L
number = xx+((long)(r.nextDouble()*(yy-xx)));
numbers.add(number)
int counter = 8
while (counter >= 0){
	number = xx+((long)(r.nextDouble()*(yy-xx)));
		if(!numbers.contains(number)){
			numbers.add(number)
			counter--
		}	
}

Collections.sort(numbers, Collections.reverseOrder());
println ("numbers are in list with reversed order "+numbers)

/*for (int z = 0; z < numbers.size(); z++){
	println ("numbers are "+numbers[z])
}*/

HashMap<Long, Integer> hamap = new HashMap<>();
for (int g=0;g<15;g++){
	number = xx+((long)(r.nextDouble()*(yy-xx)));
	hamap.put(number, g);
}
System.out.println("The set is: " + hamap.keySet());
TreeMap<Long, Integer> sortedHasMap = new TreeMap<>();
// Copy all data from hashMap into TreeMap
sortedHasMap.putAll(hamap);
// Display the TreeMap which is naturally sorted
for (Map.Entry<Long, Integer> entry : sortedHasMap.entrySet())
	System.out.println("Key = " + entry.getKey() +
				 ", Value = " + entry.getValue());


Date date = new Date();
TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
Calendar cal = Calendar.getInstance(TimeZone.getDefault());
date = cal.getTime();

println date

HashMap<String, String> hmapStr = new HashMap<String, String>();
hmapStr.put("eka","first")
hmapStr.put("toka","second")
hmapStr.put("kolmas","third")
hmapStr.put("neljas","fourth")
hmapStr.put("viides","fifth")

log.logInfo("value = " + hmapStr.get("kolmas"));

Iterator entries = hmapStr.entrySet().iterator();

while (entries.hasNext()) {
	Map.Entry entry = (Map.Entry) entries.next();
	String key = (String)entry.getKey();
	String value = (String)entry.getValue();
	//System.out.println("Key = " + key + ", Value = " + value);
}

//change map order by keys desc
TreeMap<String, String> sortedMap = new TreeMap<String, String>(Collections.reverseOrder());
// Copy all data from hashMap into TreeMap
sortedMap.putAll(hmapStr);

// Display the TreeMap which is reversed sorted
for (Map.Entry<String, String> entry : sortedMap.entrySet()){
	println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	log.logInfo("Key = " + entry.getKey() + ", Value = " + entry.getValue());
}

Double min = 90.0;  //  Set To Your Desired Min Value
Double max = 140.5; //    Set To Your Desired Max Value
double x = (Math.random()*((max-min)+1))+min; //    This Will Create A Random Number In between Your Min And Max.
double xrounded = Math.round(x * 100.0) / 100.0; // Creates Answer To The Nearest 100th, You Can Modify This To Change How It Rounds.
//System.out.println(xrounded); //    This Will Now Print Out The Rounded, Random Number.

double leader = 140.6

List<Double> list = new ArrayList<Double>();
for (int j = 0; j < 5; j++){
	x = (Math.random()*((max-min)+1))+min
	list.add(xrounded = Math.round(x * 100.0) / 100.0)
}
/*list.add(8.5)
list.add(7.6)
list.add(6.8)
list.add(5.4)
list.add(6.7)
list.add(4.4)
list.add(9.2)*/

List<Double> offset = new ArrayList<Double>();

for (Double l: list){
	offset.add(leader - l)
}

/*for (Double k: offset){
	println k
}*/

//add list values to hasmap
HashMap<Double, Double> hmap = new HashMap<Double, Double>();
Iterator<Double> i1 = list.iterator();
Iterator<Double> i2 = offset.iterator();
while (i1.hasNext() && i2.hasNext()) {
   hmap.put(i1.next(), i2.next());
  }

DecimalFormat df = new DecimalFormat("#.#");
df.setRoundingMode(RoundingMode.FLOOR);
//double result = new Double(df.format(3.545555555));
//change map order by keys desc
TreeMap<Double, Double> sorted = new TreeMap<Double, Double>(Collections.reverseOrder());
// Copy all data from hashMap into TreeMap
sorted.putAll(hmap);

// Display the TreeMap which is reversed sorted
for (Map.Entry<Double, Double> entry : sorted.entrySet()){
	println("leader: "+leader+"\t points: " + entry.getKey() + "\t difference:" + df.format(entry.getValue()));
	log.logInfo("leader: "+leader+"\t points: " + entry.getKey() + "\t difference:" + df.format(entry.getValue()));
}

/*
for (Map.Entry<Double, Double> entry : hmap.entrySet()) {
	System.out.println("Key = " + entry.getKey() + ", Value = " + df.format(entry.getValue()));
}*/

//Map<Double, Double> treemap = new TreeMap<Double, Double>(Collections.reverseOrder());
//Set set = treemap.entrySet();



/*
Map<Double, Double> treeMap = new TreeMap<>(hmap);
for (Double doub : treeMap.keySet()) {
	System.out.println(doub);
}*/
 

for (int count = 3; count >= 0; count--) {
	KeywordUtil.logInfo("count: $count")
}
KeywordUtil.logInfo("done")

//WebUI.openBrowser('')

//import com.gargoylesoftware.htmlunit.javascript.host.intl.DateTimeFormat
//import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet.DateTime


/*
WebUI.openBrowser('https://www.katalon.com/')
def driver = DriverFactory.getWebDriver()
String baseUrl = "https://www.katalon.com/"
selenium = new WebDriverBackedSelenium(driver, baseUrl)
selenium.openWindow("openWindow", "google")
selenium.selectWindow("google")
selenium.open("https://www.google.com/")
selenium.runScript("window.test = 5")
selenium.runScript("return window.test ${testvar}")
System.out.println("${testvar}");
// selenium.()
*/
//runScript javascript{alert(" 'DO YOU LIKE ALL THESE EXAMPLES? :) IF YES THEN SUBSCRIBE VIA EMAIL TO GET MORE SUCH EXAMPLES ON YOUR EMAIL ID' ")}
String expectedString = "two"

List<String> myArray = new ArrayList<>()
myArray.add("one")
myArray.add("two")
myArray.add("three")

if (myArray.contains(expectedString)){
	println "Value is in array"
}
else{
	println "Value is not in array"
}

boolean val = false

//try {
	for (String item: myArray){
		if (item.equals(expectedString)){
			println "Value is in array: "+item
			val = true
			break;
		}
	}
		if (!val){
			throw new com.kms.katalon.core.exception.StepErrorException('test failed expected string is not in array')
		}
	/*} catch (com.kms.katalon.core.exception.StepErrorException e){
		this.println(e)
	} catch (Exception e){
		this.println ("Test Failed general issue")
	} finally {
		println "expected string is in array "
	}*/


def firstEventDateTime= "2019-02-04T12:01:08.397106+00:00";
def parsedFirstDateTime = Date.parse("yyyy-MM-dd'T'HH:mm:ss", firstEventDateTime);
println parsedFirstDateTime; //Mon Feb 04 12:01:08 EET 2019

DateTimeFormatter jodaFormatter = ISODateTimeFormat.dateTime();
DateTime jodaParsed = jodaFormatter
		.parseDateTime("2013-05-17T16:27:34.9+05:30");
Date date2 = jodaParsed.toDate();
System.out.println("Date & Day:" + jodaParsed.getDayOfMonth() + "-" + jodaParsed.getMonthOfYear() + "-" + jodaParsed.getYear() + " " + jodaParsed.getHourOfDay() + ":" + jodaParsed.getMinuteOfHour()+" "+jodaParsed.dayOfWeek().getAsText());

def extractedValue = ""
def url = "https://here.comes.token"+extractedValue+".rest.of.ulr.parameters"

//WebElement element = WebUI.executeJavaScript("document.getElementById('someId').readOnly=false;", true)

