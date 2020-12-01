package datePicker

import java.security.Provider
import java.security.Security

import org.bouncycastle.jce.provider.BouncyCastleProvider

import com.kms.katalon.core.annotation.Keyword


public class Classpaths {

	@Keyword
	public void getSystemInfo(){

		String Name= "BC";
		if (Security.getProvider(Name) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}

		if (Security.getProvider(Name) == null) {
			System.out.println("not installed");
		}
		else {
			System.out.println("installed");
		}
		ClassLoader cl = ClassLoader.getSystemClassLoader();

		URL[] urls = ((URLClassLoader)cl).getURLs();

		for(URL url: urls){
			System.out.println(url.getFile());
		}

		Provider[] providers = Security.getProviders();
		for (Provider p : providers) {
			System.out.println("Provider Name : " + p.getName());
			System.out.println("Provider Information : " + p.getInfo());
			System.out.println("Provider Version : " + p.getVersion());

			Set entries = p.entrySet();
			Iterator iterator = entries.iterator();
			while (iterator.hasNext())
				System.out.println("Property Entry : " + iterator.next())

			System.out.println("-------------------------------");
		};
	}
}
