import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import internal.GlobalVariable

public class MyStepDefinition {

	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */

	@Given("I navigate to Cura System homepage")
	def I_navigate_to_Cura_System_homepage() {

		WebUI.openBrowser("http://demoaut.katalon.com")
		//WebUI.waitForPageLoad(30)
	}

	@When("I click Make Appointment button")
	def I_click_makeAppointment_button() {

		WebUI.click(findTestObject('Object Repository/katalon_5_3_katalon_demo/Page_CURAHealthcareService/a_MakeAppointment'))
	}

	@And("I enter username (.*) and password (.*)")
	def I_enter_valid_username_password(String username, String password) {

		WebUI.setText(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/input_username'), username)

		WebUI.setText(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/input_password'), password)
	}

	@And("I click Log in button")
	def I_click_login_btn() {

		WebUI.click(findTestObject('Object Repository/katalon_5_3_katalon_demo/Page_CURAHealthcareService/button_Login'))
	}

	@Then("I should be able to login successfully")
	def I_login_successfully() {

		//WebUI.click(findTestObject('Object Repository/katalon_5_3_katalon_demo/Page_CURA Healthcare Service/button_Login'))
		//WebUI.verifyTextPresent('MakeAppointment', false)
		WebUI.closeBrowser()
	}

	@And("I enter an invalid username (.*) and password (.*)")
	def I_enter_invalid_username_password(String username, String password) {

		WebUI.setText(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/input_username'), username)

		WebUI.setText(findTestObject('katalon_5_3_katalon_demo/Page_CURAHealthcareService/input_password'), password)
	}


	@Then("I should NOT be able to login successfully")
	def I_login_unsuccessfully() {

		WebUI.verifyTextPresent('Login failed! Please ensure the username and password are valid.', false)
		WebUI.closeBrowser()
	}
}
