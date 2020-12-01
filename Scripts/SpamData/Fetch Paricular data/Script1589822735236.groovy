import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.google.common.base.Functions
import com.kms.katalon.core.testdata.TestData as TestData

import internal.GlobalVariable as GlobalVariable

def input = "Mark"

println(GlobalVariable.URL_Application)

println("${GlobalVariable.URL_Application}")

def v = "${GlobalVariable.URL_Application}"

println(v)

TestData data = findTestData('Data Files/SpamData/FetchParticularData')


	//data.changeSheet('Entity Types')


boolean found = false
	
	for (int excelRow : (1 ..  data.getRowNumbers())) {
		
		for (int columns :(1 .. data.getColumnNumbers())){
			
			if ((data.getValue(columns, excelRow).equals(input)) && (data.getValue('IsDefault', excelRow).equals('1')) ){
				
				println("English found!!!"+columns+" "+excelRow+" "+data.getValue('IsDefault', excelRow))
				println("Name: "+data.getValue('Name', excelRow)+" Country: "+data.getValue('Country', excelRow)+ " CountryId: "+data.getValue('CountryId', excelRow)+" EntityType: "+
					data.getValue('EntityType', excelRow)+" IsDefault: "+data.getValue('IsDefault', excelRow))
				found=true
				break
			}
			else{
				println("English not found!!!")
			}
			
		}
		if(found){
			break
		}
	}
	

	
	def ret = getELValue("The current date is ${todaysDate()}, and the current timestamp is ${timestamp()}")

	public static String getELValue(final String value) {
		int occurrence = value.indexOf("\${");
		if(occurrence >= 0) {
			String expression = value.substring(occurrence, value.indexOf("}") + 1);
			String function = expression.substring(2, expression.indexOf("("));
			String[] arguments = expression.substring(expression.indexOf("(") + 1, expression.indexOf(")")).split(",");
			String replacement = "INVALID_FUNCTION";
			switch(function) {
				case "randomAlphanumeric":''
					replacement = Functions.randomAlphanumeric(Integer.parseInt(arguments[0]));
					break;
				case "randomString":
					replacement = Functions.randomString(Integer.parseInt(arguments[0]));
					break;
				case "randomNumber":
					replacement = Functions.randomNumber(Integer.parseInt(arguments[0]));
					break;
				case "randomCharSequence":
					replacement = Functions.randomCharSequence(Integer.parseInt(arguments[0]), arguments[1]);
					break;
				case "todaysDate":
					replacement = Functions.todaysDate(Integer.parseInt(arguments[0]));
					break;
				case "timestamp":
					replacement = Functions.timestamp();
					break;
			}
			return getELValue(value.replace(expression, replacement));
		}
		return value;
	}

		
		/*
			'IsDefault in loop'
			for (int selectval : (1..data.getRowNumbers())) {
			selectval1 = data.getValue('IsDefault', selectval)
			println "value with 1    " +selectval1
		
			'If is default is 1'
				if(selectval1=="1"){
					ent = data.getValue('EntityType',selectval )
			
					println "entttttttt " +ent
			break
				}
			}
		}*/
		
	