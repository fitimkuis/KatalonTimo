import static com.google.common.collect.MapDifference.ValueDifference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.MapDifference
import com.google.common.collect.Maps
import com.google.common.collect.MapDifference.*;
import com.kms.katalon.core.util.KeywordUtil


KeywordUtil logger = new KeywordUtil()

String pdfFilePath = System.getProperty("user.dir")+"\\pdfFiles\\Kreditbeslutsfil_500786.pdf";
//PDFUtil pdfUtil = new PDFUtil();
//String content = pdfUtil.getText(pdfFilePath,1);
//println content
println pdfFilePath

List<String> content = new ArrayList<>()

String text = ""
PDDocument document = PDDocument.load(new File(pdfFilePath));
if (!document.isEncrypted()) {
	PDFTextStripper stripper = new PDFTextStripper();
	text = stripper.getText(document);
	System.out.println("Text:" + text);
	content.add(text)
}
document.close();

def lines = text.split("(\r\n|\r|\n)", -1);

print lines

//for testing purpose
Map<String, String> rules = new HashMap<>();

/*
//String regex = "CR001 Description 1 Person 1 GREEN"
String regex = "20-03-10 13:12 CR008 - 199003122455 System Godkänt"
//String pattern1 = '([\\S\\s+]) Person ([\\S\\s])([\\S\\s])([\\S\\s]+)';
//String pattern1 = '([CR\\d]+) * Description ([\\S\\s]) Person ([\\S\\s])([\\S\\s]+)';
String pattern1 = '([\\S\\s]+) ([\\S\\s]+) ([CR\\d]+) - ([\\d]+) System ([\\S\\s]+)'
Pattern r1 = Pattern.compile(pattern1);
Matcher m1 = r1.matcher(regex);
String rule1 = ""
String outcome1 = ""
if (m1.find( )) {
   System.out.println("Found value: " + m1.group(0) );
   System.out.println("Found value: " + m1.group(1) ); //rule
   //rule1 = m1.group(1).replaceAll("\\s","")
   System.out.println("Found value: " + m1.group(2) );
   System.out.println("Found value: " + m1.group(3) ); //rule x
   rule1 = m1.group(1).replaceAll("\\s","")
   System.out.println("Found value: " + m1.group(4) ); //outcome
   //outcome1 = m1.group(4).replaceAll("\\s","")
   System.out.println("Found value: " + m1.group(5) ); //outcome x
   outcome1 = m1.group(4).replaceAll("\\s","")
   rules.put(rule1, outcome1)
   //outcome = outcome.replaceAll("\\s","");
   if (outcome1.equals("YELLOW")){
	   logger.markWarning("value is wrong should be GREEN")
   }
}else {
   System.out.println("NO MATCH");
}
for (Map.Entry<String, String> entry : rules.entrySet()){
	String k = entry.getKey();
	String v = entry.getValue();
	System.out.println("Key: " + k + ", Value: " + v);
}
//for testing purpose
*/

//String pattern = '^.*Person ([\\S\\s])([\\S\\s])([\\S\\s]+)';
//String pattern = '([CR\\d]+) * Description ([\\S\\s]) Person ([\\S\\s])([\\S\\s]+)';
String pattern = '([\\S\\s]+) ([\\S\\s]+) ([CR\\d]+) - ([\\d]+) System ([\\S\\s]+)'
String searchString = "Avslag"
String rule = ""
String outcome = ""
Map<String, String> rulesOutcomes = new HashMap<>();

// Create a Pattern object
Pattern r = Pattern.compile(pattern);
// Now create matcher object.
for(String line:lines){
	Matcher m = r.matcher(line);
	if (m.find( )) {
	   System.out.println("Found value: " + m.group(0) );
	   System.out.println("Found value: " + m.group(1) ); //rule
	   //rule1 = m1.group(1).replaceAll("\\s","")
	   System.out.println("Found value: " + m.group(2) );
	   System.out.println("Found value: " + m.group(3) ); //rule x
	   rule = m.group(3).replaceAll("\\s","")
	   System.out.println("Found value: " + m.group(4) ); //outcome
	   //outcome1 = m1.group(4).replaceAll("\\s","")
	   System.out.println("Found value: " + m.group(5) ); //outcome x
	   outcome = m.group(5).replaceAll("\\s","")
	   rulesOutcomes.put(rule, outcome)
	   if (m.group(5).replaceAll("\\s","").equals(searchString)){
		   logger.markFailed("value is wrong should be Godkänt")
	   }
	}else {
	   System.out.println("NO MATCH");
	}
}

