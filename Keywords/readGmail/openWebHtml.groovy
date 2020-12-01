package readGmail

import java.awt.Desktop

import com.kms.katalon.core.annotation.Keyword

public class openWebHtml {

	@Keyword
	public void openHtmlPage(String url){

		File file = new File(url);
		URI oURL = file.toURI();
		Desktop.getDesktop().browse(oURL);
	}
}
