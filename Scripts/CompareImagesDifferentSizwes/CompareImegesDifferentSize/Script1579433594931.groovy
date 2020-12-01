import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException;
import com.kms.katalon.core.exception.StepFailedException;
import com.kms.katalon.core.logging.ErrorCollector;
//import com.kms.katalon.core.logging.KeywordLogger;
import com.kms.katalon.core.util.KeywordUtil;


KeywordUtil logger = new KeywordUtil()

def img1 = RunConfiguration.getProjectDir() + "/images/Lenna100.jpg"
def img2 = RunConfiguration.getProjectDir() + "/images/Lenna101.jpg"

File f1 = new File(img1);
File f2 = new File(img2);

def im1 = f1.getName()
def im2 = f2.getName()

def stringComp = Compare.processImage(img1, img2);
if (stringComp.equals("true")){
	println ("images "+im1 +" and "+im2+" are equal sizes, "+stringComp)
	logger.markPassed("images "+im1 +" and "+im2+" are equal sizes, "+stringComp);
}
else{
	println ("images "+im1 +" and "+im2+" are not equal sizes, "+stringComp)
	logger.markWarning("images "+im1 +" and "+im2+" are not equal sizes, "+stringComp);
}

img1 = RunConfiguration.getProjectDir() + "/images/Lenna100.jpg"
img2 = RunConfiguration.getProjectDir() + "/images/Lenna100.jpg"

stringComp = Compare.processImage(img1, img2);
if (stringComp.equals("true")){
	println ("images "+im1 +" and "+im2+" are equal sizes, "+stringComp)
	logger.markPassed("images "+im1 +" and "+im2+" are equal sizes, "+stringComp);
}
else{
	println ("images "+im1 +" and "+im2+" are not equal sizes, "+stringComp)
	logger.markWarning("images "+im1 +" and "+im2+" are not equal sizes, "+stringComp);
}

public class Compare {
	
		static String processImage(String imag1, String imag2) {
	
			String file1 = imag1;
			String file2 = imag2;
	
			Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
			Image image2 = Toolkit.getDefaultToolkit().getImage(file2);
	
			try {
	
				PixelGrabber grab1 =new PixelGrabber(image1, 0, 0, -1, -1, false);
				PixelGrabber grab2 =new PixelGrabber(image2, 0, 0, -1, -1, false);
	
				int[] data1 = null;
	
				if (grab1.grabPixels()) {
					int width = grab1.getWidth();
					int height = grab1.getHeight();
					data1 = new int[width * height];
					data1 = (int[]) grab1.getPixels();
				}
	
				int[] data2 = null;
	
				if (grab2.grabPixels()) {
					int width = grab2.getWidth();
					int height = grab2.getHeight();
					data2 = new int[width * height];
					data2 = (int[]) grab2.getPixels();
				}
	
				//System.out.println("Pixels equal: " + java.util.Arrays.equals(data1, data2));
				//return "Pixels equal: " + java.util.Arrays.equals(data1, data2)
				return java.util.Arrays.equals(data1, data2)
	
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
}