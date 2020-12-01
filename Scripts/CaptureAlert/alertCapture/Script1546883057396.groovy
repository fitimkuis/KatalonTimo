import java.awt.Graphics2D
import java.awt.Image;
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage as BufferedImage
import java.text.SimpleDateFormat

import javax.imageio.ImageIO as ImageIO

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

KeywordLogger logger = new KeywordLogger()

//Run the Test from TestSuite
WebUI.openBrowser('http://www.google.com')
//WebUI.maximizeWindow()

String path = WebUI.takeScreenshot()

//This line will print the path and name of the screenshot file.’
System.err.println(path)

WebUI.delay(2)

//Use Javascript to make an alert
WebUI.executeJavaScript("alert(\'*****This is an alert***** \')", null)

//Capture the alert
BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))
ImageIO.write(image, 'png', new File(path))
WebUI.delay(2)


String prefix = new SimpleDateFormat("yyyy_MM_dd_HH_mm_").format(new Date());

//TODO testing
//String testImagePath = "C:\\KatalonStudio\\DataDrivenTestMaster\\images\\google.png";
//File input = new File(testImagePath);

File input = new File(path);
BufferedImage bufImage = ImageIO.read(input);

WebUI.delay(3)
String scaledConvertedImagePath = "C:\\Users\\fitim\\KatalonFromGitHub\\KatalonProject\\images\\"+prefix+"Google.png";
//BufferedImage scaledBufImage = CustomKeywords.'readImageText.readImage.resized'(bufImage,544, 184); //scale image testing
BufferedImage scaledBufImage = CustomKeywords.'readImageText.readImage.resized'(bufImage,1066, 468) //scale image
BufferedImage convertBufImage = CustomKeywords.'readImageText.readImage.convert'(scaledBufImage)//convert to gray
ImageIO.write(convertBufImage, 'png', new File(scaledConvertedImagePath))//save for new path

def res = CustomKeywords.'readImageText.readImage.readImageText'(scaledConvertedImagePath)//read text
System.out.print(res);
//logger.logDebug(res)

WebUI.delay(2)

public BufferedImage getScaledImage(BufferedImage img, int wt, int ht) {
	
	Image tmp = img.getScaledInstance(wt, ht, img.SCALE_SMOOTH);
	BufferedImage resized = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2d = resized.createGraphics();
	g2d.drawImage(tmp, 0, 0, null);
	g2d.dispose();
	return resized;
}