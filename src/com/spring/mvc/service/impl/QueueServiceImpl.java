package com.spring.mvc.service.impl;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.spring.mvc.service.IQueueService;
import com.spring.util.GsonUtil;
import com.spring.util.NumGenerator;

@Service
public class QueueServiceImpl implements IQueueService{

	@Resource
	private JmsTemplate jmsTemplate;
	
	
	
	
	@Override
	public void msgInsert(String queId, String destName, String destType,
			final Object msgObj, String objType, String objId) {
		
		try {
			jmsTemplate.send(destName, new MessageCreator() {

				@Override
				public Message createMessage(Session session)
						throws JMSException {
					
					String msgStr = GsonUtil.fromObjToJson(msgObj);
					TextMessage msg = session.createTextMessage(msgStr);
					
					return msg;
				}
				
			});
		} catch (JmsException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String getQueId() {
		String prefix = "queId";
		int digit = 8;
		return NumGenerator.recordNoGen(prefix, digit);
	}

}
