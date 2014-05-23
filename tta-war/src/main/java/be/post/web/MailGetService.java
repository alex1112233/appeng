package be.post.web;

import java.io.IOException; 
import java.util.Properties; 
import java.util.UUID;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session; 
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 
import javax.servlet.http.*; 

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class MailGetService extends HttpServlet { 
	
	
	private static final Logger log = Logger.getLogger(MailGetService.class.getName());
	
    @Override
    public void doPost(HttpServletRequest req, 
                       HttpServletResponse resp) 
            throws IOException { 
        Properties props = new Properties(); 
        Session session = Session.getDefaultInstance(props, null); 
        try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			Object content = message.getContent();
			
			Address[] addresses = message.getReplyTo();
			
			Key key = KeyFactory.createKey("MailMessage", UUID.randomUUID().toString());
			Entity mail = new Entity("Mail", key);
			   
			mail.setProperty("content", content.toString());

			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(mail);		
			
			//message.
		      
        
      
            String msgBody = "replay to your message";
            
            MimeMessage reply = (MimeMessage)message.reply(false);
//Set the from field
reply.setFrom(new InternetAddress("string@tta10333.appspotmail.com", "Example.com Admin"));
log.info("message.getFrom()[0]: " + message.getFrom()[0]);
reply.addRecipient(Message.RecipientType.TO, new InternetAddress("alex1033@gmail.com", "Alex"));
//            reply.setFrom(new InternetAddress("president@whitehouse.gov"));reply.setText("Thanks");Transport.send(reply);
//            Message msg = new MimeMessage(session);
//           
//            msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
//            msg.addRecipient(Message.RecipientType.TO,
//                             new InternetAddress("user@example.com", "Mr. User"));
//            msg.setSubject("Your Example.com account has been activated");
            reply.setText(msgBody);
            Transport.send(reply);
            
          
            
          
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("string@tta10333.appspotmail.com", "Example.com Admin"));
                msg.addRecipient(Message.RecipientType.TO,
                                 new InternetAddress("alex1033@gmail.com", "Mr. User"));
                msg.setSubject("Your Example.com account has been activated");
                msg.setText(msgBody);
                Transport.send(msg);

                log.info("all mails sent");
          

        } catch (Exception e) {
        	log.severe("error in sending mail: " + e.getMessage());
        }
    }
}
