import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.google.gson.Gson
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('WebServiceReqs/webRequest'))
def responseText = response.getResponseText()
//println responseText

/*
JsonSlurper jsl = new JsonSlurper()	
RequestObject ro = new RequestObject()	
def myJson = ""	
def parsedResponse = ""	
String defaultUrl = "http://webhook.satyamphysio.in:8081/Lang.Product-en.json"	
String customUrl = "http://webhook.satyamphysio.in:8081/Lang.UserProduct-en.json"	
ro.setRestRequestMethod("GET")	
ro.setRestUrl(defaultUrl)	
ResponseObject resp = WSBuiltInKeywords.sendRequest(ro)	
if (resp.getStatusCode() == 200) {
	
  // replace BOM in response text		
	myJson = resp.getResponseText().replaceAll("\uFEFF","")		
	parsedResponse = jsl.parseText(myJson)	
	}	
*/
def jsonSlurper = new JsonSlurper()

//def jsontest = new JsonSlurper().parseText('{Test1:{Item:"Name",Number:0},Test2:{Item:"Name1",Number:21},"0563300111":{Item:"NameP",Number:0},"0563300127":{Item:"NameP",Number:21},"0563300154":{Item:"NameP",Number:22},"0563300166":{Item:"NameP",Number:22},"0563300142":{Item:"NameP",Number:99},"0563300131":{Item:"Name2",Number:18},"0563300151":{Item:"Name2",Number:18},"0563300163":{Item:"Name2",Number:0},"0563300136":{Item:"Name3",Number:0},"0563300143":{Item:"Name3",Number:0},"0563300152":{Item:"Name3",Number:21},"0563300164":{Item:"Name3",Number:22},"0563300147":{Item:"Name3",Number:99},"0563300139":{Item:"pe",Number:6},"0563300170":{Item:"pe",Number:7},"0563300172":{Item:"pe",Number:7},"0563300173":{Item:"pe",Number:7},"0563300137":{Item:"Name",Number:0},"0563300144":{Item:"Name",Number:0},"0563300145":{Item:"Name",Number:0},"0563300146":{Item:"Name",Number:0},"0563300148":{Item:"Name",Number:0},"0563300165":{Item:"Name",Number:21},"0563300156":{Item:"Name",Number:21},"0563300157":{Item:"Name",Number:21},"0563300158":{Item:"Name",Number:21},"0563300159":{Item:"Name",Number:21},"0563300160":{Item:"Name",Number:21},"0563300153":{Item:"Name mac",Number:21},"0563300161":{Item:"Name mac",Number:21},"0563300168":{Item:"Name beta",Number:21},"0563300169":{Item:"Name mac beta",Number:21},"0563300140":{Item:"Name1",Number:21},"0563300162":{Item:"Name1",Number:21},"0563300167":{Item:"Name1",Number:21},"0563300149":{Item:"Name8",Number:1},"0563300150":{Item:"Name8",Number:99}}')
//println("***DEBUG**** "+jsontest.Test1[1].Number)

String jsonString = JsonOutput.prettyPrint(responseText)
println ("DEBUG jason string "+jsonString)
println JsonOutput.prettyPrint(responseText)



def object = jsonSlurper.parseText(responseText)
int jsonSize = object.MRData.CircuitTable.Circuits.circuitId.size()

KeywordUtil log = new KeywordUtil()
log.logInfo("count of json side "+jsonSize)

//println "count of json side "+jsonSize
List <String> data = new ArrayList<>()

for (int i = 0; i < jsonSize; i++){
	println(object.MRData.CircuitTable.Circuits.circuitId[i])
	data.add(object.MRData.CircuitTable.Circuits.circuitId[i].toString())
}
//CustomKeywords.'spreadsheet.WriteToFile.writeToExcelJsonData'(data, 1, 2)

for (int x = 0; x < object.MRData.CircuitTable.Circuits.circuitName.size(); x++){
	println(object.MRData.CircuitTable.Circuits.circuitName[x])
	
}

