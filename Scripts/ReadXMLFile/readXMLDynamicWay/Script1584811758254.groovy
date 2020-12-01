import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.math.NumberUtils
import org.w3c.dom.*;

try {

	String xmlFilePath = System.getProperty("user.dir")+"\\Include\\xmlFiles\\stuff.xml";
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlFilePath);
	doc.getDocumentElement().normalize();
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	NodeList nList = doc.getElementsByTagName("report");

	int cntNlist = nList.getLength();
	int tagCounter = 0;
	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	Document document = docBuilder.parse(xmlFilePath);


	List<String> tags = new ArrayList<>();
	NodeList nodeList = document.getElementsByTagName("*");
	//get count of R tag names in a node
	int countReport = 0;
	for(int i = 0; i < nodeList.getLength(); i++){
		Node node = nodeList.item(i);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.getNodeName().equals("report")){
				countReport++;
			} 
			if (node.getNodeName().contains("R")){
				tags.add(node.getNodeName());
				tagCounter++;
			}
			if(countReport > 1){
				break;
			}
			//System.out.println(node.getNodeName());
		}

	}

	//dynamic list for tag values
	HashMap<String,ArrayList<String>> maps1 = new HashMap<String, ArrayList<String>>();
	String tag = tags.get(0);
	String latest = tags.get(tags.size() - 1);
	latest = latest.replace("R","");
	int latestTag = Integer.parseInt(latest);
	tag = tag.replace("R","");
	int startTag = Integer.parseInt(tag);
	for (int i = 0; i <= tagCounter-1; i++) {
		maps1.put("r" + startTag, new ArrayList<>());
		startTag++;
	}

	startTag = Integer.parseInt(tag);
	//get R values from xml
	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
			for (int i = 0; i <= tagCounter-1; i++) {
				//if(maps1.get("r" + startTag).add(eElement.getElementsByTagName("R" + startTag).item(0).getTextContent() != null)){
					maps1.get("r" + startTag).add(eElement.getElementsByTagName("R" + startTag).item(0).getTextContent());
					startTag++;
					if (startTag >= latestTag + 1) {
						startTag = Integer.parseInt(tag);
					}
				//}
			}
		}
	}
	
	startTag = Integer.parseInt(tag);
	//TODO print out and calculate
	HashMap<Integer,ArrayList<Integer>> maps2 = new HashMap<Integer, ArrayList<Integer>>();
	for (int i = 0; i <= tagCounter-1; i++){
		maps2.put(startTag, new ArrayList<>());
		startTag++;
	}
            //add maps1 values to maps2 as integer
            startTag = Integer.parseInt(tag);
            //String val = maps1.get("r"+startTag).get(0);
            for (int i = 0; i < nList.getLength(); i++){

                for (int j = 0; j <= tagCounter-1; j++) {
					
						boolean digits = NumberUtils.isDigits(maps1.get("r" + startTag).get(i));
	
	                    if (maps1.get("r"+startTag).get(i).equals("") || digits == false){
	                        maps2.get(startTag).add(0);
	                        //maps2.get(startTag).add(Integer.parseInt(maps1.get("r" + startTag).get(0)));
	                    }
	                    else{
	                        maps2.get(startTag).add(Integer.parseInt(maps1.get("r" + startTag).get(i)));
	                    }
	                    startTag++;
	                    if (startTag >= latestTag + 1) {
	                        startTag = Integer.parseInt(tag);
	                    }
                }
            }
			
            startTag = Integer.parseInt(tag);
            System.out.println("Node values");
            int sumCounter = 1;
            int nodesSum = 0;
            int getCounter = 0;
            int tempSum = 0;
            for (int i = 0; i < nList.getLength(); i++) {
                for (int j = 0; j <= tagCounter-1; j++) {
                    if (maps2.get(startTag).get(i) != 0) {
                        nodesSum += maps2.get(startTag).get(i);
                        tempSum += maps2.get(startTag).get(i);
                    }
                    else{
                        nodesSum += 0;
                        tempSum += 0;
                        System.out.println("node R"+startTag+ " value: "+maps1.get("r"+startTag).get(getCounter));

                    }
                    //System.out.println("node R"+startTag+ " sum: "+nodesSum);
                    System.out.println("node R"+startTag+ " value: "+maps2.get(startTag).get(getCounter));
                    //System.out.println("node R"+startTag+ " value: "+maps1.get(startTag).get(0));
                    if (sumCounter % tagCounter == 0){
                        System.out.println("nodes sum: "+nodesSum);
                        getCounter++;
                        nodesSum = 0;
                    }
                    startTag++;
                    if (startTag >= latestTag + 1) {
                        startTag = Integer.parseInt(tag);
                    }
                    sumCounter++;
                }
            }

            System.out.println("R Node sum values: "+tempSum);

} catch (Exception e) {
	e.printStackTrace();
}