import java.util.concurrent.TimeUnit

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.Select;

import com.kms.katalon.core.webui.driver.DriverFactory


String baseUrl = "https://scratch.mit.edu/"
String downloadPath = "C:\\Users\\fitim\\Desktop\\data\\pdf\\"
Map<String, Object> chromePrefs = new HashMap<String, Object>()
chromePrefs.put("download.default_directory", downloadPath)
chromePrefs.put("download.prompt_for_download", false)
chromePrefs.put("pdfjs.disabled", true);
System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeOptions options = new ChromeOptions()
//options.addArguments("--headless")
options.setExperimentalOption("prefs", chromePrefs)
WebDriver driver = new ChromeDriver(options)

//selenium = new WebDriverBackedSelenium(driver, baseUrl)
driver.get(baseUrl)
int size = driver.findElements(By.tagName("iframe")).size();
println size
driver.findElement(By.cssSelector("#navigation > div > ul > li.link.right.join > a > span")).click()
size = driver.findElements(By.tagName("iframe")).size();
println size

driver.switchTo().frame(0); //Switching to the frame

driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
WebElement user = driver.findElement(By.cssSelector("#registration-form > div.reg-body-1.modal-body > div:nth-child(2) > div > input"))
user.sendKeys("hitimhuis")

WebElement pass1 = driver.findElement(By.cssSelector("#registration-form > div.reg-body-1.modal-body > div:nth-child(3) > div > input"))
pass1.sendKeys("Qwerty123")

WebElement pass2 = driver.findElement(By.cssSelector("#registration-form > div.reg-body-1.modal-body > div:nth-child(4) > div > input"))
pass2.sendKeys("Qwerty123")

size = driver.findElements(By.tagName("iframe")).size();
println size
driver.findElement(By.cssSelector("#registration-next")).click()

Select month = new Select(driver.findElement(By.cssSelector("#registration-form > div.reg-body-2.modal-body > div:nth-child(2) > div > select.birthmonth")));
month.selectByIndex(4);

Select year = new Select(driver.findElement(By.cssSelector("#registration-form > div.reg-body-2.modal-body > div:nth-child(2) > div > select.birthyear")));
year.selectByIndex(4);

driver.findElement(By.cssSelector("#registration-form > div.reg-body-2.modal-body > div:nth-child(3) > div > input[type=radio]:nth-child(2)")).click()

Select country = new Select(driver.findElement(By.cssSelector("#registration-form > div.reg-body-2.modal-body > div:nth-child(4) > div > select")));
country.selectByIndex(76);

driver.findElement(By.cssSelector("#registration-next")).click()

WebElement email1 = driver.findElement(By.cssSelector("#registration-form > div.reg-body-3.modal-body > div:nth-child(2) > div > input"))
email1.sendKeys("hitimhuis@gmail.com")

WebElement email2 = driver.findElement(By.cssSelector("#registration-form > div.reg-body-3.modal-body > div:nth-child(3) > div > input"))
email2.sendKeys("hitimhuis@gmail.com")

driver.findElement(By.cssSelector("#registration-next")).click()
