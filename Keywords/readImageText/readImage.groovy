package readImageText

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

//from .jar file
import com.image.txt.GetImageText.*;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import internal.GlobalVariable

public class readImage {

	@Keyword
	public String readImageText(String imPath){

		String imagePath = imPath;
		com.image.txt.GetImageText image = new com.image.txt.GetImageText();
		String res = image.getImageText(imagePath);
		//image.getText(imagePath);
		return res;
	}

	@Keyword
	public BufferedImage resized(BufferedImage bufImage,int x, int y){
		com.image.txt.GetImageText image = new com.image.txt.GetImageText();
		BufferedImage resized = image.scaleImage(bufImage, x, y);
		return resized;
	}

	@Keyword
	public BufferedImage convert(BufferedImage bufImage){
		com.image.txt.GetImageText image = new com.image.txt.GetImageText();
		BufferedImage convert = image.convertImage(bufImage)
		return convert;
	}
}
