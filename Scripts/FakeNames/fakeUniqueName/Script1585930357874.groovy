import java.util.stream.Collectors

import com.github.javafaker.Faker


Random rand = new Random();

List<String> names = new ArrayList<>();

for(int i = 0; i < 100; i++){
	Faker faker = new Faker(new Random(rand.nextInt(100000)));
	String name = faker.name().firstName();
	names.add(name);
	//System.out.println(name);
}
//Set<String> namesSet = new HashSet<String>(names);

List uniqueList = names.stream().distinct().collect(Collectors.toList());

List<String> sortedNames = uniqueList.stream().sorted().collect(Collectors.toList());

PrintWriter pw = null;
try {
	pw = new PrintWriter(new File("unique_names.csv"));
} catch (FileNotFoundException e) {
	e.printStackTrace();
}
StringBuilder builder = new StringBuilder();
//String columnNamesList = "NAME";
// No need give the headers Like: id, Name on builder.append
//builder.append(columnNamesList +"\n");

for(String s: sortedNames){
	builder.append(s);
	builder.append('\n');
	//System.out.println(s);
}
pw.write(builder.toString());
pw.close();
System.out.println("done!");
System.out.println("unique names in a list: "+uniqueList.size());


