package com.activemq.demo.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	
	public static final int SEND_NUMBER = 2000;

	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		
		Connection connection = null;
		
		Session session;
		
		Destination destination;
		
		MessageProducer producer;
		
		connectionFactory = new ActiveMQConnectionFactory(
								ActiveMQConnection.DEFAULT_USER,
								ActiveMQConnection.DEFAULT_PASSWORD,
								"tcp://localhost:61616");
		
		
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			sendMessage(session, producer);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != connection){  
	              try {  
	                  connection.close();  
	              } catch (JMSException e) {  
	                  e.printStackTrace();  
	              }  
	           }      
		}
	}
	
	
	public static void sendMessage(Session session, MessageProducer producer) throws Exception {
		for (int i=0; i<= SEND_NUMBER; i++) {
			TextMessage message = session.createTextMessage("ActiveMQ发送消息"+i);
			System.out.println("发送消息：ActiveMQ发送的消息"+i);
			producer.send(message);
		}
	}

}
