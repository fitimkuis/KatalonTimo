package demo
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.BrowserNotOpenedException


class PythonKeywords {

	@Keyword
	def readimage(filepath){
		runPython("keywords.read_image", filepath)
	}

	@Keyword
	def getimagetext(filename, tempname){
		runPython("keywords.get_image_text", filename, tempname)
	}

	@Keyword
	def helloWorld(int a, int b) {
		runPython("keywords.hello_world", a, b)
	}

	@Keyword
	def testDemo(int a, int b) {
		runPython("keywords.testing", a, b)
	}

	@Keyword
	def sortList(String names){
		runPython("keywords.sort_string", names)
	}

	@Keyword
	def getDiff(def a, def b){
		runPython("keywords.diff", a, b)
	}

	@Keyword
	def fibionacciSequence(int a){
		runPython("keywords.fibonacci", a)
	}

	@Keyword
	def gotoGoogle() {
		runPython("keywords.goto_google")
	}

	@Keyword
	def gotoKatalonDemo(){
		runPython("keywords.goto_katalon_demo")
	}

	@Keyword
	def compareTwoCsv(String csv1, String csv2){
		runPython("keywords.compare_csv", csv1,csv2)
	}

	private runPython(String script, Object[] keywordArgs) {
		try {
			KeywordUtil.logInfo(Arrays.deepToString(keywordArgs))
			def outputFile = File.createTempFile("ks_py_output_", ".tmp")
			KeywordUtil.logInfo(outputFile.absolutePath)

			def allArgs = [
				keyword: script,
				keywordArgs: keywordArgs,
				outputPath: outputFile.absolutePath
			]

			try {
				def webdriver = DriverFactory.getWebDriver()
				allArgs["webDriver"] = [
					sessionId: webdriver.getSessionId().toString(),
					serverUrl: DriverFactory.getWebDriverServerUrl(webdriver)
				]
			} catch (BrowserNotOpenedException e) {
			}

			def inputFile = File.createTempFile("ks_py_input_", ".tmp")
			def allArgsJson = groovy.json.JsonOutput.toJson(allArgs)
			KeywordUtil.logInfo(allArgsJson)
			inputFile.write(allArgsJson)

			KeywordUtil.logInfo("Project directory " + RunConfiguration.getProjectDir());
			def pb = new ProcessBuilder(
					"python",
					RunConfiguration.getProjectDir() + "/python/execution.py",
					inputFile.absolutePath)
			def process = pb.start()
			def errCode = process.waitFor()

			def line
			def retval = []
			def input = new BufferedReader(new InputStreamReader(process.getInputStream()))
			while ((line = input.readLine()) != null) {
				KeywordUtil.logInfo(line)
				retval.add(line)
			}
			input.close()
			return retval
		} catch (Exception err) {
			err.printStackTrace()
		}
	}
}