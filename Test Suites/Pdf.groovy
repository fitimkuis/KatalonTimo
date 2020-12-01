import java.nio.file.Paths

import com.kms.katalon.core.annotation.SetUp as SetUp
import com.kms.katalon.core.annotation.SetupTestCase as SetupTestCase
import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.annotation.TearDownTestCase as TearDownTestCase

// Please change skipped to be false to activate this method.
// Put your code here.
/**
 * Clean test suites environment.
 */
// Please change skipped to be false to activate this method.
// Put your code here.
/**
 * Run before each test case starts.
 */
// Please change skipped to be false to activate this method.
// Put your code here.
/**
 * Run after each test case ends.
 */
// Please change skipped to be false to activate this method.
// Put your code here.

@SetUp(skipped = true)
def setUp() {
}

@TearDown(skipped = true)
def tearDown() {
	
}

@SetupTestCase(skipped = true)
def setupTestCase() {
}

@TearDownTestCase(skipped = true)

def tearDownTestCase() {

}
/*
    String fileName = CustomKeywords.'readGmail.getGmail.getGmailAttachment'()
    println('**************DEBUG returned filename *************** ' + fileName)
    CustomKeywords.'readGmail.unZip.unzipFile'(fileName)
    String zip = fileName.substring(0, 15)
    String path = Paths.get('.').toAbsolutePath().normalize().toString()
    path = path.replace('\\', '/')
    String url = path + '/extractedFiles/' + zip + '/Report.html'
    CustomKeywords.'readGmail.openWebHtml.openHtmlPage'(url)
}
*/
