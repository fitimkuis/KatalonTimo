import java.awt.Dimension
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


InputStream image = new FileInputStream("C:\\Users\\fitim\\Desktop\\data\\screenshots\\test-image2.png");
bytes = IOUtils.toByteArray(image);
print bytes

captureImage()

/*WebUI.openBrowser('')
KeywordLogger log = new KeywordLogger()
log.logInfo('yourMsg')
WebUI.comment('myMessage')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
WebUI.click(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/a_MakeAppointment'))*/

//screen2image s2i = new screen2image();
//while(true)
//{
	//robo();
	//Thread.sleep(10000);
//}
	//partial()


	
WebUI.closeBrowser()

public void partial() throws Exception{
	Robot robot = new Robot();
	String format = "jpg";
	String fileName = "PartialScreenshot." + format;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
	Calendar now = Calendar.getInstance();
				 
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Rectangle captureRect = new Rectangle(100, 100, screenSize.width / 2, screenSize.height / 2);
	BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
	ImageIO.write(screenFullImage, format, new File("C:\\Users\\fitim\\Desktop\\data\\screenshots\\"+formatter.format(now.getTime())+fileName));
}

public void captureImage(){
	WebUI.openBrowser("https://www.google.fi/")
	WebElement image = DriverFactory.getWebDriver().findElement(By.xpath("//*[@id='hplogo']/a/img"))
	//WebUI.openBrowser("https://d1h3p5fzmizjvp.cloudfront.net/themes/katalon_4/images/katalon_template_1809/logo@2x.png")
	//WebElement image = DriverFactory.getWebDriver().findElement(By.xpath("//img"))
	String imgSrc = image.getAttribute("src")
	
	URL imageURL = new URL(imgSrc)
	BufferedImage saveImage = ImageIO.read(imageURL)
	
	ImageIO.write(saveImage, "png", new File("C:\\Users\\fitim\\Desktop\\data\\screenshots\\test-image2.png"))
}


public void robo() throws Exception
{
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
	Calendar now = Calendar.getInstance();
	Robot robot = new Robot();
	BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	ImageIO.write(screenShot, "JPG", new File("C:\\Users\\fitim\\Desktop\\data\\screenshots\\"+formatter.format(now.getTime())+".jpg"));
	System.out.println(formatter.format(now.getTime()));
}