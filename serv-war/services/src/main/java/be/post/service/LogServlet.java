package be.post.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Servlet implementation class LogServlet
 */
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter writer = response.getWriter();
		  
		  
		  String text = "nothing received";
		  try {
              // Create a ConnectionFactory
              ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://L211396:61616");

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
              Message messageRec = consumer.receive(100000);

              if (messageRec instanceof TextMessage) {
                  TextMessage textMessage = (TextMessage) messageRec;
                  text = textMessage.getText();
                  System.out.println("Received: " + text);
                  
                  // Create a MessageProducer from the Session to the Topic or Queue
                  MessageProducer producer = session.createProducer( messageRec.getJMSReplyTo());
                  producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

                  // Create a messages
                  String replyText = "reply to the message: " + text;
                  TextMessage message = session.createTextMessage(replyText);
                 

                  // Tell the producer to send the message
                  System.out.println("Sent message: "+ message.hashCode() );
                  producer.send(message);
                  producer.close();
                 
                  
              } else {
                  System.out.println("Received: " + messageRec);
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
		  writer.print("LogServlet gives message:" + text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
