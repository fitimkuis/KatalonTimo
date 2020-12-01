import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;


String yamlContactPath = System.getProperty("user.dir")+"\\YAML\\contact.yaml";
String yamlUsersPath = System.getProperty("user.dir")+"\\YAML\\users.yaml";

YamlReader reader = new YamlReader(new FileReader(yamlContactPath));
Object object = reader.read();
System.out.println(object);
Map map = (Map)object;
System.out.println(map.get("address"));
System.out.println(map.get("name"));
System.out.println(map.get("phone numbers"));


reader2 = new YamlReader(new FileReader(yamlUsersPath));
Object object2 = reader2.read();
System.out.println(object2);
Map map2 = (Map)object2;
System.out.println(map2.get("url1"));
System.out.println(map2.get("user1"));
System.out.println(map2.get("pass1"));


