package be.post.activemq;

import static org.junit.Assert.*;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class MessageSendTest {

	@Test
	public void testSendMessage() {
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
            
              // Clean up
              session.close();
              connection.close();
          }
          catch (Exception e) {
              System.out.println("Caught: " + e);
              e.printStackTrace();
          }
	}

}
