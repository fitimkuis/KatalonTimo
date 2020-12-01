import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


String testDateString1 = "02/04/2014";
String testDateString2 = "02-04-2014 23:37:50";
String testDateString3 = "02-04-2014";
String testDateString4 = "04 02, 2014";
String testDateString5 = "Thu, 02 Apr 2014";
String testDateString6 = "Thu, 02 Apr 2014 23:37:50";

def pattern1 = "dd/MM/yyyy"
def pattern2 = "dd-MM-yyyy HH:mm:ss"
def pattern3 = "dd-MM-yyyy"
def pattern4 = "MM dd, yyyy"
def pattern5 = "EEE',' dd MMM yyyy"
def pattern6 = "EEE',' dd MMM yyyy HH:mm:ss"


DateTimeFormatter df1 = DateTimeFormat.forPattern("EEE',' dd MMM yyyy HH':'mm':'ss zzz").withZoneUTC();
DateTime dtnew = df1.withLocale(Locale.ENGLISH).parseDateTime("Fri, 24 Jun 2016 13:23:02 GMT");
println dtnew


DateTimeFormatter dtf1 = DateTimeFormat.forPattern("EEE',' dd MMM yyyy HH':'mm':'ss zzz").withZoneUTC();
String dateString = "Fri, 24 Jun 2016 13:23:02 GMT";
DateTime date;
date = dtf1.withLocale(Locale.ENGLISH).parseDateTime(dateString);
System.out.println(date);
String parsedString = date.toString()
DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZoneUTC();
org.joda.time.LocalDate d = org.joda.time.LocalDate.parse(parsedString, formatter)
System.out.println(d);
DateTime dateTime = new DateTime(d.toString());
System.out.println(dateTime);
DateTime datet = DateTime.parse(d.toString());
System.out.println(datet);
def oldestDate = date.toString()
println oldestDate
String odate = oldestDate.substring(0, 19);
println odate.replace("T"," ")


//2016-06-24T13:23:02.000Z
/*SimpleDateFormat formatter4, FORMATTER;
formatter4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
String oldDate = "2008-03-14T11:53:31.200+05:00";
Date date4 = formatter4.parse(oldestDate);
println date4
//Date date4 = formatter4.parse(oldDate.substring(0, 26) + oldDate.substring(27, 29));
FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
System.out.println("OldDate-->"+oldestDate);
System.out.println("NewDate-->"+FORMATTER.format(date4));*/


DateTimeFormatter dtf2 =
DateTimeFormat.forPattern("EEE',' dd MMM yyyy").withZoneUTC();
String dateString2 = "Fri, 24 Jun 2016";
DateTime date2;
date2 = dtf2.withLocale(Locale.ENGLISH).parseDateTime(dateString2);
System.out.println(date2);
String parsedString2 = date.toString()
DateTimeFormatter formatter2 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZoneUTC();
org.joda.time.LocalDate d2 = org.joda.time.LocalDate.parse(parsedString2, formatter2)
System.out.println(d2);

DateTimeFormatter dtf3 =
DateTimeFormat.forPattern("EEE',' dd MMM yyyy HH:mm:ss").withZoneUTC();
String dateString3 = "Fri, 24 Jun 2016 23:37:50";
DateTime date3;
date3 = dtf3.withLocale(Locale.ENGLISH).parseDateTime(dateString3);
System.out.println(date3);
String parsedString3 = date.toString()
DateTimeFormatter formatter3 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZoneUTC();
//DateTimeFormatter formatter3 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZoneUTC();
org.joda.time.LocalDate d3 = org.joda.time.LocalDate.parse(parsedString2, formatter3)
System.out.println(d3);

/*
DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEE',' dd MMM yyyy HH':'mm':'ss zzz").withZoneUTC();
DateTime date;
date = dateTimeFormatter.parseDateTime(str);
System.out.println(date);*/
//DateTime jodaTime = dateTimeFormatter.parseDateTime(str);
//DateTimeFormatter resultFormat = DateTimeFormat.forPattern("dd/MM/yyyy");

DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//format() method Formats a Date into a date/time string.
Date d1 = df.parse(testDateString1);
System.out.println("Date: " + d1);
System.out.println("Date in dd/MM/yyyy format is: "+df.format(d1));


def test1 = convertStringToDate(testDateString1,pattern1)
println ("test 1 "+test1)
def test2 = convertStringToDate(testDateString2,pattern2)
println ("test 2 "+test2)
def test3 = convertStringToDate(testDateString3,pattern3)
println ("test 3 "+test3)
def test4 = convertStringToDate(testDateString4,pattern4)
println ("test 4 "+test4)
def test5 = convertStringToDate(testDateString5,pattern5)
println ("test 5 "+test5)
//def test6 = convertStringToDate(testDateString6,pattern6)
//println ("test 6 "+test6)

//EEE MMM dd HH:mm:ss zzz yyyy
//Thu Jun 18 20:56:02 EDT 2009
public String convertStringToDate(String dateString, String pattern)
{
	Date date = null;
	String formatteddate = null;
	DateFormat df = null;
	//if(dateString.startsWith("Thu")){
	//	df = new SimpleDateFormat("EEE',' MMM dd yyyy", Locale.ENGLISH).parse(dateString);
	//}
	//else{
	df = new SimpleDateFormat(pattern);
	//}
	
	//DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	try{
		date = df.parse(dateString);
		formatteddate = df.format(date);
	}
	catch ( Exception ex ){
		System.out.println(ex);
	}
	return formatteddate;
}