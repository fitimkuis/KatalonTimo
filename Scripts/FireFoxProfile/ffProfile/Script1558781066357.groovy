import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.internal.ProfilesIni

//ProfilesIni profile = new ProfilesIni();

//FirefoxProfile myprofile = profile.getProfile("KatalonTests");

String path = System.getProperty("user.dir")+"\\geckodriver.exe";
println path

System.setProperty("webdriver.gecko.driver",path);
//WebDriver driver = new FirefoxDriver();

WebDriver driver = new FirefoxDriver();

String baseUrl = 'http://demo.guru99.com/test/newtours/'

String expectedTitle = 'Welcome: Mercury Tours'

String actualTitle = ''

// launch Fire fox and direct it to the Base URL
driver.get(baseUrl)

driver.close()