import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


KeywordUtil log = new KeywordUtil()

//get images dir
def imgDir = RunConfiguration.getProjectDir() + "/images/"

//capture object image from the webpage
def fileSuffix1 = CustomKeywords.'compare.images.CompareTwoImages.captureImage'(imgDir)
println "filesuffix1 "+fileSuffix1
//delay few seconds to get different timestamp
WebUI.delay(2)

//set image path to graycsale
def imagePath = imgDir+fileSuffix1
println "imagepath to grayscale "+imagePath

//grayscale image and save with different timestamp
def fileSuffix2 = CustomKeywords.'compare.images.Grayscale.imgGrayScale'(imagePath)
println "filesuffix2 "+fileSuffix2

//delay few seconds to get different timestamp
WebUI.delay(2)

//write to image and save with different timestamp
def fileSuffix3 = CustomKeywords.'compare.images.Grayscale.writeToImage'(imagePath)
println "filesuffix3 "+fileSuffix3

//set both images path
String pathA = imgDir+fileSuffix1+"-google.png"
String pathB = imgDir+fileSuffix2+"-google.png"
String pathC = imgDir+fileSuffix3+"-google.png"

//compare images
double percent = CustomKeywords.'compare.images.CompareTwoImages.compareImagePercentages'(pathA, pathB)

//info error if images are not the same
if (percent > 0){	
	log.logger.logError("Google images are not the same: "+percent)
	//throw new StepErrorException("Images are not same: "+percent);
}

//compare images
percent = CustomKeywords.'compare.images.CompareTwoImages.compareImagePercentages'(pathA, pathC)

//info error if images are not the same
if (percent > 0){
	log.logger.logError("Google images are not the same: "+percent)
	//throw new StepErrorException("Images are not same: "+percent);
}

/*File file1 = new File(imgDir+"2017-10-23-20_20_34_gray_image.tif")
File file2 = new File(imgDir+"images/2017-10-23-20_20_34_imageCapture.png")

float per = CustomKeywords.'compare.images.CompareTwoImages.compareImage'(file1, file2)
log.logInfo("Images are different "+per)*/

pathA = imgDir+"Lenna1002.jpg"
pathB = imgDir+"Lenna100.jpg"
pathC = imgDir+"Lenna50.jpg"

percent = CustomKeywords.'compare.images.CompareTwoImages.compareImagePercentages'(pathA, pathB)
log.logInfo("Images are same "+percent)

percent = CustomKeywords.'compare.images.CompareTwoImages.compareImagePercentages'(pathB, pathC)
log.logInfo("Images are dirrerent "+percent)

if (percent > 0){	
	log.logger.logError("Images are not the same: "+percent)
	//throw new StepErrorException("Images are not same: "+percent);
}


