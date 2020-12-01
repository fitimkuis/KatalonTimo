package com.check.link

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindBrokenLinksDemo {

	@Keyword
	public void findPageBrokenLInks(WebDriver driver, String pageUrl){

		String testUrl = pageUrl;

		HttpURLConnection urlCon = null;

		int statusCode = 200; //It means in general URL is OK

		//System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");

		//WebDriver driver  = new ChromeDriver();

		driver.get(testUrl);

		driver.manage().window().maximize();

		Thread.sleep(2000);

		List<WebElement> linkList = driver.findElements(By.tagName("a"));
		List<String> validURL = new ArrayList<String>();
		List<String> brokenURL = new ArrayList<String>();

		for(WebElement e : linkList){
			String urls = e.getAttribute("href");


			if(!(urls==null) && !urls.isEmpty()){

				//Look for the same domain URL only

				if(urls.startsWith(testUrl)){

					try{
						URL url = new URL(urls);
						urlCon = (HttpURLConnection)(url.openConnection());
						urlCon.setRequestMethod("HEAD");
						urlCon.connect();
						statusCode = urlCon.getResponseCode();
						if(statusCode>=400){
							System.out.println("This is client side error, hence, page is broken "+
									"\n Broke URL is- "+url+
									" \n Its status code is- "+statusCode);
							brokenURL.add(urls);
						}else if(statusCode>=500){
							System.out.println("This is server side error, hence, page is broken "+
									"\n Broke URL is- "+url+
									" \n Its status code is- "+statusCode);
							brokenURL.add(urls);
						}else {
							System.out.println("This is valid URL- "+urls);
							validURL.add(urls);

						}
					}catch(MalformedURLException e0){
						e0.printStackTrace();
					}catch(IOException e1){
						e1.printStackTrace();
					}catch(Exception e3){
						e3.printStackTrace();
					}
				}
			}

		}

		driver.close();
		driver.quit();

	}
}
