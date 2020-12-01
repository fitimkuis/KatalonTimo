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

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


String key = "JavasEncryptDemo"; // 128 bit key
String randomVector = "RandomJavaVector"; // 16 bytes IV

String enCrypted = EncryptorDemo.encrypt(key, randomVector, "Anything you want to encrypt!")
println enCrypted

String deCrypted = EncryptorDemo.decrypt(key, randomVector, enCrypted);
println deCrypted


public class EncryptorDemo {
	
		public static String encrypt(String key, String randomVector, String value) {
			try {
				IvParameterSpec iv = new IvParameterSpec(randomVector.getBytes("UTF-8"));
				SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
				byte[] encrypted = cipher.doFinal(value.getBytes());
				//System.out.println("encrypted text: "  + Base64.encodeBase64String(encrypted));
				return Base64.encodeBase64String(encrypted);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
		public static String decrypt(String key, String randomVector, String encrypted) {
			try {
				IvParameterSpec iv = new IvParameterSpec(randomVector.getBytes("UTF-8"));
				SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
				byte[] originalText = cipher.doFinal(Base64.decodeBase64(encrypted));
				//System.out.println("decrypted text: "  + new String(originalText));
				return new String(originalText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}