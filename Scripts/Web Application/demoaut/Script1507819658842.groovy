import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

for (def index : (0..3 - 1)) {
    WebUI.navigateToUrl('http://newtours.demoaut.com/')

    WebUI.setText(findTestObject('demoaut/Page_WelcomeMercuryTours/input_userName'), GlobalVariable.user)

    WebUI.setText(findTestObject('demoaut/Page_WelcomeMercuryTours/input_password'), GlobalVariable.pass)

    WebUI.click(findTestObject('demoaut/Page_WelcomeMercuryTours/input_login'))

    if ((index % 2) == 0) {
        WebUI.click(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/input_tripType-oneway'))
    } else {
        WebUI.click(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/input_tripType-roundtrip'))
    }
    
    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_passCount'), '2', true)

    //WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_fromPort'), 'Frankfurt', true)
    //WebUI.selectOptionByIndex(findTestObject('Page_CuraAppointment/lst_Facility'), 1)
    WebUI.verifyTextPresent('Use our Flight Finder to search for the lowest fare on participating airlines. Once you\'ve booked your flight, don\'t forget to visit the Mercury Tours Hotel Finder to reserve lodging in your destination city', 
        false)

    number = WebUI.getNumberOfTotalOption(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_fromPort'), FailureHandling.CONTINUE_ON_FAILURE)

    println('count of deparding from values is: ' + number)

    WebUI.selectOptionByIndex(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_fromPort'), index)

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_fromMonth'), '1', true)

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_fromDay'), '20', true)

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_toPort'), 'London', true)

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_toMonth'), '3', true)

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_toDay'), '19', true)

    WebUI.click(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/input_servClass'))

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/select_airline'), 'Blue Skies Airlines', 
        true)

    WebUI.click(findTestObject('demoaut/Page_Find-a-FlightMercuryTours/input_findFlights'))

    WebUI.click(findTestObject('demoaut/Page_Select-a-FlightMercuryTours/input_outFlight'))

    WebUI.click(findTestObject('demoaut/Page_Select-a-FlightMercuryTours/input_inFlight'))

    WebUI.click(findTestObject('demoaut/Page_Select-a-FlightMercuryTours/input_reserveFlights'))

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_passFirst0'), 'tim')

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_passLast0'), 'kuis')

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/select_pass.0.meal'), 'BLML', true)

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_passFirst1'), 'tim')

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_passLast1'), 'kuis')

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/select_pass.1.meal'), 'BLML', true)

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/select_creditCard'), 'IK', true)

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_creditnumber'), '1234567890')

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/select_cc_exp_dt_mn'), '01', true)

    WebUI.selectOptionByValue(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/select_cc_exp_dt_yr'), '2000', true)

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_cc_frst_name'), 'tim')

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_cc_mid_name'), 'ti')

    WebUI.setText(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_cc_last_name'), 'kuis')

    WebUI.click(findTestObject('demoaut/Page_Book-a-FlightMercuryTours/input_buyFlights'))

    WebUI.click(findTestObject('demoaut/Page_FlightConfirmationMercuryTo/img'))
}

WebUI.closeBrowser()

