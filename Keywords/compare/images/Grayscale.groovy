package compare.images

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration

public class Grayscale {

	@Keyword
	public String imgGrayScale(String imgPath){

		BufferedImage img = null;
		File f = null;

		//read image
		try{
			f = new File(imgPath+"-google.png");
			img = ImageIO.read(f);
		}catch(IOException e){
			System.out.println(e);
		}

		//get image width and height
		int width = img.getWidth();
		int height = img.getHeight();

		//convert to grayscale
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int p = img.getRGB(x,y);

				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p&0xff;

				//calculate average
				int avg = (r+g+b)/3;

				//replace RGB value with avg
				p = (a<<24) | (avg<<16) | (avg<<8) | avg;

				img.setRGB(x, y, p);
			}
		}

		//write image
		def imgDir = RunConfiguration.getProjectDir() + "/images/"
		String fileSuffix = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		try{
			f = new File(imgDir+fileSuffix+"-google.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println(e);
		}
		return fileSuffix
	}

	@Keyword
	public String writeToImage(String imgPath){

		BufferedImage img = null;
		File f = null;

		def imgDir = RunConfiguration.getProjectDir() + "/images/"
		String fileSuffix = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

		//read image
		try{
			f = new File(imgPath+"-google.png");
			img = ImageIO.read(f);
		}catch(IOException e){
			System.out.println(e);
		}

		String key = "Sample";
		//BufferedImage bufferedImage2 = new BufferedImage();
		//BufferedImage bufferedImage = new BufferedImage(170, 30,BufferedImage.TYPE_INT_RGB);
		//BufferedImage bufferedImage = new BufferedImage(imgPath+"-google.pom");
		Graphics graphics = img.getGraphics();
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 200, 50);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
		graphics.drawString(key, 10, 25);
		ImageIO.write(img, "jpg", new File(imgDir+fileSuffix+"-google.png"));
		System.out.println("Image Created");

		return fileSuffix
	}
}

