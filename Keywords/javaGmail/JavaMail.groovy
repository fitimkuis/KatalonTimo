package javaGmail

import com.kms.katalon.core.annotation.Keyword

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

public class JavaMail {

	/**
	 * Searches for e-mail messages containing the specified keyword in
	 * Subject field.
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param keyword
	 */
	@Keyword
	public void searchEmail(String host, String userName,
			String password, final String keyword) {
		/*Properties properties = new Properties();
		 // server setting
		 properties.put("mail.imap.host", host);
		 properties.put("mail.imap.port", port);
		 // SSL setting
		 properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		 properties.setProperty("mail.imap.socketFactory.fallback", "false");
		 properties.setProperty("mail.imap.socketFactory.port",String.valueOf(port));
		 Session session = Session.getDefaultInstance(properties);*/
		//String host = "imap.gmail.com";
		//String username = GlobalVariable.gmailUser;
		//password = GlobalVariable.pass;

		try {
			// connects to the message store
			//Store store = session.getStore("imap");
			//store.connect(userName, password);

			Properties props = new Properties();
			props.setProperty("mail.imap.ssl.enable", "true");
			// set any other needed mail.imap.* properties here
			Session session = Session.getInstance(props);
			Store store = session.getStore("imap");
			store.connect(host, userName, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_WRITE);

			// creates a search criterion
			SearchTerm searchCondition = new SearchTerm() {
						@Override
						public boolean match(Message message) {
							System.out.println("Found message #: " + message.getSubject());
							try {
								if (message.getSubject().contains("Katalon")){
									return true;
								}
								/*else{
						 System.out.println("no emails");
						 }*/
							} catch (MessagingException ex) {
								ex.printStackTrace();
							}
							return false;
						}
					};

			//SentDateSearchTerm date = new SentDateSearchTerm(afterDate)

			//System.out.println("Are we able to be here? "+date.toString());
			//Flags recent = new Flags(Flags.Flag.RECENT);
			//Message[] foundMessages = folderInbox.search(Flags.Flag.RECENT);
			// performs search through the folder
			//Message[] foundMessages = folderInbox.search(date);

			System.out.println("Are we able to be here?");


			Message[] foundMessages = folderInbox.search(searchCondition);

			//To get the latest 5 mails use:
			int count = folderInbox.getMessageCount();
			foundMessages= folderInbox.getMessages(count-5,count);

			if (foundMessages.length == 0){
				System.out.println("No messages found.");
				throw new com.kms.katalon.core.exception.StepErrorException("No unread mesages will found!!!");
			}
			else{
				for (int i = 0; i < foundMessages.length; i++) {
					Message message = foundMessages[i];
					String subject = message.getSubject();
					String getDate = message.getReceivedDate()
					String sentDate = message.getSentDate()
					System.out.println("Found message #" + i + ": " + subject+" recivedDate "+getDate+" sentDate "+sentDate);
				}
			}

			//

			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store.");
			ex.printStackTrace();
		}
	}

}

