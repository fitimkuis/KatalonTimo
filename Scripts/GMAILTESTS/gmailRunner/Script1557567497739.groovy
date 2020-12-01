import java.util.logging.Logger

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;

import java.util.logging.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mail.gmail.util.GmailUtil
import mail.gmail.util.SendEmail

import com.kms.katalon.core.configuration.RunConfiguration

def testString = "Treat this as confidential. 869256 is your login OTP. Sharing it with anyone gives them full access to your Paytm Wallet. Paytm never calls to verify your OTP."

//will match any 6 digit number in the message
Pattern p = Pattern.compile("(|^)\\d{6}");
		
if(testString!=null){
	 Matcher m = p.matcher(testString);
	 if(m.find()) {
		 println(m.group(0));
	 }
   else
   {
	   println("no match");
   }
}


Logger LOGGER = Logger.getLogger("InfoLogging");

		Properties prop = new Properties();
		//def configPath = RunConfiguration.getProjectDir() + '/Include/PROPERTIES/config.properties'
		//println configPath
		InputStream input = new FileInputStream(RunConfiguration.getProjectDir() + '/Include/PROPERTIES/config.properties')
		//InputStream input = GmailRunner.class.getClassLoader().getResourceAsStream("gmailUser.properties");
		if (input == null) {
			LOGGER.info("Sorry, unable to find config.properties");
			return;
		}
		prop.load(input);
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");

		GmailUtil util = new GmailUtil();

		List<String> unreadEmails = new ArrayList<>();
		List<String> lastSent = new ArrayList<>();
		List<String> allEmails = new ArrayList<>();
		List<String> emailBySubject = new ArrayList<>();
		List<String> emailByTimeStamp = new ArrayList<>();
		List<String> searchByKeyword = new ArrayList<>();
		List<String> searchByDate = new ArrayList<>();
		List<String> deletedByKeyword = new ArrayList<>();
		
		//send email with or without attachement (password, username, sender, receiver, subject, body, attachement [if no then ""])
		String path = "C:/Users/fitim/Desktop/Tappara_banners.jpg"
		SendEmail mailSender;
		mailSender = new SendEmail(password,user, user,"Hello Katalon","Testing with gmail sender","");
		//mailSender = new SendEmail(password,user, user,"Hello Katalon with attachement","Gmail with attachement",path);

		//will delete email(s) by given search keyword (subject)
		deletedByKeyword = util.deleteMails(user, password, "Katalon");
		LOGGER.info("Deleted email(s) by keyword: " + deletedByKeyword);

		//will delete email(s) by given search keyword (from)
		GmailUtil util3 = new GmailUtil(user);
		deletedByKeyword = util3.deleteMailsByFrom(user, password);
		LOGGER.info("Deleted email(s) by from: " + deletedByKeyword);

		//will return all unread email from INBOX and set flag to SEEN
		unreadEmails = util.getUnreadGmails(user,password);
		LOGGER.info("latest unread message(s) contents: " + unreadEmails);

		//will return last sent email from the INBOX
		lastSent = util.getLatestSentEmail(user,password, "[Gmail]/Lähetetyt viestit");
		LOGGER.info("last sent message contents: " + lastSent);


		//String content = util.showUnreadMails(user,password,"pop.gmail.com");
		//String content = util.getLatestMailByDate(user,password,"pop.gmail.com");
		//String content = util.getLatestMailBySubject(user,password,"pop.gmail.com","testing");
		//LOGGER.info("latest message contents: " + content);

		//allEmails = util.showAllUnreadMails(user,password);
		//LOGGER.info("latest unread message(s) contents: " + allEmails);


		//emailBySubject = util.getEmailBySubject(user,password, "gmail test");
		//LOGGER.info("latest message contents: " + emailBySubject);

		//will return email search criteria by timestamp as "2019-05-10 20:42:43"
		emailByTimeStamp = util.getEmailByTimeStamp(user,password, "2018-11-13 06:13");
		LOGGER.info("search by timestamp contents: " + emailByTimeStamp);

		String keyword = "Metallican";
		//will return email with search criteria where subject contains keyword and will get first occurs of http tag and open it in browser
		searchByKeyword = util.searchEmailByKeyword(user, password, keyword);
		//LOGGER.info("latest message(s) contents search by keyword: " + searchByKeyword);

		//Pattern urlPattern = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);
		//Pattern urlPattern = Pattern.compile("^(?i)(http?|ftp)://.*$", Pattern.CASE_INSENSITIVE);
		Pattern urlPattern = Pattern.compile(".*http://.*",Pattern.CASE_INSENSITIVE);

		String urlString = searchByKeyword.get(0);
		//LOGGER.info(urlString.matches("^(http://|https|ftp)://.*$"));

		Matcher matcher = urlPattern.matcher(urlString);

		while (matcher.find()) {
			String address = matcher.group();

			LOGGER.info("Got URL: " + address);

			Runtime rt = Runtime.getRuntime();
			String url = address;
			rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
			break;
			//WebUI.openBrowser(address)
		}

		//will return all email(s) from INBOX sent by day(s) ago
		Date d = new Date();
		Date daysAgo = new DateTime(d).minusDays(1).toDate();
		GmailUtil util1 = new GmailUtil(daysAgo);
		searchByDate = util1.searchEmailByDate(user, password);
		//LOGGER.info("latest message(s) contents search by date: " + searchByDate);
		for (int i = 0; i < searchByDate.size(); i++){
			LOGGER.info("message "+i+" contents search by date: " + searchByDate.get(i));
		}
		
		