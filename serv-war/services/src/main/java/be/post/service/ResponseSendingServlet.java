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
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ResponseSendingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		PrintWriter writer = response.getWriter();
		  
		HttpSession httpSession = request.getSession();
		Object requestTO = httpSession.getAttribute(request.getParameter("reqId"));
		
		if(requestTO instanceof RequestTO){
		
		 
		  try {
              // Create a ConnectionFactory
              ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://L211396:61616");

              // Create a Connection
              Connection connection = connectionFactory.createConnection();
              connection.start();

              // Create a Session
              Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

              MessageProducer producer = session.createProducer( ((RequestTO) requestTO).getReplyJmsDestination());
              producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

              // Create a messages
           
              TextMessage message = session.createTextMessage(request.getParameter("text"));
              producer.send(message);
 
              // Clean up
              session.close();
              connection.close();
          }
          catch (Exception e) {
              System.out.println("Caught: " + e);
              e.printStackTrace();
              writer.print(e.getMessage());
          }
		  writer.print("respnse sent" );
		  
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
