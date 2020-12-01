import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

String url1 = "http://www.zlti.com"
//CustomKeywords.'com.check.link.checkLinks.checkPageLinks'(url1)

String url2 = "http://demo.guru99.com/test/newtours/"
//CustomKeywords.'com.check.link.checkLinks.checkPageLinks'(url2)

// TODO Auto-generated method stub

//FirefoxDriver ff = new FirefoxDriver();
driver = new ChromeDriver();
//driver.manage().window().maximize();

CustomKeywords.'com.check.link.FindBrokenLinksDemo.findPageBrokenLInks'(driver, url2);

/*
driver.get("http://toolsqa.com/automation-practice-switch-windows/");
//ff.get("http://toolsqa.com/automation-practice-switch-windows/");
//ff.get("http://www.yahoo.com/");

List allImages = CustomKeywords.'com.check.link.checkLinksImages.findAllLinks'(driver);

System.out.println("Total number of elements found " + allImages.size());

	for( WebElement element : allImages){
		try
		{
			//CustomKeywords.'com.check.link.checkLinksImages.isLinkBroken'(new URL(element.getAttribute("href")));
			System.out.println("URL: " + element.getAttribute("href")+ " returned " + CustomKeywords.'com.check.link.checkLinksImages.isLinkBroken'(new URL(element.getAttribute("href"))));
			//System.out.println("URL: " + element.getAttribute("outerhtml")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
		}
		catch(Exception exp)
		{
			System.out.println("At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());

		}

	}
	
driver.quit();
*/