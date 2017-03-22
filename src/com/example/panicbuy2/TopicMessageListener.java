package com.example.panicbuy2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.data.redis.core.RedisTemplate;

import com.google.gson.JsonObject;
import com.util.GsonUtil;

public class TopicMessageListener implements MessageListener{
	
	private final String key = "freeAmount";

	private RedisTemplate redisTemplate;
	
	private IGoodService goodService;
	
	public TopicMessageListener(RedisTemplate rt) {
		this.redisTemplate = rt;
	}
	
	@Override
	public void onMessage(Message message) {
		
		TextMessage textMsg = null;
		
		if (message instanceof TextMessage) {
			try {
				textMsg = (TextMessage)message;
				String msg = textMsg.getText();
				
				goodService.doOrderBusiness(msg);
				redisTemplate.boundSetOps("success").add(uid);
			} catch (JMSException e) {
				redisTemplate.boundValueOps(key).increment(money);
				redisTemplate.boundSetOps("failed").add(uid);
				e.printStackTrace();
			}
		}
	}

}
