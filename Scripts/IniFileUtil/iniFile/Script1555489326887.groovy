import internal.GlobalVariable

//CustomKeywords.'sshUtil.ReadSSHFile.readSsh'()

CustomKeywords.'com.global.variables.CreateGlobalVariables.addGlobalVariable'('localURL', 'katalon.com')
println GlobalVariable.localURL

CustomKeywords.'com.global.variables.CreateGlobalVariables.addGlobalVariable'('age', 53)
println GlobalVariable.age

CustomKeywords.'com.global.variables.CreateGlobalVariables.addGlobalVariable'('salary', 5800.99)
println GlobalVariable.salary  //created new global variable without defining it

List <Object> ret = new ArrayList<>()
ret = CustomKeywords.'com.ini.IniUtil.readInitFile'()
Object checkValue = 24
for (Object r: ret){
	System.out.print("Value: " + r + "\n");
	if (r.equals(checkValue)){
		print "value is in .ini file"
		break
	}
	else
		print "value is NOT in .ini file"
}

CustomKeywords.'com.ini.IniUtil.simpleIni'()

CustomKeywords.'com.ini.IniUtil.HierarchicalINIConfigurationExample'()




