package be.post.activemq;

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

public class MqClient {

public static String getSyncResp(String mqConnectString, String queueName, String msgText, long waitTimeMs) throws Exception {
		
		// String mqConnectString = "tcp://L211396:61616"; // "vm://localhost";
       
		  try {
              // Create a ConnectionFactory
              ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqConnectString);

              // Create a Connection
              Connection connection = connectionFactory.createConnection();
              connection.start();

              // Create a Session
              Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

              // Create the destination (Topic or Queue)
              Destination destination = session.createQueue(queueName);

              // Create a MessageProducer from the Session to the Topic or Queue
              MessageProducer producer = session.createProducer(destination);
              producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

             
              TextMessage message = session.createTextMessage(msgText);
              TemporaryQueue tempQueue = session.createTemporaryQueue();
              message.setJMSReplyTo(tempQueue);

              // Tell the producer to send the message
              System.out.println("Sent message: "+ message.hashCode() );
              producer.send(message);
              
              // Create a MessageConsumer from the Session to the Topic or Queue
              MessageConsumer consumer = session.createConsumer(tempQueue);

              // Wait for a message
              Message messageRec = consumer.receive(waitTimeMs);

              if (messageRec instanceof TextMessage) {
                  TextMessage textMessage = (TextMessage) messageRec;
                  String text = textMessage.getText();
                  System.out.println("Received: " + text);
                  return text;
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
		return null;
     
    } 
 
}
