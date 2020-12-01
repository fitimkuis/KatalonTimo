package readGmail

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

import javax.mail.*
import javax.mail.search.*;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.exception.StepErrorException

import internal.GlobalVariable as GlobalVariable

public class getGmail {

	public String fname

	@Keyword
	public String getGmailAttachment(){
		Date afterDate;
		String  gmailServer
		int gmailPort
		def user, password, LIMIT
		def inboxFolder, root, StartDate, EndDate

		//    Downloads all attachments from a gmail mail box as per some criteria
		//    to a specific folder
		//    Based on code from
		//    http://agileice.blogspot.com/2008/10/using-groovy-to-connect-to-gmail.html
		//    http://stackoverflow.com/questions/155504/download-mail-attachment-with-java
		//
		//    Requires:
		//        java mail jars in the class path (mail.jar and activation.jar)
		//        openssl, with gmail certificate added to java keystore (see agileice blog)
		//
		//    further improvement: maybe findAll could be used to filter messages
		//    subject could be added as another criteria
		////////////////////// <CONFIGURATION> //////////////////////
		// Maximm number of emails to access in case parameter range is too high

		LIMIT = 10

		// gmail credentials
		//gmailServer = "imap.gmail.com"
		//gmailPort = 587

		//user = GlobalVariable.gmailUser
		//password = GlobalVariable.pass

		// gmail label, or "INBOX" for inbox
		inboxFolder = "INBOX"

		// local file system where the attachment files need to be stored
		//def saveDirectory = "C:\\Users\\fitim\\Desktop\\KatalonStudio\\TestResults"

		// date range dd-mm-yyyy
		//StartDate= "2-4-2018"
		//EndDate = "2-4-2018"
		////////////////////// </CONFIGURATION> //////////////////////

		Properties props = new Properties();
		//props.load(new FileInputStream(new File( "C:\\Users\\fitim\\Desktop\\gmail\\smtp.properties" )));
		props.load(new FileInputStream(new File( "C:/Users/fitim/Google Drive/Desktop2019/tyopoydalta_2019/gmail/smtp.properties" )));
		Session session = Session.getDefaultInstance(props, null);

		//StartDate = Date.parse("dd-MM-yyyy", StartDate)
		//EndDate = Date.parse("dd-MM-yyyy", EndDate)
		password = GlobalVariable.pass;
		String username = GlobalVariable.gmailUser;
		Store store = session.getStore("imaps");
		///store.connect("smtp.gmail.com", username,password);
		store.connect("smtp.gmail.com", "fitimkuis@gmail.com", "ModeeMi16");

		int i = 0;
		def folder = store.getFolder(inboxFolder)

		folder.open(Folder.READ_WRITE)

		// search for all "unseen" messages
		Flags seen = new Flags(Flags.Flag.SEEN);
		///Flags recent = new Flags(Flags.Flag.RECENT);
		///FlagTerm recentFlagTerm = new FlagTerm(recent, true);
		FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
		Message[] messages = folder.search(unseenFlagTerm);
		///Message[] messages = folder.search(recentFlagTerm);

		//mark gmail message as read
		//folder.setFlags(new Message[] {message}, new Flags(Flags.Flag.SEEN), true);
		//folder.setFlags(messages, new Flags(Flags.Flag.SEEN), true);


		//To get the latest 1 mails use:
		//int count = folder.getMessageCount();
		//messages= folder.getMessages(count-1,count);

		/*
		 // creates a search criterion
		 SearchTerm searchCondition = new SearchTerm() {
		 @Override
		 public boolean match(Message message) {
		 try {
		 if (message.getSentDate().after(afterDate)) {
		 return true;
		 }
		 } catch (MessagingException ex) {
		 ex.printStackTrace();
		 }
		 return false;
		 }
		 };
		 */

		// search for messages delivered in the last day
		//Message[] messages = folder.search(new YoungerTerm(24 * 60 * 60));

		//Thread.sleep(60000)
		if (messages.length == 0){
			System.out.println("No messages found.");
			throw new com.kms.katalon.core.exception.StepErrorException("No unread mesages will found!!!");
		}


		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		afterDate = new Date()
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String reportDate = df.format(afterDate);
		//reportDate ="06-04-2018"

		int z,n;
		for(z = 0; z<messages.length; z++){
			Message message = messages[z];

			System.out.println(i + ": " + messages[i].getFrom()[0]+ "\t" + messages[i].getSubject());
			System.out.println("Wanted the content? [Y to read/Q to end]");
			System.out.println("Date : " + message.getSentDate());
			System.out.println("report date : " + reportDate);
			String messageDate = df.format(message.getSentDate());
			System.out.println("message date : " + messageDate);

			if (reportDate.equals(messageDate)){
				System.out.println("*****dates match****");
			}else{
				System.out.println("dates NOT match");
			}


			String ans = "y";
			ans=ans.toLowerCase();
			if (messageDate.equals(reportDate)) {
				Object content = messages[i].getContent();
				if (content instanceof Multipart) {
					handleMultipart((Multipart)content);
				}
				else {
					handlePart(messages[i]);
				}
			}
			else {
				throw new com.kms.katalon.core.exception.StepErrorException("dates not match!!!");
				break;
			}

		}

		//mark gmail message as read
		//folder.setFlags(new Message[] {message}, new Flags(Flags.Flag.SEEN), true);
		folder.setFlags(messages, new Flags(Flags.Flag.SEEN), true);

		folder.close(true);
		store.close();
		return fname
	}

	public String handleMultipart(Multipart multipart) throws MessagingException, IOException {
		for (int i=0; i<multipart.getCount(); i++){
			handlePart(multipart.getBodyPart(i));
		}
	}

	public String handlePart(Part part)  throws MessagingException, IOException {
		String dposition = part.getDisposition();
		String cType = part.getContentType();
		if (dposition == null) {
			System.out.println("Null"+ cType);
			if ((cType.length() >= 10) && (cType.toLowerCase().substring(0, 10).equals("text/plain"))) {
				part.writeTo(System.out);
			}
			else {
				System.out.println("Other: " + cType);
				part.writeTo(System.out);
			}
		}
		else if (dposition.equalsIgnoreCase(Part.ATTACHMENT)) {
			System.out.println("Attachment"+ part.getFileName() + " : " + cType);
			saveFile(part.getFileName(), part.getInputStream());
			fname = part.getFileName()
			println("######## DEBUG attachment ##### "+fname)
		}
		else if (dposition.equalsIgnoreCase(Part.INLINE)) {
			System.out.println("Inline"+ part.getFileName() +  " : " + cType);
			saveFile(part.getFileName(), part.getInputStream());
			fname = part.getFileName()
			println("######## DEBUG inline ##### "+fname)
		}
		else {
			System.out.println("Other dposition");
		}
	}

	public static void saveFile(String filename,InputStream input) throws IOException {
		if (filename == null) {
			filename = File.createTempFile("MailAttacheFile", ".out").getName();
		}
		System.out.println("downloadingAttachment..."+filename);
		File file = new File(filename);
		for (int i=0; file.exists(); i++) {
			file = new File(filename+i);
		}
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		BufferedInputStream bis = new BufferedInputStream(input);
		int fByte;
		while ((fByte = bis.read()) != -1) {
			bos.write(fByte);
		}
		bos.flush();
		bos.close();
		bis.close();
		System.out.println("done...");
	}

}
