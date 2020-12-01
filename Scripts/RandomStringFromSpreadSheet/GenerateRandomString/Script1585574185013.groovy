import java.awt.Desktop

import org.jopendocument.dom.spreadsheet.Sheet
import org.jopendocument.dom.spreadsheet.SpreadSheet


String filename = System.getProperty("user.dir")+"\\Include\\spreadsheet\\random_string.ods";

for (int i = 0; i < 5; i++){
	
	Desktop.getDesktop().open(new File(filename));
	
	Thread.sleep(4000);
	
	def randString = getRandString(filename);
	println ("DEBUG returned random string: "+randString)
	
}


public static String getRandString(String filename) throws IOException {

    File f = new File(filename);

    //We'll assume everything we want is on the first sheet:
    Sheet sheet = SpreadSheet.createFromFile(f).getSheet(0);


    String cellText = "";
    cellText = sheet.getCellAt(0, 0).getTextValue();
    //System.out.println("loadfile method read: " +cellText);
	
	return cellText

} 