import java.nio.file.Paths

String name = "index.html"
String home = System.getProperty("user.home")
url = home+"\\Downloads\\Website\\Website\\" + name
println url
String path = Paths.get(".").toAbsolutePath().normalize().toString();
println path
//File file = new File(home+"\\Downloads\\Website\\Website\\" + fileName);


String fileName = CustomKeywords.'readGmail.getGmail.getGmailAttachment'()
println("**************DEBUG returned filename *************** "+fileName)
CustomKeywords.'readGmail.unZip.unzipFile'(fileName)
String zip = fileName.substring(0, 29);
println("*********DEBUG zipfile*******"+zip)
path = Paths.get(".").toAbsolutePath().normalize().toString();
path = path.replace("\\", "/");
spam1 = zip.substring(0,8)
spam2 = zip.substring(9,15)
completeUrl = spam1+"_"+spam2
println completeUrl
String url = path+"/extractedFiles/"+zip+"/"+completeUrl+".html"
CustomKeywords.'readGmail.openWebHtml.openHtmlPage'(url)


