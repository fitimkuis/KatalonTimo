
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Pattern p = Pattern.compile('/^[0-9]{10}$/');

List<String> li = new ArrayList<>();
Set<String> dup = new HashSet<String>();
String inputStr = "{Test1:0563300111{Item:\"Name\",Number:0},Test2:{Item:\"Name1\",Number:21},\"0563300111\":{Item:\"NameP\",Number:0},\"0563300127\":{Item:\"NameP\",Number:21},\"0563300154\":{Item:\"NameP\",Number:22},\"0563300166\":{Item:\"NameP\",Number:22},\"0563300142\":{Item:\"NameP\",Number:99},\"0563300131\":{Item:\"Name2\",Number:18},\"0563300151\":{Item:\"Name2\",Number:18},\"0563300163\":{Item:\"Name2\",Number:0},\"0563300136\":{Item:\"Name3\",Number:0},\"0563300143\":{Item:\"Name3\",Number:0},\"0563300152\":{Item:\"Name3\",Number:21},\"0563300164\":{Item:\"Name3\",Number:22},\"0563300147\":{Item:\"Name3\",Number:99},\"0563300139\":{Item:\"pe\",Number:6},\"0563300170\":{Item:\"pe\",Number:7},\"0563300172\":{Item:\"pe\",Number:7},\"0563300173\":{Item:\"pe\",Number:7},\"0563300137\":{Item:\"Name\",Number:0},\"0563300144\":{Item:\"Name\",Number:0},\"0563300145\":{Item:\"Name\",Number:0},\"0563300146\":{Item:\"Name\",Number:0},\"0563300148\":{Item:\"Name\",Number:0},\"0563300165\":{Item:\"Name\",Number:21},\"0563300156\":{Item:\"Name\",Number:21},\"0563300157\":{Item:\"Name\",Number:21},\"0563300158\":{Item:\"Name\",Number:21},\"0563300159\":{Item:\"Name\",Number:21},\"0563300160\":{Item:\"Name\",Number:21},\"0563300153\":{Item:\"Name mac\",Number:21},\"0563300161\":{Item:\"Name mac\",Number:21},\"0563300168\":{Item:\"Name beta\",Number:21},\"0563300169\":{Item:\"Name mac beta\",Number:21},\"0563300140\":{Item:\"Name1\",Number:21},\"0563300162\":{Item:\"Name1\",Number:21},\"0563300167\":{Item:\"Name1\",Number:21},\"0563300149\":{Item:\"Name8\",Number:1},\"0563300150\":{Item:\"Name8\",Number:99}};)";
li = testRegEx(inputStr);
dup = searchDublicates(li);
System.out.println("DEBUG duplicate item "+dup);

Pattern p = Pattern.compile("\\d+");

/*
Matcher m = p.matcher('{Test1:{Item:"Name",Number:0},Test2:{Item:"Name1",Number:21},"0563300111":{Item:"NameP",Number:0},"0563300127":{Item:"NameP",Number:21},"0563300154":{Item:"NameP",Number:22},"0563300166":{Item:"NameP",Number:22},"0563300142":{Item:"NameP",Number:99},"0563300131":{Item:"Name2",Number:18},"0563300151":{Item:"Name2",Number:18},"0563300163":{Item:"Name2",Number:0},"0563300136":{Item:"Name3",Number:0},"0563300143":{Item:"Name3",Number:0},"0563300152":{Item:"Name3",Number:21},"0563300164":{Item:"Name3",Number:22},"0563300147":{Item:"Name3",Number:99},"0563300139":{Item:"pe",Number:6},"0563300170":{Item:"pe",Number:7},"0563300172":{Item:"pe",Number:7},"0563300173":{Item:"pe",Number:7},"0563300137":{Item:"Name",Number:0},"0563300144":{Item:"Name",Number:0},"0563300145":{Item:"Name",Number:0},"0563300146":{Item:"Name",Number:0},"0563300148":{Item:"Name",Number:0},"0563300165":{Item:"Name",Number:21},"0563300156":{Item:"Name",Number:21},"0563300157":{Item:"Name",Number:21},"0563300158":{Item:"Name",Number:21},"0563300159":{Item:"Name",Number:21},"0563300160":{Item:"Name",Number:21},"0563300153":{Item:"Name mac",Number:21},"0563300161":{Item:"Name mac",Number:21},"0563300168":{Item:"Name beta",Number:21},"0563300169":{Item:"Name mac beta",Number:21},"0563300140":{Item:"Name1",Number:21},"0563300162":{Item:"Name1",Number:21},"0563300167":{Item:"Name1",Number:21},"0563300149":{Item:"Name8",Number:1},"0563300150":{Item:"Name8",Number:99}};')
List<String> list = new ArrayList<>()

while(m.find()) {
	def num = m.group()
	if (num.length() > 9){
		list.add(num)
	}
}
println list
*/

public static List<String> testRegEx(String inputStr) {
	Pattern p = Pattern.compile("\\d+");
	Matcher m = p.matcher(inputStr);
	List<String> list = new ArrayList<>();

	while (m.find()) {
		String num = m.group();
		if (num.length() > 9) {
			list.add(num);
		}
	}
	System.out.println(list);
	return list;
}

public static Set<String> searchDublicates(List<String> list){
	final Set<String> setToReturn = new HashSet<String>();
	final Set<String> set1 = new HashSet<String>();

	for (String s : list) {
		if (!set1.add(s)) {
			setToReturn.add(s);
		}
	}
	return setToReturn;
}

Map<String,String> queryParameters(String urlString) {
	URL url = new URL(urlString)
	// get all query params as list
	def queryParams = url.query?.split('&') // safe operator for urls without query params
	// transform the params list to a Map spliting
	// each query param
	if (queryParams != null) {
		return queryParams.collectEntries { param -> param.split('=').collect { URLDecoder.decode(it,'UTF-8') }}
	} else {
		return [:]
	}
}

String queryParameter(String urlString, String name) {
	def mapParams = queryParameters(urlString)
	return mapParams[name]
}

String line = "2013-10-27T13:00:00.325234Z";
DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
LocalDateTime time = LocalDateTime.parse(line, dateFormat);
Timestamp ts = Timestamp.valueOf(time);
System.out.println(ts);

/*String s = "2017-12-29-05.04.18.104071";
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");//Yes, it works without .SSSSSS
Timestamp ts = new Timestamp(sdf.parse(s).getTime());
//ts.setNanos(Integer.valueOf(s.substring(20)) * 1000);
System.out.println(ts.toString());*/

String input = 'http://somewebsite.com/path?nonce=17&cmac=2e1224422baa44134e87cdff2303d417'
String nonce = queryParameter(input,'nonce')
String cmac  = queryParameter(input,'cmac')

println "queryParameters is ${queryParameters(input)}"
println "nonce is ${nonce}"
println "cmac is ${cmac}"

WebUI.verifyMatch(nonce, '[0-9]*\\d', true)
WebUI.verifyMatch(cmac , '[a-f0-9]{32}', true)
