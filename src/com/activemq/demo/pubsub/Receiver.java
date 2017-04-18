package com.activemq.demo.pubsub;

import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.MessageConsumer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
import javax.jms.Topic;  
   
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
        // �����ߣ���Ϣ������  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory(  
               ActiveMQConnection.DEFAULT_USER,  
                ActiveMQConnection.DEFAULT_PASSWORD,  
                "tcp://localhost:61616");  
        try {  
            // ����ӹ����õ����Ӷ���  
            connection =connectionFactory.createConnection();  
              
            connection.setClientID("clientID001");  
            // ����  
            connection.start();  
            // ��ȡ��������  
            session = connection.createSession(false,  
                    Session.AUTO_ACKNOWLEDGE);  
            // ��ȡsession  
           Topic topic = session.createTopic("MQ_test");         
           // �õ���Ϣ�����ߡ������ߡ�  
           consumer = session.createDurableSubscriber(topic, "MQ_sub");  
             
            while(true){  
              //���ý����߽�����Ϣ��ʱ�䣬Ϊ�˱��ڲ��ԣ�����˭��Ϊ100s  
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
