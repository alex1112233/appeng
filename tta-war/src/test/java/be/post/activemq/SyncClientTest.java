package be.post.activemq;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SyncClientTest {

	public static void main(String[] args) throws Exception {
		
		String mqConnectString = "tcp://L211396:61616"; // "vm://localhost";
       
		  try {
              // Create a ConnectionFactory
              ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqConnectString);

              // Create a Connection
              Connection connection = connectionFactory.createConnection();
              connection.start();

              // Create a Session
              Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

              // Create the destination (Topic or Queue)
              Destination destination = session.createQueue("test");

              // Create a MessageProducer from the Session to the Topic or Queue
              MessageProducer producer = session.createProducer(destination);
              producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

              // Create a messages
              String text = "message sent:" + new Date().toString();
              TextMessage message = session.createTextMessage(text);
              TemporaryQueue tempQueue = session.createTemporaryQueue();
              message.setJMSReplyTo(tempQueue);

              // Tell the producer to send the message
              System.out.println("Sent message: "+ message.hashCode() );
              producer.send(message);
              
              // Create a MessageConsumer from the Session to the Topic or Queue
              MessageConsumer consumer = session.createConsumer(tempQueue);

              // Wait for a message
              Message messageRec = consumer.receive(10000);

              if (messageRec instanceof TextMessage) {
                  TextMessage textMessage = (TextMessage) messageRec;
                  text = textMessage.getText();
                  System.out.println("Received: " + text);
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
     
    }
 
    
 
   
 
    public static class HelloWorldConsumer implements Runnable, ExceptionListener {
        public void run() {
            try {
 
                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
 
                // Create a Connection
                Connection connection = connectionFactory.createConnection();
                connection.start();
 
                connection.setExceptionListener(this);
 
                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
 
                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("TEST.FOO");
 
                // Create a MessageConsumer from the Session to the Topic or Queue
                MessageConsumer consumer = session.createConsumer(destination);
 
                // Wait for a message
                Message message = consumer.receive(1000);
 
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                } else {
                    System.out.println("Received: " + message);
                }
 
                consumer.close();
                session.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }
 
        public synchronized void onException(JMSException ex) {
            System.out.println("JMS Exception occured.  Shutting down client.");
        }
    }
    
}
