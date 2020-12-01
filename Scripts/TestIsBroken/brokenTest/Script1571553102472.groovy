import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.commons.lang3.RandomStringUtils as RandomStringUtils
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://scratch.mit.edu/')

String title = WebUI.getWindowTitle()
println(title)

List<String> newUser = new ArrayList()
newUser = registerToUser()

println(newUser.get(0))
println(newUser.get(1))

WebUI.click(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/span_Kirjaudu sisn'))
WebUI.setText(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/input__username'), newUser.get(
        0))

WebUI.setText(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/input__password'), newUser.get(
        1))

String css = '#navigation > div > ul > li.link.right.login-item > div > div > form > div.flex-row.submit-row > button'
TestObject to = new TestObject('Object')

to.addProperty('css', ConditionType.EQUALS, css, true)

WebUI.click(to)

WebUI.delay(3)

title = WebUI.getWindowTitle()

println(title)

String text = 'Tervetuloa Scratchiin!'

//verify element text is
css = '#view > div > div > div.splash-header > div.box.welcome > div.box-header > h4'

TestObject to1 = new TestObject('Object1')
to1.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.verifyElementText(to1, text)

//log out
css = '#navigation > div > ul > li.link.right.account-nav > div > a > span' //navigate to user profiles

TestObject to2 = new TestObject('Object1')
to2.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.click(to2)

css = '#navigation > div > ul > li.link.right.account-nav > div > ul > li.divider > a' //log out
TestObject to3 = new TestObject('Object2')
to3.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.click(to3)

String logOutText = 'Luo tarinoita, pelejä ja animaatioita'
css = '#view > div > div.title-banner.intro-banner > div.flex-row.intro-container > div.flex-row.intro-content.column > h1 > span:nth-child(1)'
TestObject to4 = new TestObject('Object3')
to4.addProperty('css', ConditionType.EQUALS, css, true)

