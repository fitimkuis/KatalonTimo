import static org.junit.Assert.*;

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

WebUI.openBrowser("https://vaadin.com/framework/demos")
def driver = DriverFactory.getWebDriver()
String baseUrl = "https://www.katalon.com/";
selenium = new WebDriverBackedSelenium(driver, baseUrl);
selenium.open("https://vaadin.com/framework/demos");
selenium.click("link=Try the demo");
selenium.selectWindow("win_ser_1");
selenium.click("id=login");
//WebUI.setText(findTestObject("Vaadin/Page_Bakery/input_username"), "admin@vaadin.com")
selenium.type("id=login", "admin@vaadin.com");
selenium.click("id=password");
selenium.type("id=password", "admin");
selenium.click("id=button-submit");
verifyEquals("Bakery", selenium.getTitle());
selenium.click("//div[@id='products']/span/span[2]");
selenium.click("id=add");
selenium.type("id=name", "Ananas");
selenium.click("id=price");
selenium.type("id=price" , "\$9.00");
selenium.click("id=name");
selenium.type("id=name", "Ananas Smoothy");
selenium.click("id=update");
selenium.click("//div[@id='users']/span/span[2]");
verifyEquals("admin@vaadin.com", selenium.getText("//div[@id='list']/div[3]/table/tbody/tr/td"));
verifyEquals("Göran", selenium.getText("//div[@id='list']/div[3]/table/tbody/tr/td[2]"));
selenium.click("//div[@id='list']/div[3]/table/tbody/tr/td[3]");
verifyEquals("admin", selenium.getText("//div[@id='list']/div[3]/table/tbody/tr/td[3]"));
selenium.click("//div[@id='storefront']/span/span[2]");
selenium.click("id=newOrder");
selenium.click("id=fullName");
selenium.type("id=fullName", "Mat Pat");
selenium.click("id=phone");
selenium.type("id=phone", "12345678");
selenium.click("id=details");
selenium.click("//div[@id='product']/div");
selenium.type("id=details", "Orange");
selenium.click("//div[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr/td/span");
selenium.click("id=quantity");
selenium.type("id=quantity", "5");
selenium.click("id=comment");
selenium.type("id=comment", "Delicious");
selenium.click("id=ok");
verifyEquals("", selenium.getText("id=fullName"));
selenium.click("id=ok");
selenium.click("id=newCommentInput");
selenium.click("id=newCommentInput");
selenium.click("id=newCommentInput");
selenium.type("id=newCommentInput", "Two piece order");
selenium.click("id=ok");
selenium.click("id=newCommentInput");
selenium.type("id=newCommentInput", "Sh");
selenium.type("id=newCommentInput", "Sho order");
selenium.click("id=ok");
selenium.click("id=newCommentInput");
selenium.type("id=newCommentInput", "New mws");
selenium.type("id=newCommentInput", "New message");
selenium.click("id=ok");
selenium.click("id=newCommentInput");
selenium.type("id=newCommentInput", "shop");
selenium.click("id=gwt-uid-39");
selenium.click("id=commitNewComment");
verifyEquals("", selenium.getText("xpath=(//input[@type='text'])[7]"));
selenium.click("//div[@id='logout']/span/span[2]");
selenium.close();
//selenium.selectWindow("win_ser_local");
