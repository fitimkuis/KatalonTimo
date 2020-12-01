import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def ssnDir = RunConfiguration.getProjectDir() + "/ssn-python"

def count = CustomKeywords.'postgresUtil.postgresHandler.getRowCount'()
int x = 1;
for (int i = 0;i < count; i++){
	
	Runtime.getRuntime().exec("python "+ssnDir+"/ssn.py")
	
	WebUI.delay(3)
	
	//get ssn from file
	println ssnDir
	File file = new File(ssnDir+"/ssn.txt");
	BufferedReader br = new BufferedReader(new FileReader(file));
	String st;
	String data = ""
	while ((st = br.readLine()) != null){
	  System.out.println(st);
	  data = st
	}
	
	/*
	 * parameters:
	 * data column name
	 * index nro to update
	 * update value
	*/
	CustomKeywords.'postgresUtil.postgresHandler.updateDbColumn'("ssn", x, data)
	x++
}
