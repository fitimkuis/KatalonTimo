import static org.junit.Assert.*;

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder

String a = "9 999 905.70"
String b = "9 999 905.70"

assertEquals(a, b);

assert a == b

Charset charset = StandardCharsets.UTF_8;
byte[] byteArrrayA = a.getBytes(charset);
byte[] byteArrrayB = b.getBytes(charset);
System.out.println("Text [Byte Format] : " + byteArrrayA);
System.out.println("Text [Byte Format] : " + byteArrrayB);
System.out.println(Arrays.equals(byteArrrayA, byteArrrayB));


//assertArrayEquals(new byte[] { -2, -1, 0, 72, 0, 101, 0, 108, 0, 108, 0,111, 0, 32, 0, 87, 0, 111, 0, 114, 0, 108, 0, 100, 0, 33 },	byteArrray);

String inputString = "Hello World!";
//byte[] byteArrray = a.getBytes();

String json = '''
{
    "name": "sampleConfiguration",
    "description": "SampleDesc",
    "version": "1.0",
    "parameters": [
    {
        "name": "sampleParameter",
        "description": "parameter description",
        "value": "20",
        "enabled": "1"
    },
    {
        "name": "items",
        "description": "parameter with subparameters",
        "value":[
            {
                "name": "item",
                "description": "nested parameter",
                "value": "13"
            },
            {
                "name": "item",
                "description": "nested parameter 2",
                "value": "TEST"
            }
        ]
    }
]}'''

def jsonPath = System.getProperty("user.dir")+"\\Include\\json\\test.json";

def jsonFile = new File(jsonPath)
def slurper = new JsonSlurper()
def data = slurper.parse(jsonFile)

assert data instanceof Map  //result is Map
println data //print json request
println data.quiz.sport.q1.answer
assert data.quiz.sport.q1.answer == 'Huston Rocket'

//https://groovy-lang.org/json.html

def json3 = JsonOutput.toJson(data) //convert to json format
println json3

println(JsonOutput.prettyPrint(json3).stripIndent()) //print out as json format

jsonToXml(json)
//jsonToXml2(json3)

//String jsonVal = "\'''"+data+"\'''";

//String value = "\'''Ram\'''";
//System.out.println("Value - " + value );

//String dat = "'''"+data+"'''"

public void jsonToXml(String json){

	def xml = new JsonSlurper().parseText(json).with { j ->
		new StringWriter().with { sw ->
			new MarkupBuilder(sw)."$name"(version: version, description:description) {
				params {
					parameters.each { p ->
						if(p.value instanceof List) {
							"$p.name"(description:p.description) {
								p.value.each { v ->
									"$v.name"(description: v.description, v.value)
								}
							}
						}
						else {
							"$p.name"(description:p.description, p.value)
						}
					}
				}
			}
			sw.toString()
		}
	}
	
	println xml
}

public void jsonToXml2(String json){
	
		def xml = new JsonSlurper().parseText(json).with { j ->
			new StringWriter().with { sw ->
				new MarkupBuilder(sw)."$quiz"(maths: maths, q1:q1) {
					params {
						options.each { p ->
							if(p.answer instanceof List) {
								"$p.name"(description:p.description) {
									p.value.each { v ->
										"$v.name"(description: v.description, v.value)
									}
								}
							}
							else {
								"$p.name"(description:p.description, p.value)
							}
						}
					}
				}
				sw.toString()
			}
		}
		
		println xml
	}