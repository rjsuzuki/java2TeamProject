
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
//import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EmailSMTP {
	
	public boolean sendMail(String email, Defect defect) throws NamingException {
    	boolean status = false;
    	
    	//Context initCtx = new InitialContext();
    	//Context envCtx = (Context) initCtx.lookup("java:comp/env");
    	//Session session = (Session) envCtx.lookup("mail/Session");
    	
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "putulpatel@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";
        //String host = "smtp.gmail.com";
        
        //String port = "587";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        //properties.setProperty("mail.smtp.port", port);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("This is the Subject Line!");

           // Now set the actual message
           message.setText("This is actual message");

           // Send message
           //Transport.send(message);
           System.out.println("Sent message successfully....");
           status = true;
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
        
        return status;
		
	}

}
