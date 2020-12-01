import java.text.DecimalFormat
import java.time.Period

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.microsoft.sqlserver.jdbc.LocalDate


//create example table
//CustomKeywords.'postgresUtil.postgresHandler.createTable'()

List<String> userData = new ArrayList<>()
List<String> userDataTrailed = new ArrayList<>()
userData = CustomKeywords.'postgresUtil.postgresHandler.selectDataByUsername'("Gerlach")
//remove whitespaces from list
for(String values : userData){
    String[] parts = values.split(" ");
    userDataTrailed.add(parts[0]);
}
println("selected userdata: "+userDataTrailed)

//generate SSN
String ssnData = ""
def ssnDir = RunConfiguration.getProjectDir() + "/ssn-python"

Runtime.getRuntime().exec("python "+ssnDir+"/ssn.py")

WebUI.delay(3)

//get ssn from file
println ssnDir
File file = new File(ssnDir+"/ssn.txt");
BufferedReader br = new BufferedReader(new FileReader(file));
String st;
while ((st = br.readLine()) != null){
  System.out.println(st);
  ssnData = st
}

//style two to generate bird date
/*Random rand = new Random();
int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
long randomDay = minDay + rand.nextInt(maxDay - minDay);
LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
System.out.println(randomBirthDate);*/

com.github.javafaker.Faker faker = new com.github.javafaker.Faker();
List <String> data = new ArrayList<>()
Random r = new Random();
double random = new Random().nextDouble();
int lowerAge = 30;
int upperAge = 65;
int age = r.nextInt(upperAge-lowerAge) + lowerAge;

double lowerSalary = 30000;
double upperSalary = 65000;

double salary = r.nextDouble()*(upperSalary - lowerSalary) + lowerSalary;
DecimalFormat f = new DecimalFormat("##.00");

String s = String.valueOf(f.format(salary));
println s

String fakeName = faker.name().lastName()
String fakeCity = faker.address().city()

/*
//generate example data
for (int i = 0; i < 10; i++){
	String firsName = faker.name().firstName()
	data.add(firsName)
	int age = r.nextInt(upperAge-lowerAge) + lowerAge;
	data.add(String.valueOf(age))
	String city = faker.address().city();
	data.add(city)
	salary = r.nextDouble()*(upperSalary - lowerSalary) + lowerSalary;
	f = new DecimalFormat("##.00");
	//f.format(salary)
	data.add(String.valueOf(f.format(salary).replace(",",".")))
}
println data
println data.get(0)
String sally = data.get(3)
sally = sally.replace(",", ".");
//double salary2 = Double.valueOf(data.get(3))
println sally
*/
//update salary for id 1
//CustomKeywords.'postgresUtil.postgresHandler.updateSalary'(1, 200000.00)

//update all data for id 1
//CustomKeywords.'postgresUtil.postgresHandler.updateExactData'(1, "Sally", 45, "New York", 100000.00, ssnString)

/*
//get row count
int count = CustomKeywords.'postgresUtil.postgresHandler.getRowCount'()
//insert new rows to the database (10)
CustomKeywords.'postgresUtil.postgresHandler.insertIntoFromList'(data, count + 1)
*/
//select databases data
//CustomKeywords.'postgresUtil.postgresHandler.selectData'()


//update any value
//Object oValue = "12072002-015H"
/*
 * 1 = name
 * 2 = age
 * 3 = address
 * 4 = salary
 * 5 = ssn
 */
//CustomKeywords.'postgresUtil.postgresHandler.updateColumn'(2, 54, oValue)//2 update column age, 1 row id 1

//updat just added new column where all data are NULL
//CustomKeywords.'postgresUtil.postgresHandler.updateSsn'(ssnString)


//get row count
int count = CustomKeywords.'postgresUtil.postgresHandler.getRowCount'()
def newRow = count + 1
//insert new datarow
def counter = CustomKeywords.'postgresUtil.postgresHandler.insertIntoNewDataRow'(newRow, fakeName, age, fakeCity, salary, ssnData)
println counter

//get rowdata to list by index number
List<String> rowData = new ArrayList<>()
rowData = CustomKeywords.'postgresUtil.postgresHandler.getRowDataById'(newRow)
String fName = rowData.get(1)
int personAge = Integer.parseInt(rowData.get(2))
String add = rowData.get(3)
double sal = Double.parseDouble(rowData.get(4))
String personSsn = rowData.get(5)
println "datarow contains: name: "+fName+" age: "+personAge+" address: "+add+" salary: "+sal+" ssn: "+personSsn

//Update column data: String column name, int id, Object arg
Object oValue = r.nextInt(upperAge-lowerAge) + lowerAge;
CustomKeywords.'postgresUtil.postgresHandler.updateDbColumn'("age", newRow, oValue)


public LocalDate randomBirthday() {
	return LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 70))));
}

public String randomAlphaNumeric(int count) {
	
	String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) {
	int character = (int)(Math.random()*alphaNumericString.length());
	builder.append(alphaNumericString.charAt(character));
	}
	return builder.toString();
}

public String getRandomAlphNumeircString(int n) {
	// Get a n-digit multiplier of 10
	int maxDigit = (int) Math.pow(10, n - 2);
	Random random = new Random();
	/*
	 * Get a random character by getting a number from 0 t0 26 and then adding an
	 * 'A' to make it a character
	 *
	 */
	char randomCharacter = (char) (random.nextInt(26) + 'A');
	/*
	 * Add 1*maxDigit to ensure that the number is equals to or greater than minimum
	 * value nextInt() method will return the number between 0 and 9*maxDigit
	 */
	int randomNumber = 1 * maxDigit + random.nextInt(9 * maxDigit);
	return String.valueOf(randomCharacter) + randomNumber;
}