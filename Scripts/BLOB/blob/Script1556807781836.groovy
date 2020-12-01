import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.apache.pdfbox.text.PDFTextStripperByArea;

//in this example i have two columns in csv file and five rows
BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\fitim\\Desktop\\data\\csv1.csv"));
List <String> arr = new ArrayList<>()
int i = 0
while ((line = br.readLine()) != null) {
	// use comma as separator
	String[] cols = line.split(";");
	arr.add(cols[0])
	arr.add(cols[1])
	i++
	System.out.println("Coulmn 0= " + cols[0] + " , Column 1=" + cols[1]);
}
println arr
def latest = arr[arr.size() - 1]; 
println latest // here is the latest cell data
	
	PDDocument document = PDDocument.load(new File("C:\\Users\\fitim\\Desktop\\BLOB\\49f93860-4d86-49c3-b6ed-003b1d41dda9.pdf"))
	document.getClass();
	if (!document.isEncrypted()) {
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);
		PDFTextStripper tStripper = new PDFTextStripper();
		String pdfFileInText = tStripper.getText(document);
		// split by whitespace
		List lines = pdfFileInText.split("\\r?\\n");
		for (String line : lines) {
			System.out.println(line);
		}
	}
