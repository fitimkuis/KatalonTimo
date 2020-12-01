import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable


println execCmd('Netsh WLAN show interfaces')

public static void showWlanId() throws java.io.IOException {
	ArrayList<String>ssids=new ArrayList<String>();
	ArrayList<String>signals=new ArrayList<String>();
	ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "netsh wlan show all");
	builder.redirectErrorStream(true);
	Process p = builder.start();
	BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	String line;
	while (r.read()!=-1) {
		line = r.readLine();
		if (line.contains("SSID")||line.contains("Signal")){
			if(!line.contains("BSSID"))
				if(line.contains("SSID")&&!line.contains("name")&&!line.contains("SSIDs"))
				{
					line=line.substring(8);
					ssids.add(line);
	
				}
				if(line.contains("Signal"))
				{
					line=line.substring(30);
					signals.add(line);
	
				}
	
				if(signals.size()==7)
				{
					break;
				}
	
		}
	
	}
	for (int i=0;i<ssids.size();i++)
	{
		System.out.println("SSID name == "+ssids.get(i)+"   and its signal == "+signals.get(i)  );
	}
}

public static String execCmd(String cmd) throws java.io.IOException {
	java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
	return s.hasNext() ? s.next() : "";
}