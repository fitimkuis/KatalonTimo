package my

import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObjectXpath

/**
 * format a Test Object into a JSON string
 * 
 * @author kazurayam
 *
 */
class TestObjectFormatter {

	/**
	 * hidden constructor
	 */
	TestObjectFormatter() {}

	/**
	 * convert a test object into string
	 *
	 * @param TestObject
	 * @return
	 */
	static String format(TestObject to) {
		StringBuilder sb = new StringBuilder()
		sb.append("{")
		// objectId
		sb.append("\"objectId\":\"")
		sb.append(to.getObjectId())
		sb.append("\"")
		// selectorMethod
		sb.append(",")
		sb.append("\"selectorMethod\":\"")
		sb.append(to.getSelectorMethod())  // XPATH,BASIC,CSS
		sb.append("\"")
		// XPATH detail
		if (to.getSelectorMethod() == SelectorMethod.XPATH) {
			sb.append(",")
			sb.append("\"activeXpaths\":")
			sb.append("[")
			int countx = 0
			for (TestObjectXpath xp : to.getActiveXpaths()) {
				if (countx > 0) {
					sb.append(",")
				}
				sb.append("{")
				sb.append("\"name\":\"")
				sb.append(xp.getName())
				sb.append("\",\"value\":\"")
				sb.append(xp.getValue())
				sb.append("\"")
				sb.append("}")
				countx += 1
			}
			sb.append("]")
		}
		// BASIC detail
		if (to.getSelectorMethod() == SelectorMethod."BASIC") {
			sb.append(",")
			sb.append("\"activeProperties\":")
			sb.append("[")
			int countb = 0
			for (TestObjectProperty top : to.getActiveProperties()) {
				if (countb > 0) {
					sb.append(",")
				}
				sb.append("{")
				sb.append("\"name\":\"")
				sb.append(top.getName())
				sb.append("\"")
				sb.append(",")
				sb.append("\"conditionType\":\"")
				sb.append(top.getCondition().toString())
				sb.append("\"")
				sb.append(",")
				sb.append("\"value\":\"")
				sb.append(top.getValue())
				sb.append("\"")
				sb.append(",")
				sb.append("\"isActive\":\"")
				sb.append(top.isActive())
				sb.append("\"")
				sb.append("}")
				countb += 1
			}
			sb.append("]")
		}
		// CSS detail
		// TODO
		sb.append("}")
		return sb.toString()
	}

}