for (int y = 0; y < object.MRData.CircuitTable.Circuits.circuitName.size(); y++){
	println(object.MRData.CircuitTable.Circuits.Location.country[y])
	
}
//def text = {"applications":[{"name":"test123","id":"c1257c5","description":"test","type":"generic","version":"0.1"},{"name":"Asset_1","id":"a9e0bce","description":"sfsdgdg","type":"generic","version":"0.1"},{"name":"Asset_2","id":"a9e0cd2","description":"sffgdgf","type":"generic","version":"0.1"}]}

def json = new JsonSlurper().parseText('{"applications":[{"name":"test123","id":"c1257c5","description":"test","type":"generic","version":"0.1"},{"name":"Asset_1","id":"a9e0bce","description":"sfsdgdg","type":"generic","version":"0.1"},{"name":"Asset_2","id":"a9e0cd2","description":"sffgdgf","type":"generic","version":"0.1"}]}')
println("***DEBUG**** "+json.applications[1].id)

def strString = json.applications[1].id
//used Gson to convert json to String
Gson gson = new Gson();
String jsonStringTest = gson.toJson(strString);
println ("DEBUG Gson used: "+jsonStringTest)


/*println JsonOutput.prettyPrint(text)
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText(text)*/

//assert object.response[0].id == 'c1257c5'
//println object.get("applications").get(0).get("id")
//JsonSlurper slurper = new JsonSlurper()
//Map parsedJson = slurper.parseText(response)
//println parsedJson.get("applications").get(0).get("id")

println "response: $response"
def cookiez = response.getHeaderFields()['Set-Cookie']
println cookiez
WS.verifyResponseStatusCode(response, 200)
WS.containsString(response, 'Bahrain International Circuit', false)


def js = new JsonSlurper().parseText('{"token": "ewrerydjgkfgijrtupk,nyouktu","tokenValidityTime":"300","signInStatus": "LoginSuccessfull","accounts": [{"accNumber": "6000112216066021","balance": "0","routingNumber": "122106316"}],"profileInformation": {"first_name": "Apple","last_name": "Test","sysDate": "2020-01-30T15:46:40.969Z"}}')
println("***DEBUG**** "+js.accounts[0].accNumber)
println("***DEBUG**** "+js.accounts[0].balance)
//***DEBUG**** 6000112216066021

// Parse the response
def restResponse = '{"token": "ewrerydjgkfgijrtupk,nyouktu","tokenValidityTime":"300","signInStatus": "LoginSuccessfull","accounts": [{"accNumber": "6000112216066021","balance": "0","routingNumber": "122106316"}],"profileInformation": {"first_name": "Apple","last_name": "Test","sysDate": "2020-01-30T15:46:40.969Z"}}'
def list = new JsonSlurper().parseText( restResponse )
// Print them out to make sure
list.each { println it }
String searchToken = ""
for (String s : list){
	if (s.startsWith("token=")){
		searchToken = s
		break;
	}	
}

println searchToken
String[] parts = searchToken.split("=");
String part1 = parts[0]; // 
String part2 = parts[1]; //
println part1
println part2 //here is token ewrerydjgkfgijrtupk,nyouktu

//String pattern = "^" + searchToken+"="+searchToken+"$";
//String tk = 

