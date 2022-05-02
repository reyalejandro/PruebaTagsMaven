package util;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class DeleteEmail {

	private DeleteEmail() {
		
	}
	private static String host = "outlook.office365.com";
	private static String port = "993";
	
	
	public static void borrarCorreos(String username,String pass,String subjectToDelete){
		deleteMessages(host, port, username, pass, subjectToDelete);
	}
	

	public static void deleteMessages(String host, String port, String userName, String password,String subjectToDelete) {
		Properties properties = new Properties();

		// server setting
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", port);

		// SSL setting
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));

		Session session = Session.getDefaultInstance(properties);

		try {
			  // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);
 
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
 
            // fetches new messages from server
            Message[] arrayMessages = folderInbox.getMessages();
 
            for (int i = 0; i < arrayMessages.length; i++) {
                Message message = arrayMessages[i];
                String subject = message.getSubject();
                if (subject.contains(subjectToDelete)) {
                    message.setFlag(Flags.Flag.DELETED, true);
                    System.out.println("Marked DELETE for message: " + subject);
                }
 
            }
 
            // expunges the folder to remove messages which are marked deleted
            boolean expunge = true;
            folderInbox.close(expunge);

			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store.");
			ex.printStackTrace();
		}
	}
	

    public static void send(){

        final String username = "PruebasQAAutomatizas@gmail.com";
        final String password = "Automation1527";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("PruebasQAAutomatizas2@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("PruebasQAAutomatizas2@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


	

}
