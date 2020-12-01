package readGmail

import java.awt.Desktop
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.kms.katalon.core.annotation.Keyword
import com.sun.media.sound.Toolkit

public class unZip {

	@Keyword
	public String unzipFile(String fname){

		String INPUT_ZIP_FILE = "C:\\KatalonStudio\\DataDrivenTestMaster\\"+fname
		println("DEBUG "+INPUT_ZIP_FILE)
		String OUTPUT_FOLDER = "C:\\KatalonStudio\\DataDrivenTestMaster\\extractedFiles\\"

		byte[] buffer = new byte[1024];

		try{

			//create output directory is not exists
			File folder = new File(OUTPUT_FOLDER);
			if(!folder.exists()){
				folder.mkdir();
			}

			//get the zip file content
			ZipInputStream zis =
					new ZipInputStream(new FileInputStream(INPUT_ZIP_FILE));
			//get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while(ze!=null){

				String fileName = ze.getName();
				File newFile = new File(OUTPUT_FOLDER + File.separator + fileName);

				println("file unzip : "+ newFile.getAbsoluteFile());

				//create all non exists folders
				//else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");

		}catch(IOException ex){
			ex.printStackTrace();
		}
	}


}
