import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import java.util.Scanner;

//Verify if checked data of checkpoint matches their source data.
WebUI.verifyCheckpoint(findCheckpoint('Checkpoints/Checkpoint'), false)

String hostname =  '192.168.0.12'
boolean reachable = InetAddress.getByName(hostname).isReachable(10);
if(reachable){
	print '*************NETWORK IS NOT VPN CONNECTED*************'
}
else{
	print '*************NETWORK IS VPN CONNECTED*************'
}


BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String accStr;
net.andreinc.mockneat.types.enums.CreditCardType ct

System.out.println("Enter your card name: ");
ct = br.readLine();
println ct.toString()

for (net.andreinc.mockneat.types.enums.CreditCardType cType : net.andreinc.mockneat.types.enums.CreditCardType.values()) {
  // do what you want
	String cardNum = CustomKeywords.'creditcard.getCardNumber.generateCardNumber'(cType)
	println("*****DEBUG**** "+cType.toString()+" Card Number "+cardNum)
}

String cardNum = CustomKeywords.'creditcard.getCardNumber.generateCardNumber'(ct)
println("*****DEBUG**** "+ct.toString()+" Card Number "+cardNum)