package com.activemq.demo.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) {  
	       // ConnectionFactory �����ӹ�����JMS������������  
	        ConnectionFactory connectionFactory;  
	        // Connection ��JMS�ͻ��˵�JMS Provider������  
	        Connection connection = null;  
	        // Session��һ�����ͻ������Ϣ���߳�  
	        Session session;  
	        // Destination ����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.  
	        Destination destination;  
	        // �����ߣ���Ϣ������  
	        MessageConsumer consumer;  
	        connectionFactory = new ActiveMQConnectionFactory(  
	                ActiveMQConnection.DEFAULT_USER,  
	                ActiveMQConnection.DEFAULT_PASSWORD,  
	                "tcp://localhost:61616");  
	        try {  
	            //�õ����Ӷ���  
	            connection =connectionFactory.createConnection();  
	            // ����  
	            connection.start();  
	            // ��ȡ��������  
	            session = connection.createSession(false,  
	                    Session.AUTO_ACKNOWLEDGE);  
	            // ����Queue  
	           destination = session.createQueue("FirstQueue");  
	            consumer =session.createConsumer(destination);          
	            while(true){  
	              //���ý����߽�����Ϣ��ʱ�䣬Ϊ�˱��ڲ��ԣ����ﶨΪ100s  
	              TextMessage message = (TextMessage)consumer.receive(100000);  
	              if(null != message){  
	                 System.out.println("�յ���Ϣ" +message.getText());  
	              }else break;  
	            }  
	        }catch(Exception e){  
	        e.printStackTrace();  
	        }finally {  
	            try {  
	                if (null != connection)  
	                    connection.close();  
	            } catch (Throwable ignore) {  
	            }  
	        }  
	    }  

}