//define map where are expected key and value pair
Map<String, String> expectedValues = new HashMap<>();
expectedValues.put("CR007","Godkänt")
expectedValues.put("CR008","Avslag")

//compare maps
assertTrue("Maps should be unequal", MapDiffUtil.validateEqual(rulesOutcomes, expectedValues, "map1", "map2"));
assertFalse("Maps should be unequal", MapDiffUtil.validateEqual(rulesOutcomes, expectedValues, "map1", "map2"));

/*
for (Map.Entry<String, String> entry : rulesOutcomes.entrySet()){
	String k = entry.getKey().replaceAll("\\s","");
	String v = entry.getValue().replaceAll("\\s","");
	System.out.println("Key: " + k + ", Value: " + v);
}
*/
//Map<String, String> differences = new HashMap<>();

//def ret = valuesEquals(rulesOutcomes, expectedValues)
//println ret



//differences = getDiff2(rulesOutcomes, expectedValues)

/*for (Map.Entry<String, String> entry : differences.entrySet()){
	String key = entry.getKey().replaceAll("\\s","");
	String val = entry.getValue().replaceAll("\\s","");
	System.out.println("Key: " + key + ", Value: " + val);
}*/

//doDifference(rulesOutcomes, expectedValues)


//Set<String> difference = new HashSet<String>(rulesOutcomes);
//difference.removeAll(expectedValues);
//println difference

/*
Iterator iter = rulesOutcomes.entrySet().iterator();

while(iter.hasNext())
{
	  Map.Entry entry = (Map.Entry)iter.next();
	  String key = entry.getKey();
	  if(expectedValues.get(key) == null)
	  {
		 System.out.println(entry.getKey()+" value =="+entry.getValue());
	  }
}

static <V extends Comparable<V>>

boolean valuesEquals(Map<?,V> map1, Map<?,V> map2) {
	List<V> values1 = new ArrayList<V>(map1.values());
	List<V> values2 = new ArrayList<V>(map2.values());
	Collections.sort(values1);
	println values1
	Collections.sort(values2);
	println values2
	return values1.equals(values2);
}


public static Map<String,Object> getDiff2(Map<String,Object> mapA, Map<String,Object> mapB) {
	Map<String,Object> diff = new HashMap<String,Object>();
	diff.putAll(mapA);
	for (Map.Entry<String, String> entry : mapB.entrySet()){
		if (diff.containsValue(entry.getValue()))
			diff.remove(entry.getValue());
		else
			diff.put(entry.getValue(), mapB.get(entry.getValue()));
	}
	return diff;
 }


public static Map<String,Object> getDiff(Map<String,Object> mapA, Map<String,Object> mapB) {
   Map<String,Object> diff = new HashMap<String,Object>();
   diff.putAll(mapA);
   for (String s: mapB.keySet()) {
       if (diff.containsKey(s))
           diff.remove(s);
       else
           diff.put(s, mapB.get(s));
   }
   return diff;
}


private static <K, V> void doDifference(
	Map<? extends K, ? extends V> left, Map<? extends K, ? extends V> right,
	Equivalence<? super V> valueEquivalence,
	Map<K, V> onlyOnLeft, Map<K, V> onlyOnRight, Map<K, V> onBoth,
	Map<K, MapDifference.ValueDifference<V>> differences) {
  for (Entry<? extends K, ? extends V> entry : left.entrySet()) {
	K leftKey = entry.getKey();
	V leftValue = entry.getValue();
	if (right.containsKey(leftKey)) {
	  V rightValue = onlyOnRight.remove(leftKey);
	  if (valueEquivalence.equivalent(leftValue, rightValue)) {
		onBoth.put(leftKey, leftValue);
	  } else {
		differences.put(
			leftKey, ValueDifferenceImpl.create(leftValue, rightValue));
	  }
	} else {
	  onlyOnLeft.put(leftKey, leftValue);
	}
  }
}
*/

/*
println content
int ind = 0
for (String s : content){
	
	if (s.contains("YELLOW")){
		
		//logger.markFailed("value is wrong ")
		logger.markWarning("value is wrong should be GREEN")
	}
}
*/
/**
 * Map comparison with detailed log messages
 */
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
	