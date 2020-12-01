import com.kms.katalon.core.configuration.RunConfiguration
import java.util.Date;
import java.text.SimpleDateFormat;


//get pdf dir
def pdfDir = RunConfiguration.getProjectDir() + "/pdfFiles/"


SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
String string  = dateFormat.format(new Date());
System.out.println(string);

try {
	//Prepare input pdf file list as list of input stream.
	List<InputStream> inputPdfList = new ArrayList<InputStream>();
	inputPdfList.add(new FileInputStream(pdfDir+"file1.pdf"));
	inputPdfList.add(new FileInputStream(pdfDir+"file2.pdf"));
	
	//inputPdfList.add(new FileInputStream("..\\pdf\\pdf_2.pdf"));
	//inputPdfList.add(new FileInputStream("..\\pdf\\pdf_3.pdf"));
	//inputPdfList.add(new FileInputStream("..\\pdf\\pdf_4.pdf"));


	//Prepare output stream for merged pdf file.
	OutputStream outputStream = new FileOutputStream(pdfDir+""+string+".pdf");

	//call method to merge pdf files.
	CustomKeywords.'com.pdf.util.MergePdfFiles.mergePdfFiles'(inputPdfList, outputStream);
} catch (Exception e) {
	e.printStackTrace();
}
