import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.commons.collections.CollectionUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

KeywordUtil log = new KeywordUtil()

WebUI.openBrowser('')

WebUI.setViewPortSize(700, 500)

WebUI.navigateToUrl('http://demoaut.katalon.com/')

//WebUI.click(findTestObject("katalon_5_3_katalon_demo"))
//WebUI.click(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/a_MakeAppointment'))

'Get \'class\' attribute value of make appointment button'
attribute = WebUI.getAttribute(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/a_MakeAppointment'), 'class')

'Verify if \'class\' attribute value is correct for the button.'
WebUI.verifyMatch(attribute, 'btn btn-dark btn-lg', false)

WebUI.click(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/a_MakeAppointment'))

WebUI.setText(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/input_username'), GlobalVariable.username)

WebUI.setEncryptedText(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/input_password'), GlobalVariable.pass)

WebUI.click(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/button_Login'))


WebDriver driver = DriverFactory.getWebDriver()
'class="form-horizontal'

//driver.find_elements_by_xpath('//form[@name="signup-form"]//input')
//List<WebElement> allFormChildElements = driver.findElements(By.name("facility"));
List<WebElement> allFormChildElements = driver.findElements(By.className("form-control"));

println "allFormChildElements.size()=${allFormChildElements.size()}"
println allFormChildElements

List<WebElement> elements = driver.findElements(By.xpath("//button"));
//List<WebElement> elements = driver.findElements(By.xpath("//*"));

for (WebElement el: elements){
	println "${el.getTagName()}"
	println "${el.getText()}"
	println "${el.getAttribute('value')}"
}

WebUI.setText(findTestObject('Object Repository/withoutSpaces/Page_CURAHealthcareService/input_VisitDate(Required)_vi'),'15/02/2090')

WebElement click = driver.findElement(By.xpath("//button[contains(.,'Book Appointment')]"))
click.click();

List<WebElement> labels = driver.findElements(By.xpath("//label"));
for (WebElement el: labels){
	//println "${el.getTagName()}"
	log.logInfo ("${el.getText()}")
	//println "${el.getAttribute('value')}"

}

String expectedString = "Medicare";

ArrayList <String> list = new ArrayList<>()
list.add("Tokyo CURA Healthcare Center")
list.add("Medicare")

ArrayList<String> array = new ArrayList <String>()
List<WebElement> ids = driver.findElements(By.tagName("p"));
for (WebElement el: ids){
	array.add("${el.getText()}")
	//println "${el.getTagName()}"
	//println "${el.getText()}"
	//println "${el.getAttribute('value')}"
}


for (int x = 0; x < array.size(); x++){
	
	if (array[x].equals(expectedString)){
		println "page has text!"
		break
	}else{
		println "page has not text!"
		break
	}
}

HashSet<String> s1 = new HashSet<String>(Arrays.asList(array));
System.out.println(s1);
s1.removeAll(Arrays.asList(list));
println(s1);

// Get String Array
//CustomKeywords.'convertArrayListToString.ConvertListToArray.getArrayList'(array)
StringBuilder sb = new StringBuilder();
for (String s : array)
{
	sb.append(s);
	//sb.append("\t");
}
log.logInfo(sb.toString());

StringBuilder sb2 = new StringBuilder();
for (String s : list)
{
	sb2.append(s);
	//sb2.append("\t");
}
log.logInfo(sb2.toString());

def diff1 = CustomKeywords.'readPdfFile.verifyPdfContent.findNotMatching'(sb.toString(), sb2.toString())
def diff2 = CustomKeywords.'readPdfFile.verifyPdfContent.findNotMatching'(sb2.toString(), sb.toString())

//def diff1 = CustomKeywords.'readPdfFile.verifyPdfContent.findNotMatching'(list.join(","), array.join(","))
//def diff2 = CustomKeywords.'readPdfFile.verifyPdfContent.findNotMatching'(array.join(","), list.join(","))

log.logInfo ( "pages have differences file1 have words "+diff1+ " which are not in file2, file2 instead have "+diff2)


def ret = strDiffChop(sb.toString(),sb2.toString())
log.logInfo (ret)


List<String> cards = new ArrayList<>()
cards.add("VISA")
cards.add("MAESTRO")
cards.add("MASTER CARD")
cards.add("VISA ELECTRON")
List<String> page = new ArrayList<>()
page.add("VISA")
page.add("MASTER CARD")
page.add("DINERS")
page.add("VISA ELECTRON")
page.add("VISA KULTA")


List<String> newAddedElementsList = new ArrayList<String>();
List<String> removedElementsList = new ArrayList<String>();
for(String ele : cards){
	if(page.contains(ele)){
		continue;
	}else{
		removedElementsList.add(ele);
	}
}
println removedElementsList

for(String ele : page){
	if(cards.contains(ele)){
		continue;
	}else{
		newAddedElementsList.add(ele);
	}
}

println(CollectionUtils.isEqualCollection(newAddedElementsList, cards)); // false

def message = "page have "+newAddedElementsList+" which not in data "+cards

if (!CollectionUtils.isEqualCollection(newAddedElementsList, cards)){
	log.logInfo ("page have "+newAddedElementsList+" which not in data "+cards)
	log.markWarning(message)
	//throw new StepErrorException(message)
} else{
	log.logInfo("page and data are equal")
}

//WebUI.verifyMatch(CurrentURL, ‘https://www.google.com’, false)

WebUI.delay(3)

'Close browser'
WebUI.closeBrowser()


String strDiffChop(String s1, String s2) {
	if (s1.length() > s2.length()) {
		return s1.substring(s2.length() - 1);
	} else if (s2.length() > s1.length()) {
		return s2.substring(s1.length() - 1);
	} else {
		return null;
	}
}
