package excelHelper

import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.annotation.Keyword

public class GetLatestExcelFile {

	@Keyword
	/* Get the newest file for a specific extension */
	public String getTheNewestFile(String filePath) {
		//String currentUsersHomeDir = System.getProperty("user.home");
		//String downloadFolder = currentUsersHomeDir + File.separator + "Downloads" + File.separator;

		File dir = new File(filePath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			println("There is no file in the folder");
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		String k = lastModifiedFile.toString();

		System.out.println(lastModifiedFile);
		Path p = Paths.get(k);
		String file = p.getFileName().toString();
		return file;

	}

}