//def j = new JsonSlurper().parseText('{"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsifQ.eyJpc3MiOiJodHRwczovL2thcnJpa2luaXRtLmIyY2xvZ2luLmNvbS8yYzcwZDA1NC1mMzA5LTQxMDctOWZhOC04NDQ2NDc3NjQ3NGQvdjIuMC8iLCJleHAiOjE1ODAzOTk0OTYsIm5iZiI6MTU4MDM5OTE5NiwiYXVkIjoiZDgzZDgxZDMtZGY4OC00MzQxLWJiYWQtM2FlZGI4MjZjNjc4IiwiaWRwIjoiTG9jYWxBY2NvdW50Iiwib2lkIjoiMDIzYWY0NWEtZGUwYi00MjI3LWFlYzktOGUzMTRmYWRhNjM0Iiwic3ViIjoiMDIzYWY0NWEtZGUwYi00MjI3LWFlYzktOGUzMTRmYWRhNjM0IiwibmV3VXNlciI6ZmFsc2UsInRmcCI6IkIyQ18xX2tyay10ZXN0LXNpZ25JbiIsInNjcCI6InVzZXJfaW1wZXJzb25hdGlvbiIsImF6cCI6ImQ4M2Q4MWQzLWRmODgtNDM0MS1iYmFkLTNhZWRiODI2YzY3OCIsInZlciI6IjEuMCIsImlhdCI6MTU4MDM5OTE5Nn0.ADKJhdy5xwWjSWs_gOedEUJHGCqf5xe1wUqY9zjCw2CM80ttvTPp9YMID_dd-q8wIQpUxaw6BHn6CZQFVUS4d7_YC86IabEkvpN9ABiwH52ZjgcsQCKKWg0HTl6IgD9IFsCtV5zt2qulSrT_9xDu6OJ6UKRjON-tE0R33_HywUUu4VbvR4AuS1ez1Abs3SJN8W08aVQXDpqfosKjDz7MI-p6SUgeuSpjvtXKObe8rxwWkLlU4ixu-okY8u-PQPkVv4s8iKd7H7krDatDJAneM9V7ShvPrxTHyVPyq6uXtWNZQG7yTmGb0C17mTJJRrbbRvVOIrodx96UTvCujNi7yg","refresh_token": "eyJraWQiOiJjcGltY29yZV8wOTI1MjAxNSIsInZlciI6IjEuMCIsInppcCI6IkRlZmxhdGUiLCJzZXIiOiIxLjAifQ…fDY_S6qNi1s4t9uF.he647xSrk2kUXfrrH7Qt8iJXJmMTH5zAhVR2IZfn3b1h4zGN2MfPe-OkP4jGMFmbJO5Njz5085Yh-jfdEv2ApY_GfR23gN9BV4pk2Oh2NLZBQ0AgfA9OqGjGQK1wmx4K49h0FMDyyzirRQ1hgedlXJ_OspHIC6EvvuAyHhmI7DjcW7QUn3d2J2DtzzWu-8cpaczp7EX8LrVgNbppMVU0erJQRdaUKiKvodeSOpfHs_sOW4CuMhfI0gSDpbJNwRAyt3zU73Yigmk0uzM9f9kh7MRMfpkLmaavB3FrqoLCHZhB3FaPNg3yxosPfEtLfYnlT7ishw96kzSegL-39D–qnRTQ9orO6upuF6W38xuKGmZKGq3eNc0aNoXUaPU-ujRCNroF9dVvwgS5kp_pCKYY646vAnWQ_tGGp3oxTgj7dR3RVOWiUjPaxidnziY7ShZLw89Llu7k4QFSLEO9nuY-vKCEw-8_dPwdrf7OY5D5itzA8fSyJjHYl0AnFKLJtDANXoxcR80tGtb90Y-_BUofXF8i8wKSLPfLrTYzJRiZBGNM9RGVLf3TcMfkho-vuf0Ur6fV8btxalKMGL-fCudNjD5_FD05OEXjBg9cxGkvqYvskB1PompB8gL4E3hCDdvyeQ-zMd7X6rh5eIVICdeiqjIT0Q-AsBgHyXM0Ny3uMMRD4zWvMcjSxAUUPXe0LBNwZo.jkoLvQGGAUDthGwDnIO60Q","tokenValidityTime": "300","signInStatus": "LoginSuccessfull","accounts": [{"accNumber": "6000112216066021","balance": "0","routingNumber": "122106316"}],"profileInformation": {"first_name": "Apple","last_name": "Test","sysDate": "2020-01-30T15:46:40.969Z"}}')
//println("***DEBUG**** "+j.accounts[0].accNumber)
//***DEBUG**** 6000112216066021

