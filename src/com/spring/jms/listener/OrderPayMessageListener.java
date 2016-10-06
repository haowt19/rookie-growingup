package com.spring.jms.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class OrderPayMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		TextMessage msg = null;
		try {
			msg = (TextMessage)message;
			String text = msg.getText();
			System.out.println(text);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	}

}