WebUI.verifyElementText(to4, logOutText)
WebUI.delay(5)
if (title.equals('Sratch - Haku')) {
    //not registered already
    WebUI.click(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/span_Kirjaudu sisn'))
    WebUI.setText(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/input__username'), 'testkatalon')
    WebUI.setEncryptedText(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/input__password'), 
       'chyteMdDRRyHETA9kDSZdGv6yYxKHLToa916STxLNEA=')
    WebUI.click(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/span_Kirjaudu sisn'))
}

url = WebUI.getUrl()
println(url)

/*if(WebUI.verifyTextPresent(text, false)){ //if not registered already
	WebUI.click(findTestObject('Object Repository/BrokenTest/Page_Scratch - Complete your Registration/button_Aloita'))
	WebUI.click(findTestObject('Object Repository/BrokenTest/Complete your Registration/input__userpassword'))
	WebUI.click(findTestObject('Object Repository/BrokenTest/Complete your Registration/span_Seuraava vaihe'))
	WebUI.selectOptionByIndex(findTestObject('Object Repository/BrokenTest/Complete your Registration/select_Month'), 4)	
	WebUI.selectOptionByIndex(findTestObject('BrokenTest/Complete your Registration/select_DateOfBird'), 5)
	WebUI.click(findTestObject('Object Repository/BrokenTest/Complete your Registration/input'))
	WebUI.selectOptionByIndex(findTestObject('Object Repository/BrokenTest/Complete your Registration/select_Country'), 10)
}
else{
	
	WebUI.delay(10)
}*/
WebUI.closeBrowser() //css = "#registration-form > div.reg-body-1.modal-body"
//String xpath= "(.//*[normalize-space(text()) and normalize-space(.)='`'])[1]/following::iframe[1]"
//WebUI.click(modal)
//WebUI.switchToFrame(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/iframe__modal-content-iframe mod-registration'),30)
//log out


/*
WebUI.openBrowser('')
WebUI.navigateToUrl('https://scratch.mit.edu/')
String title = WebUI.getWindowTitle()
println(title)
List<String> newUser = new ArrayList()

newUser = registerToUser()

println(newUser.get(0))

println(newUser.get(1))

WebUI.click(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/span_Kirjaudu sisn'))

WebUI.setText(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/input__username'), 
    newUser.get(0))

WebUI.setText(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/input__password'), 
    newUser.get(1))

String css = '#navigation > div > ul > li.link.right.login-item > div > div > form > div.flex-row.submit-row > button'
TestObject to = new TestObject('Object')
to.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.click(to)

WebUI.delay(3)

title = WebUI.getWindowTitle()
println(title)
String text = 'Tervetuloa Scratchiin!'

//verify element text is
css = '#view > div > div > div.splash-header > div.box.welcome > div.box-header > h4'
TestObject to1 = new TestObject('Object1')
to1.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.verifyElementText(to1, text)

//log out
css = '#navigation > div > ul > li.link.right.account-nav > div > a > span' //navigate to user profiles
TestObject to2 = new TestObject('Object1')
to2.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.click(to2)

css = '#navigation > div > ul > li.link.right.account-nav > div > ul > li.divider > a' //log out
TestObject to3 = new TestObject('Object2')
to3.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.click(to3)

String logOutText = 'Luo tarinoita, pelejä ja animaatioita'
css = '#view > div > div.title-banner.intro-banner > div.flex-row.intro-container > div.flex-row.intro-content.column > h1 > span:nth-child(1)'
TestObject to4 = new TestObject('Object3')
to4.addProperty('css', ConditionType.EQUALS, css, true)
WebUI.verifyElementText(to4, logOutText)
WebUI.delay(5)

if (title.equals('Sratch - Haku')) {
    //not registered already
    WebUI.click(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/span_Kirjaudu sisn'))
    WebUI.setText(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/input__username'), 
        'testkatalon')
    WebUI.setEncryptedText(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/input__password'), 
        'chyteMdDRRyHETA9kDSZdGv6yYxKHLToa916STxLNEA=')
    WebUI.click(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/span_Kirjaudu sisn'))
}

url = WebUI.getUrl()

println(url)

/*if(WebUI.verifyTextPresent(text, false)){ //if not registered already
	
	WebUI.click(findTestObject('Object Repository/BrokenTest/Page_Scratch - Complete your Registration/button_Aloita'))
	
	WebUI.click(findTestObject('Object Repository/BrokenTest/Complete your Registration/input__userpassword'))
	
	WebUI.click(findTestObject('Object Repository/BrokenTest/Complete your Registration/span_Seuraava vaihe'))
	
	WebUI.selectOptionByIndex(findTestObject('Object Repository/BrokenTest/Complete your Registration/select_Month'), 4)
	
	WebUI.selectOptionByIndex(findTestObject('BrokenTest/Complete your Registration/select_DateOfBird'), 5)
	
	WebUI.click(findTestObject('Object Repository/BrokenTest/Complete your Registration/input'))
	
	WebUI.selectOptionByIndex(findTestObject('Object Repository/BrokenTest/Complete your Registration/select_Country'), 10)
	
}
else{
	
	WebUI.delay(10)
}
WebUI.closeBrowser() 
*/
//css = "#registration-form > div.reg-body-1.modal-body"
//String xpath= "(.//*[normalize-space(text()) and normalize-space(.)='`'])[1]/following::iframe[1]"
//WebUI.click(modal)
//WebUI.switchToFrame(findTestObject('Object Repository/BrokenTest/Page_Scratch - Imagine Program Share/iframe__modal-content-iframe mod-registration'),30)
//log out

/*WebUI.openBrowser('')

WebUI.navigateToUrl('https://scratch.mit.edu/')

WebUI.click(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/span_Liity Scratchiin'))

WebUI.setText(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/input_Valitse kyttjnimi_username'), 
    'johanneskastaja')

WebUI.click(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/div_Valitse kyttjnimi                      _01bd3b'))

WebUI.setText(findTestObject('Object Repository/BrokenTest/Recorded/Page_Scratch - Imagine Program Share/input_Valitse kyttjnimi_username'), 
    'kalastaja')
    */

//WebUI.closeBrowser()

static List<String> registerToUser() {
    List<String> arr = new ArrayList()

    int length = 15

    boolean useLetters = true

    boolean useNumbers = false

    String userString = RandomStringUtils.random(length, useLetters, useNumbers)

    String passString = RandomStringUtils.random(length, useLetters, useNumbers)

    String email = userString + '@gmail.com'

    arr.add(userString)

    arr.add(passString)

    String css = '#navigation > div > ul > li.link.right.join > a > span'
    TestObject reg = new TestObject('ObjectRegister')
    reg.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.click(reg)
	
	WebUI.delay(2)
	
WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Valitse kyttjnimi_username'), 
    userString)
//findTestObject('Object Repository/BrokenTest/RecorderNewUser/Page_Scratch - Imagine Program Share/input_Valitse salasana_password')


WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Valitse salasana_password'), 
    passString)

WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Vahvista salasana_password-confirm'), 
    passString)

//document.querySelector("#registration-next")
////*[@id="registration-next"]
WebElement elem = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'),30)
String js = "document.querySelector('#registration-next');";
WebUI.executeJavaScript(js, elem);

//WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'))



WebUI.selectOptionByValue(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/select_- Kuukausi -                Tammikuu_3d5432'), 
    '5', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/select_- Vuosi -            201920182017201_4a7fee'), 
    '1960', true)

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Sukupuoli_gender'))

WebUI.selectOptionByValue(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/select_- Maa -                             _393619'), 
    'Finland', true)

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'))

WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_concat(Parent  s or guardian  s email_7815e9'), 
   email)

WebUI.setText(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_Confirm email address_email-confirm'), 
    email)

WebUI.click(findTestObject('Object Repository/BrokenTest/AllRecorded/Page_Scratch - Imagine Program Share/input_contact us_registration-next'))
	
	
	//WebUI.click(findTestObject('Object Repository/BrokenTest/ModalPage/Page_Scratch - Imagine Program Share/input_password-confirm'))
	//WebUI.setText(findTestObject('Object Repository/BrokenTest/ModalPage/Page_Scratch - Imagine Program Share/input_password-confirm'), passString)

	    /*css = 'body > div:nth-child(8) > div > div'
    TestObject modal = new TestObject('ObjectModal')
    modal.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.switchToFrame(modal, 30)*/

	/*
    css = '#registration-form > div.reg-body-1.modal-body > div:nth-child(2) > div > input'
    TestObject user = new TestObject('ObjectUser')
    user.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.setText(user, userString)
    
	css = '#registration-form > div.reg-body-1.modal-body > div:nth-child(3) > div > input'
    TestObject pass1 = new TestObject('ObjectPass1')
    pass1.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.setText(pass1, passString)

    css = '#registration-form > div.reg-body-1.modal-body > div:nth-child(4) > div > input'
    TestObject pass2 = new TestObject('ObjectPass2')
    pass2.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.setText(pass2, passString)
	*/

    /*css = '#registration-next'
    TestObject next = new TestObject('ObjectNext')
    next.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.click(next)

    css = '#registration-form > div.reg-body-2.modal-body > div:nth-child(2) > div > select.birthmonth'
    TestObject month = new TestObject('ObjectMonth')
    month.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.selectOptionByIndex(month, 4)

    css = '#registration-form > div.reg-body-2.modal-body > div:nth-child(2) > div > select.birthyear'
    TestObject year = new TestObject('ObjectYear')
    year.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.selectOptionByLabel(year, '1960')

    css = '#registration-form > div.reg-body-2.modal-body > div:nth-child(3) > div > input[type=radio]:nth-child(2)'
    TestObject gender = new TestObject('ObjectGender')
    gender.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.click(gender)

	css = '#registration-form > div.reg-body-2.modal-body > div:nth-child(4) > div > select'
    TestObject country = new TestObject('ObjectCountry')
    country.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.selectOptionByLabel(country, 'Finland')

    WebUI.click(next)

    css = '#registration-form > div.reg-body-3.modal-body > div:nth-child(2) > div > input'
    TestObject email1 = new TestObject('ObjectEmail1')
    email1.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.setText(email1, email)

	css = '#registration-form > div.reg-body-3.modal-body > div:nth-child(3) > div > input'
    TestObject email2 = new TestObject('ObjectEmail2')
    email2.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.setText(email2, email)

    WebUI.click(next)

    css = '#get-started-info > strong > span'
    TestObject emailField = new TestObject('ObjectEmailField')
    emailField.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.verifyElementText(emailField, email)

	css = '#registration-done'
    TestObject done = new TestObject('ObjectDone')
    done.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.click(done)*/

    css = '#navigation > div > ul > li.link.right.account-nav > div > ul > li.divider > a'
    TestObject to3 = new TestObject('Object2')
    to3.addProperty('css', ConditionType.EQUALS, css, true)
    WebUI.click(to3)

    return arr
}

