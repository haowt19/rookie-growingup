package com.activemq.demo.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	private static final int SEND_NUMBER = 100;  
    public static void main(String[] args) {  
       // ConnectionFactory ：连接工厂，JMS用它创建连接  
       ConnectionFactory connectionFactory;  
       // Connection ：JMS客户端到JMS Provider的连接  
       Connection connection = null;  
        // Session：一个发送或接收消息的线程  
       Session session;  
       // MessageProducer：消息发送者  
       MessageProducer producer;  
        // TextMessage message;  
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现  
       connectionFactory = new ActiveMQConnectionFactory(  
              ActiveMQConnection.DEFAULT_USER,  
               ActiveMQConnection.DEFAULT_PASSWORD,  
              "tcp://localhost:61616");  
       try{  
           //得到连接对象  
           connection = connectionFactory.createConnection();  
           //启动  
           connection.start();  
           //获取操作连接  
           session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);          
           Topic topic = session.createTopic("MQ_test");         
           // 得到消息生成者【发送者】  
           producer = session.createProducer(topic);  
           //设置持久化  
           producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
           //构造消息  
           sendMessage(session, producer);  
           //session.commit();  
           connection.close();  
       }  
       catch(Exception e){  
           e.printStackTrace();  
       }finally{  
           if(null != connection){  
              try {  
                  connection.close();  
              } catch (JMSException e) {  
                  // TODO Auto-generatedcatch block  
                  e.printStackTrace();  
              }  
           }      
       }  
    }  
    public static void sendMessage(Session session, MessageProducer producer)throws Exception{  
       for(int i=1; i<=SEND_NUMBER; i++){  
           TextMessage message = session.createTextMessage("ActiveMQ发送消息"+i);  
           System.out.println("发送消息：ActiveMQ发送的消息"+i);  
           producer.send(message);  
       }  
    }  
}
