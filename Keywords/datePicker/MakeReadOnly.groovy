package datePicker

import java.awt.Desktop

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

public class ReadOnly {

	@Keyword
	def readOnlyTrue(){
		if(Desktop.isDesktopSupported()) {

			WebUI.openBrowser('')
			WebUI.navigateToUrl("https://jqueryui.com/datepicker/")
			WebUI.switchToFrame(findTestObject('DatePicker/Page_Datepicker_ jQueryUI2/iframe_demo-frame'),10)

			WebUI.click(findTestObject('DatePicker/Page_Datepicker_ jQueryUI2/input_datepicker'))

			WebUI.click(findTestObject('DatePicker/Page_Datepicker jQueryUI/a_20'))

			//Desktop.getDesktop().browse(new URI("https://jqueryui.com/datepicker/"));
			String param = "kissa"

			if(param.matches("^[a-zA-Z]+\$")) {
				println "only chars"
			}
			else if(param.matches("^[0-9]+\$")) {
				println "only numbers"
			}
			else if(param.matches("^[0-9a-zA-Z]+\$")) {
				println "alphanumeric"
			}
		}
	}

	@Keyword
	public void key(){
		String str = new String("Java String Methods");

		System.out.print("Regex: (.*)String(.*) matches string? " );
		System.out.println(str.matches("(.*)String(.*)"));

		System.out.print("Regex: (.*)Strings(.*) matches string? " );
		System.out.println(str.matches("(.*)Strings(.*)"));

		System.out.print("Regex: (.*)Methods matches string? " );
		System.out.println(str.matches("(.*)Methods"));
	}
}
