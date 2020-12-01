package compare.images

import java.awt.image.BufferedImage
import java.awt.image.DataBuffer
import java.text.SimpleDateFormat

import javax.imageio.ImageIO

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class CompareTwoImages {

	@Keyword
	public float compareImage(File fileA, File fileB) {

		/*BufferedImage img1 = ImageIO.read(new File("C:/KatalonStudio/KatalonProject/images/Lenna1002.jpg"));
		 BufferedImage img2 = ImageIO.read(new File("C:/KatalonStudio/KatalonProject/images/Lenna100.jpg"));
		 double p = getDifferencePercent(img1, img2);
		 System.out.println("******diff percent:****** " + p);*/

		float percentage = 0;

		try {
			// take buffer data from both image files //
			BufferedImage biA = ImageIO.read(fileA);
			DataBuffer dbA = biA.getData().getDataBuffer();
			int sizeA = dbA.getSize();
			BufferedImage biB = ImageIO.read(fileB);
			DataBuffer dbB = biB.getData().getDataBuffer();
			int sizeB = dbB.getSize();
			int count = 0;
			// compare data-buffer objects //
			if (sizeA == sizeB) {

				for (int i = 0; i < sizeA; i++) {

					if (dbA.getElem(i) == dbB.getElem(i)) {
						count = count + 1;
					}

				}
				percentage = (count * 100) / sizeA;
			} else {
				System.out.println("Both the images are not of same size");
			}

		} catch (Exception e) {
			System.out.println("Failed to compare image files ...");
		}
		return percentage;
	}

	@Keyword
	public double compareImagePercentages(String pathA, String pathB) {

		BufferedImage img1 = ImageIO.read(new File(pathA));
		BufferedImage img2 = ImageIO.read(new File(pathB));

		double p = getDifferencePercent(img1, img2);
		//System.out.println("******diff percent:****** " + p);
		return p
	}


	@Keyword
	public String captureImage(String imgDir){

		String fileSuffix = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

		WebUI.openBrowser("https://www.google.fi/")
		WebElement image = DriverFactory.getWebDriver().findElement(By.xpath("//img[@id='hplogo']"))
		//#hplogo > a > img
		//WebUI.openBrowser("https://d1h3p5fzmizjvp.cloudfront.net/themes/katalon_4/images/katalon_template_1809/logo@2x.png")
		//WebElement image = DriverFactory.getWebDriver().findElement(By.xpath("//img"))
		String imgSrc = image.getAttribute("src")

		URL imageURL = new URL(imgSrc)
		BufferedImage saveImage = ImageIO.read(imageURL)

		ImageIO.write(saveImage, "png", new File(imgDir+fileSuffix+"-google.png"))

		WebUI.closeBrowser()

		return fileSuffix
	}

	private static double getDifferencePercent(BufferedImage img1, BufferedImage img2) {
		int width = img1.getWidth();
		int height = img1.getHeight();
		int width2 = img2.getWidth();
		int height2 = img2.getHeight();
		if (width != width2 || height != height2) {
			//throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
			throw new StepErrorException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
		}

		long diff = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
			}
		}
		long maxDiff = 3L * 255 * width * height;

		return 100.0 * diff / maxDiff;
	}

	private static int pixelDiff(int rgb1, int rgb2) {
		int r1 = (rgb1 >> 16) & 0xff;
		int g1 = (rgb1 >>  8) & 0xff;
		int b1 =  rgb1        & 0xff;
		int r2 = (rgb2 >> 16) & 0xff;
		int g2 = (rgb2 >>  8) & 0xff;
		int b2 =  rgb2        & 0xff;
		return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
	}
}