//println("***DEBUG**** "+j.refresh_token[0].tokenValidityTime)
def accessString = ""
def refreshString = ""
restResponse = '{"token_type":"Bearer","expires_in":300,"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY2MTdjNjViOTQ4NzM0YjI3MDBhMzRkMWQyMDEzYTNlNWY3MGMzOGUwNjJlNmZlNjE3YTI3YjU5OTc2NTM1NjdmYTY0ZDczYWU3NmRiZGM2IyJhdWQiOiIyIiwianRpIjoiNjYxN2M2NWI5NDg3MzRiMjcwMGEzNGQxZDIwMTNhM2U1ZjcwYzM4ZTA2MmU2ZmU2MTdhMjdiNTk5NzY1MzU2N2ZhNjRkNzNhZTc2ZGJkYzYiLCJpYXQiOjE1ODA3MTM0NTYsIm5iZiI6MTU4MDcxMzQ1NiwiZXhwIjoxNjEyMjQ5NDU2LCJzdWIiOiI4Njk1NyIsInNjb3BlcyI6WyIqIl19.mwFJWZoZz7fv8gybBL1EjpbmrOLpcY4OEANpk65BF-l5XDCoyhZoef8T675-VgfomLKQjuzgb9siokq9LdmUNm1H1ZB0unnwECK4gYjz3Ua3oGVmED6oPeH3x8Gt_tCc60cdmHa0bxFyGwx2LsMYhy_HNHIzkJ1Xrk-mchuVYYowCd3hqzlh02QnPKNqe8bqi0ETfU6GK7sLKDwUgyOhDPTFSrdgDAe3QWukfiF4WBeLVKDNWjoyJ_BAwh20kEdpgNWlWKtDOALsLr2NSEHuQU-w-yXJ4JpwQj1SBULxw8nzEhsAmI916_FIH_OgNf7DLu8tnHOauQnJi2xpmQrf1u3633QhO5ah9GAuCafEDoVuIEmZoiykn118wqa5rwx9gVl9epM7zEDAUuaTagEZpRuqZVM5z-RPxtuWjf2EnRFgQphlp7d0pgoR2XEuPO_FsyaMrI_z-qVYjXLHnFto8gACaVug3O1sRDxBGZGt1Nvpyu-8gUA-_2wi1x2t9LzTwIn7qfB03HbnE9nolvBEIgX4GVN_uspPKtXR1NkKWepWqoWR2Dv0zl8XNUwCi0CaIzkM1-2YskHMj95gRT6jIhW6eSSUOOTT3yOkIAc1OJq5m_rfSCmdtMT_zoFYWkvUu3RNbbc3cZVQtJomegTXyB5Cpq8oOcgBB9k","refresh_token":"def50200aa6503e01d85876d8de0a812143744dba64187b2b24591cdba3840c0b0f013350a0d79fc601f346f356b4c702110045b3927b92f13d2339e612697ea13ee7c433d208af3b7b879d6b8c61ab1362659c5d8283ac3b50648d09f68fb165bb1c339c505b37f21e2fdb2e79a5562e65b8dbe1864697632fc98d454b1a93e6bfbc2803d6c1d0f3533e48a29b0a1e9097e20b5fb2931128de5fb49e59177045384d87cee4e597a22b14cf037617edbf93b9eedefad3281a216cd0cb134a9454858a8f36e798e1c78e4f4a8b7f17dc6370045ac785a115e7d95d90d53e0ea13fdc1e4d801eb517e84457504e39ffc8ab4996745006a0d8b3903d3d0379da9478eea966bdecba7d856ac341cf8c58929edb613fdd7af47f8932bf5d566e624b81621013632d902c6c7dcf28d37e4a3c52d8b5222df2748bdd8b44df7e042a8670f2cd2d92aaaaa70be70d979bf7d452f63dee7166900bcc4be7675f7cf054b865d408","region":"test"}'

list = new JsonSlurper().parseText( restResponse )
list.each { println it }

for (String s : list){
	if (s.startsWith("access_token=")){
		accessString = s
		break;
	}
}
parts = accessString.split("=");
String access1 = parts[0]; 
String access2 = parts[1]; 

for (String s : list){
	if (s.startsWith("refresh_token=")){
		refreshString = s
		break;
	}
}
parts = refreshString.split("=");
String refresh1 = parts[0];
String refresh2 = parts[1];

