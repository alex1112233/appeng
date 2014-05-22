package be.post.web;

import java.io.IOException; 
import java.util.Properties; 
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session; 
import javax.mail.internet.MimeMessage; 
import javax.servlet.http.*; 

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class MailGetService extends HttpServlet { 
	
    @Override
    public void doPost(HttpServletRequest req, 
                       HttpServletResponse resp) 
            throws IOException { 
        Properties props = new Properties(); 
        Session session = Session.getDefaultInstance(props, null); 
        try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			Object content = message.getContent();
			
			Key key = KeyFactory.createKey("MailMessage", UUID.randomUUID().toString());
			Entity mail = new Entity("Mail", key);
			   
			mail.setProperty("content", content);

			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(mail);		
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
    }
}
