package be.post.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ImageRenderingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   

	 static ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://L211396:61616");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		PrintWriter writer = response.getWriter();
		
		
		  String text = "nothing received";
		  try {
              // Create a ConnectionFactory
            
              // Create a Connection
              Connection connection = connectionFactory.createConnection();
              connection.start();

              // Create a Session
              Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

              // Create the destination (Topic or Queue)
              Destination destination = session.createQueue("test");           
              
              // Create a MessageConsumer from the Session to the Topic or Queue
              MessageConsumer consumer = session.createConsumer(destination);

              // Wait for a message
              Message messageRec = consumer.receive(10000);

              if (messageRec instanceof TextMessage) {
            	  
            	  HttpSession httpSession = request.getSession();
            	  
            	  RequestTO requestTO = new RequestTO();
          		
          		  String requestId =  UUID.randomUUID().toString(); 
          		  
                  TextMessage textMessage = (TextMessage) messageRec;
                  text = textMessage.getText();
                  System.out.println("Received: " + text);
                  
                  requestTO.setRequestId(requestId);
                  requestTO.setReplyJmsDestination(messageRec.getJMSReplyTo());
                  requestTO.setRequestText(text);
                  
                  httpSession.setAttribute(requestId, requestTO);
                  
                  writer.print("{\"requestId\":\"" + requestId +"\", \"message\":\"" + text +"\"}");
                  
                 
                 
                  
              } else {
                  System.out.println("Received: " + messageRec);
                  writer.print("{\"message\":\"no_valid_request\"}");
              }

              consumer.close();

              // Clean up
              session.close();
              connection.close();
          }
          catch (Exception e) {
              System.out.println("Caught: " + e);
              e.printStackTrace();
          }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