println("DEBUG access_token: "+accessString)
println("DEBUG refresh_token: "+refreshString)

println("DEBUG access_token: "+access1)
println("DEBUG access_token value: "+access2)
println("DEBUG refresh_token: "+refresh1)
println("DEBUG refresh_token value: "+refresh2)

//DEBUG access_token: access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY2MTdjNjViOTQ4NzM0YjI3MDBhMzRkMWQyMDEzYTNlNWY3MGMzOGUwNjJlNmZlNjE3YTI3YjU5OTc2NTM1NjdmYTY0ZDczYWU3NmRiZGM2IyJhdWQiOiIyIiwianRpIjoiNjYxN2M2NWI5NDg3MzRiMjcwMGEzNGQxZDIwMTNhM2U1ZjcwYzM4ZTA2MmU2ZmU2MTdhMjdiNTk5NzY1MzU2N2ZhNjRkNzNhZTc2ZGJkYzYiLCJpYXQiOjE1ODA3MTM0NTYsIm5iZiI6MTU4MDcxMzQ1NiwiZXhwIjoxNjEyMjQ5NDU2LCJzdWIiOiI4Njk1NyIsInNjb3BlcyI6WyIqIl19.mwFJWZoZz7fv8gybBL1EjpbmrOLpcY4OEANpk65BF-l5XDCoyhZoef8T675-VgfomLKQjuzgb9siokq9LdmUNm1H1ZB0unnwECK4gYjz3Ua3oGVmED6oPeH3x8Gt_tCc60cdmHa0bxFyGwx2LsMYhy_HNHIzkJ1Xrk-mchuVYYowCd3hqzlh02QnPKNqe8bqi0ETfU6GK7sLKDwUgyOhDPTFSrdgDAe3QWukfiF4WBeLVKDNWjoyJ_BAwh20kEdpgNWlWKtDOALsLr2NSEHuQU-w-yXJ4JpwQj1SBULxw8nzEhsAmI916_FIH_OgNf7DLu8tnHOauQnJi2xpmQrf1u3633QhO5ah9GAuCafEDoVuIEmZoiykn118wqa5rwx9gVl9epM7zEDAUuaTagEZpRuqZVM5z-RPxtuWjf2EnRFgQphlp7d0pgoR2XEuPO_FsyaMrI_z-qVYjXLHnFto8gACaVug3O1sRDxBGZGt1Nvpyu-8gUA-_2wi1x2t9LzTwIn7qfB03HbnE9nolvBEIgX4GVN_uspPKtXR1NkKWepWqoWR2Dv0zl8XNUwCi0CaIzkM1-2YskHMj95gRT6jIhW6eSSUOOTT3yOkIAc1OJq5m_rfSCmdtMT_zoFYWkvUu3RNbbc3cZVQtJomegTXyB5Cpq8oOcgBB9k
//DEBUG refresh_token: refresh_token=def50200aa6503e01d85876d8de0a812143744dba64187b2b24591cdba3840c0b0f013350a0d79fc601f346f356b4c702110045b3927b92f13d2339e612697ea13ee7c433d208af3b7b879d6b8c61ab1362659c5d8283ac3b50648d09f68fb165bb1c339c505b37f21e2fdb2e79a5562e65b8dbe1864697632fc98d454b1a93e6bfbc2803d6c1d0f3533e48a29b0a1e9097e20b5fb2931128de5fb49e59177045384d87cee4e597a22b14cf037617edbf93b9eedefad3281a216cd0cb134a9454858a8f36e798e1c78e4f4a8b7f17dc6370045ac785a115e7d95d90d53e0ea13fdc1e4d801eb517e84457504e39ffc8ab4996745006a0d8b3903d3d0379da9478eea966bdecba7d856ac341cf8c58929edb613fdd7af47f8932bf5d566e624b81621013632d902c6c7dcf28d37e4a3c52d8b5222df2748bdd8b44df7e042a8670f2cd2d92aaaaa70be70d979bf7d452f63dee7166900bcc4be7675f7cf054b865d408


