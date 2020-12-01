import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.swre.report as Report

import internal.GlobalVariable as GlobalVariable


PrepareTestDOC doc = new PrepareTestDOC()
doc.guard()

class PrepareTestDOC {

Report r = new Report()

private static TestCaseContext testCaseContext
private static TestSuiteContext testSuiteContext

private static Date start = new Date()

def runThread = true

def guard = new Thread({
	sleep(5000) // dont need to start early
	while(runThread){
		try{
// this is where i try to get driver stored by OpenBrowser TC
			def d = (WebDriver)GlobalVariable.webDriver
			
//My element that i want to watch
			WebElement e = d.findElement(By.xpath("//div[contains(@class,'noty_type_error')]//span"))
			
			if(e != null ){
				println "*** Found an ERROR bar!"
				def txt = e.text
				r.addStep("<center><b>UI Error!</b><br>${txt}</center>", false) //call to my reporting solution with information on UI error
				runThread = false
			}
		} catch (a){
			println a
		}
		println "*** from Guard ${new Date()}"
		sleep(3000)
	}
} as Runnable)

/**
 * Executes before every test case starts.
 * @param testCaseContext related information of the executed test case.
 */
@BeforeTestCase
def prepareTestCase(TestCaseContext testCaseContext) {
	GlobalVariable.testCaseContext = testCaseContext
// waking up guard
	r.setStartTest()
	println "*** Setting up guard"
	guard.start()
}

@AfterTestCase
def printStatus(TestCaseContext testCaseContext){
	r.addTestCase()
	r.setEndTest()
	r.flush()
 // thread.close() is deprecated so i use semafor to gracefully end it
	runThread = false
	println "*** Guard dissmissed"
}
}