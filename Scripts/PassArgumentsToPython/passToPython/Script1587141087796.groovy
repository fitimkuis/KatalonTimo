/*import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.__builtin__;
import org.python.util.PythonInterpreter;*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.joining;

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.stream.IntStream;

import com.kms.katalon.core.configuration.RunConfiguration

def eurotext = RunConfiguration.getProjectDir() + "/Include/tessdata/eurotext.png"
def filename = RunConfiguration.getProjectDir() + "/Include/tessdata/image.png"

def text = CustomKeywords.'demo.PythonKeywords.getimagetext'(eurotext)
for (String a in text){
	println(a)
}
//println text[0]

text = CustomKeywords.'demo.PythonKeywords.getimagetext'(filename)
println text[0]


List<String> list = new ArrayList<String>();
list.add("AA-10");
list.add("AA-1");
list.add("AA-2");
list.add("AA-2 (1)");

//String result = list.join(",")

StringBuilder sb = new StringBuilder()
for (String item: list) {
	if (sb.length() > 0 ) {
	  sb.append(", ");
	}
   sb.append(item)
}
String result = sb.toString()

def sum = CustomKeywords.'demo.PythonKeywords.helloWorld'(11, 15)
print (sum[2]+"\n")
Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher(sum[2]);
    while (matcher.find()) {
        System.out.print("Start index: " + matcher.start());
        System.out.print(" End index: " + matcher.end() + " ");
        System.out.println(matcher.group());
    }

def ress = CustomKeywords.'demo.PythonKeywords.sortList'(result)
print (ress[0]+"\n")

def sorted
pattern = Pattern.compile("([\\S\\s]+)");
matcher = pattern.matcher(ress[0]);
while (matcher.find()) {
	System.out.print("Start index: " + matcher.start());
	System.out.print("End index: " + matcher.end() + " ");
	sorted = matcher.group()
	System.out.println(sorted);
}
def arr = sorted.replace("[","").replace("]","").replaceAll("'","").split(",");

for(String s: arr){
	println s.trim()
}


CustomKeywords.'demo.PythonKeywords.fibionacciSequence'(10)




//CustomKeywords.'demo.PythonKeywords.compareTwoCsv'(null, null)

/*
def pythonDir = RunConfiguration.getProjectDir() + "/python"

Runtime.getRuntime().exec("python "+pythonDir+"/test.py",""+result)

ProcessBuilder pb = new ProcessBuilder("C:/Users/fitim/AppData/Local/Programs/Python/Python37/python",pythonDir+"/test.py",""+result);
Process p = pb.start();
BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));

System.out.println(".........start   process.........");
String line = "";
while ((line = bfr.readLine()) != null){
	System.out.println("Python Output: " + line);
}*/


RuleBasedCollator localRules = (RuleBasedCollator) Collator.getInstance();
String extraRules = IntStream.range(0, 100).mapToObj(String.&valueOf).collect(joining(" < "));
RuleBasedCollator c = new RuleBasedCollator(localRules.getRules() + " & " + extraRules);

//List<String> a = asList("1-2", "1-02", "1-20", "10-20", "fred", "jane", "pic01", "pic02", "pic02a", "pic 5", "pic05", "pic   7", "pic100", "pic100a", "pic120", "pic121");
shuffle(list);
list.sort(c);
System.out.println(list);

def a = ['AA-1', 'AA-2', 'AA-2 (1)', 'AA-10', 'BB-7']
def b = list
def r = CustomKeywords.'demo.PythonKeywords.getDiff'(a, b)
print (r[0]+"\n")

/*
def arguments = ["sort.py", "arg1", "arg2", "arg3"];
PythonInterpreter.initialize(System.getProperties(), System.getProperties(),arguments);
org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
StringWriter out = new StringWriter();
python.setOut(out);
python.execfile("sort.py");
String outputStr = out.toString();
System.out.println(outputStr);*/